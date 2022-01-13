package pl.lodz.p.it.spjava.e11.huntingBook.web.result;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;

@Named
@RequestScoped
public class GetAllAnimalsPageBean {

    @Inject
    ResultController resultController;

    private List<CullDetailsDTO> result = new ArrayList<>();

    @PostConstruct
    private void init() {
        result = resultController.getAllAnimals();
    }
    
//    @PreDestroy
//    private void detroy() {
//        result = new ArrayList<>();
//    }

    public GetAllAnimalsPageBean() {
    }

    public List<CullDetailsDTO> getResult() {
        return result;
    }
    
    

}
