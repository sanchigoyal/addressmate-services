package com.services.addressmate.service;


import javax.ws.rs.core.UriInfo;

import com.services.addressmate.bean.UserProfile;
import com.services.addressmate.exception.BeanEntityConversionException;
import com.services.addressmate.exception.ResourceNotFoundException;

public interface UserService {
	public UserProfile getUserProfile(String userName)throws ResourceNotFoundException, BeanEntityConversionException;

	public UserProfile createUserProfile(UserProfile user) throws BeanEntityConversionException;

	public UserProfile updateUserProfile(UserProfile user)throws ResourceNotFoundException,BeanEntityConversionException;

	public UserProfile deleteUserProfile(String userUUID)throws ResourceNotFoundException, BeanEntityConversionException;

	public boolean validate(UserProfile user, String password);

	public void addLink(UserProfile user, UriInfo uriInfo);
}
