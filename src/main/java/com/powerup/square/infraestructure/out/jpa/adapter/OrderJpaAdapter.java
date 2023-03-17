package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;



    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        orderMapper.toOrder(orderRepository.save(orderEntity));


    }

    @Override
    public List<Order> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC,"id")
        );

        return orderMapper.toOrder(orderRepository.getOrdersByState(ordersStateRequest.getState(), pageable));
    }


    @Override
    public Order getOrderByIdClient(Long idClient) {
        return orderMapper.toOrder(orderRepository.getOrderByIdClient(idClient));
    }

    @Override
    public boolean existsByIdClient(Long idClient) {
        return orderRepository.existsByIdClient(idClient);
    }


}
