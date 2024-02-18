package com.grpc.trading.platform.user.exception;

public class InsufficientBalanceException extends RuntimeException {

    private static final String MESSAGE = "User [id=%d] does not have enough fund to complete the transaction";

    public InsufficientBalanceException(Integer userId) {
        super(MESSAGE.formatted(userId));
    }

}