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
}
