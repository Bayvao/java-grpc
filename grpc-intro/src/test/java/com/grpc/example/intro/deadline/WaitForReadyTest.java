package com.grpc.example.intro.deadline;

import com.google.common.util.concurrent.Uninterruptibles;
import com.grpc.example.intro.common.AbstractChannelTest;
import com.grpc.example.intro.common.GrpcServer;
import com.grpc.example.intro.models.deadline.BankServiceGrpc;
import com.grpc.example.intro.models.deadline.WithdrawRequest;
import io.grpc.Deadline;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WaitForReadyTest extends AbstractChannelTest {

    private static final Logger log = LoggerFactory.getLogger(WaitForReadyTest.class);
    private final GrpcServer grpcServer = GrpcServer.create(new DeadlineBankService());
    private BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;

    @BeforeAll
    public void setup() {
        Runnable runnable = () -> {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            this.grpcServer.start();
        };
        Thread.ofVirtual().start(runnable);
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void blockingDeadlineTest() {
        log.info("sending the request");
        var request = WithdrawRequest.newBuilder()
                                     .setAccountNumber(1)
                                     .setAmount(50)
                                     .build();
        var ex = Assertions.assertThrows(StatusRuntimeException.class, () -> {
            var iterator = this.bankBlockingStub.withWaitForReady()
                                                .withDeadline(Deadline.after(8, TimeUnit.SECONDS))
                                                .withdraw(request);
            while (iterator.hasNext()) {
                log.info("{}", iterator.next());
            }
        });
        Assertions.assertEquals(Status.Code.DEADLINE_EXCEEDED, ex.getStatus().getCode());
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }

}