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
@Table(name = "hunt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hunt.findAll", query = "SELECT h FROM Hunt h"),
    @NamedQuery(name = "Hunt.findById", query = "SELECT h FROM Hunt h WHERE h.id = :id"),
    @NamedQuery(name = "Hunt.findByStartTime", query = "SELECT h FROM Hunt h WHERE h.startTime = :startTime"),
    @NamedQuery(name = "Hunt.findByEndTime", query = "SELECT h FROM Hunt h WHERE h.endTime = :endTime"),
    @NamedQuery(name = "Hunt.findByVersion", query = "SELECT h FROM Hunt h WHERE h.version = :version"),
    @NamedQuery(name = "Hunt.findByCreationTimestamp", query = "SELECT h FROM Hunt h WHERE h.creationTimestamp = :creationTimestamp"),
    @NamedQuery(name = "Hunt.findByModificationTimestamp", query = "SELECT h FROM Hunt h WHERE h.modificationTimestamp = :modificationTimestamp")})
public class Hunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modification_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "huntId")
    private Collection<Hunter> hunterCollection;
    @JoinColumn(name = "hunter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hunter hunterId;
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Result resultId;

    public Hunt() {
    }

    public Hunt(Long id) {
        this.id = id;
    }

    public Hunt(Long id, Date startTime, Date endTime, int version, Date creationTimestamp, Date modificationTimestamp) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.version = version;
        this.creationTimestamp = creationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Date getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(Date modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
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

    public Result getResultId() {
        return resultId;
    }

    public void setResultId(Result resultId) {
        this.resultId = resultId;
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
        if (!(object instanceof Hunt)) {
            return false;
        }
        Hunt other = (Hunt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt[ id=" + id + " ]";
    }

}
