package com.grpc.example.proto.imports;

import com.grpc.example.proto.model.imports.Address;
import com.grpc.example.proto.model.imports.BodyStyle;
import com.grpc.example.proto.model.imports.Car;
import com.grpc.example.proto.model.imports.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportDemo {

    private static final Logger log = LoggerFactory.getLogger(ImportDemo.class);

    public static void main(String[] args) {

        var address = Address.newBuilder().setCity("atlanta").build();
        var car = Car.newBuilder().setBodyStyle(BodyStyle.COUPE).build();
        var person = Person.newBuilder()
                .setName("sam")
                .setAge(12)
                .setCar(car)
                .setAddress(address)
                .build();

        log.info("{}", person);

    }

}