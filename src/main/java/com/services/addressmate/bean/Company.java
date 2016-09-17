package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.beanutils.BeanUtils;


@XmlRootElement
public class Company {
	
	/** company id auto generated */
	private int companyID;
	
	/** company name */
	private String companyName;
	
	/** company phone number */
	private String phoneNumber;
	
	/** company street address line 1 */
	private String streetAddressLine1;
	
	/** company street address line 1 */
	private String streetAddressLine2;
	
	/** company state */
	private String state;
	
	/** country of operation */
	private String country;
	
	/** zip code of operating location */
	private String zip;
	
	/** user's UUID for maintaining relation */
	private String userUUID;
	
	/** list of links */
	/* initializing this now helps to avoid extra lines of code later */
	List<Link> links = new ArrayList<Link>();
	
	public void addLink(String link, String rel){
		this.links.add(new Link(link, rel));
	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public Company(CompanyEntity entity) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, entity);
	}
	
	public Company(int companyId, String companyName, String phoneNumber, String streetAddressLine1,
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
	
	public Company(){
		
	}
	
	/*
	 * Adding @XMLTransient make sure the field is not available in response
	 * If required to add on variable instead of method, add @XmlAccessorType(XmlAccessType.FIELD) to class
	 */
	@XmlTransient
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
