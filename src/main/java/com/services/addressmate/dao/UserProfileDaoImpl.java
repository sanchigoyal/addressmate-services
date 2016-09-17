package com.services.addressmate.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.services.addressmate.bean.UserProfileEntity;
import com.services.addressmate.exception.ResourceNotFoundException;

/**
 * Data access object implementation for handling UserProfile DB transactions
 * 
 * @author Sanchi
 *
 */
@Transactional
public class UserProfileDaoImpl implements UserProfileDao{

	@PersistenceContext(unitName="addressmateServicesPersistence")
	private EntityManager entityManager;
	
	/**
	 * Method to retrieve UserProfile resource based on the unique userName
	 * 
	 * @param userName
	 * @return UserProfile
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserProfileEntity getUserProfile(String email) throws ResourceNotFoundException{
		try {
			String qString = "SELECT u FROM UserProfileEntity u WHERE u.email = ?1";
			
			TypedQuery<UserProfileEntity> query = entityManager.createQuery(qString, UserProfileEntity.class);		
			query.setParameter(1, email);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResourceNotFoundException("UserProfile resource with email - "+email+" was not found.");
		}
	}

	/**
	 * Method to create a new UserProfile resource 
	 * 
	 * @param user
	 * @return UserProfile
	 */
	@Override
	public UserProfileEntity createUserProfile(UserProfileEntity user) {
		
		UserProfileEntity createdUser = entityManager.merge(user);
		entityManager.flush();
		return createdUser;
	}
	
	/**
	 * Method to update UserProfile resource 
	 * 
	 * @param user
	 * @return UserProfile
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserProfileEntity updateUserProfile(UserProfileEntity user) throws ResourceNotFoundException {
		
		UserProfileEntity currentUser = new UserProfileEntity();
		UserProfileEntity updatedUser = new UserProfileEntity();
		try{
			
			/* check if user exists before deleting */
			String qString = "SELECT u FROM UserProfileEntity u WHERE u.userUUID = ?1";
			TypedQuery<UserProfileEntity> query = entityManager.createQuery(qString, UserProfileEntity.class);		
			query.setParameter(1, user.getUserUUID());
			currentUser = query.getSingleResult();
			
			/* updated user id if anything passed as dummy in the request */
			user.setUserID(currentUser.getUserID());
			
			/* update the resource */
			updatedUser = entityManager.merge(user);
			
		}catch (NoResultException e) {
			throw new ResourceNotFoundException("UserProfile resource with userUUID - "+user.getUserUUID()+" was not found.");
		}
		
		return updatedUser;
	}
	
	/**
	 * Method to delete UserProfile resource based on the unique userId
	 * 
	 * @param userId
	 * @return UserProfile
	 * @throws ResourceNotFoundException
	 */
	@Override
	public UserProfileEntity deleteUserProfile(String userUUID) throws ResourceNotFoundException{
		UserProfileEntity deletedUser = new UserProfileEntity();
		try{
			
			/* check if user exists before deleting */
			String qString = "SELECT u FROM UserProfileEntity u WHERE u.userUUID = ?1";
			TypedQuery<UserProfileEntity> query = entityManager.createQuery(qString, UserProfileEntity.class);		
			query.setParameter(1, userUUID);
			deletedUser = query.getSingleResult();
			
			/* delete the resource */
			entityManager.remove(deletedUser);
			
		}catch (NoResultException e) {
			throw new ResourceNotFoundException("UserProfile resource with userUUID - "+userUUID+" was not found.");
		}
	
		return deletedUser;
	}

}
