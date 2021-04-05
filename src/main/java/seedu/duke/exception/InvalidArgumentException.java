package seedu.duke.exception;

public class InvalidArgumentException extends Exception {
    String errorMsg;

    public InvalidArgumentException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
