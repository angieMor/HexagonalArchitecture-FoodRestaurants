package com.powerup.square.application.handler;

import com.powerup.square.application.dto.PlateIsActiveRequest;
import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.PlateResponse;
import com.powerup.square.application.dto.PlateUpdatingRequest;

public interface IPlateHandler {

    void savePlate(PlateRequest plateRequest);

    PlateResponse getPlate(Long id);
    void updatePlate(PlateUpdatingRequest plateUpdatingRequest);

    void isActivePlate(PlateIsActiveRequest plateIsActiveRequest);
}
