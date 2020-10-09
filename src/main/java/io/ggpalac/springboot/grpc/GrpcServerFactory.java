package io.ggpalac.springboot.grpc;

import io.grpc.BindableService;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.net.InetSocketAddress;
import java.util.Arrays;

public class GrpcServerFactory {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServerFactory.class);

    private final ApplicationContext applicationContext;
    private final GrpcProperties properties;

    public GrpcServerFactory(ApplicationContext applicationContext, GrpcProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    private void addServices(ServerBuilder builder) {
        String[] serviceNames = applicationContext.getBeanNamesForAnnotation(GrpcService.class);
        if (serviceNames.length == 0) {
            logger.warn("No GRPC service was registered");
            return;
        }

        Arrays.stream(serviceNames)
                .map(applicationContext::getBean)
                .map(BindableService.class::cast)
                .forEach(service -> addService(builder, service));
    }

    private void addService(ServerBuilder builder, BindableService service) {
        builder.addService(service);
        logger.info("Registering GRPC service: {}", service.getClass().getSimpleName());
    }

    public GrpcServer createServer() {
        Integer port = properties.getServer().getPort();
        if (port <= 0) {
            port = new InetSocketAddress(0).getPort();
        }

        ServerBuilder builder = ServerBuilder.forPort(port);
        addServices(builder);
        return new GrpcServer(builder.build(), properties.getServer().getAutoStart());
    }
}
