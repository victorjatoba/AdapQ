/*
 * Copyright 2017 V. M. G. Jatobá
 *
 * Licensed under the MIT;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/licenses/MIT
 *
 * The software is provided "AS IS", without warranty of any kind, 
 * express or implied, including but not limited to the warranties
 * of merchantability, fitness for a particular purpose and
 * noninfringement. In no event shall the authors or copyright
 * holders be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise, arising
 * from, out of or in connection with the software or the use
 * or other dealings in the software.
 */
package managedBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.math3.util.Precision;

import util.Constants;
import util.FacesUtil;
import util.FileUploadUtil;

import com.itemanalysis.psychometrics.data.VariableName;
import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.estimation.JointMaximumLikelihoodEstimation;
import com.itemanalysis.psychometrics.irt.model.Irm3PL;
import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

import data.ExamineeModel;
import enums.Page;

@ManagedBean(name = "contextMB")
@SessionScoped
public class ContextMB {

	private String actualPage = Page.HOME.getName();
	private List<IrtExaminee> iVecs;
	private List<ExamineeModel> iVecsModel;
	private List<ItemResponseModel> irms;
	private int pageChose;

	public ContextMB() {
		this.clearFields();
	}

	/**
	 * Method to clear fields when the class be instantiated.
	 * 
	 * @author anchieta
	 */
	private void clearFields() {

		// Set the managed bean in session to get anywhere else.
		FacesUtil.setManagedBeanInSession(Constants.CONTEXT_MB, this);

		// Init dataset
		byte[][] lsat7 = FileUploadUtil.readTestData("lsat7.txt", 32, 5);
		iVecs = this.mountExamineeList(lsat7);
		this.pageChose = Page.UNKNOWN.getValue();
	}

	@PostConstruct
	public void init() {
	}

	/**
	 * 
	 * @return
	 */
	public String thetaEstimationKnownItem() {
		this.clearFields();
		this.pageChose = Page.THETA_MLE.getValue();
		this.runMaximumLikelihood2PL();

		// return goToHomePage();
		return "";
	}

	public String jointEstimation() {
		this.clearFields();
		this.pageChose = Page.JOINT_MLE.getValue();
		this.runJointMleRasch();

		// return goToHomePage();
		return "";
	}

	/**
	 * 
	 * @param dataSet
	 * @return
	 */
	private List<IrtExaminee> mountExamineeList(byte[][] dataSet) {
		int n = Constants.aParamLSAT7.length;
		int nPeople = dataSet.length;

		ItemResponseModel[] irmArray = new ItemResponseModel[n];
		irms = new ArrayList<ItemResponseModel>();

		// create item response models objects
		VariableName iName = null;
		for (int i = 0; i < n; i++) {
			String name = "Item" + i;
			iName = new VariableName(name);
			Irm3PL irm = new Irm3PL(Constants.aParamLSAT7[i], Constants.bParamLSAT7[i], 1.702);
			irm.setName(iName);

			irmArray[i] = irm;
		}

		this.irms = Arrays.asList(irmArray);
		List<IrtExaminee> iVecs = new ArrayList<IrtExaminee>();

		for (int j = 0; j < nPeople; j++) {
			IrtExaminee iVec = new IrtExaminee(irmArray);
			iVec.setResponseVector(dataSet[j]);
			iVecs.add(iVec);
		}

		return iVecs;
	}

	/**
	 * 
	 * @return
	 */
	public void runMaximumLikelihood2PL() {

		double mle = 0.0;

		for (IrtExaminee irtExaminee : iVecs) {
			mle = irtExaminee.maximumLikelihoodEstimate(Constants.minTheta, Constants.maxTheta);
			irtExaminee.mleStandardErrorAt(mle);
		}

	}

	/**
	 * This test involves the TAP data from Wright and Stone's Best Test Design text. It is also the data in WINSTEPS example1.
	 *
	 * Using the values jmle.estimateParameters(150, 0.00001, 1, .01), jMetrik should give four decimal places of accuracy with winsteps when winsteps uses the
	 * same convergence criterion (i.e control file uses CONVERGE=L and LCONV = 0.00001.) More decimal places of accuracy are possible if use a more stringent
	 * criterion.
	 *
	 */
	public void runJointMleRasch() {
		System.out.println("JMLE TAP DATA TEST");
		byte[][] data = FileUploadUtil.readTestData("tap-data.txt", 35, 18);
		int nItems = data[0].length;
		int nPeople = data.length;

		// true estimates from WINSTEPS
		double[] tap_true_theta = { -3.0853, -.2759, 0.9885, -3.7594, -2.3553, -1.4619, 2.9878, -0.2759, -2.3553, -3.7594, 2.0459, -.2759, -.2759, -1.4619,
				-6.7909, -.2759, .9885, -.2759, -.2759, .9885, -4.4750, -.2759, -.2759, 3.8891, -.2759, -3.0853, -.2759, 2.0459, .9885, -1.4619, -.2759,
				3.8891, 2.0459, .9885, 2.0459 };
		double[] tap_true_difficulty = { -6.7490, -6.7490, -6.7490, -4.5520, -3.9692, -3.5053, -3.9692, -2.4393, -3.5053, -1.6287, 0.8284, 2.3267, 2.0282,
				3.4997, 4.9620, 4.9620, 4.9620, 6.2965 };
		double[] true_item_stdError = { 1.8730, 1.8730, 1.8730, .8230, .7144, .6520, .7144, .5509, .6520, .4925, .4584, .5605, .5331, .7123, 1.0894, 1.0894,
				1.0894, 1.8759 };
		// winsteps only gives two decimal places for fit statistics
		double[] true_wms = { 1.00, 1.00, 1.00, 0.92, 1.07, 1.21, 1.38, 0.60, 0.63, 1.10, 1.12, 1.21, 0.71, 1.64, 0.77, 0.77, 0.77, 1.00 };
		double[] true_ums = { 1.00, 1.00, 1.00, 0.35, 0.53, 1.03, 2.48, 0.44, 0.21, 0.86, 0.81, 1.14, 0.37, 1.67, 0.11, 0.11, 0.11, 1.00 };

		ItemResponseModel[] irm = new ItemResponseModel[18];
		for (int i = 0; i < nItems; i++) {
			irm[i] = new Irm3PL(0.0, 1.0);
			irm[i].setName(new VariableName("V" + (i + 1)));
		}

		JointMaximumLikelihoodEstimation jmle = new JointMaximumLikelihoodEstimation(data, irm);
		jmle.summarizeData(0.3);

		jmle.itemProx();
		// System.out.println(jmle.printBasicItemStats());
		jmle.estimateParameters(150, 0.00001);
		jmle.computeItemStandardErrors();
		jmle.computePersonStandardErrors();
		jmle.computeItemFitStatistics();

		System.out.println("     Testing difficulty");
		for (int j = 0; j < nItems; j++) {
			System.out.println("  JMLE tap test: difficulty " + tap_true_difficulty[j] + " " + Precision.round(irm[j].getDifficulty(), 5) + " " + 1e-4);
		}

		System.out.println("     Testing persons");
		double[] theta = jmle.getPersonEstimates();
		for (int i = 0; i < nPeople; i++) {
			System.out.println("  JMLE tap test: ability" + " " + tap_true_theta[i] + " " + Precision.round(theta[i], 5) + " " + 1e-4);
		}

		this.irms = Arrays.asList(jmle.getItems());

		this.iVecsModel = new ArrayList<ExamineeModel>();
		for (int j = 0; j < nPeople; j++) {
			ExamineeModel iVecModel = new ExamineeModel(jmle.getItems());
			iVecModel.setTheta(theta[j]);
			iVecModel.getExaminee().setResponseVector(data[j]);
			iVecsModel.add(iVecModel);
		}

	}

	public void reset() {
		this.clearFields();
	}

	public String goToNewStudyPlan() {
		return "index";
	}

	public String goToHomePage() {
		return "home";
	}

	public String getActualPage() {
		return actualPage;
	}

	public void setActualPage(String actualPage) {
		this.actualPage = actualPage;
	}

	public List<IrtExaminee> getiVecs() {
		return iVecs;
	}

	public void setiVecs(List<IrtExaminee> iVecs) {
		this.iVecs = iVecs;
	}

	public List<ItemResponseModel> getIrms() {
		return irms;
	}

	public void setIrms(List<ItemResponseModel> irms) {
		this.irms = irms;
	}

	public int getPageChose() {
		return pageChose;
	}

	public void setPageChose(int pageChose) {
		this.pageChose = pageChose;
	}

	public List<ExamineeModel> getiVecsModel() {
		return iVecsModel;
	}

	public void setiVecsModel(List<ExamineeModel> iVecsModel) {
		this.iVecsModel = iVecsModel;
	}

}