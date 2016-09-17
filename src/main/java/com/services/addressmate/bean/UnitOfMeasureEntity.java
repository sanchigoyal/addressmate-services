package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

@Entity
@Table(name = "UNIT_OF_MEASURE")
public class UnitOfMeasureEntity {
	@Id @GeneratedValue
	@Column(name = "UOM_ID")
	private int uomID;
	@Column(name = "UOM_NAME")
	private String uomName;
	@Column(name = "SYMBOL")
	private String symbol;
	@Column(name = "PRECISION_VALUE")
	private int precision;
	@Column(name = "USER_UUID")
	private String userUUID;
	
	public UnitOfMeasureEntity(UnitOfMeasure bean) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, bean);
	}
	
	public UnitOfMeasureEntity(int uomID, String uomName, String symbol, int precision, String userUUID){
		this.uomID = uomID;
		this.uomName = uomName;
		this.symbol = symbol;
		this.precision = precision;
		this.userUUID = userUUID;
	}
	
	public UnitOfMeasureEntity(){
		
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

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	
}
