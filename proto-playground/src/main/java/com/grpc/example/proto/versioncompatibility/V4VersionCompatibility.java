package com.grpc.example.proto.versioncompatibility;

import com.google.protobuf.InvalidProtocolBufferException;
import com.grpc.example.proto.model.versioncompatibilty.v4.Television;
import com.grpc.example.proto.model.versioncompatibilty.v4.Type;
import com.grpc.example.proto.versioncompatibility.parser.V1Parser;
import com.grpc.example.proto.versioncompatibility.parser.V2Parser;
import com.grpc.example.proto.versioncompatibility.parser.V3Parser;
import com.grpc.example.proto.versioncompatibility.parser.V4Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V4VersionCompatibility {

    private static final Logger log = LoggerFactory.getLogger(V4VersionCompatibility.class);

    public static void main(String[] args) throws InvalidProtocolBufferException {

        var tv = Television.newBuilder()
                .setBrand("samsung")
                .setType(Type.UHD)
                .setPrice(15000)
                .build();

        V1Parser.parse(tv.toByteArray());
        V2Parser.parse(tv.toByteArray());
        V3Parser.parse(tv.toByteArray());
        V4Parser.parse(tv.toByteArray());

    }

}