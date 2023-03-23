package com.powerup.square.application.handler;

import com.powerup.square.application.dto.plate.*;

import java.util.List;

public interface IPlateHandler {

    void savePlate(PlateRequest plateRequest, Long idOwner);

    PlateResponse getPlate(Long id);
    void updatePlate(PlateUpdatingRequest plateUpdatingRequest, Long idOwner);

    void isActivePlate(PlateIsActiveRequest plateIsActiveRequest, Long idOwner);

    List<PlateResponse> getPlatesFromRestaurant(PlateListRequest plateListRequest);

}
