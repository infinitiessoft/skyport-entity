package com.infinities.skyport.service.jpa;

import java.util.Collection;
import java.util.List;

import com.infinities.skyport.entity.Job;

public interface IJobHome extends GenericHome<Job> {

	List<Job> findByExecutorKey(String executorKey);

	List<Job> findAll(Collection<String> keys);

	Job findByEventid(Object eventid);

}
