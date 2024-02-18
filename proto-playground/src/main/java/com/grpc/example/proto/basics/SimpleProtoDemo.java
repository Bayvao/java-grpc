package com.grpc.example.proto.basics;

import com.grpc.example.proto.model.basics.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProtoDemo {

    private static final Logger log = LoggerFactory.getLogger(SimpleProtoDemo.class);

    public static void main(String[] args) {
        var person = Person.newBuilder()
                                            .setName("sam")
                                            .setAge(12)
                                            .build();
        log.info("{}", person);
    }

}