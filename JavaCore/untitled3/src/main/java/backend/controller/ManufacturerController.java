package backend.controller;

import backend.service.IManufacturerService;
import backend.service.impl.ManufacturerService;
import entity.Manufacturer;

import java.util.List;

public class ManufacturerController {
    private final IManufacturerService service;

    public ManufacturerController() {
        service = new ManufacturerService();
    }

    public List<Manufacturer> getManufacturers() {
        return service.getManufacturers();
    }

    public Manufacturer findManufacturerById(int manufacturerId) {
        return service.findManufacturerById(manufacturerId);
    }
}
