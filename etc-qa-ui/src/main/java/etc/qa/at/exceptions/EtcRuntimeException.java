package etc.qa.at.exceptions;

public class EtcRuntimeException extends RuntimeException {
    Exception e;
    ExceptionLevel level = null;
    ExceptionCause cause = null;
    String friendlyMessage = null;

    public EtcRuntimeException(Exception e, ExceptionLevel level, ExceptionCause cause, String friendlyMessage) {
        this.e = e;
        this.level = level;
        this.cause = cause;
        this.friendlyMessage = friendlyMessage;
    }

    public EtcRuntimeException(Exception e, ExceptionCause cause, String friendlyMessage) {
        this.e = e;
        this.cause = cause;
        this.friendlyMessage = friendlyMessage;
    }

    public String getDetailedMessage() {
        return cause.toString() + "\n" + this.friendlyMessage + "\n" + e.getMessage() + "\n" + e.getStackTrace();
    }

    @Override
    public String toString() {
        return cause.toString() + "\n" + this.friendlyMessage + "\n" + e.getMessage();
    }
}
