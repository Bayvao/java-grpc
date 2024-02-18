package com.grpc.example.proto.versioncompatibility;

import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.example.proto.model.versioncompatibilty.v3.Television;
import com.grpc.example.proto.model.versioncompatibilty.v3.Type;
import com.grpc.example.proto.versioncompatibility.parser.V1Parser;
import com.grpc.example.proto.versioncompatibility.parser.V2Parser;
import com.grpc.example.proto.versioncompatibility.parser.V3Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V3VersionCompatibility {

    private static final Logger log = LoggerFactory.getLogger(V3VersionCompatibility.class);

    public static void main(String[] args) throws InvalidProtocolBufferException {

        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setType(Type.UHD)
                .build();

        V1Parser.parse(tv.toByteArray());
        V2Parser.parse(tv.toByteArray());
        V3Parser.parse(tv.toByteArray());

    }

}