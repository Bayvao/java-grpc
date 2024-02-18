package com.grpc.example.intro.deadline;

import com.grpc.example.intro.common.AbstractChannelTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    It is a class to demo the eager channel creation behavior.
    There is a bug: https://github.com/grpc/grpc-java/issues/10517
 */
public class EagerChannelDemoTest extends AbstractChannelTest {

    private static final Logger log = LoggerFactory.getLogger(EagerChannelDemoTest.class);

    @Test
    public void eagerChannelDemo() {
        log.info("{}", channel.getState(true));
    }

}