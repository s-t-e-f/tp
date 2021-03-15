package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DukeTest {
    String dukeStandardHeading = Duke.LOGO_STRING + "\n"
            + Duke.PROJECT_TEAM_ID + "\n"
            + Duke.APP_NAME_AND_VERSION + "\n"
            + Duke.HOW_TO_GET_HELP + "\n"
            + Duke.SIGNAL_FOR_USER_TO_INPUT;

    @Test
    public void dummyTest() {
        assertTrue(true);
    }

    @Test
    public void testAdd1Resource() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = dukeStandardHeading
                + "The resource is added into the new project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project)\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testAdd2Resource() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "add p/CS2113 Group Project url/other website\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = dukeStandardHeading
                + "The resource is added into the new project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "The resource is added to the existing project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project)\n"
                + "2): other website\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testAddAndOverwrite1Resource() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project for CS2113\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = dukeStandardHeading
                + "The resource is added into the new project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "The resource of the project \"CS2113 Group Project\" is overwritten.\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project for CS2113)\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testDeleteNotFoundResource() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project for CS2113\n"
                + "add p/CS2113 Group Project url/https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html d/tp Website\n"
                + "list-all\n"
                + "delete p/CS2113 Group Project i/4\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = dukeStandardHeading
                + "The resource is added into the new project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "The resource is added to the existing project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project for CS2113)\n"
                + "2): https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html (Description: tp Website)\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Resource is not found. Please enter a valid index. \n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testDeleteNotFoundProject() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project for CS2113\n"
                + "add p/CS2113 Group Project url/https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html d/tp Website\n"
                + "list-all\n"
                + "delete p/CS2113 Individual Project i/4\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = dukeStandardHeading
                + "The resource is added into the new project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "The resource is added to the existing project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project for CS2113)\n"
                + "2): https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html (Description: tp Website)\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Project is not found ... \n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }

    @Test
    public void testDeleteFromProject() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project for CS2113\n"
                + "add p/CS2113 Group Project url/https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html d/tp Website\n"
                + "list-all\n"
                + "delete p/CS2113 Group Project i/1\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = dukeStandardHeading
                + "The resource is added into the new project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "The resource is added to the existing project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project for CS2113)\n"
                + "2): https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html (Description: tp Website)\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "The resource is deleted from the project \"CS2113 Group Project\".\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + "\n"
                + "Project 1: CS2113 Group Project\n"
                + "Resource(s):\n"
                + "1): https://nus-cs2113-ay2021s2.github.io/website/admin/tp-expectations.html (Description: tp Website)\n"
                + "--------------------------------------------------------\n"
                + Duke.SIGNAL_FOR_USER_TO_INPUT
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), targetString);

        System.setOut(System.out);
    }
  
    @Test
    public void testListAllCommands() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String helpExpectedOutput = "-----------------------------------------"
                + "-------------------------------\n"
                + "Here are the available commands:\n"
                + "add: Adds a resource to a project\n"
                + "\tFormat: add p/PROJECT_NAME url/URL_LINK [d/LINK_DESCRIPTION]\n"
                + "delete: Deletes a resource from the resource list based on the project.\n"
                + "\tFormat: delete p/PROJECT_NAME [i/INDEX]\n"
                + "list-all: Shows a list of all resources used in all projects.\n"
                + "exit: Exits the program.\n"
                + "------------------------------------------------------------------------\n\n";

        Duke.listAllCommands();
        assertEquals(newOutputStream.toString(), helpExpectedOutput);

        System.setOut(System.out);
    }

    @Test
    public void testExit() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String helpExpectedOutput = dukeStandardHeading
                + Duke.EXIT_MESSAGE + "\n";

        assertEquals(newOutputStream.toString(), helpExpectedOutput);

        System.setOut(System.out);
    }

}
