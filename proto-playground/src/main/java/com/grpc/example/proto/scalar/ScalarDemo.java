package com.grpc.example.proto.scalar;

import com.grpc.example.proto.model.scalar.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScalarDemo {

    private static final Logger log = LoggerFactory.getLogger(ScalarDemo.class);

    public static void main(String[] args) {

        var person = Person.newBuilder()
                .setLastName("sam")
                .setAge(12)
                .setEmail("sam@gmail.com")
                .setEmployed(true)
                .setSalary(1000.2345)
                .setBankAccountNumber(123456789012L)
                .setBalance(-10000)
                .build();

        log.info("{}", person);

    }


}