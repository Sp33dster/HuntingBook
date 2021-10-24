package pl.lodz.p.it.spjava.e11.huntingBook.exception;

public class ResultException extends AppBaseException {

    public ResultException(String message) {
        super(message);
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ResultException createResultExceptionWithTxRetryRollback() {
        ResultException he = new ResultException(KEY_TX_RETRY_ROLLBACK);
        return he;
    }

}
