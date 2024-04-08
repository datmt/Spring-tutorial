package com.datmt.spring.grpcstore;

import com.datmt.spring.OrderOuterClass;
import com.datmt.spring.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class StoreClient {
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        OrderServiceGrpc.OrderServiceBlockingStub stub
                = OrderServiceGrpc.newBlockingStub(channel);


        var order = OrderOuterClass.Order.newBuilder()
                .setId(1L)
                .setProduct("Macbook Pro")
                .setQuantity(2)
                .build();


        var orderRequest = OrderOuterClass.OrderRequest.newBuilder()
                .setId(1L)
                .setOrder(order)
                .build();

        OrderOuterClass.OrderResponse response = stub.saveOrder(order);

        System.out.println(response);

        channel.shutdown();
    }
}
