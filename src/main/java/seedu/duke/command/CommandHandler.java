package seedu.duke.command;

import java.util.Arrays;

public class CommandHandler {
    String command;
    String[] infoFragments;

    public CommandHandler(String rawInput) {
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

    /**
     * Transforms infoFragments to clean arguments.
     * <br><br>
     * Illustration:<br>
     * User input: add p/project name url/URL d/description <br>
     * Command: add <br>
     * infoFragments: {"p/project","name","url/URL","d/description"} (separating user input with " " and ignore command)
     *
     * @param keywords             A string array of keywords. e.g. {"p/","url/","d/"} for add()
     * @param firstOptionalKeyword index of first optional keyword. If no optional keyword is required,
     *                             input "length of keywords" e.g. 3 for add() since d/description is optional
     * @return clean arguments e.g. {e.g. "project name","URL","description"}
     */
    public String[] decodeInfoFragments(String[] keywords, int firstOptionalKeyword) {
        int[] keywordLocations = getKeywordLocations(infoFragments, keywords);

        if (!isUserInputValid(keywordLocations, firstOptionalKeyword)) {
            System.out.print("Mandatory parameters are not provided or given provided in invalid format." + "\n");
            return null;
        }

        return getUsefulInfo(infoFragments, keywordLocations, keywords);
    }

    private int[] getKeywordLocations(String[] arguments, String[] keywords) {
        int[] keywordLocations = makeEmptyArray(keywords.length);
        int index = 0;
        for (int i = 0; i < arguments.length && index < keywords.length; i++) {
            if (arguments[i].contains(keywords[index])) {
                keywordLocations[index] = i;
                index++;
            }
        }
        return keywordLocations;
    }

    private int[] makeEmptyArray(int numberOfKeywords) {
        int[] newArray = new int[numberOfKeywords];
        Arrays.fill(newArray, -1);
        return newArray;
    }

    private boolean isUserInputValid(int[] keywordLocation, int firstOptionalArgumentIndex) {
        boolean isMandatoryInputProvided = true;
        for (int i = 0; i < firstOptionalArgumentIndex; i++) {
            isMandatoryInputProvided = isMandatoryInputProvided && keywordLocation[i] != -1;
        }
        boolean isPositionCorrect = true;
        for (int i = 0; i < firstOptionalArgumentIndex - 1; i++) {
            isPositionCorrect = isPositionCorrect && (keywordLocation[i] < keywordLocation[i + 1]);
        }
        boolean isCorrectOptionalKeywordPosition = true;
        for (int i = firstOptionalArgumentIndex; i < keywordLocation.length; i++) {
            boolean isCorrectPosition = keywordLocation[i] > keywordLocation[i - 1] || keywordLocation[i] == -1;
            isCorrectOptionalKeywordPosition = isCorrectOptionalKeywordPosition && isCorrectPosition;
        }
        return (isMandatoryInputProvided && isPositionCorrect && isCorrectOptionalKeywordPosition);
    }

    private String[] getUsefulInfo(String[] arguments, int[] keywordLocations, String[] keywords) {
        String[] processedArguments = new String[keywords.length];
        int nextIndex;
        for (int i = 0; i < processedArguments.length; i++) {
            if (keywordLocations[i] == -1) {
                processedArguments[i] = null;
                continue;
            }
            nextIndex = i + 1;
            int endLocation;
            if (nextIndex < keywords.length) {
                endLocation = keywordLocations[nextIndex];
            } else {
                endLocation = arguments.length;
            }
            while (++nextIndex < keywords.length && endLocation == -1) {
                endLocation = keywordLocations[nextIndex];
            }
            if (endLocation == -1) {
                endLocation = arguments.length;
            }
            processedArguments[i] = extractInfo(arguments, keywords[i].length(), keywordLocations[i],
                    endLocation - 1);
        }
        return processedArguments;
    }

    private String extractInfo(String[] arguments, int chopLocation, int from, int to) {
        String output = arguments[from].substring(chopLocation);
        for (int i = from + 1; i <= to; i++) {
            output = String.join(" ", output, arguments[i]);
        }
        return output;
    }
}
