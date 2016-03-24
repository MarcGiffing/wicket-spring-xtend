package com.giffing.wicket.spring.xtend.activeannotations

import com.google.common.annotations.Beta
import java.lang.annotation.Target
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.Type
import org.eclipse.xtend.lib.macro.declaration.TypeReference
import org.springframework.data.jpa.domain.Specification

@Beta
@Active(SpecsProcessor)
annotation Specs {
	Class value
	Join[] joins  = #[]
}

@Target(TYPE)
annotation Join {
	Class value
}

class SpecsProcessor extends AbstractClassProcessor {

	extension RegisterGlobalsContext globalContext
	
	override doRegisterGlobals(ClassDeclaration annotatedClass, RegisterGlobalsContext context) {
		globalContext = context
  	}

	
	override doTransform(MutableClassDeclaration cls, extension TransformationContext context) {
	
		val specAnnoation = cls.findAnnotation(findTypeGlobally(Specs));
		val specClassName = specAnnoation.getClassValue("value")
		val joinArray = specAnnoation.getAnnotationArrayValue("joins")
		val collectionClass = findTypeGlobally(Iterable);
		specClassName.declaredResolvedMethods.forEach[
			if(it.resolvedParameters.size == 1) {
				it.resolvedParameters.
					forEach[
						if(!collectionClass.isAssignableFrom(it.resolvedType.type)) {
							val fieldName = it.declaration.simpleName
							val fieldType = findTypeGlobally(it.declaration.type.name)
							val customerSuperName = findTypeGlobally(specClassName.name+"_")
								
							val config = new MethodConfig => [
								it.fieldName = fieldName
								metaModelName = specClassName.type
								genericClassName = customerSuperName
								param = new MethodParam("value", newTypeReference(fieldType))
								returnType = newTypeReference(Specification, specClassName)
							]
							
							switch (it.declaration.type.name) {
								case "java.lang.String": stringPredicate(cls, context, specClassName,  config)
								case "boolean": booleanPredicate(cls, context, specClassName,  config)
								case "java.lang.Boolean": booleanPredicate(cls, context, specClassName,  config) 
								
							}
						}
				]
			}
		]
		
//		if(joinArray != null){
//			joinArray.forEach[
//				val joinClassName = it.getClassValue("value")
//				if(joinClassName != null) {
//					joinClassName.declaredResolvedMethods.forEach[
//						if(it.resolvedParameters.size == 1) {
//							it.resolvedParameters.
//								forEach[
//									
//									val fieldName = it.declaration.simpleName
//									val fieldType = findTypeGlobally(it.declaration.type.name)
//									val customerSuperName = findTypeGlobally(specClassName.name+"_")
//									
//									val config = new MethodConfig => [
//										it.fieldName = fieldName
//										metaModelName = specClassName.type
//										genericClassName = customerSuperName
//										param = new MethodParam("value", newTypeReference(fieldType))
//										returnType = newTypeReference(Specification, specClassName)
//									]
//									
//									
//									switch (it.declaration.type.name) {
//										case "java.lang.String": 	stringPredicate(cls, context, specClassName,  config)
//										case "boolean": 			booleanPredicate(cls, context, specClassName,  config)
//										case "java.lang.Boolean": 	booleanPredicate(cls, context, specClassName,  config) 
//									}
//								]
//						}
//					]
//				}
//			]
//		}
	}
	
	@Accessors
	static class MethodConfig implements Cloneable {
		CompareType compareType
		String fieldName
		Type genericClassName
		Type metaModelName
		MethodParam  param
		TypeReference returnType
		boolean isStatic = true;
		boolean isFinal = true
		
		override Object clone() throws CloneNotSupportedException {
		    return super.clone();
		}
	}
	
	@Accessors
	@Data
	static class MethodParam implements Cloneable{
		String name
		TypeReference type
	}
	
	
	
	def booleanPredicate(MutableClassDeclaration cls, extension TransformationContext context, TypeReference specClassName, MethodConfig config) {
		
		var clone1 = config.clone as MethodConfig
		clone1.compareType = CompareType.EQUALS
		addSpecMethod(cls, context, clone1, false)
		
		var clone2 = config.clone as MethodConfig
		clone2.compareType = CompareType.IS_TRUE
		addSpecMethod(cls, context, clone2, false)
		
		var clone3 = config.clone as MethodConfig
		clone3.compareType = CompareType.IS_FALSE
		addSpecMethod(cls, context, clone3, false)
	}
	
	def stringPredicate(MutableClassDeclaration cls, extension TransformationContext context, TypeReference specClassName, MethodConfig config) {
		
		var clone1 = config.clone as MethodConfig
		clone1.compareType = CompareType.EQUALS
		addSpecMethod(cls, context, clone1, false)
		addSpecMethod(cls, context, clone1, true)
		
		var clone2 = config.clone as MethodConfig
		clone2.compareType = CompareType.LIKE
		addSpecMethod(cls, context, clone2, false)
		addSpecMethod(cls, context, clone2, true)
		
		var clone3 = config.clone as MethodConfig
		clone3.compareType = CompareType.NOT_LIKE
		addSpecMethod(cls, context, clone3, false)
		addSpecMethod(cls, context, clone3, true)
		
		var clone4 = config.clone as MethodConfig
		clone4.compareType = CompareType.IS_NULL
		addSpecMethod(cls, context, clone4, false)
		
	}
	
	def addSpecMethod(MutableClassDeclaration cls, extension TransformationContext context, MethodConfig config, boolean ignoreCase) {
		var methodName = config.fieldName + config.compareType.displayName
		if(ignoreCase) {
			methodName += "IgnoreCase"
		}
		cls.addMethod(methodName) [
			static = config.isStatic
			final = config.isFinal
			if(config.param != null && config.compareType.parameterCount == 1){
				addParameter(config.param.name, config.param.type)
			}
			if(returnType != null){
				returnType = config.returnType
			}
			body = '''
				return new Specification<«config.metaModelName»>() {
					public «Predicate» toPredicate(«Root»<«config.metaModelName.simpleName»> root, «CriteriaQuery»<?> query, «CriteriaBuilder» cb) {
						«IF config.compareType.parameterCount == 1»
							«IF ignoreCase»
								return cb.«config.compareType.getName»(cb.lower(root.get(«config.genericClassName.simpleName».«config.fieldName»)), value);
							«ELSE»
								return cb.«config.compareType.getName»(root.get(«config.genericClassName.simpleName».«config.fieldName»), value);
							«ENDIF»
						«ELSE»
							«IF ignoreCase»
								return cb.«config.compareType.getName»(cb.lower(root.get(«config.genericClassName».«config.fieldName»)));
							«ELSE»
								return cb.«config.compareType.getName»(root.get(«config.genericClassName».«config.fieldName»));
							«ENDIF»
						«ENDIF»
						
					}
				};
			'''
			primarySourceElement = cls
		]
	}
	

	
}
