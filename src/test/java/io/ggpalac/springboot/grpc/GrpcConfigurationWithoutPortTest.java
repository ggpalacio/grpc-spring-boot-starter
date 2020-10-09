package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = GrpcConfiguration.class)
@TestPropertySource(properties = "grpc.server.port=0")
public class GrpcConfigurationWithoutPortTest extends GrpcConfigurationBaseTest {

    @Test
    public void shouldHaveAGrpcServerStartedAtCustomPort() {
        Assertions.assertNotNull(grpcServer);
        Assertions.assertTrue(grpcServer.isStarted());
        Assertions.assertTrue(grpcServer.getPort() > 0);
    }
}
