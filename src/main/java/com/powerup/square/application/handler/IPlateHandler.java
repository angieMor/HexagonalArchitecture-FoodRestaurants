package com.powerup.square.application.handler;

import com.powerup.square.application.dto.plate.*;

import java.util.List;

public interface IPlateHandler {

    void savePlate(PlateRequest plateRequest);

    PlateResponse getPlate(Long id);
    void updatePlate(PlateUpdatingRequest plateUpdatingRequest);

    void isActivePlate(PlateIsActiveRequest plateIsActiveRequest);

    List<PlateResponse> getPlatesFromRestaurant(PlateListRequest plateListRequest);

}
