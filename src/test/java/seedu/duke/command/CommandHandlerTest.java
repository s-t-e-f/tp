package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.parser.InputParser;
import seedu.duke.project.Project;
import seedu.duke.ui.MainUi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandHandlerTest {

    @Test
    public void testInputSpacing() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = " \n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "Invalid input! Please type \"help\" for more details.\n"
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testInvalidInput() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "Hello! :)\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "Invalid input! Please type \"help\" for more details.\n"
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testInvalidInput2() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add url/p/ p/url/\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "Error: Resource failed to be added. "
                + "(Reason: Mandatory parameters are not provided or given provided in invalid order.)\n"
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    /**
     * Test Case 1 for printResourceListForAProject.
     */
    @Test
    public void testCase1ForPrintResourceListForAProject() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        //test case 1
        String inputToCmd = "add p/CS2113 Documentation url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "list p/CS2113 Documentation\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "The resource is added into the new project \"CS2113 Documentation\".\n"
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + "--------------------------------------------------------" + CommandHandler.NEW_LINE
                + "Project: CS2113 Documentation\n"
                + "Resource(s):\n"
                + "1): "
                + "[" + LocalDate.now() + "] "
                + "https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project)\n"
                + "--------------------------------------------------------\n"
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + CommandHandler.NEW_LINE;

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    /**
     * Test Case 2 for printResourceListForAProject.
     */
    @Test
    public void testCase2ForPrintResourceListForAProject() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "list CS2113 Documentation\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "Project not found in database!" + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + CommandHandler.NEW_LINE;

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    /**
     * Test Case 3 for printResourceListForAProject.
     */
    @Test
    public void testCase3ForPrintResourceListForAProject() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "list \n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "You did not key in the Project Name! Please type \"help\" for more details."
                + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + CommandHandler.NEW_LINE;

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void printResourceList() {
        //TODO
    }

    @Test
    public void printResourceListForAllProjects() {
        //TODO
    }
}