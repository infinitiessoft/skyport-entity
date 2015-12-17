package com.infinities.skyport.service.jpa;

import java.io.Serializable;
import java.util.List;

public interface GenericHome<T> extends Serializable {

	public enum Order {
		desc, asc
	}


	void persist(T transientInstance); // throws Exception;

	void remove(T persistentInstance); // throws Exception;

	T merge(T detachedInstance); // throws Exception;

	T findById(Object id); // throws Exception;

	List<T> findAll(); // throws Exception;

	void refresh(T detachedInstance);

	// void commit(); // throws Exception;
}
