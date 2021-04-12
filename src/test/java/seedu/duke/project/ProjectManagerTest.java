package seedu.duke.project;

import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.command.CommandHandler;
import seedu.duke.exception.NoProjectNameException;
import seedu.duke.exception.WrongInputFormatException;
import seedu.duke.ui.MainUi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectManagerTest {

    //@@author jovanhuang
    /**
     * Test cases for processProjectName.
     */
    @Test
    public void testProcessInputs() {
        //test case 1
        String[] infoFragments1 = {"CZ2003", "Documentation"};
        assertEquals("CZ2003 Documentation", ProjectManager.processInputs(infoFragments1));

        //test case 2
        String[] infoFragments2 = {""};
        assertEquals("", ProjectManager.processInputs(infoFragments2));

        //test case 3
        String[] infoFragments3 = {"CS2113"};
        assertEquals("CS2113", ProjectManager.processInputs(infoFragments3));
    }

    //@@author jovanhuang
    /**
     * Test cases for checkIfProjectNameEmpty.
     */
    @Test
    public void testCheckForInputsIsEmpty() {
        //test case 1
        String projectName1 = "CZ2003 Documentation";
        assertEquals(false, ProjectManager.checkIfStringIsEmpty(projectName1));

        //test case 2
        String projectName2 = "";
        assertEquals(true, ProjectManager.checkIfStringIsEmpty(projectName2));

        //test case 3
        String projectName3 = "CS2113";
        assertEquals(false, ProjectManager.checkIfStringIsEmpty(projectName3));
    }

    //@@author jovanhuang
    /**
     * Test case for printResourceListForAllProjects()
     * When no project exists in the list.
     */
    @Test
    public void testCase1PrintResourceListForAllProjects() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------"
                + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + CommandHandler.NEW_LINE;

        assertEquals(targetString,newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author jovanhuang
    /**
     * Test case for printResourceListForAllProjects()
     * When 1 project exists in the list.
     *
     */
    @Test
    public void testCase2PrintResourceListForAllProjects() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Documentation url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "The resource is added into the new project \"CS2113 Documentation\"." + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!\n"
                + "--------------------------------------------------------" + CommandHandler.NEW_LINE
                + "Project 1: CS2113 Documentation\n"
                + "Resource(s):\n"
                + "1): "
                + "[" + LocalDate.now() + "] "
                + "https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project)\n"
                + "--------------------------------------------------------"
                + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + CommandHandler.NEW_LINE;

        assertEquals(targetString,newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author jovanhuang
    /**
     * Test case for printResourceListForAllProjects()
     * When 2 projects exist in the list.
     *
     */
    @Test
    public void testCase3PrintResourceListForAllProjects() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String inputToCmd = "add p/CS2113 Documentation url/https://ay2021s2-cs2113-w10-3.github.io/tp/ d/Team Project\n"
                + "add p/IT3011 Reference url/https://google.com d/Kaggle\n"
                + "list-all\n"
                + "exit";

        System.setIn(new ByteArrayInputStream(inputToCmd.getBytes()));

        Duke.main(null);

        String targetString = MainUi.DUKE_STANDARD_HEADING
                + "The resource is added into the new project \"CS2113 Documentation\"." + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + "The resource is added into the new project \"IT3011 Reference\"." + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + "Here is the list of all project(s) and it's resource(s)!" + CommandHandler.NEW_LINE
                + "--------------------------------------------------------" + CommandHandler.NEW_LINE
                + "Project 1: CS2113 Documentation" + CommandHandler.NEW_LINE
                + "Resource(s):" + CommandHandler.NEW_LINE
                + "1): "
                + "[" + LocalDate.now() + "] "
                + "https://ay2021s2-cs2113-w10-3.github.io/tp/ (Description: Team Project)"
                + CommandHandler.NEW_LINE
                + "--------------------------------------------------------" + CommandHandler.NEW_LINE
                + "Project 2: IT3011 Reference" + CommandHandler.NEW_LINE
                + "Resource(s):" + CommandHandler.NEW_LINE
                + "1): "
                + "[" + LocalDate.now() + "] "
                + "https://google.com (Description: Kaggle)" + CommandHandler.NEW_LINE
                + "--------------------------------------------------------"
                + CommandHandler.NEW_LINE
                + MainUi.SIGNAL_FOR_USER_TO_INPUT
                + MainUi.EXIT_MESSAGE + CommandHandler.NEW_LINE;

        assertEquals(targetString,newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author jovanhuang
    /**
     * Test cases for validateAndExtractProjectNameInput().
     */
    @Test
    public void test1ValidateAndExtractProjectNameInput() throws NoProjectNameException, WrongInputFormatException {
        //Test case 1: when project name input is empty
        try {
            ProjectManager.validateAndExtractProjectNameInput("p/");
        } catch (NoProjectNameException e) {
            assertEquals(ProjectManager.NO_INPUT_FOR_PROJECT_NAME_ERROR_MESSAGE, e.getErrorMsg());
        }

        //Test case 2: when p/ is not used before project name
        try {
            ProjectManager.validateAndExtractProjectNameInput("CZ2003");
        } catch (WrongInputFormatException e) {
            assertEquals(ProjectManager.WRONG_INPUT_FORMAT, e.getErrorMsg());
        }

        //Test case 3: when correct input format is used
        try {
            String projectName = ProjectManager.validateAndExtractProjectNameInput("p/CZ2003");
            assertEquals("CZ2003", projectName);
        } catch (WrongInputFormatException e) {
            assertEquals("", e.getErrorMsg());
        } catch (NoProjectNameException e) {
            assertEquals("", e.getErrorMsg());
        }
    }

    //@@author s-t-e-f
    @Test
    public void testGetProjByName() {
        ArrayList<Project> projects = new ArrayList<>();
        Project testProject = new Project("CS2113", "www.test.com", "Test");
        projects.add(testProject);
        ProjectManager.setProjects(projects);

        assertEquals(testProject, ProjectManager.getProjByProjName("CS2113"));
    }
}