package com.grpc.trading.platform.aggregator.service;

import com.grpc.trading.platform.model.user.UserInformation;
import com.grpc.trading.platform.model.user.UserInformationRequest;
import com.grpc.trading.platform.model.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userClient;

    public UserInformation getUserInformation(int userId) {
        var request = UserInformationRequest.newBuilder()
                                            .setUserId(userId)
                                            .build();
        return this.userClient.getUserInformation(request);
    }

}