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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "cull")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cull.findAll", query = "SELECT c FROM Cull c"),
    @NamedQuery(name = "Cull.findById", query = "SELECT c FROM Cull c WHERE c.id = :id"),
    @NamedQuery(name = "Cull.findByStartDate", query = "SELECT c FROM Cull c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Cull.findByEndDate", query = "SELECT c FROM Cull c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "Cull.findByAnimalNumber", query = "SELECT c FROM Cull c WHERE c.animalNumber = :animalNumber")})
public class Cull implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "animal_number")
    private int animalNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cullId")
    private Collection<Hunter> hunterCollection;
    @JoinColumn(name = "hunter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hunter hunterId;
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Animal animalId;

    public Cull() {
    }

    public Cull(Long id) {
        this.id = id;
    }

    public Cull(Long id, Date startDate, Date endDate, int animalNumber) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.animalNumber = animalNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAnimalNumber() {
        return animalNumber;
    }

    public void setAnimalNumber(int animalNumber) {
        this.animalNumber = animalNumber;
    }

    @XmlTransient
    public Collection<Hunter> getHunterCollection() {
        return hunterCollection;
    }

    public void setHunterCollection(Collection<Hunter> hunterCollection) {
        this.hunterCollection = hunterCollection;
    }

    public Hunter getHunterId() {
        return hunterId;
    }

    public void setHunterId(Hunter hunterId) {
        this.hunterId = hunterId;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Animal animalId) {
        this.animalId = animalId;
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
        if (!(object instanceof Cull)) {
            return false;
        }
        Cull other = (Cull) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Cull[ id=" + id + " ]";
    }

}
