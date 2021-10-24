package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class HuntDTO {

    private Long id;

    @NotNull(message = "{constraint.notnull}")
    private Date startTime;

    private Date endTime;
    @NotNull(message = "{constraint.notnull}")
    private String area;

    public HuntDTO() {
    }

    public HuntDTO(Long id, Date startTime, String area) {
        this.id = id;
        this.startTime = startTime;
        this.area = area;
    }

    public HuntDTO(Long id, Date startTime, Date endTime, String area) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
