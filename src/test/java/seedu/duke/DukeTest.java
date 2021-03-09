package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        PrintStream originalOut;
        originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        String data = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(data.getBytes()));

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

        assertEquals(bos.toString(), targetString);

        System.setOut(originalOut);
    }

    @Test
    public void testAdd2Resource() {
        PrintStream originalOut;
        originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        String data = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "add p/CS2113 Group Project url/other website\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(data.getBytes()));

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

        assertEquals(bos.toString(), targetString);

        System.setOut(originalOut);
    }

    @Test
    public void testAddAndOverwrite1Resource() {
        PrintStream originalOut;
        originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        String data = "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "add p/CS2113 Group Project url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project for CS2113\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(data.getBytes()));

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

        assertEquals(bos.toString(), targetString);

        System.setOut(originalOut);
    }
}
