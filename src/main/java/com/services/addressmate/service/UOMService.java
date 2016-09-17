package com.services.addressmate.service;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.services.addressmate.bean.UnitOfMeasure;

public interface UOMService {

	List<UnitOfMeasure> getUnitOfMeasures(String userUUID);

	void addLinks(UriInfo uriInfo, List<UnitOfMeasure> uoms);

	UnitOfMeasure getUnitOfMeasure(String userUUID, int uomId);

	void addLinks(UriInfo uriInfo, UnitOfMeasure uom);

	UnitOfMeasure createUnitOfMeasure(UnitOfMeasure uom);

	UnitOfMeasure updateUnitOfMeasure(UnitOfMeasure uom);

	UnitOfMeasure deleteUnitOfMeasure(String userUUID, int uomId);

}
