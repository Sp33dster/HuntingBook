package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import pl.lodz.p.it.spjava.e11.huntingBook.model.HuntResult;

public class HuntDTO {

    private Long id;

    @NotNull(message = "{constraint.notnull}")
    private Date startTime;

    private Date endTime;

    private boolean isEnded;

    @NotNull(message = "{constraint.notnull}")
    private String area;

    private HuntResultDTO resultDTO;

    public HuntDTO() {
    }

    public HuntResultDTO getResultDTO() {
        return resultDTO;
    }

    public void setResultDTO(HuntResultDTO resultDTO) {
        this.resultDTO = resultDTO;
    }

    public boolean getIsEnded() {
        return isEnded;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    public HuntDTO(Long id, Date startTime, Date endTime, String area, boolean isEnded, HuntResultDTO resultDTO) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.area = area;
        this.isEnded = isEnded;
        this.resultDTO = resultDTO;
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

    public HuntDTO(Long id, Date startTime, Date endTime, String area, boolean isEnded) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.area = area;
        this.isEnded = isEnded;
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
