package seedu.duke.input;

import org.junit.jupiter.api.Test;
import seedu.duke.parser.InputParser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    @Test
    public void testSpaceInput() {
        String rawInput = " ";
        InputParser inputParser = new InputParser(rawInput);
        assertNull(inputParser.getCommand());
        assertNull(inputParser.getInfoFragments());
    }

    @Test
    public void testNullInput() {
        String rawInput = null;
        try{
            new InputParser(rawInput);
            fail("It should have thrown any exception");
        } catch (AssertionError e){
            System.out.println("Test OK");
        }
    }

    @Test
    public void testInput() {
        String rawInput = "    add    p/ p/ Happy Project    url/ CCT p/ p/ ";
        InputParser inputParser = new InputParser(rawInput);
        String command = "add";
        assertEquals(inputParser.getCommand(), command);
        String[] infoFragments = {"", "", "", "p/", "p/", "Happy", "Project", "", "", "", "url/", "CCT", "p/", "p/"};
        assertEquals(Arrays.toString(inputParser.getInfoFragments()), Arrays.toString(infoFragments));
    }
}
