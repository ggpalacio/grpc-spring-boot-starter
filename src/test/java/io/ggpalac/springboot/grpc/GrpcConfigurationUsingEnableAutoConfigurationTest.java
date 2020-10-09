package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GrpcConfigurationUsingEnableAutoConfigurationTest.TestConfiguration.class)
public class GrpcConfigurationUsingEnableAutoConfigurationTest extends GrpcConfigurationBaseTest {

    @Test
    public void shouldHaveAGrpcServerStarted() {
        shouldHaveAGrpcServerStartedAt(9090);
    }

    @Configuration
    @EnableAutoConfiguration
    public static class TestConfiguration {
    }
}
