package com.services.addressmate.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.log4j.Logger;

import com.services.addressmate.bean.ErrorMessage;
import com.services.addressmate.util.constant.ErrorConstants;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
	Logger LOGGER = Logger.getLogger(GenericExceptionMapper.class);
	
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_TECHNICAL, "Technical Error :: "+ex.getMessage());
		LOGGER.error(ex.getMessage(),ex);
		
		/*
		 * including Media Type is important here
		 * this make sure any request returned before entering the service 
		 * has a error message and type is application/json
		 * e.g. in case of 404 URL not found
		 */
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON)
				.entity(errorMessage).build();
		
	}
	

}
