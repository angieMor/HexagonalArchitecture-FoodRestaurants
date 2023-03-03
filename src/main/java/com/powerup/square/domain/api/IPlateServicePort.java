package com.powerup.square.domain.api;

import com.powerup.square.domain.model.Plate;

import java.util.List;

public interface IPlateServicePort {

    void savePlate(Plate plate);
    List<Plate> getAllPlates();
    Plate getPlate(Long id);
    void updatePlate(Plate plate);
    void deletePlate(Long id);
    boolean existById(Long id);
    boolean existByName(String name);

}
