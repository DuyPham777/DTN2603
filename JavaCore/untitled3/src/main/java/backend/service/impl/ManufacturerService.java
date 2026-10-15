package backend.service.impl;

import backend.repository.IManufacturerRepository;
import backend.repository.impl.ManufacturerRepository;
import backend.service.IManufacturerService;
import entity.Manufacturer;

import java.util.List;

public class ManufacturerService implements IManufacturerService {
    private final IManufacturerRepository manufacturerRepository;

    public ManufacturerService() {
        manufacturerRepository = new ManufacturerRepository();
    }

    @Override
    public List<Manufacturer> getManufacturers() {
        return manufacturerRepository.getManufacturers();
    }

    @Override
    public Manufacturer findManufacturerById(int manufacturerId) {
        return manufacturerRepository.findManufacturerById(manufacturerId);
    }
}
