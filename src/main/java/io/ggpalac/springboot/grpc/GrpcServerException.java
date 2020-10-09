package io.ggpalac.springboot.grpc;

public class GrpcServerException extends RuntimeException {

    public GrpcServerException(String message, Throwable cause) {
        super(cause);
    }
}
