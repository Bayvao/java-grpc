package com.grpc.example.proto.imports;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import com.grpc.example.proto.model.imports.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class WellKnownTypesDemo {

    private static final Logger log = LoggerFactory.getLogger(WellKnownTypesDemo.class);

    public static void main(String[] args) {

        var sample = Sample.newBuilder()
                .setAge(Int32Value.of(12))
                .setLoginTime(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build())
                .build();

        log.info("{}", Instant.ofEpochSecond(sample.getLoginTime().getSeconds()));


    }


}