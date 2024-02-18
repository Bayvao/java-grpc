package com.grpc.example.intro.advancedfeatures;

import com.grpc.example.intro.advancedfeatures.interceptors.ApiKeyValidationInterceptor;
import com.grpc.example.intro.common.GrpcServer;
import com.grpc.example.intro.models.advancedfeatures.BalanceCheckRequest;
import io.grpc.ClientInterceptor;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/*
    It is a class to demo how to attach metadata for every request via interceptor
 */
public class ClientApiKeyInterceptorTest extends AbstractInterceptorTest {

    private static final Logger log = LoggerFactory.getLogger(ClientApiKeyInterceptorTest.class);

    @Override
    protected List<ClientInterceptor> getClientInterceptors() {
        return List.of(
                MetadataUtils.newAttachHeadersInterceptor(getApiKey())
        );
    }

    @Override
    protected GrpcServer createServer() {
        return GrpcServer.create(6565, builder -> {
            builder.addService(new BankService())
                   .intercept(new ApiKeyValidationInterceptor());
        });
    }

    @Test
    public void clientApiKeyDemo(){
        var request = BalanceCheckRequest.newBuilder()
                                         .setAccountNumber(1)
                                         .build();
        var response = this.bankBlockingStub.getAccountBalance(request);
        log.info("{}", response);
    }

    private Metadata getApiKey(){
       var metadata = new Metadata();
       metadata.put(Constants.API_KEY, "bank-client-secret");
       return metadata;
    }

}