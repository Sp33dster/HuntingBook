package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Result.findAll", query = "SELECT r FROM Result r"),
    @NamedQuery(name = "Result.findById", query = "SELECT r FROM Result r WHERE r.id = :id"),
    @NamedQuery(name = "Result.findByShootingTime", query = "SELECT r FROM Result r WHERE r.shootingTime = :shootingTime"),
    @NamedQuery(name = "Result.findByIsPrivateUse", query = "SELECT r FROM Result r WHERE r.isPrivateUse = :isPrivateUse"),
    @NamedQuery(name = "Result.findByTypeOfResult", query = "SELECT r FROM Result r WHERE r.typeOfResult = :typeOfResult")})
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shooting_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shootingTime;
    @Column(name = "is_private_use")
    private Boolean isPrivateUse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "type_of_result")
    private String typeOfResult;
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @ManyToOne
    private Animal animalId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultId")
    private Collection<Hunt> huntCollection;

    public Result() {
    }

    public Result(Long id) {
        this.id = id;
    }

    public Result(Long id, Date shootingTime, String typeOfResult) {
        this.id = id;
        this.shootingTime = shootingTime;
        this.typeOfResult = typeOfResult;
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

    public String getTypeOfResult() {
        return typeOfResult;
    }

    public void setTypeOfResult(String typeOfResult) {
        this.typeOfResult = typeOfResult;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Animal animalId) {
        this.animalId = animalId;
    }

    @XmlTransient
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
