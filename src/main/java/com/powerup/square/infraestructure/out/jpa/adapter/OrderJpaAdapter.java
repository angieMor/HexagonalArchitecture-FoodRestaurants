package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.domain.model.Employee;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.EmployeeEntity;
import com.powerup.square.infraestructure.out.jpa.entity.OrderEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IEmployeeMapper;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IEmployeeRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;

    private final IEmployeeRepository employeeRepository;



    @Override
    public void saveOrder(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        orderMapper.toOrder(orderRepository.save(orderEntity));


    }

    @Override
    public List<Order> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest, Long restaurantOfEmployee) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC,"id_orders")
        );

//        Long employeeRestaurantId = employeeRepository.getRestaurantIdByEmployeeId(ordersStateRequest.getIdEmployee()).getRestaurant().getId();
//        List<Order> findByRestaurantOrder =  orderMapper.toOrder(orderRepository.getOrdersByRestaurantId(employeeRestaurantId));
        return orderMapper.toOrder(orderRepository.getOrdersByState(ordersStateRequest.getState(), restaurantOfEmployee, pageable));
//        return orderRepository.getOrdersByState(ordersStateRequest.getState(), pageable).stream().map(orderMapper::toOrder).collect(Collectors.toList());

    }


    @Override
    public Order getOrderByIdClient(Long idClient) {
        return orderMapper.toOrder(orderRepository.getOrderByIdClient(idClient));
    }

    @Override
    public boolean existsByIdClient(Long idClient) {
        return orderRepository.existsByIdClient(idClient);
    }

    @Override
    public void updateOrderToAsignIt(List<Order> orders) {
        List<OrderEntity> ordersEntities = new ArrayList<>();
        EmployeeEntity employee = employeeRepository.findByIdUser(orders.get(0).getIdEmployee());
        for(Order order: orders){
            OrderEntity orderEntity = orderMapper.toEntity(order);
            orderEntity.setEmployee(employee);
            ordersEntities.add(orderEntity);
        }
        orderRepository.saveAll(ordersEntities);
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderMapper.toOrder(orderRepository.getOrderById(idOrder));
    }


}
