package backend.service;

import entity.Manufacturer;

import java.util.List;

public interface IManufacturerService {
    List<Manufacturer> getManufacturers();
    Manufacturer findManufacturerById(int manufacturerId);
}
