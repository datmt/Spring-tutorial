package com.datmt.spring.grpcstore;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class StoreServer {
    public static void main(String[] args) throws InterruptedException, IOException {

        Server server = ServerBuilder.forPort(8080)
                .addService(new OrderServiceImpl())
                .build();

        server.start();
        server.awaitTermination();
    }
}
