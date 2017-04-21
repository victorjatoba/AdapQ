package data;

/**
 * Model Profile
 * 
 * @author victorjatoba
 * 
 */
// @Entity
// @Table(name = "TB_PROFILE")
// @NamedQueries({ @NamedQuery(name = "findById", query = "SELECT p FROM ProfileModel p WHERE p.id = :id") })
// @SequenceGenerator(name = "TB_PROFILE_ID_SEQ", sequenceName = "TB_PROFILE_ID_SEQ", allocationSize = 1)
public class ProfileModel {

	// @Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PROFILE_ID_SEQ")
	// @Column(name = "PROF_ID")
	private Long id;

	// @Column(name = "PROF_NAME", unique = true)
	private String name;

	// @Column(name = "PROF_FLAG_ACTIVE")
	private boolean flagActive;

	public ProfileModel() {

	}

	public ProfileModel(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ProfileModel(final Long id) {
		this.id = id;
	}

	public ProfileModel(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlagActive() {
		return flagActive;
	}

	public void setFlagActive(boolean flagActive) {
		this.flagActive = flagActive;
	}

}
