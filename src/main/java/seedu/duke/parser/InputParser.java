package seedu.duke.parser;

import java.util.Arrays;

public class InputParser {
    String command;
    String[] infoFragments;

    public InputParser(String rawInput) {
        parserInput(rawInput);
    }

    private void parserInput(String rawInput) {
        String[] inputFragments = splitString(rawInput);
        this.command = inputFragments[0];
        this.infoFragments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
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
}
