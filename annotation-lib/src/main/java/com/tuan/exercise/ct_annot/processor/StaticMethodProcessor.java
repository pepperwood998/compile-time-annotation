package com.tuan.exercise.ct_annot.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes("com.tuan.exercise.ct_annot.annotation.StaticMethod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class StaticMethodProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        messager = env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv) {

        for (TypeElement annot : annotations) {

            Set<? extends Element> annotatedElems = roundEnv
                    .getElementsAnnotatedWith(annot);

            for (Element elem : annotatedElems) {
                if (elem.getKind() != ElementKind.METHOD) {
                    messager.printMessage(Kind.ERROR,
                            "@StaticMethod only be used for method", elem);
                } else {
                    ExecutableElement method = (ExecutableElement) elem;
                    Set<Modifier> modifiers = method.getModifiers();

                    if (!modifiers.contains(Modifier.STATIC)) {
                        messager.printMessage(Kind.ERROR,
                                "@StaticMethod only be applied to static method",
                                elem);
                    }
                }
            }
        }

        return true;
    }

}
