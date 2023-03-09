package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.PlateListRequest;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;

import java.util.List;

public interface IPlatePersistencePort {

    void savePlate(Plate plate);
    List<Plate> getPlatesFromRestaurant(PlateListRequest plateListRequest);
    Plate getPlate(Long id);
    void updatePlate(Plate plate);
    void deletePlate(Long id);
    boolean existById(Long id);
    boolean existByName(String name);

}
