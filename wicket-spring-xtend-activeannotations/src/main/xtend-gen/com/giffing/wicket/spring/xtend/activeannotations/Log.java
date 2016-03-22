package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.LogProcessor;
import com.google.common.annotations.Beta;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Adds a Logger field to this class
 */
@Beta
@Target(ElementType.TYPE)
@Active(LogProcessor.class)
public @interface Log {
}
