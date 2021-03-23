package seedu.duke;

import seedu.duke.resource.Resource;

import java.util.ArrayList;

public class Project {
    private final String projectName;
    private final ArrayList<Resource> resources = new ArrayList<>();

    public Project(String projectName, String projLink, String linkDescription) {
        this.projectName = projectName;
        this.resources.add(new Resource(projLink, linkDescription));
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public String getProjectName() {
        return projectName;
    }

    public void addResources(String projectUrl, String urlDescription) {
        resources.add(new Resource(projectUrl, urlDescription));
    }

    public boolean isUrlAlreadyExist(String projectUrl) {
        for (Resource resource : resources) {
            if (resource.getResourceLink().equals(projectUrl)) {
                return true;
            }
        }
        return false;
    }


    public boolean checkResourceExistsByIndex(int idx) {
        if (idx >= resources.size() || idx < 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getProjectName();
    }
}
