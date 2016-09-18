package com.services.addressmate.util.helper;

import javax.ws.rs.core.UriInfo;

import com.services.addressmate.resource.UserProfileResource;

public class LinkGenerator {
	public static String getUserProfileResourceLink(UriInfo uriInfo){
		return uriInfo.getBaseUriBuilder().path(UserProfileResource.class).build().toString();
	}
	
	public static String getUserProfileResourceLink(UriInfo uriInfo, String userName, String password){
		/* TODO - provide a cleaner approach to below url builder */
		String url = uriInfo.getBaseUriBuilder().path(UserProfileResource.class)
					 .build()
					 .toString()+"?username="+userName+"&password="+password;
		/* this helps to bind the url properly */
		url = url.replace(" ", "%20");
		return url;
	}
	

}
