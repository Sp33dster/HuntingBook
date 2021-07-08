package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Entity
@Table(name = "Result")
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "shooting_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shootingTime;

    @Column(name = "is_private_use")
    private Boolean isPrivateUse;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultId")
    private Collection<Hunt> huntCollection;

    public Result() {
    }

    public Result(Long id) {
        this.id = id;
    }

    public Result(Long id, Date shootingTime, TypeOfResult typeOfResult) {
        this.id = id;
        this.shootingTime = shootingTime;
        this.typeOfResult = typeOfResult;
    }

    public Result(Long id, Date shootingTime, Boolean isPrivateUse, TypeOfResult typeOfResult, AnimalType animalType, int animalWeight) {
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

    public Collection<Hunt> getHuntCollection() {
        return huntCollection;
    }

    public void setHuntCollection(Collection<Hunt> huntCollection) {
        this.huntCollection = huntCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Result)) {
            return false;
        }
        Result other = (Result) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Result[ id=" + id + " ]";
    }

}
