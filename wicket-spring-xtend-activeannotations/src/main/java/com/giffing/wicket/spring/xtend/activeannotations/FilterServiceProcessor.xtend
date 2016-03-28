package com.giffing.wicket.spring.xtend.activeannotations

import com.google.common.annotations.Beta
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractInterfaceProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration

@Beta
@Target(TYPE)
@Active(FilterServiceProcessor)
annotation FilterServiceGen {
	
	Class value;
	
}

class FilterServiceProcessor extends AbstractInterfaceProcessor {

	override doTransform(MutableInterfaceDeclaration annotatedInterface, @Extension TransformationContext context) {
		
		val domainClass = annotatedInterface.findAnnotation(findTypeGlobally(FilterServiceGen));
		val className = domainClass.getClassValue("value")
		
//		var domainIntefaceType = newTypeReference(FilterService, className, className, className)
//		if(domainIntefaceType != null){
//			annotatedInterface.extendedInterfaces = annotatedInterface.extendedInterfaces + #[domainIntefaceType]
//		}
	}
	
}
