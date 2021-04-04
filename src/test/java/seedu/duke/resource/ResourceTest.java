package seedu.duke.resource;

import org.junit.jupiter.api.Test;
import seedu.duke.project.Project;
import seedu.duke.resource.Resource;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceTest {

    @Test
    public void testStringConversion() {
        Project project = new Project("CZ2003", "www.google.com", "Search Engine");
        ArrayList<Resource> resources = project.getResources();
        assertEquals("["
                        + "[" + LocalDate.now() + "] "
                        + "www.google.com (Description: Search Engine)]",
                resources.toString());
    }

    @Test
    public void testEqualsSameLink() {
        Resource first = new Resource("github.com", "test1");
        Resource second = new Resource("github.com", "test2");
        boolean result = first.equals(second);
        assertTrue(result);
    }

    @Test
    public void testEqualsDifferentLink() {
        Resource first = new Resource("github.com", "test1");
        Resource second = new Resource("nusmods.com", "test2");
        boolean result = first.equals(second);
        assertFalse(result);
    }

    @Test
    public void testEqualsNotResourceInstance() {
        Object obj = new Object();
        Resource first = new Resource("github.com", "test1");
        boolean result = first.equals(obj);
        assertFalse(result);
    }

    @Test
    public void testEquals() {
        Resource first = new Resource("github.com", "test1");
        boolean result = first.equals(first);
        assertTrue(result);
    }

}
