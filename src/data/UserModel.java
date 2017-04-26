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
package data;

import java.text.ParseException;
import java.util.Date;

import util.Constants;
import util.Util;
import util.ValidationUtil;

/**
 * Entity Studenty
 * 
 * @author mauricio
 * 
 */
// @Entity
// @Table(name = "TB_USER")
// @NamedQueries({
// @NamedQuery(name = "login", query = "SELECT u FROM UserModel u WHERE u.email = :email AND u.password = :password"),
// @NamedQuery(name = "findUserById", query = "SELECT u FROM UserModel u WHERE u.id = :id"),
// @NamedQuery(name = "findUserByEmail", query = "SELECT u FROM UserModel u WHERE u.email = :email"),
// @NamedQuery(name = "findStudyPlanByUser", query = "SELECT sp FROM StudyPlanModel sp WHERE sp.endDate IS NULL AND sp.userModel.id = :id"),
// @NamedQuery(name = "finaliseStudyPlanByUser",
// query = "UPDATE StudyPlanModel sp SET sp.endDate = :endDate  WHERE sp.endDate IS NULL AND sp.userModel.id = :id"),
// @NamedQuery(name = "findUserByEncriptedAcessCode", query = "SELECT u FROM UserModel u WHERE u.encryptedAccessCode = :acessCode"),
// @NamedQuery(name = "updateAvatarUser", query = "UPDATE UserModel u SET u.avatar = :avatar WHERE u.id = :id"),
// @NamedQuery(name = "updateUserAcessCode", query = "UPDATE UserModel u SET u.encryptedAccessCode = :acessCode WHERE u.id = :id"),
// @NamedQuery(name = "updateUserPassword", query = "UPDATE UserModel u SET u.password = :password WHERE u.id = :id") })
// @SequenceGenerator(name = "TB_USER_ID_SEQ", sequenceName = "TB_USER_ID_SEQ", allocationSize = 1)
public class UserModel {

	// @Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USER_ID_SEQ")
	// @Column(name = "USER_ID")
	private Long id;

	private double theta;

	// @Column(name = "USER_NAME")
	private String name;

	// @Column(name = "USER_LAST_NAME")
	private String lastName;

	// @Column(name = "USER_PASSWORD")
	private String password;

	// @Column(name = "USER_EMAIL")
	private String email;

	// @Temporal(TemporalType.DATE)
	// @Column(name = "USER_DT_BIRTH")
	private Date birthDate;

	// @Temporal(TemporalType.TIMESTAMP)
	// @Column(name = "USER_DT_CADASTRE")
	private Date cadastreDate;

	// @OneToOne
	// @JoinColumn(name = "FK_PROFILE_ID")
	private ProfileModel profileModel;

	// @Column(name = "USER_AVATAR_ICON")
	private String avatar;

	// @Column(name = "USER_ENCRYPTED_ACCESS_CODE")
	private String encryptedAccessCode;

	// @Column(name = "USER_REGISTERED")
	private boolean registered;

	// @Transient
	private String accessCode;

	// @Transient
	private String oldPassword;

	// @Transient
	private AvatarModel avatarModel;

	public UserModel() {
	}

	public UserModel(final Long id) {
		this.id = id;
	}

	public UserModel(String name, String email, Date birthDate) {
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}

	public static UserModel standarUser() {
		return new UserModel("Make Your Time", "make@intelectin.com.br", new Date());
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

	public String getShowName() {
		return this.name + " " + this.lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAge() {
		int age = 0;

		if (!ValidationUtil.isNullOrEmpty(this.birthDate)) {
			try {
				age = Util.calculaIdade(Util.convertDateToString(this.birthDate, Constants.DD_MM_YYYY)).intValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileModel getProfileModel() {
		return profileModel;
	}

	public void setProfileModel(ProfileModel profileModel) {
		this.profileModel = profileModel;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCadastreDate() {
		return cadastreDate;
	}

	public void setCadastreDate(Date cadastreDate) {
		this.cadastreDate = cadastreDate;
	}

	public AvatarModel getAvatarModel() {
		return avatarModel;
	}

	public void setAvatarModel(AvatarModel avatarModel) {
		this.avatarModel = avatarModel;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getEncryptedAccessCode() {
		return encryptedAccessCode;
	}

	public void setEncryptedAccessCode(String encryptedAccessCode) {
		this.encryptedAccessCode = encryptedAccessCode;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

}
