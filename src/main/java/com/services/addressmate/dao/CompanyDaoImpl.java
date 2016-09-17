package com.services.addressmate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.services.addressmate.bean.CompanyEntity;
import com.services.addressmate.exception.ResourceNotFoundException;

@Transactional
public class CompanyDaoImpl implements CompanyDao {

	@PersistenceContext(unitName="addressmateServicesPersistence")
	private EntityManager entityManager;
	
	@Override
	public List<CompanyEntity> getCompanies(String userUUID) {
		List<CompanyEntity> entities = null;
			
		String qString = "SELECT c FROM CompanyEntity c WHERE c.userUUID = ?1";
		TypedQuery<CompanyEntity> query = entityManager.createQuery(qString, CompanyEntity.class);		
		query.setParameter(1, userUUID);
		entities = query.getResultList();
		
		return entities;
	}

	@Override
	public CompanyEntity getCompany(int companyId, String userUUID) {
		try {
			String qString = "SELECT c FROM CompanyEntity c WHERE c.companyID = ?1 and c.userUUID = ?2";
			
			TypedQuery<CompanyEntity> query = entityManager.createQuery(qString, CompanyEntity.class);		
			query.setParameter(1, companyId);
			query.setParameter(2, userUUID);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResourceNotFoundException("Company resource with ID - "+companyId+" was not found.");
		}
	}

	@Override
	public CompanyEntity createCompany(CompanyEntity entity) {
		CompanyEntity createdCompany = entityManager.merge(entity);
		entityManager.flush();
		return createdCompany;
	}

	@Override
	public CompanyEntity updateCompany(CompanyEntity entity) {
		CompanyEntity updatedCompany = null;
		try{
			
			/* check if user exists before deleting */
			String qString = "SELECT c FROM CompanyEntity c WHERE c.companyID= ?1 and c.userUUID = ?2";
			TypedQuery<CompanyEntity> query = entityManager.createQuery(qString, CompanyEntity.class);		
			query.setParameter(1, entity.getCompanyID());
			query.setParameter(2, entity.getUserUUID());
			query.getSingleResult();
			
			/* update the resource */
			updatedCompany = entityManager.merge(entity);
			
		}catch (NoResultException e) {
			throw new ResourceNotFoundException("Company resource with ID - "+entity.getCompanyID()+" was not found.");
		}
		
		return updatedCompany;
	}

	@Override
	public CompanyEntity deleteCompany(String userUUID, int companyId) {
		CompanyEntity deletedCompany = null;
		try{
			
			/* check if user exists before deleting */
			String qString = "SELECT c FROM CompanyEntity c WHERE c.companyID= ?1 and c.userUUID = ?2";
			TypedQuery<CompanyEntity> query = entityManager.createQuery(qString, CompanyEntity.class);		
			query.setParameter(1, companyId);
			query.setParameter(2, userUUID);
			deletedCompany = query.getSingleResult();
			
			entityManager.remove(deletedCompany);
			
		}catch (NoResultException e) {
			throw new ResourceNotFoundException("Company resource with ID - "+companyId+" was not found.");
		}
		
		return deletedCompany;
	}

}
