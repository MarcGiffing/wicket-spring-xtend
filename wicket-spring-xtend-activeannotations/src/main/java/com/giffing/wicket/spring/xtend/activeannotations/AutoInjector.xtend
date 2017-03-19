package com.giffing.wicket.spring.xtend.activeannotations

import com.google.common.annotations.Beta
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.CodeGenerationContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.apache.wicket.injection.Injector

/**
 * Adds a Logger field to this class  
 */
@Beta
@Target(TYPE)
@Active(AutoInjectorProcessor)
annotation AutoInjector {
}

class AutoInjectorProcessor extends AbstractClassProcessor {

	extension CodeGenerationContext

	override doTransform(MutableClassDeclaration cls, extension TransformationContext context) {
		
		
		cls.declaredConstructors.forEach[constructor, index |
			val oldBody = constructor.body
			val subMethod = cls.addMethod("construtor_" + index)[
				constructor.parameters.forEach[param | addParameter(param.simpleName, param.type)]
				body = oldBody
				primarySourceElement = cls
			]
			
			constructor.body = '''
				«subMethod.simpleName»(«constructor.parameters.map[it.simpleName].join(',')»);
				«Injector».get().inject(this);
				'''
			
			
		]
		
	}
	
}

