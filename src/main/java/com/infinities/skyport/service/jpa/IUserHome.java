package com.infinities.skyport.service.jpa;

import java.util.List;

import com.infinities.skyport.entity.User;

public interface IUserHome extends GenericHome<User> {

	User findByLoginname(String loginname);

	List<User> findAll(Long startId, Long endId, Integer limit, Order order) throws Exception;

	List<User> findAllByOrganization(Long organizationId, Long startId, Long endId, Integer limit, Order order)
			throws Exception;
}
