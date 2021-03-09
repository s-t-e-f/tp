package seedu.duke;

public class Resource {

    private String resourceLink;
    private String resourceDescription;

    public Resource(String resourceLink, String resourceDescription) {
        this.resourceLink = resourceLink;
        this.resourceDescription = resourceDescription;
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public boolean checkIfDescriptionExists() {
        if (resourceDescription == null) {
            return false;
        }
        return true;
    }

    public String getResourceDescription() {
        if (checkIfDescriptionExists()) {
            return resourceDescription;
        }
        else {
            return "No description available";
        }

    }

    @Override
    public String toString() {
        return getResourceLink() + " (" + getResourceDescription() + ")";
    }
}
