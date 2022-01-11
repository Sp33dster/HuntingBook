package pl.lodz.p.it.spjava.e11.huntingBook.web.hunt;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.HuntEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.HuntException;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@SessionScoped
public class HuntController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(HuntController.class.getSimpleName());

    @Inject
    private HuntEndpoint huntEndpoint;

    private HuntDTO endHunt;
    
    private HuntDTO resultHunt;

    public HuntDTO getEndHunt() {
        return endHunt;
    }

    public HuntDTO getResultHunt() {
        return resultHunt;
    }
 
    public String addNewHunt(HuntDTO hunt) {
        try {
            huntEndpoint.addNewHunt(hunt);
            return "success";
        } catch (HuntException he) {
            Logger.getLogger(HuntController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji addNewHunt wyjątku: ", he);
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(HuntController.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji addNewHunt wyjątku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());

            }
            return null;
        }
    }
   
    public String getHuntToAddResult(HuntDTO hunt){
        resultHunt = huntEndpoint.getHuntToAddResult(hunt);
        return "addResult";
    }

    public String getHuntToEnd(HuntDTO hunt) {
        endHunt = huntEndpoint.getHuntToEnd(hunt);
        return "endHunt";
    }

    public List<HuntDTO> getMyHunts() {
        return huntEndpoint.getMyHunts();
    }

    public String endHunt(HuntDTO hunt) throws AppBaseException {
        huntEndpoint.endHunt(hunt);
        return "successHunt";
    }

    public void confirmResult(HuntDTO hunt) {
        huntEndpoint.confirmResult(hunt);
        //TODO emitSuccessMessage();
    }

}
