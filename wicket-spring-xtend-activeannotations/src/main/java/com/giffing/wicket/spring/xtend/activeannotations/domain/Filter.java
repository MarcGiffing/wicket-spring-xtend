package com.giffing.wicket.spring.xtend.activeannotations.domain;

import java.io.Serializable;

public interface Filter extends Serializable {
	
	Sort sort();
	
	boolean isAscending();
	
	void setSort(Sort sort, boolean ascending);
	
}
