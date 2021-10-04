package pl.lodz.p.it.spjava.e11.huntingBook.web.utils;

import java.security.Principal;
import java.util.ResourceBundle;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApplicationScoped
@Named
public class ContextUtils {

    public ContextUtils() {
    }

    public static ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public String invalidateSession() {
        ((HttpSession) getContext().getSession(true)).invalidate();
        return "main";
    }

    public static String getSessionID() {
        HttpSession session = (HttpSession) getContext().getSession(true);
        return session.getId();
    }

    public String getUserName() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return (null == p ? "" : p.getName());
    }

    public static String getUserAddress() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return "login: " + externalContext.getUserPrincipal() + " ip: " + ((HttpServletRequest) externalContext.getRequest()).getRemoteAddr();
    }

    public static String getContextParameter(String paramName) {
        return getContext().getInitParameter(paramName);
    }

    public static ResourceBundle getDefaultBundle() {
        String bundlePath = getContextParameter("resourceBundle.path");
        if (null == bundlePath) {
            return null;
        } else {
            return ResourceBundle.getBundle(bundlePath, FacesContext.getCurrentInstance().getViewRoot().getLocale());
        }
    }

    public static boolean isInternationalizationKeyExist(String key) {
        return ContextUtils.getDefaultBundle().getString(key) != null && !"".equals(ContextUtils.getDefaultBundle().getString(key));
    }

    public static void emitInternationalizedMessage(String id, String key) {
        FacesMessage msg = new FacesMessage(ContextUtils.getDefaultBundle().getString(key));
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(id, msg);
    }

    public static void emitSuccessMessage(String id) {
        emitInternationalizedMessage(id, "general.success.message");
    }
}
