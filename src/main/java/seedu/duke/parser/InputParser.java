package seedu.duke.parser;

import java.util.Arrays;

public class InputParser {
    String command;
    String[] infoFragments;

    public InputParser(String rawInput) {
        processInput(rawInput);
    }

    private void processInput(String rawInput) {
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
