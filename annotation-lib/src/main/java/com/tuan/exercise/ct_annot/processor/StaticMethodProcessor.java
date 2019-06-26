package com.tuan.exercise.ct_annot.processor;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

public class StaticMethodProcessor extends AbstractProcessor {

	private Messager messager;
	
	private String methodNameTemplate = "^static.+$";

	@Override
	public synchronized void init(ProcessingEnvironment env) {
		messager = env.getMessager();
	}

	// "annotations" are all the annotation specified in
	// "getSupportedAnnotationTypes()"
	// "roundEnv" is the information about the current processing round
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		for (TypeElement annot : annotations) {

			Set<? extends Element> annotatedElems = roundEnv.getElementsAnnotatedWith(annot);

			for (Element elem : annotatedElems) {
				if (elem.getKind() != ElementKind.METHOD) {
					messager.printMessage(Kind.ERROR, "@StaticMethod only be used for method", elem);
				} else {
					ExecutableElement method = (ExecutableElement) elem;
					
					Name name = method.getSimpleName();
					Pattern pattern = Pattern.compile(methodNameTemplate);
					Matcher matcher = pattern.matcher(name.toString());
					if (!matcher.matches())
					{
						messager.printMessage(Kind.ERROR, "@StaticMethod requires method name to start with \"static\"", elem);
					}
					
					Set<Modifier> modifiers = method.getModifiers();
					if (!modifiers.contains(Modifier.STATIC)) {
						messager.printMessage(Kind.ERROR, "@StaticMethod only be applied to static method", elem);
					}
				}
			}
		}

		return true;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {

		TreeSet<String> supportedAnnotationTypes = new TreeSet<String>();

		supportedAnnotationTypes.add("com.tuan.exercise.ct_annot.annotation.StaticMethod");

		return supportedAnnotationTypes;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.RELEASE_8;
	}
}
