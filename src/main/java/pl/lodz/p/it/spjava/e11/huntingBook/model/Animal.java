package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findById", query = "SELECT a FROM Animal a WHERE a.id = :id"),
    @NamedQuery(name = "Animal.findBySpieces", query = "SELECT a FROM Animal a WHERE a.spieces = :spieces"),
    @NamedQuery(name = "Animal.findByType", query = "SELECT a FROM Animal a WHERE a.type = :type"),
    @NamedQuery(name = "Animal.findBySex", query = "SELECT a FROM Animal a WHERE a.sex = :sex"),
    @NamedQuery(name = "Animal.findByWeight", query = "SELECT a FROM Animal a WHERE a.weight = :weight")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "spieces")
    private String spieces;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private int weight;
    @OneToMany(mappedBy = "animalId")
    private Collection<Result> resultCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animalId")
    private Collection<Cull> cullCollection;

    public Animal() {
    }

    public Animal(Long id) {
        this.id = id;
    }

    public Animal(Long id, String spieces, String type, String sex, int weight) {
        this.id = id;
        this.spieces = spieces;
        this.type = type;
        this.sex = sex;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpieces() {
        return spieces;
    }

    public void setSpieces(String spieces) {
        this.spieces = spieces;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @XmlTransient
    public Collection<Result> getResultCollection() {
        return resultCollection;
    }

    public void setResultCollection(Collection<Result> resultCollection) {
        this.resultCollection = resultCollection;
    }

    @XmlTransient
    public Collection<Cull> getCullCollection() {
        return cullCollection;
    }

    public void setCullCollection(Collection<Cull> cullCollection) {
        this.cullCollection = cullCollection;
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
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Animal[ id=" + id + " ]";
    }

}
