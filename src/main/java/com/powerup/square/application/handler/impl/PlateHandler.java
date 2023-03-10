package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.*;
import com.powerup.square.application.handler.IPlateHandler;
import com.powerup.square.application.mapper.IPlateRequestMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.exception.RestaurantDoNotExistException;
import com.powerup.square.domain.exception.SameStateException;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.spi.ICategoryPersistencePort;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PlateHandler implements IPlateHandler {

    private final IPlateServicePort iPlateServicePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final ICategoryPersistencePort iCategoryPersistencePort;
    private final IPlateRequestMapper iPlateRequestMapper;
    private final IPlateResponseMapper iPlateResponseMapper;

    public PlateHandler(IPlateServicePort iPlateServicePort, IRestaurantPersistencePort iRestaurantPersistencePort, ICategoryPersistencePort iCategoryPersistencePort, IPlateRequestMapper iPlateRequestMapper, IPlateResponseMapper iPlateResponseMapper) {
        this.iPlateServicePort = iPlateServicePort;
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.iCategoryPersistencePort = iCategoryPersistencePort;
        this.iPlateRequestMapper = iPlateRequestMapper;
        this.iPlateResponseMapper = iPlateResponseMapper;
    }


    @Override
    public void savePlate(PlateRequest plateRequest) {
        Plate plate = iPlateRequestMapper.toPlate(plateRequest);
        plate.setActive(true);
        if(!iRestaurantPersistencePort.existById(plateRequest.getIdRestaurant())){
            throw new RestaurantDoNotExistException();
        }

        plate.setRestaurant(iRestaurantPersistencePort.getRestaurant(plateRequest.getIdRestaurant()));
        plate.setCategory(iCategoryPersistencePort.getCategory(plateRequest.getIdCategory()));
        plate.setId(-1L);
        iPlateServicePort.savePlate(plate);
    }

    @Override
    public PlateResponse getPlate(Long id) {
        Plate plate = iPlateServicePort.getPlate(id);
        return iPlateResponseMapper.toPlateResponse(plate);
    }

    @Override
    public void updatePlate(PlateUpdatingRequest plateUpdatingRequest) {
        if(!iRestaurantPersistencePort.existByIdOwner(plateUpdatingRequest.getIdOwner())) throw new NoDataFoundException();
        else{
            Plate plate = iPlateServicePort.getPlate(plateUpdatingRequest.getId());
            if(Strings.isNotBlank(plateUpdatingRequest.getDescription()) || Strings.isNotEmpty(plateUpdatingRequest.getDescription())) plate.setDescription(plateUpdatingRequest.getDescription());
            if(plateUpdatingRequest.getPrice() > 0) plate.setPrice(plateUpdatingRequest.getPrice());
            iPlateServicePort.updatePlate(plate);
        }
    }

    @Override
    public void isActivePlate(PlateIsActiveRequest plateIsActiveRequest) {
        Plate plate = iPlateServicePort.getPlate(plateIsActiveRequest.getId());
        Boolean status = plateIsActiveRequest.getActive();

//        if(status == iPlateServicePort.getActive(status)) {
//            throw new SameStateException();
//        }
        plate.setActive(status);
        iPlateServicePort.updatePlate(plate);
    }

    @Override
    public List<PlateResponse> getPlatesFromRestaurant(PlateListRequest plateListRequest) {
        return iPlateResponseMapper.toPlateResponseList(iPlateServicePort.getPlatesFromRestaurant(plateListRequest));
    }

}
