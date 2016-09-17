package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;


/**
 * User Profile Entity
 * 
 * @author Sanchi
 *
 */

@Entity
@Table(name="USER")
public class UserProfileEntity {
	
	/** unique id of user */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userID;
	
	/** unique nickname of user */
	@Column(name = "USER_NAME")
	private String userName;
	
	/** email address of user */
	@Column(name = "EMAIL")
	private String email;
	
	/** encrypted password */
	@Column(name = "PASSWORD")
	private String password;
	
	/** password decryption key */
	@Column(name = "SALT")
	private String salt;
	
	/** user first name */
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	/** user last name */
	@Column(name = "LAST_NAME")
	private String lastName;
	
	/** user date of registration */
	@Column(name = "DATE_OF_REGISTRATION")
	private Date dateOfRegistration;
	
	/** user last login */
	@Column(name = "LAST_LOGIN")
	private Date lastLogin;
	
	/** user UUID for URL */
	@Column(name = "USER_UUID")
	private String userUUID;
	
	/*
	 * this establishes one-to-many relationship
	 * @JsonBackReference and @JsonManagedReference helps to avoid cyclic Json serialization error
	 * Fetch Type - Lazy fetched data only when the variable is referred 
	 * Cascade ALL cascades all operation to child as well
	 * 
	 */
	
//	@JsonManagedReference
//	@OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	List<Company> companies;
	
	public UserProfileEntity(UserProfile bean) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, bean);
	}
	
	public UserProfileEntity(int userId, String userName, String email, String password, 
			String salt, String firstName,String lastName, Date dateOfRegistration,
			Date lastLogin, String userUUID){
		this.userID = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfRegistration = dateOfRegistration;
		this.lastLogin = lastLogin;
		this.userUUID = userUUID;
		
	}
	
	public UserProfileEntity(){
		
	}
	
//	public List<Company> getCompanies() {
//		return companies;
//	}
//	public void setCompanies(List<Company> companies) {
//		this.companies = companies;
//	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	
	
}
