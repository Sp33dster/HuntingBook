package pl.lodz.p.it.spjava.e11.huntingBook.security;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FacesConfig
@ApplicationScoped
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/faces/login/formLogin.xhtml",
                errorPage = "",
                useForwardToLogin = false
        ))
public class SecurityAppConfig {
    
}
