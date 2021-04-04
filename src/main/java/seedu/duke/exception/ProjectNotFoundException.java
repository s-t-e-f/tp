package seedu.duke.exception;


public class ProjectNotFoundException extends Exception {

    public static void printProjNotFoundMsg() {
        System.out.print("Project is not found ... " + "\n");
    }
}
