package seedu.duke.resource;

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
            return " (Description: " + resourceDescription + ")";
        }
        return "";
    }

    @Override
    public String toString() {
        return getResourceLink() + getResourceDescription();
    }
}
