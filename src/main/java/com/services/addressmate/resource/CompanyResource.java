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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.addressmate.bean.Company;
import com.services.addressmate.service.CompanyService;

@Path("/companies")
public class CompanyResource {
	
	@Autowired
	private CompanyService companyService;
	Logger LOGGER = Logger.getLogger(CompanyResource.class);
	
	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	// todo :: prepare filter to test if UUID is provided
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies(@HeaderParam("User-UUID")String userUUID, @Context UriInfo uriInfo){
		List<Company> companies = companyService.getCompanies(userUUID);
		
		/* add links */
		companyService.addLinks(uriInfo, companies);
		
		return Response.status(Status.OK).entity(companies).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{companyId}")
	public Response getCompany(@PathParam("companyId")int companyId, @HeaderParam("User-UUID")String userUUID, 
			@Context UriInfo uriInfo){
		Company company = companyService.getCompany(companyId, userUUID);
		companyService.addLinks(uriInfo, company);
		return Response.status(Status.OK).entity(company).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCompany(Company company, @HeaderParam("User-UUID") String userUUID, @Context UriInfo uriInfo){
		
		company.setUserUUID(userUUID);
		Company createdCompany = companyService.createCompany(company);
		companyService.addLinks(uriInfo, createdCompany);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
								.path(String.valueOf(company.getCompanyID()))
								.build())
					.entity(createdCompany)
					.build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{companyId}")
	public Response updateCompany(Company company, @HeaderParam("User-UUID") String userUUID, 
			@PathParam("companyId") int companyId, @Context UriInfo uriInfo){
		
		company.setCompanyID(companyId);
		company.setUserUUID(userUUID);
		Company updatedCompany = companyService.updateCompany(company);
		companyService.addLinks(uriInfo, updatedCompany);
		
		return Response.status(Status.OK).entity(updatedCompany)
				.header("Location", uriInfo.getAbsolutePathBuilder()
						.path(String.valueOf(updatedCompany.getCompanyID())))
				.build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{companyId}")
	public Response deleteCompany(@HeaderParam("User-UUID") String userUUID, 
			@PathParam("companyId") int companyId, @Context UriInfo uriInfo){
		
		Company deletedCompany = companyService.deleteCompany(userUUID, companyId);
		
		return Response.status(Status.OK).entity(deletedCompany).build();
	}
}
