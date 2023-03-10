package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.application.dto.PlateListRequest;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlateJpaAdapter implements IPlatePersistencePort{
    private final IPlateRepository plateRepository;
    private final IPlateMapper plateMapper;

    @Override
    public void savePlate(Plate plate){
        PlateEntity plateEntity = plateMapper.toEntity(plate);
        plateRepository.save(plateEntity);
    }

    @Override
    public List<Plate> getPlatesFromRestaurant(PlateListRequest plateListRequest) {
        // Getting page and amount for paging functionality
        Pageable pageable = PageRequest.of(plateListRequest.getPage().intValue(),
                plateListRequest.getAmount().intValue(),
                Sort.by(Sort.Direction.ASC,"category"));

        return plateMapper.toPlate(plateRepository.findPlatesByRestaurantId(plateListRequest.getIdRestaurant(), pageable));
    }

    @Override
    public Plate getPlate(Long id) {
        return plateMapper.toPlate(plateRepository.findById(id).get());
    }

    @Override
    public void updatePlate(Plate plate) {
        plateRepository.save(plateMapper.toEntity(plate));
    }

    @Override
    public void deletePlate(Long id) {
        plateRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return plateRepository.existsById(id);
    }

//    @Override
////    public boolean getActive(Boolean active) {
////        return plateRepository.findByActive(active);
////    }

    @Override
    public boolean existByName(String name) {
        return plateRepository.existsByName(name);
    }

}
