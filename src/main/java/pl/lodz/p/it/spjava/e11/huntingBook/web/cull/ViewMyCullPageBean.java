package pl.lodz.p.it.spjava.e11.huntingBook.web.cull;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDTO;

@Named
@ViewScoped
public class ViewMyCullPageBean implements Serializable {

    @Inject
    CullController cullController;

    private CullDTO cull = new CullDTO();

    private DataModel<CullDTO> cullDataModel;

    public DataModel<CullDTO> getCullDataModel() {
        return cullDataModel;
    }

    @PostConstruct
    private void init() {
        cull = cullController.getMyCull();
    }

    public CullController getCullController() {
        return cullController;
    }

    public void setCullController(CullController cullController) {
        this.cullController = cullController;
    }

    public CullDTO getCull() {
        return cull;
    }

    public void setCull(CullDTO cull) {
        this.cull = cull;
    }

}
