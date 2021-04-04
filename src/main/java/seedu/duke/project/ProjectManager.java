package seedu.duke.project;

import seedu.duke.resource.Resource;
import seedu.duke.resource.ResourceManager;

import java.util.ArrayList;

public class ProjectManager {
    public static final String NEW_LINE = "\n";

    public static void getAllProjectsAndResourcesMatchingKeyword(String keyword, ArrayList<Project> projects) {
        int projectCount = 0;
        for (Project project : projects) {
            projectCount += 1;
            System.out.print("Project " + projectCount + ": " + project + NEW_LINE);
            ArrayList<Resource> resources = project.getResources();
            ResourceManager.printResourcesMatchingKeyword(resources, keyword);
        }
    }
}
