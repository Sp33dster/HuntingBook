package pl.lodz.p.it.spjava.e11.huntingBook.enumView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Named
@RequestScoped
public class TypeOfResultView {

    private String typeOfResult;

    private List<String> types;
    
    @PostConstruct
    public void init() {
        types = new ArrayList<>();
        types.addAll(Arrays.asList(Arrays.toString(TypeOfResult.values())));
    }

    public String getTypeOfResult() {
        return typeOfResult;
    }

    public void setTypeOfResult(String typeOfResult) {
        this.typeOfResult = typeOfResult;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    
    

}
