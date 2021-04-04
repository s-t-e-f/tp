package seedu.duke.project;

import seedu.duke.Duke;
import seedu.duke.resource.Resource;
import seedu.duke.resource.ResourceManager;

import java.util.ArrayList;

public class ProjectManager {
    public static final String NEW_LINE = "\n";

    public static ArrayList<Project> projects = Duke.getProjects();

    public static void getAllProjectsAndResourcesMatchingKeyword(String keyword, ArrayList<Project> projects) {
        int projectCount = 0;
        for (Project project : projects) {
            projectCount += 1;
            System.out.print("Project " + projectCount + ": " + project + NEW_LINE);
            ArrayList<Resource> resources = project.getResources();
            ResourceManager.printResourcesMatchingKeyword(resources, keyword);
        }
    }

    //@@author stefanie
    public static Project getProjByProjName(String projName) {
        for (Project project : projects) {
            if (project.getProjectName().equals(projName)) {
                return project;
            }
        }
        return null;
    }

    //@@author stefanie
    /**
     * Delete all the resources in the specified project and remove the entire project from the list.
     * @param proj
     */
    public static void deleteWholeProject(Project proj) {
        proj.getResources().removeAll(proj.getResources());
        proj.getResources().removeAll(proj.getResources());
        System.out.printf("All the resources in %s has been deleted.\n", proj.getProjectName());
        projects.remove(proj);
        // Confirm that project is deleted.
        assert proj == null;
    }

}
