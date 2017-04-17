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

		// Init dataset
		byte[][] lsat7 = FileUploadUtil.readTestData("lsat7.txt", 32, 5);
		iVecs = this.mountExamineeList(lsat7);

		this.pageChose = Page.THETA_MLE.getValue();
		this.runMaximumLikelihood2PL();

		// return goToHomePage();
		return "";
	}

	public String jointEstimation() {
		this.clearFields();

		this.pageChose = Page.JOINT_MLE.getValue();

		// this.runJointMleRasch("tap-data.txt");
		this.runJointMleRasch("enem2012.txt");

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
	public void runJointMleRasch(String dataName) {
		System.out.println("JMLE TAP DATA TEST");
		// byte[][] data = FileUploadUtil.readTestData(dataName, 35, 18);
		byte[][] data = FileUploadUtil.readTestData(dataName, 1414, 175);
		int nItems = data[0].length;
		int nPeople = data.length;

		ItemResponseModel[] irm = new ItemResponseModel[nItems];
		for (int i = 0; i < nItems; i++) {
			irm[i] = new Irm3PL(0.0, 1.0);
			irm[i].setName(new VariableName("V" + (i + 1)));
		}

		JointMaximumLikelihoodEstimation jmle = new JointMaximumLikelihoodEstimation(data, irm);
		jmle.summarizeData(0.3);

		jmle.itemProx();
		jmle.estimateParameters(150, 0.00001);
		jmle.computeItemStandardErrors();
		jmle.computePersonStandardErrors();
		jmle.computeItemFitStatistics();

		double[] theta = jmle.getPersonEstimates();
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