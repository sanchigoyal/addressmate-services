package com.services.addressmate.bean;

public class Link {
	/** hold the url */
	private String link;
	
	/** holds the relation to the parent resource */
	private String rel;
	
	public Link(String link, String rel){
		this.link = link;
		this.rel = rel;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	
}
