package seedu.duke.exception;


public class ProjectNotFoundException extends Exception {

    String errorMsg;

    public ProjectNotFoundException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static void printProjNotFoundMsg() {
        System.out.print("Project is not found ... " + "\n");
    }
}
