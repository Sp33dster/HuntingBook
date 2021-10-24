package pl.lodz.p.it.spjava.e11.huntingBook.web.cull;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.CullDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.CullEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;
import pl.lodz.p.it.spjava.e11.huntingBook.web.utils.ContextUtils;

@Named
@SessionScoped
public class CullController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CullController.class.getSimpleName());

    @Inject
    CullEndpoint cullEndpoint;

    private CullDTO cullDTO;

    public String add(CullDTO cull) {
        try {
            cullEndpoint.addCull(cullDTO);
            return "success";
        } catch (AppBaseException ex) {
            Logger lg = Logger.getLogger(CullController.class
                    .getName());
            lg.log(Level.SEVERE, "Zgłoszenie w metodzie akcji wyjatku: ", ex);

            ContextUtils.emitInternationalizedMessageOfException(ex);
            return null;
        }
    }

}
