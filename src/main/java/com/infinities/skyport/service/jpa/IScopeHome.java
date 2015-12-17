package com.infinities.skyport.service.jpa;

import java.util.List;

import com.infinities.skyport.entity.Scope;

public interface IScopeHome extends GenericHome<Scope> {
	
	List<Scope> findAll(Long startId, Long endId, Integer limit, Order order) throws Exception;

}
