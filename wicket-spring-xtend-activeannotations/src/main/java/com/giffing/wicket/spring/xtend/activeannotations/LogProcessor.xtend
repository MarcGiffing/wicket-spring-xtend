package com.giffing.wicket.spring.xtend.activeannotations

import com.google.common.annotations.Beta
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.CodeGenerationContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Adds a Logger field to this class  
 */
@Beta
@Target(TYPE)
@Active(LogProcessor)
annotation Log {
}

class LogProcessor extends AbstractClassProcessor {

	extension CodeGenerationContext

	override doTransform(MutableClassDeclaration cls, extension TransformationContext context) {
		cls.addField("log") [
			static = true
			final = true
			type = Logger.newTypeReference
			initializer = '''
				«LoggerFactory».getLogger("«cls.simpleName»")
			'''
			primarySourceElement = cls
		]
		
	  val filePath = cls.compilationUnit.filePath
      val file = filePath.targetFolder.append(cls.qualifiedName.replace('.', '/') + ".properties")
      cls.addWarning(file.toString)
      file.contents = ""
		
	}
}
