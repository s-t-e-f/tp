package seedu.duke.project;

import seedu.duke.resource.Resource;

import java.util.ArrayList;

public class Project {
    private final String projectName;
    private final ArrayList<Resource> resources = new ArrayList<>();

    public Project(String projectName, String projLink, String description) {
        this.projectName = projectName;
        this.resources.add(new Resource(projLink, description));
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public String getProjectName() {
        return projectName;
    }

    public void addResources(String projectUrl, String description) {
        resources.add(new Resource(projectUrl, description));
    }

    public void addResourceObj(Resource resource) {
        resources.add(resource);
    }

    public boolean isUrlAlreadyExist(String projectUrl) {
        for (Resource resource : resources) {
            if (resource.getResourceLink().equals(projectUrl)) {
                return true;
            }
        }
        return false;
    }

    public void loadResource(Resource resource) {
        resources.add(resource);
    }

    public int getNumberOfResources() {
        return resources.size();
    }

    @Override
    public String toString() {
        return getProjectName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Project)) {
            return false;
        }

        Project compareTo = (Project) obj;

        return compareTo.getProjectName().equals(this.projectName);
    }
}
