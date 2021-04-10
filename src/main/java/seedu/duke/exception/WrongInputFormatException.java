package seedu.duke.exception;

public class WrongInputFormatException extends Throwable {
    String errorMsg;

    public WrongInputFormatException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
    
}
