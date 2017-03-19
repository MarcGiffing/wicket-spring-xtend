package com.giffing.wicket.spring.xtend.activeannotations

import com.giffing.wicket.spring.xtend.activeannotations.domain.Domain
import com.google.common.annotations.Beta
import java.lang.annotation.Target
import java.util.Collection
import java.util.List
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.CodeGenerationContext
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.TypeReference

@Beta
@Target(TYPE)
@Active(EntityJpaMetaModelProcessor)
annotation EntityMetaModel {
}


class EntityJpaMetaModelProcessor extends AbstractClassProcessor implements CodeGenerationParticipant<ClassDeclaration> {
	
	override doTransform(MutableClassDeclaration cls, extension TransformationContext context) {
		var domainIntefaceType = newTypeReference(Domain, newTypeReference(Long))
		cls.implementedInterfaces = cls.implementedInterfaces + #[domainIntefaceType]
	}
	
	override doGenerateCode(List<? extends ClassDeclaration> annotatedSourceElements,
		extension CodeGenerationContext context) {
		for (clazz : annotatedSourceElements) {
			val filePath = clazz.compilationUnit.filePath
			val file = filePath.targetFolder.append(clazz.qualifiedName.replace('.', '/') + "_.java")
			file.contents = '''
				package «clazz.compilationUnit.packageName»;
				
				import javax.persistence.metamodel.SingularAttribute;
				import javax.persistence.metamodel.ListAttribute;
				import javax.persistence.metamodel.SetAttribute;
				
				public class «clazz.simpleName»_ {
					«FOR field : clazz.declaredFields»
						«field.fieldDescription(clazz, context)»
					«ENDFOR»
					
				}
			'''
		}
	}
	
	def String fieldDescription(FieldDeclaration field, ClassDeclaration clazz, extension CodeGenerationContext context) {
		var collectionClass = findTypeGlobally(Iterable);
		if (collectionClass.isAssignableFrom(field.type.type)) {
			return '''public static volatile ListAttribute<«clazz.simpleName», «field.type.actualTypeArguments.get(0)»> «field.simpleName»;'''
		} else {
			return '''public static volatile SingularAttribute<«clazz.simpleName», «field.type.genericType»> «field.simpleName»;'''
		}
	}
	
	def genericType(TypeReference ref){
		if(ref.isPrimitive) {
			return ref.name.toFirstUpper
		}
		ref
		
	}
	
}
