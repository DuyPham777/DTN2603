package backend.repository;

import entity.Manufacturer;

import java.util.List;

public interface IManufacturerRepository {
    List<Manufacturer> getManufacturers();
    Manufacturer findManufacturerById(int id);
}
