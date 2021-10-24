package pl.lodz.p.it.spjava.e11.huntingBook.web.hunt;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.HuntDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.ResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.HuntEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.HuntException;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@SessionScoped
public class HuntController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(HuntController.class.getSimpleName());

    @Inject
    private HuntEndpoint huntEndpoint;
    
    private HuntDTO huntToEnd;

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
    
    public String getHuntToEnd(HuntDTO hunt){
        huntToEnd = huntEndpoint.getHuntToEnd(hunt);
        return "endHunt";
    }

    public String endHunt(HuntDTO hunt, ResultDTO result) throws AppBaseException{
        huntEndpoint.endHunt(hunt, result);
        return "successHunt";
    }
}
