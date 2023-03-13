package com.powerup.square.domain.api;

import com.powerup.square.application.dto.PlateListRequest;
import com.powerup.square.domain.model.Plate;

import java.util.List;

public interface IPlateServicePort {

    void savePlate(Plate plate);
    List<Plate> getPlatesFromRestaurant(PlateListRequest plateListRequest);
    Plate getPlate(Long id);
    void updatePlate(Plate plate);
    void deletePlate(Long id);
    boolean existById(Long id);

    boolean getActive(Boolean active);

    boolean existByName(String name);

}
