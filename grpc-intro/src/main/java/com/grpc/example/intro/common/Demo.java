package com.grpc.example.intro.common;

import com.grpc.example.intro.rpctypes.BankService;
import com.grpc.example.intro.rpctypes.TransferService;

/*
    a simple class to start the server with specific services for demo purposes
 */
public class Demo {

    public static void main(String[] args) {

        GrpcServer.create(6565, builder -> {
                      builder.addService(new BankService());
                             //.intercept(new ApiKeyValidationInterceptor());
                  })
                  .start()
                  .await();

    }


    /*  Created for load balancing demo
    private static class BankInstance1 {
        public static void main(String[] args) {
            GrpcServer.create(6565, new BankService())
                      .start()
                      .await();
        }
    }

    private static class BankInstance2 {
        public static void main(String[] args) {
            GrpcServer.create(7575, new BankService())
                      .start()
                      .await();
        }
    }
    */
}