package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import javax.validation.constraints.NotNull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

public class ResultDTO {

    private Long id;

    private Boolean isPrivateUse;

    @NotNull(message = "{constraint.notnull}")
    private TypeOfResult typeOfResult;

    private AnimalType animalType;

    private int animalWeight;

    private Boolean isConfirmed;

    public ResultDTO() {
    }

    public ResultDTO(Long id, Boolean isPrivateUse, TypeOfResult typeOfResult) {
        this.id = id;
        this.isPrivateUse = isPrivateUse;
        this.typeOfResult = typeOfResult;
    }

    public ResultDTO(Long id, Boolean isPrivateUse, TypeOfResult typeOfResult, AnimalType animalType, int animalWeight, Boolean isConfirmed) {
        this.id = id;
        this.isPrivateUse = isPrivateUse;
        this.typeOfResult = typeOfResult;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.isConfirmed = isConfirmed;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsPrivateUse() {
        return isPrivateUse;
    }

    public void setIsPrivateUse(Boolean isPrivateUse) {
        this.isPrivateUse = isPrivateUse;
    }

    public TypeOfResult getTypeOfResult() {
        return typeOfResult;
    }

    public void setTypeOfResult(TypeOfResult typeOfResult) {
        this.typeOfResult = typeOfResult;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public int getAnimalWeight() {
        return animalWeight;
    }

    public void setAnimalWeight(int animalWeight) {
        this.animalWeight = animalWeight;
    }

}
