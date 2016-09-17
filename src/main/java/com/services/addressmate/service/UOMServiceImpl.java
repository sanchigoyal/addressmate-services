package com.services.addressmate.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.services.addressmate.bean.UnitOfMeasure;
import com.services.addressmate.bean.UnitOfMeasureEntity;
import com.services.addressmate.dao.UOMDao;
import com.services.addressmate.exception.BeanEntityConversionException;
import com.services.addressmate.util.helper.LinkGenerator;

public class UOMServiceImpl implements UOMService {
	@Autowired
	private UOMDao uomDAO;
	Logger LOGGER = Logger.getLogger(UOMServiceImpl.class);
	
	@Override
	public List<UnitOfMeasure> getUnitOfMeasures(String userUUID) {
		List<UnitOfMeasureEntity> entities = uomDAO.getUnitOfMeasures(userUUID);
		List<UnitOfMeasure> uoms = null;
		
		try {
			uoms = copyUOMEntityList(entities);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return uoms;
	}

	private List<UnitOfMeasure> copyUOMEntityList(List<UnitOfMeasureEntity> entities) throws IllegalAccessException, InvocationTargetException {
		if(entities == null){
			return null;
		}
		
		List<UnitOfMeasure> uoms = new ArrayList<UnitOfMeasure>();
		for(UnitOfMeasureEntity entity : entities){
			uoms.add(new UnitOfMeasure(entity));
		}
		
		return uoms;
	}

	@Override
	public void addLinks(UriInfo uriInfo, List<UnitOfMeasure> uoms) {
		/*
		 * Using iterator framework as looping and updating both required together
		 */
		Iterator<UnitOfMeasure> iterator = uoms.iterator();
		UnitOfMeasure uom;
		while(iterator.hasNext()){
			uom = iterator.next();
			/* using common method; all links logic consolidated at one place */
			addLinks(uriInfo, uom);
		}
		
	}

	@Override
	public UnitOfMeasure getUnitOfMeasure(String userUUID, int uomId) {
		UnitOfMeasure uom = null;
		UnitOfMeasureEntity entity = uomDAO.getUnitOfMeasure(userUUID, uomId);
		
		try {
			uom = new UnitOfMeasure(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return uom;
	}

	@Override
	public void addLinks(UriInfo uriInfo, UnitOfMeasure uom) {
		uom.addLink(LinkGenerator.getUOMResourceLink(uriInfo, uom.getUomID()), "self");	
	}

	@Override
	public UnitOfMeasure createUnitOfMeasure(UnitOfMeasure uom) {
		UnitOfMeasureEntity createdEntity = null;
		UnitOfMeasure createdUom = null;
		
		try{
			createdEntity = uomDAO.createUnitOfMeasure(new UnitOfMeasureEntity(uom));
			createdUom = new UnitOfMeasure(createdEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return createdUom;
	}

	@Override
	public UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure uom) {
		UnitOfMeasureEntity updatedEntity = null;
		UnitOfMeasure updatedUom = null;
		
		try{
			updatedEntity = uomDAO.updateUnitOfMeasure(new UnitOfMeasureEntity(uom));
			updatedUom = new UnitOfMeasure(updatedEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return updatedUom;
	}

	@Override
	public UnitOfMeasure deleteUnitOfMeasure(String userUUID, int uomId) {
		UnitOfMeasure deletedUom = null;
		
		try {
			deletedUom = new UnitOfMeasure(uomDAO.deleteUnitOfMeasure(userUUID, uomId));
		} catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			throw new BeanEntityConversionException(e.getMessage());
		}
		
		return deletedUom;
	}

	public UOMDao getUomDAO() {
		return uomDAO;
	}

	public void setUomDAO(UOMDao uomDAO) {
		this.uomDAO = uomDAO;
	}

}
