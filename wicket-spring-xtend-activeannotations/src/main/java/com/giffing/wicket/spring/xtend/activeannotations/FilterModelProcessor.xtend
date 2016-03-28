package com.giffing.wicket.spring.xtend.activeannotations

import com.google.common.annotations.Beta
import java.lang.annotation.Target
import java.util.List
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.CodeGenerationContext
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.TypeReference
import java.io.Serializable
import com.giffing.wicket.spring.xtend.activeannotations.domain.Fields

@Beta
@Target(TYPE)
@Active(FilterModelProcessor)
annotation FilterModel {
}

class FilterModelProcessor extends AbstractClassProcessor implements CodeGenerationParticipant<ClassDeclaration> {

	override doGenerateCode(List<? extends ClassDeclaration> annotatedSourceElements,
		extension CodeGenerationContext context) {
		for (clazz : annotatedSourceElements) {
			
			val filePath = clazz.compilationUnit.filePath
//			val filterFile = filePath.targetFolder.append(clazz.qualifiedName.replace('.', '/') + "Filter.java")
//			filterFile.contents = '''
//				package «clazz.compilationUnit.packageName»;
//				
//				public class «clazz.simpleName»Filter extends «DefaultFilter.canonicalName» {
//					«FOR field : clazz.declaredFields»
//						«field.generateVariable»
//					«ENDFOR»
//					
//				}
//				
//			'''
			
			val sortFile = filePath.targetFolder.append(clazz.qualifiedName.replace('.', '/') + "Fields.java")
			sortFile.contents = '''
				package «clazz.compilationUnit.packageName»;
				
				public enum «clazz.simpleName»Fields implements «Fields.canonicalName» {
					
					«FOR field : clazz.declaredFields BEFORE '' SEPARATOR ',' AFTER ';'»
						«field.simpleName»("«field.simpleName»", «field.type.name».class)
					«ENDFOR»
					
					private String fieldName;
						
					«clazz.simpleName»Fields(String fieldName, Object type){
						this.fieldName = fieldName;
						this.type = type;
					}
					
					public String getFieldName(){
						return this.fieldName;
					}
					
					public Class getType() {
						
					
					
				}
				
			'''
		}
	}
	
	def generateVariable(FieldDeclaration field){
		switch(field.type.name) {
			case "java.lang.Integer" : field.generateVariableNumber
			case "java.lang.Long" : field.generateVariableNumber
			case "java.lang.String" : field.generateVariableString
			default: field.generateVariableDefault(field.type)
		}
		
	}
	
	def generateVariableNumber(FieldDeclaration field) {
		'''
		
		private «field.type.genericType» «field.simpleName»Equals;
		private «field.type.genericType» «field.simpleName»Greater;
		private «field.type.genericType» «field.simpleName»GreaterEqual;
		private «field.type.genericType» «field.simpleName»Lesser;
		private «field.type.genericType» «field.simpleName»LesserEqual;
		
		'''
	}
	
	def generateVariableString(FieldDeclaration field) {
		'''
		
		private «field.type.genericType» «field.simpleName»Like;
		private «field.type.genericType» «field.simpleName»NotLike;
		private «field.type.genericType» «field.simpleName»Equals;
		
		'''
	}
	
	def generateVariableDefault(FieldDeclaration field, Object type) {
		'''private «field.type.genericType» «field.simpleName»;'''
	}
	
	def genericType(TypeReference ref){
		if(ref.isPrimitive) {
			return ref.name.toFirstUpper
		}
		ref
		
	}
	
}
