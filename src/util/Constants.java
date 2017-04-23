package util;

import javax.faces.context.FacesContext;

public class Constants {
	public static final String USER_LOGGED = "userLogged";

	public static final String CONTEXT_MB = "contextMB";
	public static final String QUESTION_MB = "questionMB";
	public static final String EXERCISE_MB = "exerciseMB";

	public static final String PAGE_SUCESS = "sucess";
	public static final String PAGE_LOGIN = "login";
	public static final String PAGE_EXERCISE = "exercise";
	public static final String PAGE_PERFORMANCE = "performance";
	public static final String PAGE_ADMINISTRATION = "dashboardAdministration";

	public static final String DOWNLOAD_PATH = "download_path";
	public static final String DIRECTORY_PROFILE = "directory_profile";
	public static final String DIRECTORY_QUESTION = "directory_question";

	public static final String IMAGE_AVATAR_DEFAULT = "avatar_default";

	public static final String VALIDATE_REGISTRATION_URL = "/pages/validateRegistration.xhtml?";
	public static final String RESET_PASSWORD_URL = "/pages/resetPassword.xhtml?";

	// Environment of dev
	public static final String REAL_PATH_SEND_EMAIL = "http://" + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName() + ":"
			+ FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort()
			+ FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

	public static final String USER_LOGIN_MB = "userLoginMB";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";

	public static final String VALIDATION_CODE = "validationCode";
	public static final String RESET_PASSWORD_CODE = "resetPasswordCode";

	public static final boolean FLAG_ACTIVO = true;
	public static final boolean FLAG_INACTIVO = false;

	public static final int WIDTH_IMAGE = 300;
	public static final int HIGTH_IMAGE = 300;

	public static final Long MODULE_ENEM = 1l;
	public static final Long MODULE_INSS = 2l;

	public static final Long PROFILE_STUDENT = 2L;
	public static final Long PROFILE_ADMINISTRATION = 1L;

	// IRT Params
	public static double[] aParamLSAT7 = { 0.5760746525, 0.6713629131, 0.9567791258, 0.4266571055, 0.4398755432 };
	public static double[] bParamLSAT7 = { -1.8877222528, -0.7154624410, -1.0825764537, -0.6760358283, -2.4772429769 };
	public static double minTheta = -4.0;// in lieu of negative infinity
	public static double maxTheta = 4.0;// in lieu of positive infinity

	public static final int ITEM_LEVEL_EASY = 0;
	public static final int QUESTION_LEVEL_MEDIUM = 1;
	public static final int ITEM_LEVEL_HARD = 2;

	public static final boolean CORRECT_ANSWER = true;
	public static final boolean WRONG_ANSWER = false;
}
