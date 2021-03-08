package seedu.duke;

import java.util.ArrayList;

public class Project {
    private final String projectName;
    private final ArrayList<Resource> resources = new ArrayList<>();

    public Project(String projectName, String projLink, String linkDescription) {
        this.projectName = projectName;
        this.resources.add(new Resource(projLink, linkDescription));
    }

    public String getProjectName() {
        return projectName;
    }

    public void addResources(String projectURL, String urlDescription) {
        resources.add(new Resource(projectURL, urlDescription));
    }

    public boolean isURLAlreadyExist(String projectURL) {
        for (Resource resource : resources) {
            if (resource.getResourceLink().equals(projectURL)) {
                return true;
            }
        }
        return false;
    }
}
