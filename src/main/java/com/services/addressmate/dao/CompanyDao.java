package com.services.addressmate.dao;

import java.util.List;

import com.services.addressmate.bean.CompanyEntity;

public interface CompanyDao {

	List<CompanyEntity> getCompanies(String userUUID);

	CompanyEntity getCompany(int companyId, String userUUID);

	CompanyEntity createCompany(CompanyEntity entity);

	CompanyEntity deleteCompany(String userUUID, int companyId);

	CompanyEntity updateCompany(CompanyEntity entity);

}
