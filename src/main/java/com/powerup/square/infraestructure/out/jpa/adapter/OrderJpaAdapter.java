package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
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
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public Order getOrderByIdClient(Long idClient) {
        return orderMapper.toOrder(orderRepository.getOrderByIdClient(idClient));
    }


}
