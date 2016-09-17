package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;



@Entity
@Table(name="COMPANY")
@XmlRootElement
public class CompanyEntity {
	/** company id auto generated */
	@Id @GeneratedValue
	@Column(name="COMPANY_ID")
	private int companyID;
	
	/** company name */
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	/** company phone number */
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	/** company street address line 1 */
	@Column(name="STREET_ADDRESS_LINE_1")
	private String streetAddressLine1;
	
	/** company street address line 1 */
	@Column(name="STREET_ADDRESS_LINE_2")
	private String streetAddressLine2;
	
	/** company state */
	@Column(name="STATE")
	private String state;
	
	/** country of operation */
	@Column(name="COUNTRY")
	private String country;
	
	/** zip code of operating location */
	@Column(name="ZIP")
	private String zip;
	
	/*
	 * this establishes many-to-one relationship
	 * @JsonBackReference and @JsonManagedReference helps to avoid cyclic Json serialization error
	 * Fetch Type - Lazy fetched data only when the variable is referred 
	 * 
	 */
//	@JsonBackReference
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="USER_ID")
//	private UserProfile user;
	
//	public UserProfile getUser() {
//		return user;
//	}
//	public void setUser(UserProfile user) {
//		this.user = user;
//	}
	
	/** user's UUID for maintaining relations*/
	@Column(name="USER_UUID")
	private String userUUID;
	
	public CompanyEntity(Company bean) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, bean);
	}
	
	public CompanyEntity(int companyId, String companyName, String phoneNumber, String streetAddressLine1,
			String streetAddressLine2, String state, String country, String zip, String userUUID){
		this.companyID = companyId;
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
		this.streetAddressLine1 = streetAddressLine1;
		this.streetAddressLine2 = streetAddressLine2;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.userUUID = userUUID;
	}
	
	public CompanyEntity(){
		
	}
	
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStreetAddressLine1() {
		return streetAddressLine1;
	}
	public void setStreetAddressLine1(String streetAddressLine1) {
		this.streetAddressLine1 = streetAddressLine1;
	}
	public String getStreetAddressLine2() {
		return streetAddressLine2;
	}
	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	
	
}
