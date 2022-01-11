package pl.lodz.p.it.spjava.e11.huntingBook.web.cull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.AccountDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HunterDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.web.account.AccountController;

@Named
@ViewScoped
public class AddCullPageBean implements Serializable {

    @Inject
    CullController cullController;

    @Inject
    AccountController hunterController;

    private CullDTO cull = new CullDTO();

    private HunterDTO hunter;

    private List<AnimalType> animalTypes = new ArrayList<>();

    private List<AccountDTO> activeHunters = new ArrayList<>();

    private List<CullDetailsDTO> cullDetails = new ArrayList<>();

    @PostConstruct
    private void init() {
        activeHunters = hunterController.getActiveHuntersList();

        for (AnimalType animal : AnimalType.values()) {
            animalTypes.add(animal);
        }
        for (AnimalType animal : AnimalType.values()){
            cullDetails.add(new CullDetailsDTO(animal, 0));
        }
        
    }

    public CullController getCullController() {
        return cullController;
    }

    public void setCullController(CullController cullController) {
        this.cullController = cullController;
    }

    public List<AccountDTO> getActiveHunters() {
        return activeHunters;
    }

    public void setActiveHunters(List<AccountDTO> activeHunters) {
        this.activeHunters = activeHunters;
    }

    public CullDTO getCull() {
        return cull;
    }

    public void setCull(CullDTO cull) {
        this.cull = cull;
    }

    public List<CullDetailsDTO> getCullDetails() {
        return cullDetails;
    }

    public void setCullDetails(List<CullDetailsDTO> cullDetails) {
        this.cullDetails = cullDetails;
    }

    public List<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(List<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public HunterDTO getHunter() {
        return hunter;
    }

    public void setHunter(HunterDTO hunter) {
        this.hunter = hunter;
    }

    public void onAddNew() {
        CullDetailsDTO cullDetail = new CullDetailsDTO(AnimalType.ROE_DEER_CALF, 2);
        cullDetails.add(cullDetail);
    }

    public String addCull() throws AppBaseException {
        return cullController.add(cull, cullDetails);
    }

    public void onRowEdit(RowEditEvent<CullDetailsDTO> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getAnimal()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<CullDetailsDTO> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getAnimal()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
