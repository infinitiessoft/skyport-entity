package com.infinities.skyport.service.jpa;

import java.util.Date;
import java.util.List;

import com.infinities.skyport.entity.TaskEvent;
import com.infinities.skyport.entity.User;

public interface ITaskEventHome extends GenericHome<TaskEvent> {

	List<TaskEvent> findAll(String configId, Date start, Date end, User user, Integer page, Integer perpage)
			throws Exception;

	List<TaskEvent> findAll(String configId, Date start, Date end, User user, Long startId, Long endId, Integer limit,
			Order order) throws Exception;

	List<TaskEvent> findAllUnfinishTask();
}
