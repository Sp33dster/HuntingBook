package pl.lodz.p.it.spjava.e11.huntingBook.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;

@ApplicationScoped
public class JpaIdentityStore implements IdentityStore {

    @Inject
    private SecurityEndpoint securityEndpoint;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return IdentityStore.super.getCallerGroups(validationResult);
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;//try
            try {
                Account account = securityEndpoint.findByName(usernamePasswordCredential.getCaller());
                if (account.getIsActive()) {

                    String group = account.getType().toString();
                    if (usernamePasswordCredential.compareTo(account.getLogin(), account.getPassword())) {
                        return new CredentialValidationResult(account.getLogin(), new HashSet<>(Arrays.asList(group)));
                    }
                } else {
                    return CredentialValidationResult.NOT_VALIDATED_RESULT;
                }
            } catch (NoResultException nre) {
                return CredentialValidationResult.INVALID_RESULT;
            }
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;

    }
}
