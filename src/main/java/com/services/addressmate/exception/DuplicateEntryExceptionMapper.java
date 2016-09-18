package com.services.addressmate.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.services.addressmate.bean.ErrorMessage;
import com.services.addressmate.util.constant.ErrorConstants;

public class DuplicateEntryExceptionMapper implements ExceptionMapper<DuplicateEntryException> {

	@Override
	public Response toResponse(DuplicateEntryException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorConstants.ERROR_ENTRY_ALREADY_EXIST, ex.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
