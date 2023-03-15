package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IOrderPersistencePort;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderPlatesMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderPlatesRepository;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderPlatesJpaAdapter implements IOrderPlatesPersistencePort {
    private final IOrderPlatesRepository orderPlatesRepository;
    private final IOrderPlatesMapper orderPlatesMapper;

    @Override
    public void saveOrderPlates(List<OrderPlates> orderPlates) {
        List<OrderPlatesEntity> orderPlatesEntities = new ArrayList<>();

        for(int x = 0; x<=orderPlates.size()-1;x++) {

            orderPlatesEntities.add(orderPlatesMapper.toEntity(orderPlates.get(x)));
            orderPlatesEntities.get(x).setId(-1L);
        }
        orderPlatesRepository.saveAll(orderPlatesEntities);
    }

    @Override
    public List<OrderPlates> getAllOrderPlatesByOrderId(Long id) {
        return null;
    }
}
