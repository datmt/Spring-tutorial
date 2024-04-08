package com.datmt.spring.grpcstore;

import com.datmt.spring.OrderOuterClass;
import com.datmt.spring.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void saveOrder(OrderOuterClass.Order request, StreamObserver<OrderOuterClass.OrderResponse> responseObserver) {
        var response = OrderOuterClass.OrderResponse.newBuilder()
                .setOrder(request)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
