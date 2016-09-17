package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.beanutils.BeanUtils;

@XmlRootElement
public class UnitOfMeasure {
	private int uomID;
	private String uomName;
	private String symbol;
	private int precision;
	private String userUUID;
	List<Link> links = new ArrayList<Link>();
	
	public UnitOfMeasure(UnitOfMeasureEntity entity) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, entity);
	}
	
	public UnitOfMeasure(int uomID, String uomName, String symbol, int precision, String userUUID){
		this.uomID = uomID;
		this.uomName = uomName;
		this.symbol = symbol;
		this.precision = precision;
		this.userUUID = userUUID;
	}
	
	public UnitOfMeasure(){
		
	}
	
	public void addLink(String link, String rel){
		links.add(new Link(link, rel));
	}
	
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public int getUomID() {
		return uomID;
	}

	public void setUomID(int uomID) {
		this.uomID = uomID;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
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

	
}
