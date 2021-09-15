package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class HuntDTO {

    private Long id;

    @NotNull(message = "{constraint.notnull}")
    private Date startTime;

    @NotNull(message = "{constraint.notnull}")
    private Date endTime;

    public HuntDTO() {
    }

    public HuntDTO(Long id, Date startTime, Date endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
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
    
    
}
