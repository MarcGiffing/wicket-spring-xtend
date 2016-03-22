package com.giffing.wicket.spring.xtend.activeannotations.domain;

public abstract class DefaultFilter implements Filter {
	
	private Sort sort;
	
	private boolean ascending;
	
	public Sort sort() {
		return sort;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setSort(Sort sort, boolean ascending) {
		this.sort = sort;
		this.ascending = ascending;
	}

}
