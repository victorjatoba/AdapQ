package enums;

public enum Page {
	UNKNOWN(0, "unknown"), HOME(1, "home"), THETA_MLE(2, "thetaMle"), JOINT_MLE(3, "jointMle"), LOGIN(4, "login");

	private int value;
	private String name;

	private Page(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
