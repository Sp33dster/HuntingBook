package pl.lodz.p.it.spjava.e11.huntingBook.web.hunt;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;

@Named
@ViewScoped
public class ViewAllMyHuntsPageBean implements Serializable {

    @Inject
    HuntController huntController;

    private List<HuntDTO> hunts;

    private DataModel<HuntDTO> huntDataModel;

    public DataModel<HuntDTO> getHuntDataModel() {
        return huntDataModel;
    }

    @PostConstruct
    private void initModel() {
        hunts = huntController.getMyHunts();
        huntDataModel = new ListDataModel<HuntDTO>(hunts);
    }

    public ViewAllMyHuntsPageBean() {
    }

    public List<HuntDTO> getHunts() {
        return hunts;
    }

    public void setHunts(List<HuntDTO> hunts) {
        this.hunts = hunts;
    }

}
