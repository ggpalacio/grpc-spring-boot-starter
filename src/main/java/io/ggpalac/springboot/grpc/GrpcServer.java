package io.ggpalac.springboot.grpc;

import io.grpc.Server;
import io.grpc.ServerServiceDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GrpcServer implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServerFactory.class);
    private static final String AWAIT_THREAD_NAME = "grpc-server";

    private Server server;
    private boolean autoStart;
    private boolean started;

    public GrpcServer(Server server, boolean autoStart) {
        this.server = server;
        this.autoStart = autoStart;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Boolean.TRUE.equals(autoStart)) {
            start();
        }
    }

    @Override
    public void destroy() throws Exception {
        shutdown();
    }

    public void start() {
        if (isStarted()) {
            throw new IllegalStateException("Already started");
        }

        try {
            server.start();
            started = true;
            startDaemonAwaitThread();
            logger.info("GRPC server started on port: {}", server.getPort());
        } catch (IOException e) {
            throw new GrpcServerException("Error starting GRPC server", e);
        }
    }

    private void startDaemonAwaitThread() {
        Thread awaitThread = new Thread(AWAIT_THREAD_NAME) {
            public void run() {
                try {
                    server.awaitTermination();
                } catch (InterruptedException e) {
                    throw new GrpcServerException("Error awaiting GRPC server termination", e);
                }
            }
        };
        awaitThread.setContextClassLoader(this.getClass().getClassLoader());
        awaitThread.setDaemon(false);
        awaitThread.start();
    }

    public boolean isStarted() {
        return started;
    }

    public int getPort() {
        return server.getPort();
    }

    public List<ServerServiceDefinition> getServices() {
        return server.getServices();
    }

    public Optional<ServerServiceDefinition> getService(String name) {
        return getServices().stream()
                .filter(service -> service.getServiceDescriptor().getName().equals(name))
                .findAny();
    }

    public void shutdown() {
        if (isShutdown()) {
            throw new IllegalStateException("Already shutdown");
        }
        server.shutdown();
    }

    public boolean isShutdown() {
        return server.isShutdown();
    }
}
