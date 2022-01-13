package pl.lodz.p.it.spjava.e11.huntingBook.web.result;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import pl.lodz.p.it.spjava.e11.huntingBook.web.hunt.*;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AnimalType;
import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.TypeOfResult;

@Named
@RequestScoped
public class AddResultPageBean {

    @Inject
    ResultController resultController;

    @Inject
    HuntController huntController;

    private String typeOfResult;

    private List<TypeOfResult> types;

    private AnimalType animalType;

    private List<AnimalType> animalTypes;

    private HuntResultDTO result = new HuntResultDTO();

    private HuntDTO hunt = new HuntDTO();

    @PostConstruct
    private void init() {
        hunt = huntController.getResultHunt();
        

        animalTypes = new ArrayList<>();
        for (AnimalType animal : AnimalType.values()) {
            animalTypes.add(animal);
        }
    }

    public HuntResultDTO getResult() {
        return result;
    }

    public HuntDTO getHunt() {
        return hunt;
    }

    public String getTypeOfResult() {
        return typeOfResult;
    }

    public List<TypeOfResult> getTypes() {
        return types;
    }

    public AddResultPageBean() {
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public List<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public String addResult() throws AppBaseException {
        return resultController.addHuntResult(hunt, result);
    }

}
