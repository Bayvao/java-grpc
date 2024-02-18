package com.grpc.example.intro.advancedfeatures;

import com.grpc.example.intro.advancedfeatures.repository.AccountRepository;
import com.grpc.example.intro.models.advancedfeatures.AccountBalance;
import com.grpc.example.intro.models.advancedfeatures.BalanceCheckRequest;
import com.grpc.example.intro.models.advancedfeatures.BankServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleBankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(UserRoleBankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var balance = AccountRepository.getBalance(accountNumber);
        if(UserRole.STANDARD.equals(Constants.USER_ROLE_KEY.get())){
            var fee = balance > 0 ? 1 : 0;
            AccountRepository.deductAmount(accountNumber, fee);
            balance = balance - fee;
        }
        var accountBalance = AccountBalance.newBuilder()
                                           .setAccountNumber(accountNumber)
                                           .setBalance(balance)
                                           .build();
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }

}