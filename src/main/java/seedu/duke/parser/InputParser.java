package seedu.duke.parser;

import seedu.duke.exception.InvalidArgumentException;

import java.util.Arrays;

public class InputParser {
    String command = null;
    String[] infoFragments = null;

    public InputParser(String rawInput) {
        parserInput(rawInput);
    }

    private void parserInput(String rawInput) {
        String trimmedRawInput = trimRawInput(rawInput);
        if (isRawInputValid(trimmedRawInput)) {
            String[] inputFragments = splitString(trimmedRawInput);
            this.command = inputFragments[0];
            this.infoFragments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
        }
    }

    private String trimRawInput(String rawInput) {
        return rawInput.trim();
    }

    private boolean isRawInputValid(String trimmedRawInput) {
        return !(trimmedRawInput.length() == 0);
    }

    private String[] splitString(String rawInput) {
        return rawInput.split(" ");
    }

    public String getCommand() {
        return command;
    }

    public String[] getInfoFragments() {
        return infoFragments;
    }

    private void printErrorMsg(String message) {
        System.out.print(message + "\n");
    }
}
