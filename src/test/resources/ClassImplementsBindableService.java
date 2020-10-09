import io.ggpalac.springboot.grpc.GrpcService;
import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;

@GrpcService
public class ClassImplementsBindableService implements BindableService {

    @Override
    public ServerServiceDefinition bindService() {
        return null;
    }
}
