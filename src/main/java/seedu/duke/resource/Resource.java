package seedu.duke.resource;

import java.time.LocalDate;

public class Resource {

    private String resourceLink;
    private String resourceDescription;
    private LocalDate dateOfCreation;

    public Resource(String resourceLink, String resourceDescription) {
        this.resourceLink = resourceLink;
        this.resourceDescription = resourceDescription;
        this.dateOfCreation = LocalDate.now();
    }

    public Resource(String resourceLink, String resourceDescription, LocalDate dateOfCreation) {
        this.resourceLink = resourceLink;
        this.resourceDescription = resourceDescription;
        this.dateOfCreation = dateOfCreation;
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

    public boolean checkKeywordMatch(String keyword) {
        if (resourceDescription != null) {
            return resourceDescription.toLowerCase().contains(keyword.toLowerCase()) ||
                    resourceLink.toLowerCase().contains(keyword.toLowerCase());
        } else {
            return false;
        }
    }


    public String getResourceDescriptionOnly() {
        return resourceDescription == null ? "" : resourceDescription;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public String toString() {
        return "[" + dateOfCreation + "] " + getResourceLink() + getResourceDescription();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Resource)) {
            return false;
        }

        Resource compareTo = (Resource) obj;

        return compareTo.getResourceLink().equals(this.resourceLink);
    }
}
