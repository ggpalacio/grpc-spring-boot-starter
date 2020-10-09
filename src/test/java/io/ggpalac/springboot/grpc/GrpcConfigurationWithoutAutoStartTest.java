package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = GrpcConfiguration.class)
@TestPropertySource(properties = "grpc.server.auto-start=false")
public class GrpcConfigurationWithoutAutoStartTest extends GrpcConfigurationBaseTest {

    @Test
    public void shouldHaveAGrpcServerNotStarted() {
        Assertions.assertNotNull(grpcServer);
        Assertions.assertFalse(grpcServer.isStarted());
    }
}
