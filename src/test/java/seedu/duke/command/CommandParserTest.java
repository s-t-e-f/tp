package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.parser.CommandParser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandParserTest {
    @Test
    public void testDecodeInfoFragments() {
        int firstOptionalKeyword = 2;
        String[] keywords = {"p/", "url/", "d/", "c/"};
        String[] infoFragments = {"", "", "", "", "", "", "p/", "p/", "Happy", "Project", "", "", "", "url/",
                "CCT", "p/", "p/"};
        String[] expectedProjectInfo = {"p/ Happy Project", "CCT p/ p/", null, null};
        try {
            String[] projectInfo = CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            assertEquals(Arrays.toString(projectInfo), Arrays.toString(expectedProjectInfo));
        } catch (InvalidArgumentException e) {
            fail("It Should not have thrown any exception");
        }

    }
}
