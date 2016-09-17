package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import org.apache.commons.beanutils.BeanUtils;


public class ItemCategoryEntity {
	private int itemCategoryID;
	private String category;
	private String userUUID;
	List<ItemEntity> items;
	
	public ItemCategoryEntity(ItemCategory bean) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, bean);
	}
	
	public ItemCategoryEntity(int itemCategoryId, String category, List<ItemEntity> items, String userUUID){
		this.itemCategoryID = itemCategoryId;
		this.category = category;
		this.items = items;
		this.userUUID = userUUID;
	}
	
	public ItemCategoryEntity(){
		
	}
	
	public int getItemCategoryID() {
		return itemCategoryID;
	}

	public void setItemCategoryID(int itemCategoryID) {
		this.itemCategoryID = itemCategoryID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	
	
}
