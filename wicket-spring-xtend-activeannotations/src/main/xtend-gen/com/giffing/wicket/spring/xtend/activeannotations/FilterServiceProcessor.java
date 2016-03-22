package com.giffing.wicket.spring.xtend.activeannotations;

import com.giffing.wicket.spring.xtend.activeannotations.FilterServiceGen;
import com.giffing.wicket.spring.xtend.activeannotations.domain.FilterService;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Collections;
import org.eclipse.xtend.lib.macro.AbstractInterfaceProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference;
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class FilterServiceProcessor extends AbstractInterfaceProcessor {
  public void doTransform(final MutableInterfaceDeclaration annotatedInterface, @Extension final TransformationContext context) {
    Type _findTypeGlobally = context.findTypeGlobally(FilterServiceGen.class);
    final AnnotationReference domainClass = annotatedInterface.findAnnotation(_findTypeGlobally);
    final TypeReference className = domainClass.getClassValue("value");
    TypeReference domainIntefaceType = context.newTypeReference(FilterService.class, className, className, className);
    boolean _notEquals = (!Objects.equal(domainIntefaceType, null));
    if (_notEquals) {
      Iterable<? extends TypeReference> _extendedInterfaces = annotatedInterface.getExtendedInterfaces();
      Iterable<TypeReference> _plus = Iterables.<TypeReference>concat(_extendedInterfaces, Collections.<TypeReference>unmodifiableList(CollectionLiterals.<TypeReference>newArrayList(domainIntefaceType)));
      annotatedInterface.setExtendedInterfaces(_plus);
    }
  }
}
