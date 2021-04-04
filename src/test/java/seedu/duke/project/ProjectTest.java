package seedu.duke.project;

import org.junit.jupiter.api.Test;
import seedu.duke.project.Project;
import seedu.duke.resource.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {

    @Test
    public void testStringConversion() {
        assertEquals("CZ2003",
                new Project("CZ2003", "www.google.com", "Search Engine").toString());
    }

    @Test
    void getProjectName() {
        assertEquals("CZ2003",
                new Project("CZ2003", "www.google.com", "Search Engine").getProjectName());
    }

    @Test
    void testEqualsSameName() {
        Project first = new Project("test1", "github.com", "test");
        Project second = new Project("test1", "nusmods.com", "test1");
        assertEquals(first, second);
    }

    @Test
    void testNotEqualsDifferentName() {
        Project first = new Project("test1", "github.com", "test");
        Project second = new Project("test2", "github.com", "test");
        assertNotEquals(second, first);
    }

    @Test
    void testAddResourceObj() {
        Project project = new Project("test1");
        project.addResourceObj(new Resource("nusmods.com","test"));
        Resource resourceToTest = new Resource("nusmods.com", "test");
        assertEquals(resourceToTest, project.getResources().get(0));
    }

}
