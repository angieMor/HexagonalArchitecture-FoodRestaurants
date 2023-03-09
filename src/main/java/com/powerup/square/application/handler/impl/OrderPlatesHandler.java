package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.application.handler.IOrderPlatesHandler;
import com.powerup.square.application.mapper.IOrderPlatesRequestMapper;
import com.powerup.square.application.mapper.IOrderPlatesResponseMapper;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.model.OrderPlates;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderPlatesHandler implements IOrderPlatesHandler {

    private final IOrderPlatesServicePort iOrderPlatesServicePort;
    private final IOrderPlatesRequestMapper iOrderPlatesRequestMapper;
    private final IOrderPlatesResponseMapper iOrderPlatesResponseMapper;

    public OrderPlatesHandler(IOrderPlatesServicePort iOrderPlatesServicePort, IOrderPlatesRequestMapper iOrderPlatesRequestMapper, IOrderPlatesResponseMapper iOrderPlatesResponseMapper) {
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iOrderPlatesRequestMapper = iOrderPlatesRequestMapper;
        this.iOrderPlatesResponseMapper = iOrderPlatesResponseMapper;
    }

    @Override
    public void saveOrderPlates(OrderGeneralRequest orderGeneralRequest) {
        OrderPlates orderPlates = iOrderPlatesRequestMapper.toOrderPlates(orderGeneralRequest);
        iOrderPlatesServicePort.saveOrderPlates(orderPlates);
    }
}
