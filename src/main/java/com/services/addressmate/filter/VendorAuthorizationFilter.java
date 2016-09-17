package com.services.addressmate.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.util.Base64;

import com.services.addressmate.bean.ErrorMessage;
import com.services.addressmate.util.constant.ErrorConstants;

public class VendorAuthorizationFilter implements ContainerRequestFilter{
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URI_PREFIX = "secured";
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// check if URI to be secured
		if(requestContext.getUriInfo().getPath().contains(SECURED_URI_PREFIX)){
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if(authHeader != null && authHeader.size() > 0){
				// take the first token and ignore the others
				String authToken = authHeader.get(0);
				authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedToken = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":");
				String accessID = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				
				// TODO: move this to some configuration file; use vendor name for look up 
				if("addressmate_app_123".equals(accessID) && "addressmate_app_123".equals(password)){
					return;
				}									  
			}
			
			ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_INVALID_VENDOR_CREDENTIAL, "Vendor credentials invalid.");
			Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED)
								  .entity(errorMessage)
								  .build();
			
			requestContext.abortWith(unauthorizedStatus);
		}
		
	}

}
