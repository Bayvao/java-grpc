package com.grpc.example.intro.trailerdemo;

import com.grpc.example.intro.common.AbstractChannelTest;
import com.grpc.example.intro.common.GrpcServer;
import com.grpc.example.intro.models.trailerdemo.BankServiceGrpc;
import com.grpc.example.intro.models.trailerdemo.ErrorMessage;
import com.grpc.example.intro.models.trailerdemo.ValidationCode;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Optional;

public abstract class AbstractTest extends AbstractChannelTest {

    private static final Metadata.Key<ErrorMessage> ERROR_MESSAGE_KEY = ProtoUtils.keyForProto(ErrorMessage.getDefaultInstance());
    private final GrpcServer grpcServer = GrpcServer.create(new BankService());
    protected BankServiceGrpc.BankServiceStub bankStub;
    protected BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;

    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.bankStub = BankServiceGrpc.newStub(channel);
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }

    protected ValidationCode getValidationCode(Throwable throwable) {
        return Optional.ofNullable(Status.trailersFromThrowable(throwable))
                       .map(m -> m.get(ERROR_MESSAGE_KEY))
                       .map(ErrorMessage::getValidationCode)
                       .orElseThrow();
    }

}