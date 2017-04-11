package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.Constants;
import util.FacesUtil;

import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.estimation.IrtExamineeTest;
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

		IrtExamineeTest irtTest = new IrtExamineeTest();
		ivec = irtTest.maximumLikelihoodTest2PLToContext();

		return goToHomePage();
	}

	public void maximumLikelihoodTest2PL() {
		IrtExamineeTest irtTest = new IrtExamineeTest();
		irtTest.maximumLikelihoodTest2PL();

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