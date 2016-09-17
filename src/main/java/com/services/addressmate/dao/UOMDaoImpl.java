package com.services.addressmate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.services.addressmate.bean.UnitOfMeasureEntity;
import com.services.addressmate.exception.ResourceNotFoundException;

@Transactional
public class UOMDaoImpl implements UOMDao{
	
	@PersistenceContext(unitName="addressmateServicesPersistence")
	private EntityManager entityManager;
	
	@Override
	public List<UnitOfMeasureEntity> getUnitOfMeasures(String userUUID) {
		List<UnitOfMeasureEntity> entities = null;
		
		String qString = "SELECT u FROM UnitOfMeasureEntity u WHERE u.userUUID = ?1";
		TypedQuery<UnitOfMeasureEntity> query = entityManager.createQuery(qString, UnitOfMeasureEntity.class);		
		query.setParameter(1, userUUID);
		entities = query.getResultList();
		
		return entities;
	}

	@Override
	public UnitOfMeasureEntity getUnitOfMeasure(String userUUID, int uomId) {
		try {
			String qString = "SELECT u FROM UnitOfMeasureEntity u WHERE u.uomID = ?1 and u.userUUID = ?2";
			
			TypedQuery<UnitOfMeasureEntity> query = entityManager.createQuery(qString, UnitOfMeasureEntity.class);		
			query.setParameter(1, uomId);
			query.setParameter(2, userUUID);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResourceNotFoundException("Unit of Measure resource with ID - "+uomId+" was not found.");
		}
	}

	@Override
	public UnitOfMeasureEntity createUnitOfMeasure(UnitOfMeasureEntity unitOfMeasureEntity) {
		UnitOfMeasureEntity createdUOM = entityManager.merge(unitOfMeasureEntity);
		entityManager.flush();
		return createdUOM;
	}

	@Override
	public UnitOfMeasureEntity updateUnitOfMeasure(UnitOfMeasureEntity unitOfMeasureEntity) {
		UnitOfMeasureEntity updatedUOM = null;
		try{
			
			/* check if user exists before deleting */
			String qString = "SELECT u FROM UnitOfMeasureEntity u WHERE u.uomID= ?1 and u.userUUID = ?2";
			TypedQuery<UnitOfMeasureEntity> query = entityManager.createQuery(qString, UnitOfMeasureEntity.class);		
			query.setParameter(1, unitOfMeasureEntity.getUomID());
			query.setParameter(2, unitOfMeasureEntity.getUserUUID());
			query.getSingleResult();
			
			/* update the resource */
			updatedUOM = entityManager.merge(unitOfMeasureEntity);
			
		}catch (NoResultException e) {
			throw new ResourceNotFoundException("Unit of Measure resource with ID - "+unitOfMeasureEntity.getUomID()+" was not found.");
		}
		
		return updatedUOM;
	}

	@Override
	public UnitOfMeasureEntity deleteUnitOfMeasure(String userUUID, int uomId) {
		UnitOfMeasureEntity deletedUOM = null;
		try{
			
			/* check if user exists before deleting */
			String qString = "SELECT u FROM UnitOfMeasureEntity u WHERE u.uomID= ?1 and u.userUUID = ?2";
			TypedQuery<UnitOfMeasureEntity> query = entityManager.createQuery(qString, UnitOfMeasureEntity.class);		
			query.setParameter(1, uomId);
			query.setParameter(2, userUUID);
			deletedUOM = query.getSingleResult();
			
			entityManager.remove(deletedUOM);
			
		}catch (NoResultException e) {
			throw new ResourceNotFoundException("Unit of Measure resource with ID - "+uomId+" was not found.");
		}
		
		return deletedUOM;
	}

}
