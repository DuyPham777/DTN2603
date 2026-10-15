package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Manufacturer {
    private int manufacturerId;
    private ManufacturerName manufacturerName;

    public Manufacturer(int manufacturerId, ManufacturerName manufacturerName) {
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
    }
}
