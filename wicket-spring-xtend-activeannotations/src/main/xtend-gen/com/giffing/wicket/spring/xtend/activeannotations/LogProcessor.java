package com.giffing.wicket.spring.xtend.activeannotations;

import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.CodeGenerationContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationUnit;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.file.Path;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class LogProcessor extends AbstractClassProcessor {
  @Extension
  private CodeGenerationContext _codeGenerationContext;
  
  public void doTransform(final MutableClassDeclaration cls, @Extension final TransformationContext context) {
    final Procedure1<MutableFieldDeclaration> _function = new Procedure1<MutableFieldDeclaration>() {
      public void apply(final MutableFieldDeclaration it) {
        it.setStatic(true);
        it.setFinal(true);
        TypeReference _newTypeReference = context.newTypeReference(Logger.class);
        it.setType(_newTypeReference);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append(LoggerFactory.class, "");
            _builder.append(".getLogger(\"");
            String _simpleName = cls.getSimpleName();
            _builder.append(_simpleName, "");
            _builder.append("\")");
            _builder.newLineIfNotEmpty();
          }
        };
        it.setInitializer(_client);
        context.setPrimarySourceElement(it, cls);
      }
    };
    cls.addField("log", _function);
    CompilationUnit _compilationUnit = cls.getCompilationUnit();
    final Path filePath = _compilationUnit.getFilePath();
    Path _targetFolder = context.getTargetFolder(filePath);
    String _qualifiedName = cls.getQualifiedName();
    String _replace = _qualifiedName.replace(".", "/");
    String _plus = (_replace + ".properties");
    final Path file = _targetFolder.append(_plus);
    String _string = file.toString();
    context.addWarning(cls, _string);
    this._codeGenerationContext.setContents(file, "");
  }
}
