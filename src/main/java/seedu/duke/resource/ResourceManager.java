package seedu.duke.resource;

import seedu.duke.Duke;
import seedu.duke.exception.ProjectNotFoundException;
import seedu.duke.exception.ResourceNotFoundException;
import seedu.duke.project.Project;
import seedu.duke.project.ProjectManager;

import java.security.KeyStore;
import java.util.ArrayList;

public class ResourceManager {

    public static final String NEW_LINE = "\n";

    public static ArrayList<Project> projects = Duke.getProjects();

    public static void printResourcesMatchingKeyword(ArrayList<Resource> resources, String keyword) {
        int resourceCount = 1;
        for (Resource resource : resources) {
            if (resource.checkKeywordMatch(keyword)) {
                System.out.print(resourceCount + "): " + resource + NEW_LINE);
                resourceCount += 1;
            }
        }
        if (resourceCount == 1) {
            System.out.printf("No resources matching keyword \"%s\" found!\n", keyword);
        }
    }

    //@@author stefanie
    public static void deleteResource(String[] projectInfo) {
        Project targetedProj;
        String projectName = projectInfo[0];
        int idx;

        targetedProj = ProjectManager.getProjByProjName(projectName);
        if (targetedProj == null) {
            ProjectNotFoundException.printProjNotFoundMsg();
            return;
        }

        try {
            if (projectInfo[1] != null) {
                idx = Integer.parseInt(projectInfo[1]) - 1;
                targetedProj.getResources().remove(idx);
                System.out.printf("The resource is deleted from the project \"%s\".\n", projectName);
            } else {
                // If index is not indicated, remove all resources from the specified project.
                ProjectManager.deleteWholeProject(targetedProj);
            }
        } catch (Exception e) {
            ResourceNotFoundException.printResourceNotFoundMsg();
            return;
        }
    }

    //@@author stefanie
    public static void editResource(String[] projectInfo) {
        Project targetedProj;
        Resource targetedResource;
        String projectName = projectInfo[0];

        targetedProj = ProjectManager.getProjByProjName(projectName);
        if (targetedProj == null) {
            ProjectNotFoundException.printProjNotFoundMsg();
            return;
        }

        try {
            int idx = Integer.parseInt(projectInfo[1]) - 1;
            targetedResource = targetedProj.getResources().get(idx);
            // Editing the url
            if (projectInfo[2] != null) {
                targetedResource.setResourceLink(projectInfo[2]);
            }
            // Editing the resource description
            if (projectInfo[3] != null) {
                targetedResource.setResourceDescription(projectInfo[3]);
            }
            // Both url/ and d/ are not specfied
            if (projectInfo[2] == null & projectInfo[3] == null) {
                System.out.print("The resource is not edited." + NEW_LINE);
                return;
            }
        } catch (Exception e) {
            ResourceNotFoundException.printResourceNotFoundMsg();
            return;
        }

        System.out.printf("The resource is successfully edited to : \n");
        System.out.printf("    " + targetedResource.toString() + NEW_LINE);

    }

}
