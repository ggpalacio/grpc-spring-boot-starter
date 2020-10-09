package io.ggpalac.springboot.grpc;

import io.grpc.BindableService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("io.ggpalac.springboot.grpc.GrpcService")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GrpcServiceProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element grpcService : roundEnv.getElementsAnnotatedWith(GrpcService.class)) {
            if (!isElementTypeOf(grpcService, BindableService.class)) {
                printMessage(Diagnostic.Kind.ERROR,
                        String.format("GRPC service class '%s' must implements '%s'", grpcService, BindableService.class.getName()));
            }
        }
        return true;
    }

    private boolean isElementTypeOf(Element element, Class<?> type) {
        TypeMirror typeMirror = processingEnv.getElementUtils().getTypeElement(type.getName()).asType();
        return processingEnv.getTypeUtils().isAssignable(element.asType(), typeMirror);
    }

    private void printMessage(Diagnostic.Kind king, String message) {
        processingEnv.getMessager().printMessage(king, message);
    }
}
