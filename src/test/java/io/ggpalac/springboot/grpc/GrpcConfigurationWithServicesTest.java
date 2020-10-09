package io.ggpalac.springboot.grpc;

import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class GrpcConfigurationWithServicesTest extends GrpcConfigurationBaseTest {

    private static final String SERVICE_DEFINITION_NAME = "GrpcServiceExample";

    @Test
    public void shouldHaveAGrpcServerStarted() {
        shouldHaveAGrpcServerStartedAt(9090);
    }

    @Test
    public void shouldHaveServicesAddedInGrpcServer() {
        Assertions.assertEquals(1, grpcServer.getServices().size());
        Assertions.assertTrue(grpcServer.getService(SERVICE_DEFINITION_NAME).isPresent());
    }

    @Configuration
    @EnableGrpcServer
    @ComponentScan
    public static class TestConfiguration {
    }

    @GrpcService
    public static class GrpcServiceExample implements BindableService {

        @Override
        public ServerServiceDefinition bindService() {
            return ServerServiceDefinition.builder(SERVICE_DEFINITION_NAME).build();
        }
    }
}
