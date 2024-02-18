package com.grpc.example.intro.advancedfeatures;

import com.grpc.example.intro.advancedfeatures.interceptors.GzipRequestInterceptor;
import com.grpc.example.intro.models.advancedfeatures.BalanceCheckRequest;
import io.grpc.ClientInterceptor;
import org.junit.jupiter.api.Test;

import java.util.List;

/*
    It is a class to demo enabling gzip compression via interceptor
    Update logback xml with DEBUG mode
 */
public class GzipInterceptorTest extends AbstractInterceptorTest {

    @Override
    protected List<ClientInterceptor> getClientInterceptors() {
        return List.of(new GzipRequestInterceptor());
    }

    @Test
    public void gzipDemo() {
        var request = BalanceCheckRequest.newBuilder()
                                         .setAccountNumber(1)
                                         .build();
        var response = this.bankBlockingStub.getAccountBalance(request);
    }

}