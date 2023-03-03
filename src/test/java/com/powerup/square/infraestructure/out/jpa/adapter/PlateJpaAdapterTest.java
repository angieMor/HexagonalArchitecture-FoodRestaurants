package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Plate;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import com.powerup.square.infraestructure.out.jpa.mapper.IPlateMapper;
import com.powerup.square.infraestructure.out.jpa.repository.IPlateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PlateJpaAdapterTest {

    @InjectMocks
    PlateJpaAdapter plateJpaAdapter;

    @Mock
    IPlateRepository iPlateRepository;

    @Mock
    IPlateMapper iPlateMapper;

    @Test
    void savePlate() {
        Plate plate = SavePlateJpaDataTest.obtainPlate();
        PlateEntity plateEntity = SavePlateJpaDataTest.obtainPlateEntity();

        when(iPlateMapper.toEntity(plate)).thenReturn(plateEntity);
        plateJpaAdapter.savePlate(plate);

        verify(iPlateRepository).save(plateEntity);


    }

    @Test
    void getAllPlates() {
    }

//    @Test
//    void getPlate() {
//        plateJpaAdapter.getPlate(anyLong());
//        verify(iPlateMapper).toPlate(iPlateRepository.findById(anyLong()).get());
//    }

    @Test
    void updatePlate() {

        Plate plate = SavePlateJpaDataTest.obtainPlate();
        plateJpaAdapter.updatePlate(plate);

        verify(iPlateRepository).save(iPlateMapper.toEntity(plate));

    }

    @Test
    void deletePlate() {
        plateJpaAdapter.deletePlate(anyLong());

        verify(iPlateRepository).deleteById(anyLong());
    }

    @Test
    void existById() {
        plateJpaAdapter.existById(anyLong());

        verify(iPlateRepository).existsById(anyLong());
    }

    @Test
    void existByName() {
        plateJpaAdapter.existByName(any());

        verify(iPlateRepository).existsByName(any());
    }
}