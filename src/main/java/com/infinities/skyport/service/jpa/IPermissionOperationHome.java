package com.infinities.skyport.service.jpa;

import java.util.List;

import com.infinities.skyport.entity.PermissionOperation;

public interface IPermissionOperationHome extends GenericHome<PermissionOperation> {

	List<PermissionOperation> findAll(Long startId, Long endId, Integer limit, Order order) throws Exception;
}
