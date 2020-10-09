package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GrpcConfiguration.class)
public class GrpcConfigurationTest extends GrpcConfigurationBaseTest {

    @Test
    public void shouldHaveAGrpcServerStarted() {
        shouldHaveAGrpcServerStartedAt(9090);
    }
}
