package com.grpc.trading.platform.aggregator.service;

import com.grpc.trading.platform.model.stock.StockPriceRequest;
import com.grpc.trading.platform.model.stock.StockServiceGrpc;
import com.grpc.trading.platform.model.user.StockTradeRequest;
import com.grpc.trading.platform.model.user.StockTradeResponse;
import com.grpc.trading.platform.model.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userClient;

    @GrpcClient("stock-service")
    private StockServiceGrpc.StockServiceBlockingStub stockClient;

    public StockTradeResponse trade(StockTradeRequest request){
        var priceRequest = StockPriceRequest.newBuilder().setTicker(request.getTicker()).build();
        var priceResponse = this.stockClient.getStockPrice(priceRequest);
        var tradeRequest = request.toBuilder().setPrice(priceResponse.getPrice()).build();
        return this.userClient.tradeStock(tradeRequest);
    }

}