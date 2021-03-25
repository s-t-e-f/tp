package seedu.duke.parser;

import java.util.Arrays;

public class InputParser {
    String command;
    String[] infoFragments;

    public InputParser(String rawInput) {
        parserInput(rawInput);
    }

    private void parserInput(String rawInput) {
        String[] inputFragments = rawInput.split(" ");
        this.command = inputFragments[0];
        this.infoFragments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
    }

    public String getCommand() {
        return command;
    }

    public String[] getInfoFragments() {
        return infoFragments;
    }
}
