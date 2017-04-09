package enums;

public enum Page {
	HOME(1, "home"), NEW_PLAN(2, "newPlan"), CONTACT(3, "contact"), CONGRATULATION(4, "congratulation"), INDEX(5,
			"index"), STUDY_PLAN(6, "studyPlan"), LOGIN(7, "login"), REGISTER(8, "register");

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
