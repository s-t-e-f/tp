package seedu.duke.project;

import seedu.duke.Duke;
import seedu.duke.command.CommandHandler;
import seedu.duke.exception.WrongInputFormatException;
import seedu.duke.exception.NoProjectNameException;
import seedu.duke.exception.ProjectNotFoundException;
import seedu.duke.resource.Resource;
import seedu.duke.resource.ResourceManager;

import java.util.ArrayList;

import static seedu.duke.command.CommandHandler.printDivider;

public class ProjectManager {
    public static final int LIST_PARAMETER_STARTING_INDEX = 0;
    public static final int LIST_PARAMETER_ENDING_INDEX = 2;
    public static final int MINIMUM_LIST_PARAMETER_LENGTH = 2;
    public static final int PROJECT_NAME_STARTING_INDEX = 2;
    public static final int INITIAL_PROJECT_COUNT = 0;
    public static final int ONE_INCREMENT = 1;

    public static final String NEW_LINE = "\n";
    public static final String LIST_PARAMETER = "p/";
    public static final String EMPTY_STRING = "";
    public static final String ONE_WHITE_SPACE = " ";
    public static final String MESSAGE_FOR_PRINTING_ALL_PROJECT_RESOURCES =
            "Here is the list of all project(s) and it's resource(s)!" + NEW_LINE;
    public static final String NO_INPUT_FOR_PROJECT_NAME_ERROR_MESSAGE = "You did not key in the Project Name! "
            + "Please type \"help\" for more details." + CommandHandler.NEW_LINE;
    public static final String PROJECT_NOT_FOUND_ERROR_MESSAGE = "Project not found in database!"
            + CommandHandler.NEW_LINE;
    public static final String WRONG_INPUT_FORMAT = "You did not insert 'p/' before the project name!"
            + " Please type \"help\" for more details. " + CommandHandler.NEW_LINE;

    private static ArrayList<Project> projects;

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    public static void setProjects(ArrayList<Project> projectsToUpdate) {
        projects = projectsToUpdate;
    }

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
        projects = Duke.getProjects();
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
        projects = Duke.getProjects();
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

    //@@author jovanhuang
    /**
     * This method will print the resource list for all projects.
     */
    public static void printResourceListForAllProjects() {
        int projectCount = INITIAL_PROJECT_COUNT;
        System.out.print(MESSAGE_FOR_PRINTING_ALL_PROJECT_RESOURCES);
        printDivider();
        for (Project project : projects) {
            projectCount += ONE_INCREMENT;
            System.out.print("Project " + projectCount + ": " + project + NEW_LINE);
            ArrayList<Resource> resources = project.getResources();
            ResourceManager.printResourceList(resources);
            printDivider();
        }
        assert true;
    }

    //@@author jovanhuang
    /**
     * This method will print the resources for a particular project.
     *
     * @param infoFragments is an string array of input from users.
     *
     * @throws NoProjectNameException   when user did not enter project name.
     * @throws ProjectNotFoundException when project is not found in database.
     */
    public static void printResourceListForAProject(String[] infoFragments)
            throws NoProjectNameException, ProjectNotFoundException, WrongInputFormatException {

        String userInputs = processInputs(infoFragments);
        String projectName = validateAndExtractProjectNameInput(userInputs);
        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                printDivider();
                System.out.print("Project: " + projectName + NEW_LINE);
                ArrayList<Resource> resources = project.getResources();
                ResourceManager.printResourceList(resources);
                printDivider();
                return;
            }
        }
        throw new ProjectNotFoundException(PROJECT_NOT_FOUND_ERROR_MESSAGE);
    }

    //@@author jovanhuang
    /**
     * This is a helper method to help check if user has the correct input for list PROJECT_NAME feature.
     * @param userInputs is user input.
     * @return projectName
     * @throws WrongInputFormatException thrown if format not followed.
     * @throws NoProjectNameException thrown if no project name is provided.
     */
    public static String validateAndExtractProjectNameInput(String userInputs)
            throws WrongInputFormatException, NoProjectNameException {
        if (userInputs.length() < MINIMUM_LIST_PARAMETER_LENGTH) {
            throw new WrongInputFormatException(WRONG_INPUT_FORMAT);
        }

        String parameters = userInputs.substring(LIST_PARAMETER_STARTING_INDEX,LIST_PARAMETER_ENDING_INDEX);
        if (!parameters.equals(LIST_PARAMETER)) {
            throw new WrongInputFormatException(WRONG_INPUT_FORMAT);
        }

        String projectName = userInputs.substring(PROJECT_NAME_STARTING_INDEX);
        boolean isProjectNameEmpty = checkIfStringIsEmpty(projectName);

        if (isProjectNameEmpty) {
            throw new NoProjectNameException(NO_INPUT_FOR_PROJECT_NAME_ERROR_MESSAGE);
        }
        return projectName;
    }

    //@@author jovanhuang
    /**
     * This method will return the userInput.
     *
     * @return user inputs.
     */
    public static String processInputs(String[] infoFragments) {
        return String.join(ONE_WHITE_SPACE, infoFragments);
    }

    //@@author jovanhuang
    /**
     * This method will check if string is empty.
     *
     * @param string is user's input.
     * @return true if empty, false if not empty.
     */
    public static boolean checkIfStringIsEmpty(String string) {
        return string.equals(EMPTY_STRING);
    }
}
