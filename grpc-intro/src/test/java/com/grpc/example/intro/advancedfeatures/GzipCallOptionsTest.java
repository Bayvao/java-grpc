package com.grpc.example.intro.advancedfeatures;

import com.grpc.example.intro.models.advancedfeatures.BalanceCheckRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    It is a class to demo enabling gzip compression
    Update logback xml with DEBUG mode
 */
public class GzipCallOptionsTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(GzipCallOptionsTest.class);

    @Test
    public void gzipDemo() {
        var request = BalanceCheckRequest.newBuilder()
                                         .setAccountNumber(1)
                                         .build();
        var response = this.bankBlockingStub.withCompression("gzip")
                                            .getAccountBalance(request);
        log.info("{}", response);
    }

}