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
        String[] infoFragments = {"", "", "p/", "p/", "Happy", "Project", "", "", "", "url/",  "CCT", "p/", "p/"};
        String[] expectedProjectInfo = {"p/ Happy Project", "CCT p/ p/", null, null};
        try {
            String[] projectInfo = CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            assertEquals(Arrays.toString(projectInfo), Arrays.toString(expectedProjectInfo));
        } catch (InvalidArgumentException e) {
            fail("It should not have thrown any exception");
        }

    }

    @Test
    public void testEmptyCompulsoryArgument() {
        int firstOptionalKeyword = 2;
        String[] keywords = {"p/", "url/", "d/", "c/"};
        String[] infoFragments = {"p/", "url/123"};
        try {
            CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            fail("It should have thrown any exception");
        } catch (InvalidArgumentException e) {
            assertEquals(e.getErrorMsg(), "Argument cannot be empty.");
        }
    }

    @Test
    public void testEmptyOptionalArgument() {
        int firstOptionalKeyword = 2;
        String[] keywords = {"p/", "url/", "d/", "c/"};
        String[] infoFragments = {"p/123", "url/123", "d/"};
        try {
            CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            fail("It should have thrown any exception");
        } catch (InvalidArgumentException e) {
            assertEquals(e.getErrorMsg(), "Argument cannot be empty.");
        }
    }

    @Test
    public void testCompulsoryArgumentMissing() {
        int firstOptionalKeyword = 2;
        String[] keywords = {"p/", "url/", "d/", "c/"};
        String[] infoFragments = {"url/123", "d/Happy"};
        try {
            CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            fail("It should have thrown any exception");
        } catch (InvalidArgumentException e) {
            assertEquals(e.getErrorMsg(), "Mandatory parameters are not provided or given provided in invalid "
                    + "order.");
        }
    }

    @Test
    public void testInvalidArgumentOrder() {
        int firstOptionalKeyword = 2;
        String[] keywords = {"p/", "url/", "d/", "c/"};
        String[] infoFragments = {"url/123", "d/Happy", "p/Project"};
        try {
            CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            fail("It should have thrown any exception");
        } catch (InvalidArgumentException e) {
            assertEquals(e.getErrorMsg(), "Mandatory parameters are not provided or given provided in invalid "
                    + "order.");
        }
    }
}
