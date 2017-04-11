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
	IrtExaminee ivec;

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
		ivec = new IrtExaminee(new ItemResponseModel[1]);
	}

	@PostConstruct
	public void init() {
	}

	/**
	 * Log out. Remove the object UserModel of session
	 * 
	 * @return go to home page.
	 * @author anchieta
	 */
	public String generateTest1() {

		ivec = this.maximumLikelihoodTest2PLToContext();

		// return goToHomePage();
		return "";
	}

	public IrtExaminee maximumLikelihoodTest2PLToContext() {
		byte[][] lsat7 = FileUploadUtil.readTestData("lsat7.txt");

		int n = Constants.aParamLSAT7.length;
		int nPeople = lsat7.length;

		ItemResponseModel[] irmArray = new ItemResponseModel[n];
		ItemResponseModel irm;

		// create item response models objects
		VariableName iName = null;
		for (int i = 0; i < n; i++) {
			String name = "V" + i;
			iName = new VariableName(name);
			irmArray[i] = new Irm3PL(Constants.aParamLSAT7[i], Constants.bParamLSAT7[i], 1.702);
			irmArray[i].setName(iName);
		}

		IrtExaminee iVec = new IrtExaminee(irmArray);

		// estimate ability scores for each response pattern
		double mle = 0.0;
		double se = 0.0;
		for (int j = 0; j < nPeople; j++) {
			iVec.setResponseVector(lsat7[j]);
			mle = iVec.maximumLikelihoodEstimate(Constants.minTheta, Constants.maxTheta);
			se = iVec.mleStandardErrorAt(mle);
			// System.out.println("MLE" + j + ": " + mle + " SE: " + se);
		}

		return iVec;

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

	public IrtExaminee getIvec() {
		return ivec;
	}

	public void setIvec(IrtExaminee ivec) {
		this.ivec = ivec;
	}

}