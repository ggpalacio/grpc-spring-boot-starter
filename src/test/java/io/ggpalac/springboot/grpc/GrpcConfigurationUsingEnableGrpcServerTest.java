package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GrpcConfigurationUsingEnableGrpcServerTest.TestConfiguration.class)
public class GrpcConfigurationUsingEnableGrpcServerTest extends GrpcConfigurationBaseTest {

    @Test
    public void shouldHaveAGrpcServerStarted() {
        shouldHaveAGrpcServerStartedAt(9090);
    }

    @Configuration
    @EnableGrpcServer
    public static class TestConfiguration {
    }
}
