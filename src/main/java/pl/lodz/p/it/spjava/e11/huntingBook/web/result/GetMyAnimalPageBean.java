package pl.lodz.p.it.spjava.e11.huntingBook.web.result;

import pl.lodz.p.it.spjava.e11.huntingBook.web.hunt.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;

@Named
@ViewScoped
public class GetMyAnimalPageBean implements Serializable {

    @Inject
    ResultController resultController;

    private List<CullDetailsDTO> myAnimals;

    private DataModel<HuntDTO> huntDataModel;

    public DataModel<HuntDTO> getHuntDataModel() {
        return huntDataModel;
    }

    @PostConstruct
    private void initModel() {
        myAnimals = resultController.getMyAnimals();
    }

    public GetMyAnimalPageBean() {
    }

    public List<CullDetailsDTO> getMyAnimals() {
        return myAnimals;
    }

    

}
