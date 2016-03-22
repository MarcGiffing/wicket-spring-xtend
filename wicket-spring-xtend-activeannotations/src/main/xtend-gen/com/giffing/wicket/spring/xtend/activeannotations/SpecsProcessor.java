package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.Specs;
import com.google.common.base.Objects;
import java.util.function.Consumer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ResolvedMethod;
import org.eclipse.xtend.lib.macro.declaration.ResolvedParameter;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("all")
public class SpecsProcessor extends AbstractClassProcessor {
  @Extension
  private RegisterGlobalsContext globalContext;
  
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    this.globalContext = context;
  }
  
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    Type _findTypeGlobally = context.findTypeGlobally(Specs.class);
    final AnnotationReference specAnnoation = cls.findAnnotation(_findTypeGlobally);
    final TypeReference specClassName = specAnnoation.getClassValue("value");
    Iterable<? extends ResolvedMethod> _declaredResolvedMethods = specClassName.getDeclaredResolvedMethods();
    final Consumer<ResolvedMethod> _function = new Consumer<ResolvedMethod>() {
      public void accept(final ResolvedMethod it) {
        Iterable<? extends ResolvedParameter> _resolvedParameters = it.getResolvedParameters();
        int _size = IterableExtensions.size(_resolvedParameters);
        boolean _equals = (_size == 1);
        if (_equals) {
          Iterable<? extends ResolvedParameter> _resolvedParameters_1 = it.getResolvedParameters();
          final Consumer<ResolvedParameter> _function = new Consumer<ResolvedParameter>() {
            public void accept(final ResolvedParameter it) {
              ParameterDeclaration _declaration = it.getDeclaration();
              TypeReference _type = _declaration.getType();
              String _name = _type.getName();
              boolean _matched = false;
              if (!_matched) {
                if (Objects.equal(_name, "java.lang.String")) {
                  _matched=true;
                  SpecsProcessor.this.stringPredicate(cls, context, specClassName, it);
                }
              }
            }
          };
          _resolvedParameters_1.forEach(_function);
        }
      }
    };
    _declaredResolvedMethods.forEach(_function);
  }
  
  public MutableMethodDeclaration stringPredicate(final MutableClassDeclaration cls, @Extension final TransformationContext context, final TypeReference specClassName, final ResolvedParameter param) {
    MutableMethodDeclaration _xblockexpression = null;
    {
      ParameterDeclaration _declaration = param.getDeclaration();
      final String fieldName = _declaration.getSimpleName();
      ParameterDeclaration _declaration_1 = param.getDeclaration();
      TypeReference _type = _declaration_1.getType();
      String _name = _type.getName();
      final Type fieldType = context.findTypeGlobally(_name);
      String _name_1 = specClassName.getName();
      String _plus = (_name_1 + "_");
      final Type customerSuperName = context.findTypeGlobally(_plus);
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setStatic(true);
          it.setFinal(true);
          TypeReference _newTypeReference = context.newTypeReference(fieldType);
          it.addParameter("value", _newTypeReference);
          TypeReference _newTypeReference_1 = context.newTypeReference(Specification.class, specClassName);
          it.setReturnType(_newTypeReference_1);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return new Specification<");
              String _simpleName = specClassName.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append(">() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("public ");
              _builder.append(Predicate.class, "\t");
              _builder.append(" toPredicate(");
              _builder.append(Root.class, "\t");
              _builder.append("<");
              String _simpleName_1 = specClassName.getSimpleName();
              _builder.append(_simpleName_1, "\t");
              _builder.append("> root, ");
              _builder.append(CriteriaQuery.class, "\t");
              _builder.append("<?> query, ");
              _builder.append(CriteriaBuilder.class, "\t");
              _builder.append(" cb) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("return cb.equal(root.get(");
              _builder.append(customerSuperName, "\t\t");
              _builder.append(".");
              _builder.append(fieldName, "\t\t");
              _builder.append("), value);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("};");
              _builder.newLine();
            }
          };
          it.setBody(_client);
          context.setPrimarySourceElement(it, cls);
        }
      };
      cls.addMethod((fieldName + "Equals"), _function);
      final Procedure1<MutableMethodDeclaration> _function_1 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setStatic(true);
          it.setFinal(true);
          TypeReference _newTypeReference = context.newTypeReference(fieldType);
          it.addParameter("value", _newTypeReference);
          TypeReference _newTypeReference_1 = context.newTypeReference(Specification.class, specClassName);
          it.setReturnType(_newTypeReference_1);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return new Specification<");
              String _simpleName = specClassName.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append(">() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("public ");
              _builder.append(Predicate.class, "\t");
              _builder.append(" toPredicate(");
              _builder.append(Root.class, "\t");
              _builder.append("<");
              String _simpleName_1 = specClassName.getSimpleName();
              _builder.append(_simpleName_1, "\t");
              _builder.append("> root, ");
              _builder.append(CriteriaQuery.class, "\t");
              _builder.append("<?> query, ");
              _builder.append(CriteriaBuilder.class, "\t");
              _builder.append(" cb) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("return cb.like(root.get(");
              _builder.append(customerSuperName, "\t\t");
              _builder.append(".");
              _builder.append(fieldName, "\t\t");
              _builder.append("), value);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("};");
              _builder.newLine();
            }
          };
          it.setBody(_client);
          context.setPrimarySourceElement(it, cls);
        }
      };
      cls.addMethod((fieldName + "Like"), _function_1);
      final Procedure1<MutableMethodDeclaration> _function_2 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setStatic(true);
          it.setFinal(true);
          TypeReference _newTypeReference = context.newTypeReference(fieldType);
          it.addParameter("value", _newTypeReference);
          TypeReference _newTypeReference_1 = context.newTypeReference(Specification.class, specClassName);
          it.setReturnType(_newTypeReference_1);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return new Specification<");
              String _simpleName = specClassName.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append(">() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("public ");
              _builder.append(Predicate.class, "\t");
              _builder.append(" toPredicate(");
              _builder.append(Root.class, "\t");
              _builder.append("<");
              String _simpleName_1 = specClassName.getSimpleName();
              _builder.append(_simpleName_1, "\t");
              _builder.append("> root, ");
              _builder.append(CriteriaQuery.class, "\t");
              _builder.append("<?> query, ");
              _builder.append(CriteriaBuilder.class, "\t");
              _builder.append(" cb) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("return cb.isNull(root.get(");
              _builder.append(customerSuperName, "\t\t");
              _builder.append(".");
              _builder.append(fieldName, "\t\t");
              _builder.append("));");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("};");
              _builder.newLine();
            }
          };
          it.setBody(_client);
          context.setPrimarySourceElement(it, cls);
        }
      };
      cls.addMethod((fieldName + "IsNull"), _function_2);
      final Procedure1<MutableMethodDeclaration> _function_3 = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setStatic(true);
          it.setFinal(true);
          TypeReference _newTypeReference = context.newTypeReference(fieldType);
          it.addParameter("value", _newTypeReference);
          TypeReference _newTypeReference_1 = context.newTypeReference(Specification.class, specClassName);
          it.setReturnType(_newTypeReference_1);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return new Specification<");
              String _simpleName = specClassName.getSimpleName();
              _builder.append(_simpleName, "");
              _builder.append(">() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("public ");
              _builder.append(Predicate.class, "\t");
              _builder.append(" toPredicate(");
              _builder.append(Root.class, "\t");
              _builder.append("<");
              String _simpleName_1 = specClassName.getSimpleName();
              _builder.append(_simpleName_1, "\t");
              _builder.append("> root, ");
              _builder.append(CriteriaQuery.class, "\t");
              _builder.append("<?> query, ");
              _builder.append(CriteriaBuilder.class, "\t");
              _builder.append(" cb) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("return cb.notLike(root.get(");
              _builder.append(customerSuperName, "\t\t");
              _builder.append(".");
              _builder.append(fieldName, "\t\t");
              _builder.append("), value);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("};");
              _builder.newLine();
            }
          };
          it.setBody(_client);
          context.setPrimarySourceElement(it, cls);
        }
      };
      _xblockexpression = cls.addMethod((fieldName + "NotLike"), _function_3);
    }
    return _xblockexpression;
  }
}
