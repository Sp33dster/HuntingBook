package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
    private String city;

    @Size(min = 2, max = 32, message = "{constraint.string.length.notinrange}")
    private String street;

    @NotNull
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
    private String zipCode;

    @NotNull
    private int flatNumber;

    private List<HunterDTO> huntersAddress;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String city, String street, String zipCode, int flatNumber, List<HunterDTO> huntersAddress) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.flatNumber = flatNumber;
        this.huntersAddress = huntersAddress;
    }

    public Long getId() {
        return id;
    }
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public List<HunterDTO> getHuntersAddress() {
        return huntersAddress;
    }

    public void setHuntersAddress(List<HunterDTO> huntersAddress) {
        this.huntersAddress = huntersAddress;
    }

    


}
