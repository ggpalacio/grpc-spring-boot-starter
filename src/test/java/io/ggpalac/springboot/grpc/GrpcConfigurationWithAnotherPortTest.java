package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = GrpcConfiguration.class)
@TestPropertySource(properties = "grpc.server.port=9191")
public class GrpcConfigurationWithAnotherPortTest extends GrpcConfigurationBaseTest {

    @Test
    public void shouldHaveAGrpcServerStarted() {
        shouldHaveAGrpcServerStartedAt(9191);
    }
}
