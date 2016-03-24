package com.giffing.wicket.spring.xtend.activeannotations;

public enum CompareType {
	EQUALS("Equals", "equal", 1),
	LIKE("Like", "like", 1),
	NOT_LIKE("NotLike", "notLike", 1),
	IS_NULL("IsNull", "isNull", 0),
	IS_TRUE("IsTrue", "isTrue", 0),
	IS_FALSE("IsFalse", "isFalse", 0);
	
	private String displayName;
	
	private String name;
	
	private Integer parameterCount;
	
	private CompareType(String displayName, String name, Integer parameterCount){
		this.displayName = displayName;
		this.name = name;
		this.parameterCount = parameterCount;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public Integer getParameterCount() {
		return parameterCount;
	}


}
