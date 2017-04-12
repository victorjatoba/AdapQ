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
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.Constants;
import util.FacesUtil;
import util.FileUploadUtil;

import com.itemanalysis.psychometrics.data.VariableName;
import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.model.Irm3PL;
import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

import enums.Page;

@ManagedBean(name = "contextMB")
@SessionScoped
public class ContextMB {

	private String actualPage = Page.HOME.getName();
	private List<IrtExaminee> iVecs;
	private List<ItemResponseModel> irms;

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
		byte[][] lsat7 = FileUploadUtil.readTestData("lsat7.txt");
		iVecs = this.mountExamineeList(lsat7);
	}

	@PostConstruct
	public void init() {
	}

	/**
	 * 
	 * @return
	 */
	public String generateTest1() {

		this.runMaximumLikelihood2PL();

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
			String name = "V" + i;
			iName = new VariableName(name);
			Irm3PL irm = new Irm3PL(Constants.aParamLSAT7[i], Constants.bParamLSAT7[i], 1.702);
			irm.setName(iName);

			irmArray[i] = irm;
			irms.add(irm);
		}

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
		double se = 0.0;

		for (IrtExaminee irtExaminee : iVecs) {
			mle = irtExaminee.maximumLikelihoodEstimate(Constants.minTheta, Constants.maxTheta);
			se = irtExaminee.mleStandardErrorAt(mle);
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

}