package util;

import javax.faces.context.FacesContext;


public class Constants {
	public static final String USER_LOGGED = "userLogged";

	public static final String CONTEXT_MB = "contextMB";
	public static final String AVAILABILITY_MB = "availabilityMB";
	public static final String RESET_PASSWORD_MB = "resetPasswordMB";
	public static final String FORGOT_PASSWORD_MB = "forgotPasswordMB";
	public static final String QUESTION_MB = "questionMB";
	public static final String EXERCISE_MB = "exerciseMB";
	public static final String VIDEO_MB = "videoMB";
	public static final String PERFORMANCE_MB = "performanceMB";
	public static final String ACESS_ADMINISTRATION_MB = "acessAdministrationMB";

	public static final String PAGE_SUCESS = "sucess";
	public static final String PAGE_LOGIN = "login";
	public static final String PAGE_EXERCISE = "exercise";
	public static final String PAGE_VIDEO = "video";
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
}
