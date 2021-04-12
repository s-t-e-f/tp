package seedu.duke.resource;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandHandler;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.project.Project;
import seedu.duke.project.ProjectManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ResourceManagerTest {

    //@author yyixue
    @Test
    public void testPrintResourcesMatchingKeyword() {
        ArrayList<Resource> resources = new ArrayList<>();
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        Resource first = new Resource("github.com", "test1");
        Resource second = new Resource("nusmods.com", "test2");
        resources.add(first);
        resources.add(second);
        String keyword = "github";
        ResourceManager.printResourcesMatchingKeyword(resources, keyword);

        String targetString = "1): [" + LocalDate.now() + "] github.com (Description: test1)\n";

        assertEquals(targetString, newOutputStream.toString());

        System.setOut(System.out);
    }

    //@author yyixue
    @Test
    public void testPrintNoResourcesMatchingKeyword() {
        ArrayList<Resource> resources = new ArrayList<>();
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        Resource first = new Resource("github.com", "test1");
        Resource second = new Resource("nusmods.com", "test2");
        resources.add(first);
        resources.add(second);
        String keyword = "CS2113";
        ResourceManager.printResourcesMatchingKeyword(resources, keyword);

        String targetString = "No resources matching keyword \"CS2113\" found!\n";

        assertEquals(targetString, newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author jovanhuang

    /**
     * Test case 1 for printResourceList()
     * When there are at least 1 resources in the list.
     */
    @Test
    public void test1PrintResourceList() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        Resource resource1 = new Resource("www.google.com", "Search");
        Resource resource2 = new Resource("www.nus.com", "School website");
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);

        ResourceManager.printResourceList(resources);

        String targetString = "Resource(s):" + CommandHandler.NEW_LINE
                + "1): [" + LocalDate.now() + "] www.google.com (Description: Search)" + CommandHandler.NEW_LINE
                + "2): [" + LocalDate.now() + "] www.nus.com (Description: School website)" + CommandHandler.NEW_LINE;

        assertEquals(targetString, newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author jovanhuang

    /**
     * Test case 2 for printResourceList()
     * When there are no resources in the list.
     */
    @Test
    public void test2PrintResourceList() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        ArrayList<Resource> resources = new ArrayList<>();
        ResourceManager.printResourceList(resources);

        String targetString = "Resource(s):" + CommandHandler.NEW_LINE;

        assertEquals(targetString, newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author s-t-e-f
    @Test
    public void testDeleteEntireProject() throws InvalidArgumentException {

        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("CS2113", "www.test.com", "Test"));
        ProjectManager.setProjects(projects);

        assertEquals(1, ProjectManager.getProjByProjName("CS2113").getNumberOfResources());

        String[] projectInfo = {"CS2113", null};
        ResourceManager.deleteResource(projectInfo);

        assertEquals(null, ProjectManager.getProjByProjName("CS2113"));

    }

    //@@author s-t-e-f
    @Test
    public void testDeleteResource() throws InvalidArgumentException {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("CS2113", "www.test.com", "Test"));
        ProjectManager.setProjects(projects);

        assertEquals(1, ProjectManager.getProjByProjName("CS2113").getNumberOfResources());

        String[] projectInfo = {"CS2113", "1"};
        ResourceManager.deleteResource(projectInfo);

        assertEquals(0, ProjectManager.getProjByProjName("CS2113").getNumberOfResources());
    }

    //@@author s-t-e-f
    @Test
    public void testEditResourceUrlOnly() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("CS2113", "www.test.com", "Test"));
        ProjectManager.setProjects(projects);

        String[] projectInfo = {"CS2113", "1", "www.test2.com"};
        ResourceManager.editResource(projectInfo);

        Project p1 = new Project("CS2113", "1", "www.test2.com");
        p1.getResources().get(0).setResourceDescription("Test");

        assertEquals(p1, ProjectManager.getProjByProjName("CS2113"));

    }

    //@@author s-t-e-f
    @Test
    public void testEditResourceUrlAndDescription() {
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("CS2113", "www.test.com", "Test"));
        ProjectManager.setProjects(projects);

        String[] projectInfo = {"CS2113", "1", "www.test2.com", "Edited description"};
        ResourceManager.editResource(projectInfo);

        Project p1 = new Project("CS2113", "1", "www.test2.com");
        p1.getResources().get(0).setResourceDescription("Edited description");

        assertEquals(p1, ProjectManager.getProjByProjName("CS2113"));
    }

    //@@author NgManSing
    @Test
    public void testAddResourceWithValidInput() {
        String[] projectInfo = {"project", "https://www.google.com/", "cs2113", "true"};
        try {
            ResourceManager.addResource(projectInfo);
        } catch (InvalidArgumentException e) {
            fail("It should not throw any exception");
        }
    }

}
