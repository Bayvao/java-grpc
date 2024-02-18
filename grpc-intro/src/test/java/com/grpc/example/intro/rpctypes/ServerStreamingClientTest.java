package com.grpc.example.intro.rpctypes;

import com.grpc.example.intro.common.ResponseObserver;
import com.grpc.example.intro.models.rpctypes.Money;
import com.grpc.example.intro.models.rpctypes.WithdrawRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStreamingClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(ServerStreamingClientTest.class);

    @Test
    public void blockingClientWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                .setAccountNumber(2)
                .setAmount(20)
                .build();
        var iterator = this.bankBlockingStub.withdraw(request);
        int count = 0;
        while (iterator.hasNext()){
            log.info("received money: {}", iterator.next());
            count++;
        }
        Assertions.assertEquals(2, count);
    }

    @Test
    public void asyncClientWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                                     .setAccountNumber(2)
                                     .setAmount(20)
                                     .build();
        var observer = ResponseObserver.<Money>create();
        this.bankStub.withdraw(request, observer);
        observer.await();
        Assertions.assertEquals(2, observer.getItems().size());
        Assertions.assertEquals(10, observer.getItems().getFirst().getAmount());
        Assertions.assertNull(observer.getThrowable());
    }

}