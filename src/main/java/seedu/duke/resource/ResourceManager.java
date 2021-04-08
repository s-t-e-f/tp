package seedu.duke.resource;

import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.ProjectNotFoundException;
import seedu.duke.exception.ResourceNotFoundException;
import seedu.duke.project.Project;
import seedu.duke.project.ProjectManager;

import java.util.ArrayList;

public abstract class ResourceManager {

    public static final String NEW_LINE = "\n";

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

    //@@author s-t-e-f
    /**
     * Delete resource(s) from a specified project.
     * If specified project is not found in the project list, throws ProjectNotFoundException.
     * If specified resource is not found in the resource list of that project (i.e. invalid index),
     * throws ResourceNotFoundException.
     * If no index is specified, delete the entire targeted project from the project list.
     * @param projectInfo Processed user's command
     */
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

    //@@author s-t-e-f
    /**
     * Edit the url/ description of a resource from a specified project.
     * If specified project is not found in the project list, throws ProjectNotFoundException.
     * If specified resource is not found in the resource list of that project (i.e. invalid index),
     * throws ResourceNotFoundException.
     * If both url/ and d/ are not specified, the resource is not edited.
     * @param projectInfo Processed user's command
     */
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

    //@@author NgManSing
    public static void addResource(String[] projectInfo) throws InvalidArgumentException {
        assert projectInfo != null;
        String projectName = projectInfo[0];
        String projectUrl = projectInfo[1];
        String descriptionOfUrl = projectInfo[2];
        String checkString = projectInfo[3];

        if (checkString != null && checkString.equals("true")) {
            checkIfUrlValid(projectUrl);
        }

        int projectIndex = ProjectManager.searchExistingProjectIndex(projectName);
        if (projectIndex == -1) {
            createNewProject(projectName, projectUrl, descriptionOfUrl);
            return;
        }

        boolean isUrlAlreadyExist = isUrlAlreadyExist(projectIndex, projectUrl);
        if (isUrlAlreadyExist) {
            promptUserUrlAlreadyExist();
        } else {
            addNewResource(projectName, projectUrl, descriptionOfUrl, projectIndex);
        }
    }

    //@@author NgManSing
    private static void checkIfUrlValid(String projectUrl) throws InvalidArgumentException {
        try {
            (new java.net.URL(projectUrl)).openStream().close();
        } catch (Exception e) {
            throw new InvalidArgumentException("URL provided is not a valid URL.");
        }
    }

    //@@author NgManSing
    private static void createNewProject(String projectName, String projectUrl, String description) {
        ProjectManager.newProject(projectName, projectUrl, description);
        System.out.printf("The resource is added into the new project \"%s\".\n", projectName);
    }
    //@@author NgManSing

    private static void promptUserUrlAlreadyExist() {
        System.out.print("A resource with The same URL has already existed in its resource list. "
                + "If you want to edit the resource, please use \"edit\" command." + NEW_LINE);
    }

    //@@author NgManSing
    private static void addNewResource(String projectName, String projectUrl, String description, int projectIndex) {
        Project targetProject = ProjectManager.getProject(projectIndex);
        targetProject.addResources(projectUrl, description);
        System.out.printf("The resource is added to the existing project \"%s\".\n", projectName);
    }

    //@@author NgManSing
    private static boolean isUrlAlreadyExist(int projectIndex, String projectUrl) {
        return ProjectManager.getProject(projectIndex).isUrlAlreadyExist(projectUrl);
    }

}
