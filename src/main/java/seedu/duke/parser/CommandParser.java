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
            String errorMsg = "Mandatory parameters are not provided or given provided in invalid order.";
            throw new InvalidArgumentException(errorMsg);
        }
        return getUsefulInfo(infoFragments, keywordLocations, keywords);
    }

    private static int[] getKeywordLocations(String[] arguments, String[] keywords) {
        int[] keywordLocations = makeEmptyArray(keywords.length);
        int index = 0;
        do {
            updateKeywordLocation(arguments, keywords, keywordLocations, index);
            index++;
        } while (index < keywords.length);
        return keywordLocations;
    }

    private static void updateKeywordLocation(String[] arguments, String[] keywords, int[] keyLocations, int index) {
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].indexOf(keywords[index]) == 0) {
                keyLocations[index] = i;
                break;
            }
        }
    }

    private static int[] makeEmptyArray(int numberOfKeywords) {
        int[] newArray = new int[numberOfKeywords];
        Arrays.fill(newArray, -1);
        return newArray;
    }

    private static boolean isUserInputValid(int[] keywordLocation, int firstOptionalIndex) {
        boolean isCompulsoryInputProvided = isCompulsoryInputProvided(keywordLocation, firstOptionalIndex);
        boolean isCompulsoryKeywordPositionOkay = isCompulsoryKeywordPositionOkay(keywordLocation, firstOptionalIndex);
        boolean isOptionalKeywordPositionOkay = isOptionalKeywordPositionOkay(keywordLocation, firstOptionalIndex);
        boolean isOverallPositionOkay = isOverallPositionOkay(keywordLocation, firstOptionalIndex);
        return isCompulsoryInputProvided && isCompulsoryKeywordPositionOkay && isOptionalKeywordPositionOkay
                && isOverallPositionOkay;
    }

    private static boolean isCompulsoryInputProvided(int[] keywordLocation, int firstOptionalArgumentIndex) {
        boolean isCompulsoryInputProvided = true;
        for (int i = 0; i < firstOptionalArgumentIndex; i++) {
            isCompulsoryInputProvided = isCompulsoryInputProvided && keywordLocation[i] != -1;
        }
        return isCompulsoryInputProvided;
    }

    private static boolean isCompulsoryKeywordPositionOkay(int[] keywordLocation, int firstOptionalArgumentIndex) {
        boolean isCompulsoryKeywordPositionOkay = true;
        for (int i = 0; i < firstOptionalArgumentIndex - 1; i++) {
            isCompulsoryKeywordPositionOkay = isCompulsoryKeywordPositionOkay
                    && (keywordLocation[i] < keywordLocation[i + 1]);
        }
        return isCompulsoryKeywordPositionOkay;
    }

    private static boolean isOptionalKeywordPositionOkay(int[] keywordLocation, int firstOptionalIndex) {
        boolean isOptionalKeywordPositionOkay = true;
        for (int i = firstOptionalIndex; i < keywordLocation.length; i++) {
            boolean isCorrectPosition = keywordLocation[i] > keywordLocation[i - 1] || keywordLocation[i] == -1;
            isOptionalKeywordPositionOkay = isOptionalKeywordPositionOkay && isCorrectPosition;
        }
        return isOptionalKeywordPositionOkay;
    }

    private static boolean isOverallPositionOkay(int[] keywordLocation, int firstOptionalIndex) {
        boolean hasOptional = false;
        int firstNonNullOptionalIndex = firstOptionalIndex;
        for (; firstNonNullOptionalIndex < keywordLocation.length; firstNonNullOptionalIndex++) {
            if (keywordLocation[firstNonNullOptionalIndex] != -1) {
                hasOptional = true;
                break;
            }
        }
        return !hasOptional || keywordLocation[firstOptionalIndex - 1] < keywordLocation[firstNonNullOptionalIndex];
    }

    private static String[] getUsefulInfo(String[] arguments, int[] keywordLocations, String[] keywords)
            throws InvalidArgumentException {
        String[] cleanArguments = new String[keywords.length];
        for (int i = 0; i < cleanArguments.length; i++) {
            if (keywordLocations[i] == -1) {
                cleanArguments[i] = null;
                continue;
            }

            int endLocation = getEndLocation(arguments, keywordLocations, keywords, i);
            String info = extractInfo(arguments, keywords[i].length(), keywordLocations[i], endLocation - 1).trim();
            cleanArguments[i] = info;

            if (cleanArguments[i].length() == 0) {
                String errorMsg = "Argument cannot be empty.";
                throw new InvalidArgumentException(errorMsg);
            }
        }
        return cleanArguments;
    }

    private static int getEndLocation(String[] arguments, int[] keywordLocations, String[] keywords, int i) {
        int nextIndex = i;
        int endLocation = -1;
        while (++nextIndex < keywords.length && endLocation == -1) {
            endLocation = keywordLocations[nextIndex];
        }

        return (endLocation != -1) ? endLocation : arguments.length;
    }

    private static String extractInfo(String[] arguments, int splitLocation, int from, int to) {
        String output = arguments[from].substring(splitLocation);
        for (int i = from + 1; i <= to; i++) {
            output = String.join(" ", output, arguments[i]);
        }
        return output;
    }
}
