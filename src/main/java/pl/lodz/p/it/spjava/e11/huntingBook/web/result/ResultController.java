package pl.lodz.p.it.spjava.e11.huntingBook.web.result;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import pl.lodz.p.it.spjava.e11.huntingBook.dto.ResultDTO;
import pl.lodz.p.it.spjava.e11.huntingBook.endpoint.ResultEndpoint;
import pl.lodz.p.it.spjava.e11.huntingBook.exception.AppBaseException;

@SessionScoped
public class ResultController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ResultController.class.getSimpleName());

    @Inject
    private ResultEndpoint resultEndpoint;

    private ResultDTO huntResult;

    public void addHuntResult(ResultDTO result) throws AppBaseException {
        resultEndpoint.addHuntResult(result);
    }

}
