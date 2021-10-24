package pl.lodz.p.it.spjava.e11.huntingBook.exception;

public class CullException extends AppBaseException {

    public CullException(String message) {
        super(message);
    }

    public CullException(String message, Throwable cause) {
        super(message, cause);
    }

    public static CullException createCullExceptionWithTxRetryRollback() {
        CullException ce = new CullException(KEY_TX_RETRY_ROLLBACK);
        return ce;
    }

}
