package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;

@Entity
@Table(name = "Cull")
public class Cull implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Map<AnimalType, Integer> animalsTypeAndNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cullId")
    private Collection<Hunter> hunterCollection;

    @JoinColumn(name = "hunter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hunter hunterId;

    public Cull() {
    }

    public Cull(Long id) {
        this.id = id;
    }

    public Cull(Long id, Date startDate, Date endDate, Map<AnimalType, Integer> animalsTypeAndNumber) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.animalsTypeAndNumber = animalsTypeAndNumber;
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

    public Map<AnimalType, Integer> getAnimalsTypeAndNumber() {
        return animalsTypeAndNumber;
    }

    public void setAnimalsTypeAndNumber(Map<AnimalType, Integer> animalsTypeAndNumber) {
        this.animalsTypeAndNumber = animalsTypeAndNumber;
    }

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
