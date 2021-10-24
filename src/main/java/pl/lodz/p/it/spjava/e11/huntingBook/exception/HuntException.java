package pl.lodz.p.it.spjava.e11.huntingBook.exception;

public class HuntException extends AppBaseException {

    public HuntException(String message) {
        super(message);
    }

    public HuntException(String message, Throwable cause) {
        super(message, cause);
    }

    public static HuntException createHuntExceptionWithTxRetryRollback() {
        HuntException he = new HuntException(KEY_TX_RETRY_ROLLBACK);
        return he;
    }

}
