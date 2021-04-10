package seedu.duke.project;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectManagerTest {
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
}