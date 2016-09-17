package com.services.addressmate.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.addressmate.bean.Company;
import com.services.addressmate.bean.CompanyEntity;
import com.services.addressmate.dao.CompanyDao;
import com.services.addressmate.exception.BeanEntityConversionException;
import com.services.addressmate.util.helper.LinkGenerator;

public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyDao companyDAO;
	Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class);
	
	public CompanyDao getCompanyDAO() {
		return companyDAO;
	}


	public void setCompanyDAO(CompanyDao companyDAO) {
		this.companyDAO = companyDAO;
	}


	@Override
	public List<Company> getCompanies(String userUUID) {
		
		List<CompanyEntity> entities = companyDAO.getCompanies(userUUID);
		List<Company> companies = null;
			
		try {
			companies = copyCompanyEntityList(entities);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return companies;
	}
	
	private List<Company> copyCompanyEntityList(List<CompanyEntity> entities)  throws IllegalAccessException, InvocationTargetException{
		if(entities == null){
			return null;
		}
		
		List<Company> companies = new ArrayList<Company>();
		
		for(CompanyEntity entity : entities){
			companies.add(new Company(entity));
		}
		
		return companies;
	}


	@Override
	public void addLinks(UriInfo uriInfo, List<Company> companies) {
		
		/*
		 * Using iterator framework as looping and updating both required together
		 */
		Iterator<Company> iterator = companies.iterator();
		Company company;
		while(iterator.hasNext()){
			company = iterator.next();
			/* using common method; all links logic consolidated at one place */
			addLinks(uriInfo, company);
		}
		
	}


	@Override
	public Company getCompany(int companyId, String userUUID) {
		CompanyEntity entity = companyDAO.getCompany(companyId, userUUID);
		Company company = null;
		try {
			company = new Company(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return company;
	}


	@Override
	public void addLinks(UriInfo uriInfo, Company company) {
		company.addLink(LinkGenerator.getCompanyResourceLink(uriInfo, company.getCompanyID()), "self");
	}


	@Override
	public Company createCompany(Company company) {
		Company createdCompany = null;
		CompanyEntity entity = null;	
		try {
			entity = new CompanyEntity(company);
			createdCompany = new Company(companyDAO.createCompany(entity));
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		return createdCompany;
	}


	@Override
	public Company updateCompany(Company company) {
		Company updatedCompany = null;
		CompanyEntity entity = null;	
		try {
			entity = new CompanyEntity(company);
			updatedCompany = new Company(companyDAO.updateCompany(entity));
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		return updatedCompany;
	}


	@Override
	public Company deleteCompany(String userUUID, int companyId) {
		Company deletedCompany = null;	
		try {
			deletedCompany = new Company(companyDAO.deleteCompany(userUUID, companyId));
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		return deletedCompany;
	}
	
}
