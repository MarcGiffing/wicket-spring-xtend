package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.domain.Domain;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.CodeGenerationContext;
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationUnit;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.file.Path;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EntityJpaMetaModelProcessor extends AbstractClassProcessor implements CodeGenerationParticipant<ClassDeclaration> {
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    TypeReference _newTypeReference = context.newTypeReference(Long.class);
    TypeReference domainIntefaceType = context.newTypeReference(Domain.class, _newTypeReference);
    Iterable<? extends TypeReference> _implementedInterfaces = cls.getImplementedInterfaces();
    Iterable<TypeReference> _plus = Iterables.<TypeReference>concat(_implementedInterfaces, Collections.<TypeReference>unmodifiableList(CollectionLiterals.<TypeReference>newArrayList(domainIntefaceType)));
    cls.setImplementedInterfaces(_plus);
  }
  
  public void doGenerateCode(final List<? extends ClassDeclaration> annotatedSourceElements, @Extension final CodeGenerationContext context) {
    for (final ClassDeclaration clazz : annotatedSourceElements) {
      {
        CompilationUnit _compilationUnit = clazz.getCompilationUnit();
        final Path filePath = _compilationUnit.getFilePath();
        Path _targetFolder = context.getTargetFolder(filePath);
        String _qualifiedName = clazz.getQualifiedName();
        String _replace = _qualifiedName.replace(".", "/");
        String _plus = (_replace + "_.java");
        final Path file = _targetFolder.append(_plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("package ");
        CompilationUnit _compilationUnit_1 = clazz.getCompilationUnit();
        String _packageName = _compilationUnit_1.getPackageName();
        _builder.append(_packageName, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import javax.persistence.metamodel.SingularAttribute;");
        _builder.newLine();
        _builder.append("import javax.persistence.metamodel.ListAttribute;");
        _builder.newLine();
        _builder.append("import javax.persistence.metamodel.SetAttribute;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public class ");
        String _simpleName = clazz.getSimpleName();
        _builder.append(_simpleName, "");
        _builder.append("_ {");
        _builder.newLineIfNotEmpty();
        {
          Iterable<? extends FieldDeclaration> _declaredFields = clazz.getDeclaredFields();
          for(final FieldDeclaration field : _declaredFields) {
            _builder.append("\t");
            _builder.append("public static volatile SingularAttribute<");
            String _simpleName_1 = clazz.getSimpleName();
            _builder.append(_simpleName_1, "\t");
            _builder.append(", ");
            TypeReference _type = field.getType();
            Object _genericType = this.genericType(_type);
            _builder.append(_genericType, "\t");
            _builder.append("> ");
            String _simpleName_2 = field.getSimpleName();
            _builder.append(_simpleName_2, "\t");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        context.setContents(file, _builder);
      }
    }
  }
  
  public Object genericType(final TypeReference ref) {
    TypeReference _xblockexpression = null;
    {
      boolean _isPrimitive = ref.isPrimitive();
      if (_isPrimitive) {
        String _name = ref.getName();
        return StringExtensions.toFirstUpper(_name);
      }
      _xblockexpression = ref;
    }
    return _xblockexpression;
  }
}
