package GrpcServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SlaughterGrpcServer
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server =  ServerBuilder.forPort(1337).addService(new SlaughterGrpcServiceImplementation()).build();
        System.out.println("Server started");
        server.start();
        server.awaitTermination();
        System.out.println("Client connected");
    }

}
