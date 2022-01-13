package pl.lodz.p.it.spjava.e11.huntingBook.web.result;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDetailsDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.ResultEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;

@SessionScoped
public class ResultController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ResultController.class.getSimpleName());

    @Inject
    private ResultEndpoint resultEndpoint;
    private List<CullDetailsDTO> allAnimals;

    private HuntResultDTO huntResult;

    public String addHuntResult(HuntDTO hunt, HuntResultDTO result) throws AppBaseException {
        resultEndpoint.addHuntResult(hunt, result);
        return "success";
    }

    public List<CullDetailsDTO> getAllAnimals() {
        return resultEndpoint.getAllAnimals();
        }

    public List<CullDetailsDTO> getMyAnimals() {
        return resultEndpoint.getMyAnimals();
    }

}
