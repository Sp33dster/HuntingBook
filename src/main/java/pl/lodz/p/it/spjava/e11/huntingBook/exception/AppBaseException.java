package pl.lodz.p.it.spjava.e11.huntingBook.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AppBaseException extends Exception{
    
    static final public String KEY_TX_RETRY_ROLLBACK = "error.tx.retry.rollback";
    
    protected AppBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AppBaseException(String message) {
        super(message);
    }
    
}
