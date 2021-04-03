package seedu.duke.resource;

import java.util.ArrayList;

public class ResourceManager {

    public static final String NEW_LINE = "\n";

    public static void printResourcesMatchingKeyword(ArrayList<Resource> resources, String keyword) {
        int resourceCount = 1;
        for (Resource resource : resources) {
            if (resource.checkKeywordMatch(keyword)) {
                System.out.print(resourceCount + "): " + resource + NEW_LINE);
                resourceCount += 1;
            }
        }
        if (resourceCount == 1) {
            System.out.printf("No resources matching keyword \"%s\" found!\n", keyword);
        }
    }


}
