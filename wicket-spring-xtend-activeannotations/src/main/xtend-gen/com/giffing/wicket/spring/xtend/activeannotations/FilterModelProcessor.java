package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.domain.DefaultFilter;
import com.giffing.wicket.spring.xtend.activeannotations.domain.Sort;
import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.CodeGenerationContext;
import org.eclipse.xtend.lib.macro.CodeGenerationParticipant;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.CompilationUnit;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.file.Path;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class FilterModelProcessor extends AbstractClassProcessor implements CodeGenerationParticipant<ClassDeclaration> {
  public void doGenerateCode(final List<? extends ClassDeclaration> annotatedSourceElements, @Extension final CodeGenerationContext context) {
    for (final ClassDeclaration clazz : annotatedSourceElements) {
      {
        CompilationUnit _compilationUnit = clazz.getCompilationUnit();
        final Path filePath = _compilationUnit.getFilePath();
        Path _targetFolder = context.getTargetFolder(filePath);
        String _qualifiedName = clazz.getQualifiedName();
        String _replace = _qualifiedName.replace(".", "/");
        String _plus = (_replace + "Filter.java");
        final Path filterFile = _targetFolder.append(_plus);
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("package ");
        CompilationUnit _compilationUnit_1 = clazz.getCompilationUnit();
        String _packageName = _compilationUnit_1.getPackageName();
        _builder.append(_packageName, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public class ");
        String _simpleName = clazz.getSimpleName();
        _builder.append(_simpleName, "");
        _builder.append("Filter extends ");
        String _canonicalName = DefaultFilter.class.getCanonicalName();
        _builder.append(_canonicalName, "");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        {
          Iterable<? extends FieldDeclaration> _declaredFields = clazz.getDeclaredFields();
          for(final FieldDeclaration field : _declaredFields) {
            _builder.append("\t");
            CharSequence _generateVariable = this.generateVariable(field);
            _builder.append(_generateVariable, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        context.setContents(filterFile, _builder);
        Path _targetFolder_1 = context.getTargetFolder(filePath);
        String _qualifiedName_1 = clazz.getQualifiedName();
        String _replace_1 = _qualifiedName_1.replace(".", "/");
        String _plus_1 = (_replace_1 + "Sort.java");
        final Path sortFile = _targetFolder_1.append(_plus_1);
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("package ");
        CompilationUnit _compilationUnit_2 = clazz.getCompilationUnit();
        String _packageName_1 = _compilationUnit_2.getPackageName();
        _builder_1.append(_packageName_1, "");
        _builder_1.append(";");
        _builder_1.newLineIfNotEmpty();
        _builder_1.newLine();
        _builder_1.append("public enum ");
        String _simpleName_1 = clazz.getSimpleName();
        _builder_1.append(_simpleName_1, "");
        _builder_1.append("Sort implements ");
        String _canonicalName_1 = Sort.class.getCanonicalName();
        _builder_1.append(_canonicalName_1, "");
        _builder_1.append(" {");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t");
        _builder_1.newLine();
        {
          Iterable<? extends FieldDeclaration> _declaredFields_1 = clazz.getDeclaredFields();
          boolean _hasElements = false;
          for(final FieldDeclaration field_1 : _declaredFields_1) {
            if (!_hasElements) {
              _hasElements = true;
              _builder_1.append("", "\t");
            } else {
              _builder_1.appendImmediate(",", "\t");
            }
            _builder_1.append("\t");
            String _simpleName_2 = field_1.getSimpleName();
            String _upperCase = _simpleName_2.toUpperCase();
            _builder_1.append(_upperCase, "\t");
            _builder_1.append("(\"");
            String _simpleName_3 = field_1.getSimpleName();
            _builder_1.append(_simpleName_3, "\t");
            _builder_1.append("\")");
            _builder_1.newLineIfNotEmpty();
          }
          if (_hasElements) {
            _builder_1.append(";", "\t");
          }
        }
        _builder_1.append("\t");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("private String sortName;");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.newLine();
        _builder_1.append("\t");
        String _simpleName_4 = clazz.getSimpleName();
        _builder_1.append(_simpleName_4, "\t");
        _builder_1.append("Sort(String sortName){");
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("\t\t");
        _builder_1.append("this.sortName = sortName;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("@Override");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("public String getFieldName() {");
        _builder_1.newLine();
        _builder_1.append("\t\t");
        _builder_1.append("return this.sortName;");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.newLine();
        _builder_1.append("\t");
        _builder_1.newLine();
        _builder_1.append("}");
        _builder_1.newLine();
        _builder_1.newLine();
        context.setContents(sortFile, _builder_1);
      }
    }
  }
  
  public CharSequence generateVariable(final FieldDeclaration field) {
    CharSequence _switchResult = null;
    TypeReference _type = field.getType();
    String _name = _type.getName();
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_name, "java.lang.Integer")) {
        _matched=true;
        _switchResult = this.generateVariableNumber(field);
      }
    }
    if (!_matched) {
      if (Objects.equal(_name, "java.lang.Long")) {
        _matched=true;
        _switchResult = this.generateVariableNumber(field);
      }
    }
    if (!_matched) {
      if (Objects.equal(_name, "java.lang.String")) {
        _matched=true;
        _switchResult = this.generateVariableString(field);
      }
    }
    if (!_matched) {
      TypeReference _type_1 = field.getType();
      _switchResult = this.generateVariableDefault(field, _type_1);
    }
    return _switchResult;
  }
  
  public CharSequence generateVariableNumber(final FieldDeclaration field) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("private ");
    TypeReference _type = field.getType();
    Object _genericType = this.genericType(_type);
    _builder.append(_genericType, "");
    _builder.append(" ");
    String _simpleName = field.getSimpleName();
    _builder.append(_simpleName, "");
    _builder.append("Equals;");
    _builder.newLineIfNotEmpty();
    _builder.append("private ");
    TypeReference _type_1 = field.getType();
    Object _genericType_1 = this.genericType(_type_1);
    _builder.append(_genericType_1, "");
    _builder.append(" ");
    String _simpleName_1 = field.getSimpleName();
    _builder.append(_simpleName_1, "");
    _builder.append("Greater;");
    _builder.newLineIfNotEmpty();
    _builder.append("private ");
    TypeReference _type_2 = field.getType();
    Object _genericType_2 = this.genericType(_type_2);
    _builder.append(_genericType_2, "");
    _builder.append(" ");
    String _simpleName_2 = field.getSimpleName();
    _builder.append(_simpleName_2, "");
    _builder.append("GreaterEqual;");
    _builder.newLineIfNotEmpty();
    _builder.append("private ");
    TypeReference _type_3 = field.getType();
    Object _genericType_3 = this.genericType(_type_3);
    _builder.append(_genericType_3, "");
    _builder.append(" ");
    String _simpleName_3 = field.getSimpleName();
    _builder.append(_simpleName_3, "");
    _builder.append("Lesser;");
    _builder.newLineIfNotEmpty();
    _builder.append("private ");
    TypeReference _type_4 = field.getType();
    Object _genericType_4 = this.genericType(_type_4);
    _builder.append(_genericType_4, "");
    _builder.append(" ");
    String _simpleName_4 = field.getSimpleName();
    _builder.append(_simpleName_4, "");
    _builder.append("LesserEqual;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateVariableString(final FieldDeclaration field) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("private ");
    TypeReference _type = field.getType();
    Object _genericType = this.genericType(_type);
    _builder.append(_genericType, "");
    _builder.append(" ");
    String _simpleName = field.getSimpleName();
    _builder.append(_simpleName, "");
    _builder.append("Like;");
    _builder.newLineIfNotEmpty();
    _builder.append("private ");
    TypeReference _type_1 = field.getType();
    Object _genericType_1 = this.genericType(_type_1);
    _builder.append(_genericType_1, "");
    _builder.append(" ");
    String _simpleName_1 = field.getSimpleName();
    _builder.append(_simpleName_1, "");
    _builder.append("NotLike;");
    _builder.newLineIfNotEmpty();
    _builder.append("private ");
    TypeReference _type_2 = field.getType();
    Object _genericType_2 = this.genericType(_type_2);
    _builder.append(_genericType_2, "");
    _builder.append(" ");
    String _simpleName_2 = field.getSimpleName();
    _builder.append(_simpleName_2, "");
    _builder.append("Equals;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateVariableDefault(final FieldDeclaration field, final Object type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private ");
    TypeReference _type = field.getType();
    Object _genericType = this.genericType(_type);
    _builder.append(_genericType, "");
    _builder.append(" ");
    String _simpleName = field.getSimpleName();
    _builder.append(_simpleName, "");
    _builder.append(";");
    return _builder;
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
