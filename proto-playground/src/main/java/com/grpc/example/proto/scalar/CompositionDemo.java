package com.grpc.example.proto.scalar;

import com.grpc.example.proto.model.scalar.Address;
import com.grpc.example.proto.model.scalar.School;
import com.grpc.example.proto.model.scalar.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompositionDemo {

    private static final Logger log = LoggerFactory.getLogger(CompositionDemo.class);

    public static void main(String[] args) {

        // create student
        var address = Address.newBuilder()
                .setStreet("123 main st")
                .setCity("atlanta")
                .setState("GA")
                .build();
        var student = Student.newBuilder()
                .setName("sam")
                .setAddress(address)
                .build();
        // create school
        var school = School.newBuilder()
                .setId(1)
                .setName("high school")
                .setAddress(address.toBuilder().setStreet("234 main st").build())
                .build();

        log.info("school: {}", school);
        log.info("student: {}", student);

    }

}