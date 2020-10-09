package io.ggpalac.springboot.grpc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class GrpcConfigurationBaseTest {

    @Autowired
    protected GrpcServer grpcServer;

    protected void shouldHaveAGrpcServerStartedAt(int port) {
        Assertions.assertNotNull(grpcServer);
        Assertions.assertTrue(grpcServer.isStarted());
        Assertions.assertFalse(grpcServer.isShutdown());
        Assertions.assertEquals(port, grpcServer.getPort());
    }
}
