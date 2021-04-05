package seedu.duke.exception;

public class ResourceNotFoundException extends Exception {
    public static void printResourceNotFoundMsg() {
        System.out.print("Resource is not found. Please enter a valid index. " + "\n");
    }
}
