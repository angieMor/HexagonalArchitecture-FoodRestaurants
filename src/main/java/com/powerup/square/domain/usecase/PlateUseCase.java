package com.powerup.square.domain.usecase;

import com.powerup.square.application.dto.PlateListRequest;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.exception.NoDataFoundException;
import com.powerup.square.domain.exception.PlateAlreadyExistsException;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.domain.model.Plate;

import java.util.List;
public class PlateUseCase implements IPlateServicePort {
    private final IPlatePersistencePort platePersistencePort;
    public PlateUseCase(IPlatePersistencePort platePersistencePort) {
        this.platePersistencePort = platePersistencePort;
    }
    @Override
    public void savePlate(Plate plate) {
//        if(existByName(plate.getName()) ) throw new PlateAlreadyExistsException();
        platePersistencePort.savePlate(plate);
    }
    @Override
    public List<Plate> getPlatesFromRestaurant(PlateListRequest plateListRequest) {

        return platePersistencePort.getPlatesFromRestaurant(plateListRequest);
    }
    @Override
    public Plate getPlate(Long id) {
        if(!existById(id)) throw new NoDataFoundException();
        return platePersistencePort.getPlate(id);
    }
    @Override
    public void updatePlate(Plate plate) {
        platePersistencePort.updatePlate(plate);
    }
    @Override
    public void deletePlate(Long id) {
        platePersistencePort.deletePlate(id);
    }

    @Override
    public boolean existById(Long id) {
        return platePersistencePort.existById(id);
    }

    @Override
    public boolean existByName(String name) {
        return platePersistencePort.existByName(name);
    }


}

