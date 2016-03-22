package com.giffing.wicket.spring.xtend.activeannotations

import com.google.common.annotations.Beta
import java.lang.annotation.Target
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.ResolvedParameter
import org.eclipse.xtend.lib.macro.declaration.TypeReference
import org.springframework.data.jpa.domain.Specification

@Beta
@Target(TYPE)
@Active(SpecsProcessor)
annotation Specs {
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
		specClassName.declaredResolvedMethods.forEach[
			if(it.resolvedParameters.size == 1) {
				it.resolvedParameters.
					forEach[
						switch (it.declaration.type.name) {
							case "java.lang.String": stringPredicate(cls, context, specClassName,  it)
						}
				]
			}
		]
	}
	
	def stringPredicate(MutableClassDeclaration cls, extension TransformationContext context, TypeReference specClassName, ResolvedParameter param) {
		val fieldName = param.declaration.simpleName
		val fieldType = findTypeGlobally(param.declaration.type.name)
		val customerSuperName = findTypeGlobally(specClassName.name+"_")
		cls.addMethod(fieldName+"Equals") [
			static = true
			final = true
			addParameter("value", newTypeReference(fieldType))
			returnType = newTypeReference(Specification, specClassName)
			body = '''
				return new Specification<«specClassName.simpleName»>() {
					public «Predicate» toPredicate(«Root»<«specClassName.simpleName»> root, «CriteriaQuery»<?> query, «CriteriaBuilder» cb) {
						return cb.equal(root.get(«customerSuperName».«fieldName»), value);
					}
				};
			'''
			primarySourceElement = cls
		]
		
		cls.addMethod(fieldName+"Like") [
			static = true
			final = true
			addParameter("value", newTypeReference(fieldType))
			returnType = newTypeReference(Specification, specClassName)
			body = '''
				return new Specification<«specClassName.simpleName»>() {
					public «Predicate» toPredicate(«Root»<«specClassName.simpleName»> root, «CriteriaQuery»<?> query, «CriteriaBuilder» cb) {
						return cb.like(root.get(«customerSuperName».«fieldName»), value);
					}
				};
			'''
			primarySourceElement = cls
		]
		
		cls.addMethod(fieldName+"IsNull") [
			static = true
			final = true
			addParameter("value", newTypeReference(fieldType))
			returnType = newTypeReference(Specification, specClassName)
			body = '''
				return new Specification<«specClassName.simpleName»>() {
					public «Predicate» toPredicate(«Root»<«specClassName.simpleName»> root, «CriteriaQuery»<?> query, «CriteriaBuilder» cb) {
						return cb.isNull(root.get(«customerSuperName».«fieldName»));
					}
				};
			'''
			primarySourceElement = cls
		]
		
		cls.addMethod(fieldName+"NotLike") [
			static = true
			final = true
			addParameter("value", newTypeReference(fieldType))
			returnType = newTypeReference(Specification, specClassName)
			body = '''
				return new Specification<«specClassName.simpleName»>() {
					public «Predicate» toPredicate(«Root»<«specClassName.simpleName»> root, «CriteriaQuery»<?> query, «CriteriaBuilder» cb) {
						return cb.notLike(root.get(«customerSuperName».«fieldName»), value);
					}
				};
			'''
			primarySourceElement = cls
		]
		
	}
	
}
