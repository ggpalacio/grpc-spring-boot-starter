package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;

public class GrpcServiceProcessorTest {

    @Test
    public void shouldCompileError() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String file = getClass().getResource("/GrpcServiceWrongExample.java").getFile();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, baos, file);
        Assertions.assertTrue(result != 0);
        Assertions.assertEquals("error: GRPC service class 'GrpcServiceWrongExample' must implements 'io.grpc.BindableService'\n" +
                "1 error\n", new String(baos.toByteArray()));
    }

    @Test
    public void shouldCompileSuccess() {
        String file = getClass().getResource("/GrpcServiceExample.java").getFile();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, file);
        Assertions.assertTrue(result == 0);
    }
}
