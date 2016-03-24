package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.Join;
import com.giffing.wicket.spring.xtend.activeannotations.SpecsProcessor;
import com.google.common.annotations.Beta;
import org.eclipse.xtend.lib.macro.Active;

@Beta
@Active(SpecsProcessor.class)
public @interface Specs {
  public Class value();
  public Join[] joins() default {};
}
