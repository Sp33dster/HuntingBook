package pl.lodz.p.it.spjava.e11.huntingBook.web.cull;

import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDTO;

@Named
public class AddCullPageBean {
    
    @Inject
    CullController cullController;
    
    private CullDTO cull = new CullDTO();

    public CullDTO getCull() {
        return cull;
    }
    
    public String add(){
       return cullController.add(cull);
    }
    
}
