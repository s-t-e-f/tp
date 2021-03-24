package seedu.duke.parser;

import seedu.duke.exception.InvalidArgumentException;

import java.util.Arrays;

public class CommandParser {

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
    public static String[] decodeInfoFragments(String[] infoFragments, String[] keywords, int firstOptionalKeyword)
            throws InvalidArgumentException {
        int[] keywordLocations = getKeywordLocations(infoFragments, keywords);

        if (!isUserInputValid(keywordLocations, firstOptionalKeyword)) {
            String errorMsg = "Mandatory parameters are not provided or given provided in invalid order. "
                    + "Resource failed to be added!";
            throw new InvalidArgumentException(errorMsg);
        }
        return getUsefulInfo(infoFragments, keywordLocations, keywords);
    }

    private static int[] getKeywordLocations(String[] arguments, String[] keywords) {
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

    private static int[] makeEmptyArray(int numberOfKeywords) {
        int[] newArray = new int[numberOfKeywords];
        Arrays.fill(newArray, -1);
        return newArray;
    }

    private static boolean isUserInputValid(int[] keywordLocation, int firstOptionalIndex) {
        boolean isMustInputProvided = isMustInputProvided(keywordLocation, firstOptionalIndex);
        boolean isMustKeywordPositionOkay = isMustKeywordPositionOkay(keywordLocation, firstOptionalIndex);
        boolean isOptionalKeywordPositionOkay = isOptionalKeywordPositionOkay(keywordLocation, firstOptionalIndex);
        return (isMustInputProvided && isMustKeywordPositionOkay && isOptionalKeywordPositionOkay);
    }

    private static boolean isMustInputProvided(int[] keywordLocation, int firstOptionalArgumentIndex) {
        boolean isMustInputProvided = true;
        for (int i = 0; i < firstOptionalArgumentIndex; i++) {
            isMustInputProvided = isMustInputProvided && keywordLocation[i] != -1;
        }
        return isMustInputProvided;
    }

    private static boolean isOptionalKeywordPositionOkay(int[] keywordLocation, int firstOptionalArgumentIndex) {
        boolean isOptionalKeywordPositionOkay = true;
        for (int i = firstOptionalArgumentIndex; i < keywordLocation.length; i++) {
            boolean isCorrectPosition = keywordLocation[i] > keywordLocation[i - 1] || keywordLocation[i] == -1;
            isOptionalKeywordPositionOkay = isOptionalKeywordPositionOkay && isCorrectPosition;
        }
        return isOptionalKeywordPositionOkay;
    }

    private static boolean isMustKeywordPositionOkay(int[] keywordLocation, int firstOptionalArgumentIndex) {
        boolean isMustKeywordPositionOkay = true;
        for (int i = 0; i < firstOptionalArgumentIndex - 1; i++) {
            isMustKeywordPositionOkay = isMustKeywordPositionOkay && (keywordLocation[i] < keywordLocation[i + 1]);
        }
        return isMustKeywordPositionOkay;
    }

    private static String[] getUsefulInfo(String[] arguments, int[] keywordLocations, String[] keywords)
            throws InvalidArgumentException {
        String[] processedArguments = new String[keywords.length];
        for (int i = 0; i < processedArguments.length; i++) {
            if (keywordLocations[i] == -1) {
                processedArguments[i] = null;
                continue;
            }
            int endLocation = getEndLocation(arguments, keywordLocations, keywords, i);
            processedArguments[i] = extractInfo(arguments, keywords[i].length(), keywordLocations[i],
                    endLocation - 1);
            if (processedArguments[i].length() == 0) {
                String errorMsg = "Argument cannot be empty. Resource failed to be added!";
                throw new InvalidArgumentException(errorMsg);
            }
        }
        return processedArguments;
    }

    private static int getEndLocation(String[] arguments, int[] keywordLocations, String[] keywords, int i) {
        int nextIndex = i;
        int endLocation = -1;
        while (++nextIndex < keywords.length && endLocation == -1) {
            endLocation = keywordLocations[nextIndex];
        }

        return (endLocation != -1) ? endLocation : arguments.length;
    }

    private static String extractInfo(String[] arguments, int chopLocation, int from, int to) {
        String output = arguments[from].substring(chopLocation);
        for (int i = from + 1; i <= to; i++) {
            output = String.join(" ", output, arguments[i]);
        }
        return output;
    }
}
