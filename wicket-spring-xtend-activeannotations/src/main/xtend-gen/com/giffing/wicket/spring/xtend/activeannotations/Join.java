package com.giffing.wicket.spring.xtend.activeannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Join {
  public Class value();
}
