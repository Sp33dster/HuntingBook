package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

public class CullDTO {

    private Long id;
    
    @NotNull
    private HunterDTO hunter;

    @NotNull(message = "{constraint.notnull}")
    private Date startDate;

    @NotNull(message = "{constraint.notnull}")
    private Date endDate;

    private List<CullDetailsDTO> cullDetails;

    public CullDTO() {
    }

    public CullDTO(Long id, HunterDTO hunter, Date startDate, Date endDate, List<CullDetailsDTO> cullDetails) {
        this.id = id;
        this.hunter = hunter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cullDetails = cullDetails;
    }

    public List<CullDetailsDTO> getCullDetails() {
        return cullDetails;
    }

    public void setCullDetails(List<CullDetailsDTO> cullDetails) {
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

    public HunterDTO getHunter() {
        return hunter;
    }

    public void setHunter(HunterDTO hunter) {
        this.hunter = hunter;
    }
    
    

}
