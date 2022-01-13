package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Entity
@Table(name = "Result")
@Inheritance(strategy = InheritanceType.JOINED)
public class HuntResult extends AbstractEntity implements Serializable {

    @Column(name = "is_private_use")
    private Boolean isPrivateUse;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "type_of_result")
    @Enumerated(EnumType.STRING)
    private TypeOfResult typeOfResult;

    @Column(name = "animal_type")
    @Enumerated(EnumType.STRING)
    private AnimalType animalType;

    @Column(name = "animal_weight")
    private int animalWeight;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "result")
    private Hunt huntID;

    public HuntResult() {
    }

    public HuntResult(Boolean isPrivateUse, TypeOfResult typeOfResult) {
        this.isPrivateUse = isPrivateUse;
        this.typeOfResult = typeOfResult;
    }

    public HuntResult(Boolean isPrivateUse, Boolean isConfirmed, TypeOfResult typeOfResult, AnimalType animalType, int animalWeight) {
        this.isPrivateUse = isPrivateUse;
        this.isConfirmed = isConfirmed;
        this.typeOfResult = typeOfResult;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
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

    public Hunt getHuntID() {
        return huntID;
    }

    public void setHuntID(Hunt huntID) {
        this.huntID = huntID;
    }

}
