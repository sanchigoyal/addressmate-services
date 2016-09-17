package com.services.addressmate.service;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.services.addressmate.bean.Company;

public interface CompanyService {

	List<Company> getCompanies(String userUUID);

	void addLinks(UriInfo uriInfo, List<Company> companies);

	Company getCompany(int companyId, String userUUID);

	void addLinks(UriInfo uriInfo, Company company);

	Company createCompany(Company company);

	Company updateCompany(Company company);

	Company deleteCompany(String userUUID, int companyId);

}
