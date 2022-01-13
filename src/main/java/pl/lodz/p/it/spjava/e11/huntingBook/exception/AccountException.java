package pl.lodz.p.it.spjava.e11.huntingBook.exception;

import javax.persistence.OptimisticLockException;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Account;

public class AccountException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.account.db.constraint.uniq.login";
    static final public String KEY_ACCOUNT_EMAIL_EXISTS = "error.account.email.exists";
    static final public String KEY_ACCOUNT_WRONG_PASSWORD = "error.account.wrong.password";
    static final public String KEY_ACCOUNT_OPTIMISTIC_LOCK = "error.account.optimisticlock";

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(String message) {
        super(message);
    }

    private Account account;

    public Account getAccount() {
        return account;
    }

    static public AccountException createTxRetryRollback() {
        AccountException ae = new AccountException(KEY_TX_RETRY_ROLLBACK);
        return ae;
    }

    static public AccountException createWithDBCheckConstraintKey(Account account, Throwable cause) {
        AccountException ae = new AccountException(KEY_DB_CONSTRAINT, cause);
        ae.account = account;
        return ae;
    }
    
    static  public AccountException createExceptionEmailAlreadyExist(Account account, Throwable cause){
        AccountException ae = new AccountException(KEY_ACCOUNT_EMAIL_EXISTS, cause);
        ae.account = account;
        return ae;
    }
    
    static public AccountException createExceptionWrongPassword(Account account, Throwable cause){
        AccountException ae = new AccountException(KEY_ACCOUNT_WRONG_PASSWORD, cause);
        ae.account = account;
        return ae;
    }
    
    static public AccountException createAccountWithOptimisticLockKey(Account account, OptimisticLockException cause){
        AccountException ae = new AccountException(KEY_ACCOUNT_OPTIMISTIC_LOCK, cause);
        ae.account = account;
        return ae;
    }

}
