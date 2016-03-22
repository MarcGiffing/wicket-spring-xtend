package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.FilterServiceProcessor;
import com.google.common.annotations.Beta;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

@Beta
@Target(ElementType.TYPE)
@Active(FilterServiceProcessor.class)
public @interface FilterServiceGen {
  public Class value();
}
