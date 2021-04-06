package seedu.duke.resource;

import org.junit.jupiter.api.Test;
import seedu.duke.resource.ResourceManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceManagerTest {

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

    @Test
    public void testPrintNoResourcesMatchingKeyword() {
        ArrayList<Resource> resources = new ArrayList<>();
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        String targetString = "No resources matching keyword \"CS2113\" found!\n";

        Resource first = new Resource("github.com", "test1");
        Resource second = new Resource("nusmods.com", "test2");
        resources.add(first);
        resources.add(second);
        String keyword = "CS2113";
        ResourceManager.printResourcesMatchingKeyword(resources, keyword);

        assertEquals(targetString, newOutputStream.toString());

        System.setOut(System.out);
    }

}
