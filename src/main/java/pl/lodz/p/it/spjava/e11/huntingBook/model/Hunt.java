package pl.lodz.p.it.spjava.e11.huntingBook.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Hunt")
public class Hunt extends AbstractEntity implements Serializable {

    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private boolean isEnded;

    @NotNull
    private String area;

    @JoinColumn(name = "hunter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hunter hunterId;

    @JoinColumn(name = "result_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Result result;

    public Hunt(Date startTime, String area, Hunter hunterId) {
        this.startTime = startTime;
        this.area = area;
        this.hunterId = hunterId;
        this.isEnded = false;

    }

    public Hunt(Date startTime, Date endTime, String area, Hunter hunterId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.area = area;
        this.hunterId = hunterId;
        this.isEnded = false;
    }
    
    public Hunt(Date startTime, Date endTime, String area, Hunter hunterId, boolean isEnded) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.area = area;
        this.hunterId = hunterId;
        this.isEnded = isEnded;
    }

    public Hunt(Date startTime, Date endTime, String area, Hunter hunterId, Result resultId, boolean isEnded) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.area = area;
        this.hunterId = hunterId;
        this.result = resultId;
        this.isEnded = isEnded;
    }

    public boolean isIsEnded() {
        return isEnded;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Hunt() {
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

    public Hunter getHunterId() {
        return hunterId;
    }

    public void setHunterId(Hunter hunterId) {
        this.hunterId = hunterId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
