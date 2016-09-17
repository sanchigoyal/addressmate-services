package com.services.addressmate.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.beanutils.BeanUtils;

@XmlRootElement
public class Item {
	private int itemID;
	private String itemName;
	private ItemCategory category;
	private UnitOfMeasure unitOfMeasure;
	private double costPrice;
	private double sellingPrice;
	private double marketRetailPrice;
	private double availableQuantity;
	private double totalValue;
	private TransactionType transactionType;
	private String userUUID;
	private List<Link> links = new ArrayList<Link>();
	
	public Item(ItemEntity entity) throws IllegalAccessException, InvocationTargetException{
		BeanUtils.copyProperties(this, entity);
	}
	
	public Item(){
		
	}
	
	public void addLink(String link, String rel){
		links.add(new Link(link, rel));
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

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getMarketRetailPrice() {
		return marketRetailPrice;
	}

	public void setMarketRetailPrice(double marketRetailPrice) {
		this.marketRetailPrice = marketRetailPrice;
	}

	public double getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(double availableQuantity) {
		this.availableQuantity = availableQuantity;
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
