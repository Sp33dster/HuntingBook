package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

public class ResultDTO {
    
    private Long id;

    @NotNull(message = "{constraint.notnull}")
    private Date shootingTime;

    
    private Boolean isPrivateUse;

    @NotNull(message = "{constraint.notnull}")
    private TypeOfResult typeOfResult;

 
    private AnimalType animalType;

   
    private int animalWeight;

    public ResultDTO() {
    }

    public ResultDTO(Long id, Date shootingTime, Boolean isPrivateUse, TypeOfResult typeOfResult) {
        this.id = id;
        this.shootingTime = shootingTime;
        this.isPrivateUse = isPrivateUse;
        this.typeOfResult = typeOfResult;
    }

    public ResultDTO(Long id, Date shootingTime, Boolean isPrivateUse, TypeOfResult typeOfResult, AnimalType animalType, int animalWeight) {
        this.id = id;
        this.shootingTime = shootingTime;
        this.isPrivateUse = isPrivateUse;
        this.typeOfResult = typeOfResult;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getShootingTime() {
        return shootingTime;
    }

    public void setShootingTime(Date shootingTime) {
        this.shootingTime = shootingTime;
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
