package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cull")
public class Cull extends AbstractEntity implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cullId")
    private List<CullDetails> cullDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cullId")
    private List<Hunter> hunterCollection;

    @JoinColumn(name = "hunter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hunter hunterId;

    public Cull() {
    }

    public Cull(Long id) {
        this.id = id;
    }

    public Cull(Long id, Date startDate, Date endDate, List<CullDetails> cullCollection) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cullDetails = cullCollection;
    }

    public List<CullDetails> getCullDetails() {
        return cullDetails;
    }

    public void setCullDetails(List<CullDetails> cullDetails) {
        this.cullDetails = cullDetails;
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

    public List<Hunter> getHunterCollection() {
        return hunterCollection;
    }

    public void setHunterCollection(List<Hunter> hunterCollection) {
        this.hunterCollection = hunterCollection;
    }

    public Hunter getHunterId() {
        return hunterId;
    }

    public void setHunterId(Hunter hunterId) {
        this.hunterId = hunterId;
    }

}
