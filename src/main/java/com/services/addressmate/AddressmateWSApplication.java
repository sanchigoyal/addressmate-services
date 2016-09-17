package com.services.addressmate;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.services.addressmate.exception.BeanEntityConversionExceptionMapper;
import com.services.addressmate.exception.GenericExceptionMapper;
import com.services.addressmate.exception.InvalidCredentialExceptionMapper;
import com.services.addressmate.exception.ResourceNotFoundExceptionMapper;
import com.services.addressmate.filter.VendorAuthorizationFilter;
import com.services.addressmate.resource.CompanyResource;
import com.services.addressmate.resource.UOMResource;
import com.services.addressmate.resource.UserProfileResource;


/**
 * Registers the components to be used by the JAX-RS application
 * 
 * @author Sanchi
 *
 */
public class AddressmateWSApplication extends ResourceConfig {
	
	/**
	 * Registers JAX-RS application components
	 */
	public AddressmateWSApplication(){
		
		// register application resources
		register(UserProfileResource.class);
		register(CompanyResource.class);
		register(UOMResource.class);
	
		// register filters
		register(RequestContextFilter.class);
		register(VendorAuthorizationFilter.class);
		
		// register exception mapper
		register(ResourceNotFoundExceptionMapper.class);
		register(BeanEntityConversionExceptionMapper.class);
		register(InvalidCredentialExceptionMapper.class);
		register(GenericExceptionMapper.class);
		
		// register features
		register(JacksonFeature.class);
	}
}
