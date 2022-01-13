package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;

public class CullDetailsDTO {
    
    private AnimalType animal;

    private Long quantity;

    public AnimalType getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalType animal) {
        this.animal = animal;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public CullDetailsDTO(AnimalType animal, Long quantity) {
        this.animal = animal;
        this.quantity = quantity;
    }

    public CullDetailsDTO() {
    }

}
