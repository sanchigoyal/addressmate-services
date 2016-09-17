package com.services.addressmate.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.services.addressmate.bean.ErrorMessage;
import com.services.addressmate.util.constant.ErrorConstants;

public class BeanEntityConversionExceptionMapper implements ExceptionMapper<BeanEntityConversionException>{

	@Override
	public Response toResponse(BeanEntityConversionException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_BEAN_ENTITY_CONVERSION, ex.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
