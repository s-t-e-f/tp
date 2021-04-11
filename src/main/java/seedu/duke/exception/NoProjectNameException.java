package seedu.duke.exception;

public class NoProjectNameException extends Exception {
    String errorMsg;

    public NoProjectNameException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
