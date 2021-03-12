package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
