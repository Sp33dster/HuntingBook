package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

public class CullDTO {

    private Long id;

    @NotNull(message = "{constraint.notnull}")
    private Date startDate;

    @NotNull(message = "{constraint.notnull}")
    private Date endDate;

    public CullDTO() {
    }

    public CullDTO(Long id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
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

    
}
