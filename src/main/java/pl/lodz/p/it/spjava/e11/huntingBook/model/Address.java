package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "city")
    private String city;

    @Size(max = 75)
    @Column(name = "street")
    private String street;

    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Column(name = "flat_number")
    private int flatNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addresId")
    private Collection<Hunter> hunterCollection;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(Long id, String city, String zipCode, int flatNumber) {
        this.id = id;
        this.city = city;
        this.zipCode = zipCode;
        this.flatNumber = flatNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Hunter> getHunterCollection() {
        return hunterCollection;
    }

    public void setHunterCollection(Collection<Hunter> hunterCollection) {
        this.hunterCollection = hunterCollection;
    }

}
