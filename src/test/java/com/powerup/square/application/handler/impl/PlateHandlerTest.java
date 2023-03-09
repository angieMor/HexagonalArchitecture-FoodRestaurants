package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.*;
import com.powerup.square.application.mapper.IPlateRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.ICategoryServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.ICategoryPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

@ExtendWith(SpringExtension.class)
class PlateHandlerTest {
    @InjectMocks
    PlateHandler plateHandler;

    @Mock
    IPlateServicePort iPlateServicePort;
    @Mock
    IRestaurantPersistencePort iRestaurantPersistencePort;
    @Mock
    ICategoryPersistencePort iCategoryPersistencePort;
    @Mock
    IPlateRequestMapper iPlateRequestMapper;
    @Mock
    IPlateResponseMapper iPlateResponseMapper;

    @Test
    void savePlate() {
        //Given
        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        PlateRequest plateRequest = SavePlateHandlerDataTest.obtainPlateRequest();

        //When
        when(iPlateRequestMapper.toPlate(plateRequest)).thenReturn(plate);
//        when(!iRestaurantPersistencePort.existByIdOwner(anyLong()))
//                .thenThrow(new NoDataFoundException())
//                        .thenReturn(Optional.empty().isPresent());
        plate.setRestaurant(iRestaurantPersistencePort.getRestaurantByIdOwner(anyLong()));
        plate.setCategory(iCategoryPersistencePort.getCategory(anyLong()));
        plate.setId(anyLong());

        plateHandler.savePlate(plateRequest);


        //Then
        verify(iPlateServicePort).savePlate(plate);


    }

    @Test
    void getPlate() {
        Plate plate = SavePlateHandlerDataTest.obtainPlate();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);
        plateHandler.getPlate(anyLong());

        verify(iPlateResponseMapper).toPlateResponse(plate);
    }

    @Test
    void updatePlate() {

        Plate plate = SavePlateHandlerDataTest.obtainPlate();
        PlateUpdatingRequest plateUpdatingRequest = SavePlateHandlerDataTest.obtainPlateUpdatingRequest();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);
        plateHandler.updatePlate(plateUpdatingRequest);

        verify(iPlateServicePort).updatePlate(plate);

    }

    @Test
    void isActivePlate() {

        PlateIsActiveRequest plateIsActiveRequest = SavePlateHandlerDataTest.obtainPlateIsActiveRequest();
        Plate plate = SavePlateHandlerDataTest.obtainPlate();

        when(iPlateServicePort.getPlate(anyLong())).thenReturn(plate);

        plateHandler.isActivePlate(plateIsActiveRequest);
        verify(iPlateServicePort).updatePlate(plate);
    }

    @Test
    void getPlatesFromRestaurant(){
        PlateListRequest plateListRequest = SavePlateHandlerDataTest.obtainPlateListRequest();

        plateHandler.getPlatesFromRestaurant(plateListRequest);

        verify(iPlateResponseMapper).toPlateResponseList(iPlateServicePort.getPlatesFromRestaurant(plateListRequest));
    }
}