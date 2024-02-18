package com.grpc.example.intro.rpctypes;

import com.grpc.example.intro.models.rpctypes.TransferRequest;
import com.grpc.example.intro.models.rpctypes.TransferResponse;
import com.grpc.example.intro.models.rpctypes.TransferServiceGrpc;
import com.grpc.example.intro.rpctypes.requesthandlers.TransferRequestHandler;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferRequestHandler(responseObserver);
    }

}