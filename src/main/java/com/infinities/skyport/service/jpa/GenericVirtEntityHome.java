package com.infinities.skyport.service.jpa;

import java.util.List;

public interface GenericVirtEntityHome<T> extends GenericHome<T> {

	void removeAllByConfig(String configId);

	void persistAll(String configId, List<T> objs);
}
