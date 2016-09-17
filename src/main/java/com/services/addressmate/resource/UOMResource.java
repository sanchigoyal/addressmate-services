package com.services.addressmate.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.services.addressmate.bean.UnitOfMeasure;
import com.services.addressmate.service.UOMService;

@Path("/uoms")
public class UOMResource {
	
	@Autowired
	private UOMService uomService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnitOfMeasures(@HeaderParam("User-UUID")String userUUID, @Context UriInfo uriInfo){
		List<UnitOfMeasure> uoms = uomService.getUnitOfMeasures(userUUID);
		uomService.addLinks(uriInfo, uoms);
		
		return Response.status(Status.OK).entity(uoms).build();
		
	}
	
	@GET
	@Path("/{uomId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnitOfMeasure(@HeaderParam("User-UUID")String userUUID, @Context UriInfo uriInfo, 
			@PathParam("uomId") int uomId){
		UnitOfMeasure uom = uomService.getUnitOfMeasure(userUUID, uomId);
		uomService.addLinks(uriInfo, uom);
		
		return Response.status(Status.OK).entity(uom).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUnitOfMeasure(@HeaderParam("User-UUID")String userUUID, @Context UriInfo uriInfo, 
			UnitOfMeasure uom){
		uom.setUserUUID(userUUID);
		UnitOfMeasure createdUom = uomService.createUnitOfMeasure(uom);
		uomService.addLinks(uriInfo, createdUom);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
								.path(String.valueOf(uom.getUomID()))
								.build())
				.entity(createdUom)
				.build();
	}
	
	@PUT
	@Path("/{uomId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUnitOfMeasure(@HeaderParam("User-UUID")String userUUID, @Context UriInfo uriInfo, 
			@PathParam("uomId") int uomId, UnitOfMeasure uom){
		uom.setUserUUID(userUUID);
		uom.setUomID(uomId);
		UnitOfMeasure updatedUom = uomService.updateUnitOfMeasure(uom);
		uomService.addLinks(uriInfo, updatedUom);
		
		return Response.status(Status.OK).entity(updatedUom)
				.build();
	}
	
	@DELETE
	@Path("/{uomId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUnitOfMeasure(@HeaderParam("User-UUID")String userUUID, @Context UriInfo uriInfo, 
			@PathParam("uomId") int uomId){
		UnitOfMeasure deletedUom = uomService.deleteUnitOfMeasure(userUUID, uomId);

		return Response.status(Status.OK).entity(deletedUom).build();
	}

	public UOMService getUomService() {
		return uomService;
	}

	public void setUomService(UOMService uomService) {
		this.uomService = uomService;
	}
}
