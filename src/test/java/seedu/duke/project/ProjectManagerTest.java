package seedu.duke.project;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectManagerTest {
    /**
     * Test cases for processProjectName.
     */
    @Test
    public void testProcessProjectName() {
        //test case 1
        String[] infoFragments1 = {"CZ2003", "Documentation"};
        assertEquals("CZ2003 Documentation", ProjectManager.processProjectName(infoFragments1));

        //test case 2
        String[] infoFragments2 = {""};
        assertEquals("", ProjectManager.processProjectName(infoFragments2));

        //test case 3
        String[] infoFragments3 = {"CS2113"};
        assertEquals("CS2113", ProjectManager.processProjectName(infoFragments3));
    }

    /**
     * Test cases for checkIfProjectNameEmpty.
     */
    @Test
    public void checkIfProjectNameEmpty() {
        //test case 1
        String projectName1 = "CZ2003 Documentation";
        assertEquals(false, ProjectManager.checkIfProjectNameEmpty(projectName1));

        //test case 2
        String projectName2 = "";
        assertEquals(true, ProjectManager.checkIfProjectNameEmpty(projectName2));

        //test case 3
        String projectName3 = "CS2113";
        assertEquals(false, ProjectManager.checkIfProjectNameEmpty(projectName3));

    }
}