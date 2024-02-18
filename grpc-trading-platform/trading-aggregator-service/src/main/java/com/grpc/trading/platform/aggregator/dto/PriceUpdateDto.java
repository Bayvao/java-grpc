package com.grpc.trading.platform.aggregator.dto;

public record PriceUpdateDto(String ticker,
                             Integer price) {
}