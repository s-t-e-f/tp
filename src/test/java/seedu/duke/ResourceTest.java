package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.resource.Resource;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
