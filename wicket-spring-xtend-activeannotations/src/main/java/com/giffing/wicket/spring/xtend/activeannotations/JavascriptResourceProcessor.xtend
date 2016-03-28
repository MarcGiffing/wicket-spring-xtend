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
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import java.util.List
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant
import org.eclipse.xtend.lib.macro.ValidationContext

/**
 * Adds a Logger field to this class  
 */
@Beta
@Target(TYPE)
@Active(JavascriptReferenceProcessor)
annotation JavascriptReference {

	String value
}

class JavascriptReferenceProcessor extends AbstractClassProcessor implements CodeGenerationParticipant<ClassDeclaration> {

	override doValidate(ClassDeclaration annotatedClass, extension ValidationContext context) {
		super.doValidate(annotatedClass, context)
		
		val filePath = annotatedClass.compilationUnit.filePath
		
		val domainClass = annotatedClass.findAnnotation(findTypeGlobally(JavascriptReference));
		val resourceFileString = domainClass.getStringValue("value")
		
		if(resourceFileString.isNullOrEmpty){
			annotatedClass.addError("Not resource file name specified")
			return
		}
		
		var fileName = annotatedClass.compilationUnit.packageName.replace('.', '/') + "/" + resourceFileString
		var resourceFile = filePath.sourceFolder.append(fileName)
		
		if(!resourceFile.exists){
			annotatedClass.addWarning("Could not find javascript file: " + resourceFileString)
		}
		
	}

	override doGenerateCode(List<? extends ClassDeclaration> annotatedSourceElements,
		extension CodeGenerationContext context) {
		for (cls : annotatedSourceElements) {
			val domainClass = cls.findAnnotation(findTypeGlobally(JavascriptReference));
			val resourceFile = domainClass.getStringValue("value")
			val filePath = cls.compilationUnit.filePath
			var simpleName = cls.simpleName.toFirstUpper;

			var fileName = cls.compilationUnit.packageName.replace('.', '/') + "/" + simpleName + "ResourceReference.java"
			
			
			val file = filePath.targetFolder.append(fileName)
			file.contents = '''
			
			package «cls.compilationUnit.packageName»;
			
			import org.apache.wicket.request.resource.JavaScriptResourceReference;
			
			public class «simpleName»ResourceReference extends JavaScriptResourceReference{
			
				private static final «simpleName»ResourceReference instance = new «simpleName»ResourceReference();
				
				/**
				 * @return the singleton instance
				 */
				public static «simpleName»ResourceReference get()
				{
					return instance;
				}
				
				public «simpleName.toFirstUpper»ResourceReference() {
					super(«simpleName.toFirstUpper»ResourceReference.class, "«resourceFile»");
				}
			
			}
			'''
		}
	}
}
