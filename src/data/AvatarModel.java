package data;

public class AvatarModel extends ImageModel {

	private static final long serialVersionUID = 1L;

	private UserModel userModel;

	public AvatarModel() {
	}

	public AvatarModel(String path) {
		super.setPath(path);
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
