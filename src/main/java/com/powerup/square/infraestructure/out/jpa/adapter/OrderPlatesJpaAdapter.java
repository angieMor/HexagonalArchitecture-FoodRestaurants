package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.OrderPlatesEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IOrderPlatesMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IOrderPlatesRepository;
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

//        for(OrderPlates newOrder : orderPlates) {
//            orderPlatesEntities.add(orderPlatesMapper.toEntity(newOrder));
//        }

        for(int x = 0; x<=orderPlates.size()-1;x++){
            orderPlatesEntities.add(orderPlatesMapper.toEntity(orderPlates.get(x)));
            orderPlatesEntities.get(x).setId(-1L);
//            Long id = orderPlates.get(x).getOrder().getId();
//            orderPlatesEntities.get(x).setId(id);

        }


        System.out.println("jpa plates annd "+orderPlatesEntities);
        orderPlatesRepository.saveAll(orderPlatesEntities);
        System.out.println("SIGOOOOO 222222");
    }

    @Override
    public List<OrderPlates> getAllOrderPlatesByOrderId(Long id) {
        return null;
    }
}
