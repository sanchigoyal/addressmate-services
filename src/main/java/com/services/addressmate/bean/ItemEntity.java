package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;


import org.apache.commons.beanutils.BeanUtils;

public class ItemEntity {
	private int itemID;
	private String itemName;
	private ItemCategory category;
	private UnitOfMeasure unitOfMeasure;
	private String userUUID;

	/*
	 * add links of prices and list of inventory once above is available in api 
	 */
	
	
	public ItemEntity(Item bean) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, bean);
	}
	
	public ItemEntity(){
		
	}
	
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	
}
