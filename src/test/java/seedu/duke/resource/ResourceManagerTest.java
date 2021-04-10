package seedu.duke.resource;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandHandler;
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
     * Test case 1 for printResourceList.
     *
     * When there are at least 1 resources in the list.
     *
     */
    @Test
    public void test1PrintResourceList() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        Resource resource1 = new Resource("www.google.com", "Search");
        Resource resource2 = new Resource("www.nus.com","School website");
        ArrayList<Resource> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);

        ResourceManager.printResourceList(resources);

        String targetString = "Resource(s):" + CommandHandler.NEW_LINE
                + "1): [" + LocalDate.now() + "] www.google.com (Description: Search)" + CommandHandler.NEW_LINE
                + "2): [" + LocalDate.now() + "] www.nus.com (Description: School website)" + CommandHandler.NEW_LINE;

        assertEquals(targetString,newOutputStream.toString());

        System.setOut(System.out);
    }

    //@@author jovanhuang
    /**
     * Test case 2 for printResourceList.
     *
     * When there are no resources in the list.
     *
     */
    @Test
    public void test2PrintResourceList() {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOutputStream));

        ArrayList<Resource> resources = new ArrayList<>();
        ResourceManager.printResourceList(resources);

        String targetString = "Resource(s):" + CommandHandler.NEW_LINE;

        assertEquals(targetString,newOutputStream.toString());

        System.setOut(System.out);
    }


}
