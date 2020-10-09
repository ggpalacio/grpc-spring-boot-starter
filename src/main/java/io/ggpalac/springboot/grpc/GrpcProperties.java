package io.ggpalac.springboot.grpc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "grpc")
public class GrpcProperties {

    @NestedConfigurationProperty
    private Server server = new Server();

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public static class Server {
        private Integer port = 9090;
        private Boolean autoStart = true;

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public Boolean getAutoStart() {
            return autoStart;
        }

        public void setAutoStart(Boolean autoStart) {
            this.autoStart = autoStart;
        }
    }
}
