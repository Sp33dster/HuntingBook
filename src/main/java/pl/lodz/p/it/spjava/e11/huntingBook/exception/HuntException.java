package pl.lodz.p.it.spjava.e11.huntingBook.exception;

import javax.persistence.OptimisticLockException;
import pl.lodz.p.it.spjava.e11.huntingBook.model.Hunt;

public class HuntException extends AppBaseException {

    static final public String KEY_HUNT_OPTIMISTIC_LOCK = "error.account.optimisticlock";

    private Hunt hunt;

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

    public static HuntException createHuntOptimisticLockException(Hunt hunt, OptimisticLockException cause) {
        HuntException he = new HuntException(KEY_HUNT_OPTIMISTIC_LOCK, cause);
        he.hunt = hunt;
        return he;
    }
}
