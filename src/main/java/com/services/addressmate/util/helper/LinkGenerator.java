package com.services.addressmate.util.helper;

import javax.ws.rs.core.UriInfo;

import com.services.addressmate.resource.CompanyResource;
import com.services.addressmate.resource.UOMResource;
import com.services.addressmate.resource.UserProfileResource;

public class LinkGenerator {
	public static String getUserProfileResourceLink(UriInfo uriInfo){
		return uriInfo.getBaseUriBuilder().path(UserProfileResource.class).build().toString();
	}
	
	public static String getUserProfileResourceLink(UriInfo uriInfo, String userName, String password){
		/* todo - provide a cleaner approach to below url builder */
		String url = uriInfo.getBaseUriBuilder().path(UserProfileResource.class)
					 .build()
					 .toString()+"?username="+userName+"&password="+password;
		/* this helps to bind the url properly */
		url = url.replace(" ", "%20");
		return url;
	}
	
	public static String getCompanyResourceLink(UriInfo uriInfo, int companyId){
		return uriInfo.getBaseUriBuilder()
				.path(CompanyResource.class)
				.path(String.valueOf(companyId))
				.build().toString();
	}

	public static String getCompanyResourceLink(UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(CompanyResource.class)
				.build().toString();
	}

	public static String getUOMResourceLink(UriInfo uriInfo, int uomID) {
		return uriInfo.getBaseUriBuilder()
				.path(UOMResource.class)
				.path(String.valueOf(uomID))
				.build().toString();
	}
	
	public static String getUOMResourceLink(UriInfo uriInfo){
		return uriInfo.getBaseUriBuilder()
				.path(UOMResource.class)
				.build()
				.toString();
	}
}
