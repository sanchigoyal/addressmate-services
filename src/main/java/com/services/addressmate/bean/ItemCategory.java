package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.beanutils.BeanUtils;


@XmlRootElement
public class ItemCategory {
	private int itemCategoryID;
	private String category;
	private double totalValue;
	private TransactionType transactionType;
	private int noOfItems;
	private String userUUID;
	private List<Link> links = new ArrayList<Link>();
	
	public ItemCategory(ItemCategoryEntity entity) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, entity);
	}
	
	public ItemCategory(int itemCategoryId, String category, double totalValue,
			TransactionType type, int noOfItems, String userUUID){
		this.itemCategoryID = itemCategoryId;
		this.category = category;
		this.totalValue = totalValue;
		this.transactionType = type;
		this.noOfItems = noOfItems;
		this.userUUID = userUUID;
	}
	
	public ItemCategory(){
		
	}
	
	public void addLink(String link, String rel){
		links.add(new Link(link, rel));
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

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}
	
	@XmlTransient
	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	
}
