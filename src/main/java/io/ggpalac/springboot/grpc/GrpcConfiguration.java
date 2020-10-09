package io.ggpalac.springboot.grpc;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GrpcProperties.class)
public class GrpcConfiguration {

    @Bean
    public GrpcServerFactory grpcServerFactory(ApplicationContext applicationContext, GrpcProperties properties) {
        return new GrpcServerFactory(applicationContext, properties);
    }

    @Bean
    public GrpcServer grpcServer(GrpcServerFactory factory) {
        return factory.createServer();
    }
}
