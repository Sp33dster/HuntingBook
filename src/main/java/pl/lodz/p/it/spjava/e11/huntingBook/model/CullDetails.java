package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;

@Entity
@Table(name = "Cull_details")
public class CullDetails extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "animal")
    private AnimalType animal;

    @NotNull
    @Column(name = "quantity")
    @Min(value = 0)
    private Long quantity;

    @JoinColumn(name = "cullid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cull cullId;

    public CullDetails() {
    }

    public CullDetails(Long id, AnimalType animal, Long quantity, Cull cullId) {
        this.id = id;
        this.animal = animal;
        this.quantity = quantity;
        this.cullId = cullId;
    }

    public CullDetails(AnimalType animal, Long quantity) {
        this.animal = animal;
        this.quantity = quantity;
    }
    
    

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

    public Cull getCullId() {
        return cullId;
    }

    public void setCullId(Cull cullId) {
        this.cullId = cullId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
