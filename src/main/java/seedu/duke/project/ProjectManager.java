package seedu.duke.project;

import seedu.duke.Duke;
import seedu.duke.resource.Resource;
import seedu.duke.resource.ResourceManager;

import java.util.ArrayList;

public abstract class ProjectManager {
    public static final String NEW_LINE = "\n";

    public static ArrayList<Project> projects;

    public static void getAllProjectsAndResourcesMatchingKeyword(String keyword, ArrayList<Project> projects) {
        int projectCount = 0;
        for (Project project : projects) {
            projectCount += 1;
            System.out.print("Project " + projectCount + ": " + project + NEW_LINE);
            ArrayList<Resource> resources = project.getResources();
            ResourceManager.printResourcesMatchingKeyword(resources, keyword);
        }
    }

    //@@author s-t-e-f
    /**
     * Search a project given the project name.
     * Return a project object if it is found.
     * Else, return null if the project name does not exist in the project list.
     * @param projName Name of the project to be searched
     * @return Project object with the specified project name
     */
    public static Project getProjByProjName(String projName) {
        for (Project project : projects) {
            if (project.getProjectName().equals(projName)) {
                return project;
            }
        }
        return null;
    }

    //@@author s-t-e-f
    /**
     * Delete all the resources in the project and the whole project from the project list.
     * @param proj Project to be deleted
     */
    public static void deleteWholeProject(Project proj) {
        proj.getResources().removeAll(proj.getResources());
        proj.getResources().removeAll(proj.getResources());
        System.out.printf("All the resources in %s has been deleted.\n", proj.getProjectName());
        projects.remove(proj);
    }

    //@@author NgManSing
    public static void newProject(String projectName, String projectUrl, String description) {
        projects.add(new Project(projectName, projectUrl, description));
    }

    //@@author NgManSing
    public static Project getProject(int projectIndex) {
        return projects.get(projectIndex);
    }

    //@@author NgManSing
    /**
     * Search a project with the provided project name within the project list. Index of the project is returned
     * if it is found.  if it is found. If no project in the list is named as the provided project name, -1 is returned.
     *
     * @param projectName Name of a Project
     * @return Index of the project or -1 if the project does not exist
     */
    public static int searchExistingProjectIndex(String projectName) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectName().equals(projectName)) {
                return i;
            }
        }
        return -1;
    }

    //@@author NgManSing
    public static void updateRecords() {
        projects = Duke.getProjects();
    }
}
