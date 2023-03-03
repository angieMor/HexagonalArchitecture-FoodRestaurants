package com.powerup.square.domain.usecase;

import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PlateUseCaseTest {

    @InjectMocks
    PlateUseCase plateUseCase;

    @Mock
    IPlatePersistencePort iPlatePersistencePort;

    @Test
    void savePlate() {

        Plate plate = SavePlateUseCaseDataTest.obtainPlate();

//        when(existByName(plate.getName())).thenReturn(Optional.empty());
        plateUseCase.savePlate(plate);

        verify(iPlatePersistencePort).savePlate(plate);
    }

    @Test
    void getAllPlates() {
    }

    @Test
    void getPlate() {
    }

    @Test
    void updatePlate() {

        Plate plate = SavePlateUseCaseDataTest.obtainPlate();
        plateUseCase.updatePlate(plate);

        verify(iPlatePersistencePort).updatePlate(plate);

    }

    @Test
    void deletePlate() {
        plateUseCase.deletePlate(anyLong());

        verify(iPlatePersistencePort).deletePlate(anyLong());
    }

    @Test
    void existById() {
        plateUseCase.existById(anyLong());

        verify(iPlatePersistencePort).existById(anyLong());
    }

    @Test
    void existByName() {
        plateUseCase.existByName(any());

        verify(iPlatePersistencePort).existByName(any());
    }
}