package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.CompareType;
import com.giffing.wicket.spring.xtend.activeannotations.Specs;
import com.google.common.base.Objects;
import java.util.function.Consumer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtend.lib.annotations.Data;
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
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("all")
public class SpecsProcessor extends AbstractClassProcessor {
  @Accessors
  public static class MethodConfig implements Cloneable {
    private CompareType compareType;
    
    private String fieldName;
    
    private Type genericClassName;
    
    private Type metaModelName;
    
    private SpecsProcessor.MethodParam param;
    
    private TypeReference returnType;
    
    private boolean isStatic = true;
    
    private boolean isFinal = true;
    
    public Object clone() throws CloneNotSupportedException {
      return super.clone();
    }
    
    @Pure
    public CompareType getCompareType() {
      return this.compareType;
    }
    
    public void setCompareType(final CompareType compareType) {
      this.compareType = compareType;
    }
    
    @Pure
    public String getFieldName() {
      return this.fieldName;
    }
    
    public void setFieldName(final String fieldName) {
      this.fieldName = fieldName;
    }
    
    @Pure
    public Type getGenericClassName() {
      return this.genericClassName;
    }
    
    public void setGenericClassName(final Type genericClassName) {
      this.genericClassName = genericClassName;
    }
    
    @Pure
    public Type getMetaModelName() {
      return this.metaModelName;
    }
    
    public void setMetaModelName(final Type metaModelName) {
      this.metaModelName = metaModelName;
    }
    
    @Pure
    public SpecsProcessor.MethodParam getParam() {
      return this.param;
    }
    
    public void setParam(final SpecsProcessor.MethodParam param) {
      this.param = param;
    }
    
    @Pure
    public TypeReference getReturnType() {
      return this.returnType;
    }
    
    public void setReturnType(final TypeReference returnType) {
      this.returnType = returnType;
    }
    
    @Pure
    public boolean isStatic() {
      return this.isStatic;
    }
    
    public void setIsStatic(final boolean isStatic) {
      this.isStatic = isStatic;
    }
    
    @Pure
    public boolean isFinal() {
      return this.isFinal;
    }
    
    public void setIsFinal(final boolean isFinal) {
      this.isFinal = isFinal;
    }
  }
  
  @Accessors
  @Data
  public static class MethodParam implements Cloneable {
    private final String name;
    
    private final TypeReference type;
    
    public MethodParam(final String name, final TypeReference type) {
      super();
      this.name = name;
      this.type = type;
    }
    
    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.name== null) ? 0 : this.name.hashCode());
      result = prime * result + ((this.type== null) ? 0 : this.type.hashCode());
      return result;
    }
    
    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      SpecsProcessor.MethodParam other = (SpecsProcessor.MethodParam) obj;
      if (this.name == null) {
        if (other.name != null)
          return false;
      } else if (!this.name.equals(other.name))
        return false;
      if (this.type == null) {
        if (other.type != null)
          return false;
      } else if (!this.type.equals(other.type))
        return false;
      return true;
    }
    
    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("name", this.name);
      b.add("type", this.type);
      return b.toString();
    }
    
    @Pure
    public String getName() {
      return this.name;
    }
    
    @Pure
    public TypeReference getType() {
      return this.type;
    }
  }
  
  @Extension
  private RegisterGlobalsContext globalContext;
  
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    this.globalContext = context;
  }
  
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    Type _findTypeGlobally = context.findTypeGlobally(Specs.class);
    final AnnotationReference specAnnoation = cls.findAnnotation(_findTypeGlobally);
    final TypeReference specClassName = specAnnoation.getClassValue("value");
    final AnnotationReference[] joinArray = specAnnoation.getAnnotationArrayValue("joins");
    final Type collectionClass = context.findTypeGlobally(Iterable.class);
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
              TypeReference _resolvedType = it.getResolvedType();
              Type _type = _resolvedType.getType();
              boolean _isAssignableFrom = collectionClass.isAssignableFrom(_type);
              boolean _not = (!_isAssignableFrom);
              if (_not) {
                ParameterDeclaration _declaration = it.getDeclaration();
                final String fieldName = _declaration.getSimpleName();
                ParameterDeclaration _declaration_1 = it.getDeclaration();
                TypeReference _type_1 = _declaration_1.getType();
                String _name = _type_1.getName();
                final Type fieldType = context.findTypeGlobally(_name);
                String _name_1 = specClassName.getName();
                String _plus = (_name_1 + "_");
                final Type customerSuperName = context.findTypeGlobally(_plus);
                SpecsProcessor.MethodConfig _methodConfig = new SpecsProcessor.MethodConfig();
                final Procedure1<SpecsProcessor.MethodConfig> _function = new Procedure1<SpecsProcessor.MethodConfig>() {
                  public void apply(final SpecsProcessor.MethodConfig it) {
                    it.fieldName = fieldName;
                    Type _type = specClassName.getType();
                    it.metaModelName = _type;
                    it.genericClassName = customerSuperName;
                    TypeReference _newTypeReference = context.newTypeReference(fieldType);
                    SpecsProcessor.MethodParam _methodParam = new SpecsProcessor.MethodParam("value", _newTypeReference);
                    it.param = _methodParam;
                    TypeReference _newTypeReference_1 = context.newTypeReference(Specification.class, specClassName);
                    it.returnType = _newTypeReference_1;
                  }
                };
                final SpecsProcessor.MethodConfig config = ObjectExtensions.<SpecsProcessor.MethodConfig>operator_doubleArrow(_methodConfig, _function);
                ParameterDeclaration _declaration_2 = it.getDeclaration();
                TypeReference _type_2 = _declaration_2.getType();
                String _name_2 = _type_2.getName();
                boolean _matched = false;
                if (!_matched) {
                  if (Objects.equal(_name_2, "java.lang.String")) {
                    _matched=true;
                    SpecsProcessor.this.stringPredicate(cls, context, specClassName, config);
                  }
                }
                if (!_matched) {
                  if (Objects.equal(_name_2, "boolean")) {
                    _matched=true;
                    SpecsProcessor.this.booleanPredicate(cls, context, specClassName, config);
                  }
                }
                if (!_matched) {
                  if (Objects.equal(_name_2, "java.lang.Boolean")) {
                    _matched=true;
                    SpecsProcessor.this.booleanPredicate(cls, context, specClassName, config);
                  }
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
  
  public MutableMethodDeclaration booleanPredicate(final MutableClassDeclaration cls, @Extension final TransformationContext context, final TypeReference specClassName, final SpecsProcessor.MethodConfig config) {
    try {
      MutableMethodDeclaration _xblockexpression = null;
      {
        Object _clone = config.clone();
        SpecsProcessor.MethodConfig clone1 = ((SpecsProcessor.MethodConfig) _clone);
        clone1.compareType = CompareType.EQUALS;
        this.addSpecMethod(cls, context, clone1, false);
        Object _clone_1 = config.clone();
        SpecsProcessor.MethodConfig clone2 = ((SpecsProcessor.MethodConfig) _clone_1);
        clone2.compareType = CompareType.IS_TRUE;
        this.addSpecMethod(cls, context, clone2, false);
        Object _clone_2 = config.clone();
        SpecsProcessor.MethodConfig clone3 = ((SpecsProcessor.MethodConfig) _clone_2);
        clone3.compareType = CompareType.IS_FALSE;
        _xblockexpression = this.addSpecMethod(cls, context, clone3, false);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public MutableMethodDeclaration stringPredicate(final MutableClassDeclaration cls, @Extension final TransformationContext context, final TypeReference specClassName, final SpecsProcessor.MethodConfig config) {
    try {
      MutableMethodDeclaration _xblockexpression = null;
      {
        Object _clone = config.clone();
        SpecsProcessor.MethodConfig clone1 = ((SpecsProcessor.MethodConfig) _clone);
        clone1.compareType = CompareType.EQUALS;
        this.addSpecMethod(cls, context, clone1, false);
        this.addSpecMethod(cls, context, clone1, true);
        Object _clone_1 = config.clone();
        SpecsProcessor.MethodConfig clone2 = ((SpecsProcessor.MethodConfig) _clone_1);
        clone2.compareType = CompareType.LIKE;
        this.addSpecMethod(cls, context, clone2, false);
        this.addSpecMethod(cls, context, clone2, true);
        Object _clone_2 = config.clone();
        SpecsProcessor.MethodConfig clone3 = ((SpecsProcessor.MethodConfig) _clone_2);
        clone3.compareType = CompareType.NOT_LIKE;
        this.addSpecMethod(cls, context, clone3, false);
        this.addSpecMethod(cls, context, clone3, true);
        Object _clone_3 = config.clone();
        SpecsProcessor.MethodConfig clone4 = ((SpecsProcessor.MethodConfig) _clone_3);
        clone4.compareType = CompareType.IS_NULL;
        _xblockexpression = this.addSpecMethod(cls, context, clone4, false);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public MutableMethodDeclaration addSpecMethod(final MutableClassDeclaration cls, @Extension final TransformationContext context, final SpecsProcessor.MethodConfig config, final boolean ignoreCase) {
    MutableMethodDeclaration _xblockexpression = null;
    {
      String _displayName = config.compareType.getDisplayName();
      String methodName = (config.fieldName + _displayName);
      if (ignoreCase) {
        String _methodName = methodName;
        methodName = (_methodName + "IgnoreCase");
      }
      final Procedure1<MutableMethodDeclaration> _function = new Procedure1<MutableMethodDeclaration>() {
        public void apply(final MutableMethodDeclaration it) {
          it.setStatic(config.isStatic);
          it.setFinal(config.isFinal);
          boolean _and = false;
          boolean _notEquals = (!Objects.equal(config.param, null));
          if (!_notEquals) {
            _and = false;
          } else {
            Integer _parameterCount = config.compareType.getParameterCount();
            boolean _equals = ((_parameterCount).intValue() == 1);
            _and = _equals;
          }
          if (_and) {
            it.addParameter(config.param.name, config.param.type);
          }
          TypeReference _returnType = it.getReturnType();
          boolean _notEquals_1 = (!Objects.equal(_returnType, null));
          if (_notEquals_1) {
            it.setReturnType(config.returnType);
          }
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return new Specification<");
              _builder.append(config.metaModelName, "");
              _builder.append(">() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("public ");
              _builder.append(Predicate.class, "\t");
              _builder.append(" toPredicate(");
              _builder.append(Root.class, "\t");
              _builder.append("<");
              String _simpleName = config.metaModelName.getSimpleName();
              _builder.append(_simpleName, "\t");
              _builder.append("> root, ");
              _builder.append(CriteriaQuery.class, "\t");
              _builder.append("<?> query, ");
              _builder.append(CriteriaBuilder.class, "\t");
              _builder.append(" cb) {");
              _builder.newLineIfNotEmpty();
              {
                Integer _parameterCount = config.compareType.getParameterCount();
                boolean _equals = ((_parameterCount).intValue() == 1);
                if (_equals) {
                  {
                    if (ignoreCase) {
                      _builder.append("\t\t");
                      _builder.append("return cb.");
                      String _name = config.compareType.getName();
                      _builder.append(_name, "\t\t");
                      _builder.append("(cb.lower(root.get(");
                      String _simpleName_1 = config.genericClassName.getSimpleName();
                      _builder.append(_simpleName_1, "\t\t");
                      _builder.append(".");
                      _builder.append(config.fieldName, "\t\t");
                      _builder.append(")), value);");
                      _builder.newLineIfNotEmpty();
                    } else {
                      _builder.append("\t\t");
                      _builder.append("return cb.");
                      String _name_1 = config.compareType.getName();
                      _builder.append(_name_1, "\t\t");
                      _builder.append("(root.get(");
                      String _simpleName_2 = config.genericClassName.getSimpleName();
                      _builder.append(_simpleName_2, "\t\t");
                      _builder.append(".");
                      _builder.append(config.fieldName, "\t\t");
                      _builder.append("), value);");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                } else {
                  {
                    if (ignoreCase) {
                      _builder.append("\t\t");
                      _builder.append("return cb.");
                      String _name_2 = config.compareType.getName();
                      _builder.append(_name_2, "\t\t");
                      _builder.append("(cb.lower(root.get(");
                      _builder.append(config.genericClassName, "\t\t");
                      _builder.append(".");
                      _builder.append(config.fieldName, "\t\t");
                      _builder.append(")));");
                      _builder.newLineIfNotEmpty();
                    } else {
                      _builder.append("\t\t");
                      _builder.append("return cb.");
                      String _name_3 = config.compareType.getName();
                      _builder.append(_name_3, "\t\t");
                      _builder.append("(root.get(");
                      _builder.append(config.genericClassName, "\t\t");
                      _builder.append(".");
                      _builder.append(config.fieldName, "\t\t");
                      _builder.append("));");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
              _builder.append("\t\t");
              _builder.newLine();
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
      _xblockexpression = cls.addMethod(methodName, _function);
    }
    return _xblockexpression;
  }
}
