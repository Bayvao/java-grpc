package com.grpc.trading.platform.user.service.handlers;

import com.grpc.trading.platform.model.user.UserInformation;
import com.grpc.trading.platform.model.user.UserInformationRequest;
import com.grpc.trading.platform.user.exception.UnknownUserException;
import com.grpc.trading.platform.user.repository.PortfolioItemRepository;
import com.grpc.trading.platform.user.repository.UserRepository;
import com.grpc.trading.platform.user.util.EntityMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInformationRequestHandler {

    private final UserRepository userRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public UserInformationRequestHandler(UserRepository userRepository, PortfolioItemRepository portfolioItemRepository) {
        this.userRepository = userRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public UserInformation getUserInformation(UserInformationRequest request) {
        var user = this.userRepository.findById(request.getUserId())
                                      .orElseThrow(() -> new UnknownUserException(request.getUserId()));
        var portfolioItems = this.portfolioItemRepository.findAllByUserId(request.getUserId());
        return EntityMessageMapper.toUserInformation(user, portfolioItems);
    }


}