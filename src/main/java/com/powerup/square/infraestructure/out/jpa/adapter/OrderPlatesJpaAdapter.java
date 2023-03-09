package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderPlatesMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderPlatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderPlatesJpaAdapter implements IOrderPlatesPersistencePort {
    private final IOrderPlatesRepository orderPlatesRepository;
    private final IOrderPlatesMapper orderPlatesMapper;

    @Override
    public void saveOrderPlates(OrderPlates orderPlates) {
        OrderPlatesEntity orderPlatesEntity = orderPlatesMapper.toEntity(orderPlates);



//        orderPlatesRepository.save(orderPlatesEntity);
    }

    @Override
    public List<OrderPlates> getAllOrderPlatesByOrderId(Long id) {
        return null;
    }
}
