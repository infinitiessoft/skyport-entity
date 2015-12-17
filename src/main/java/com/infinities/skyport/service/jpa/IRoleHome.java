package com.infinities.skyport.service.jpa;

import java.util.List;

import com.infinities.skyport.entity.Role;

public interface IRoleHome extends GenericHome<Role> {

	List<Role> findAll(Long startId, Long endId, Integer limit, Order order) throws Exception;

	List<Role> findAllUnreferencedRolesByOrganization(Long organizationId, Long userId, Long startId, Long endId,
			Integer limit, Order order) throws Exception;
}
