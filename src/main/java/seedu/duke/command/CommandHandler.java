package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.project.Project;
import seedu.duke.exception.InvalidArgumentException;
import seedu.duke.exception.NoProjectNameException;
import seedu.duke.exception.ProjectNotFoundException;
import seedu.duke.parser.CommandParser;
import seedu.duke.parser.InputParser;
import seedu.duke.project.ProjectManager;
import seedu.duke.resource.Resource;
import seedu.duke.resource.ResourceManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.MainUi;

import java.util.ArrayList;
import java.util.Locale;

public class CommandHandler {
    private static final String ADD_COMMAND = "add";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "exit";
    private static final String LIST_ALL_COMMAND = "list-all";
    private static final String HELP_COMMAND = "help";
    private static final String LIST_ONE_PROJECT_COMMAND = "list";
    private static final String FIND_COMMAND = "find";
    private static final String EDIT_COMMAND = "edit";
    private static final String SAVE_COMMAND = "save";
    private static final String LOAD_COMMAND = "load";
    public static final String NEW_LINE = "\n";
    public static final String PROJECT_NOT_FOUND_ERROR_MESSAGE = "Project not found in database!" + NEW_LINE;
    public static final String NO_INPUT_FOR_PROJECT_NAME_ERROR_MESSAGE = "You did not key in the Project Name! "
            + "Please type \"help\" for more details." + NEW_LINE;
    public static final String DIVIDER = "--------------------------------------------------------";
    String command;
    String[] infoFragments;
    private final ArrayList<Project> projects;

    public CommandHandler(InputParser userInput, ArrayList<Project> projects) {
        this.command = userInput.getCommand();
        this.infoFragments = userInput.getInfoFragments();
        this.projects = projects;
    }

    public String[] getInfoFragments() {
        return infoFragments;
    }

    //@@author NgManSing
    /**
     * Process the command given by the user if it is not null. A boolean value is returned to indicate whether to
     * continue looping, which is based on what command is executed. If command is null, the method returns true.
     *
     * @return Whether to continue looping
     */
    public boolean processCommand() {
        boolean isLoop;
        if (isCommandNull()) {
            promptUserInvalidInput();
            return true;
        }

        isLoop = switchCommand();
        return isLoop;
    }

    //@@author NgManSing
    private boolean switchCommand() {
        boolean isLoop = true;
        switch (this.command) {
        case ADD_COMMAND:
            processInputBeforeAdding();
            break;
        case DELETE_COMMAND:
            processInputBeforeDeleting();
            break;
        case EXIT_COMMAND:
            MainUi.showExitMessage();
            isLoop = false;
            break;
        case LIST_ALL_COMMAND:
            printResourceListForAllProjects();
            break;
        case LIST_ONE_PROJECT_COMMAND:
            listProjectResource();
            break;
        case EDIT_COMMAND:
            processInputBeforeEditing();
            break;
        case FIND_COMMAND:
            processInputBeforeFinding();
            break;
        case HELP_COMMAND:
            listAllCommands();
            break;
        case SAVE_COMMAND:
            Storage.updateStorage(Duke.getProjects());
            break;
        case LOAD_COMMAND:
            Duke.setProjects(Storage.readFromStorage());
            break;
        default:
            promptUserInvalidInput();
            break;
        }
        return isLoop;
    }

    //@@author NgManSing
    private boolean isCommandNull() {
        return command == null;
    }

    //@@author
    private void listProjectResource() {
        try {
            printResourceListForAProject();
        } catch (NoProjectNameException e) {
            System.out.print(NO_INPUT_FOR_PROJECT_NAME_ERROR_MESSAGE);
        } catch (ProjectNotFoundException e) {
            System.out.print(PROJECT_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    //@@author jovanhuang

    /**
     * This method will return the project name from userInput.
     *
     * @return Project Name is the name of the project.
     */
    public String processProjectName(String[] infoFragments) {
        String projectName = String.join(" ", infoFragments);
        return projectName;
    }

    //@@author NgManSing
    private void processInputBeforeAdding() {
        String[] keywords = {"p/", "url/", "d/", "c/"};
        int firstOptionalKeyword = 2;
        String[] projectInfo;
        try {
            projectInfo = CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
            ResourceManager.addResource(projectInfo);
        } catch (InvalidArgumentException e) {
            printErrorMsg("Resource failed to be added. (Reason: " + e.getErrorMsg() + ")");
        }
    }

    //@@author stefanie
    private void processInputBeforeDeleting() {
        String[] keywords = {"p/", "i/"};
        int firstOptionalKeyword = 1;
        String[] projectInfo;
        try {
            projectInfo = CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
        } catch (InvalidArgumentException e) {
            printErrorMsg("Resource failed to be deleted. (Reason: " + e.getErrorMsg() + ")");
            return;
        }

        ResourceManager.deleteResource(projectInfo);
    }

    //@@author stefanie
    private void processInputBeforeEditing() {
        String[] keywords = {"p/", "i/", "url/", "d/"};
        int firstOptionalKeyword = 2;
        String[] projectInfo;
        try {
            projectInfo = CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
        } catch (InvalidArgumentException e) {
            printErrorMsg("Resource failed to be edited. (Reason: " + e.getErrorMsg() + ")");
            return;
        }

        ResourceManager.editResource(projectInfo);
    }

    //@@author jovanhuang
    /**
     * This method will print the resources for a particular project.
     *
     * @throws NoProjectNameException   when user did not enter project name.
     * @throws ProjectNotFoundException when project is not found in database.
     */
    public void printResourceListForAProject() throws NoProjectNameException, ProjectNotFoundException {
        String projectName = processProjectName(getInfoFragments());
        boolean isProjectNameEmpty = checkIfProjectNameEmpty(projectName);
        if (isProjectNameEmpty) {
            throw new NoProjectNameException();
        }
        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                printDivider();
                System.out.print("Project: " + projectName + NEW_LINE);
                ArrayList<Resource> resources = project.getResources();
                printResourceList(resources);
                printDivider();
                return;
            }
        }
        throw new ProjectNotFoundException();
    }

    //@@author jovanhuang
    /**
     * This method will check if project name is empty.
     *
     * @param projectName This string user's input for projectName.
     * @return true if empty, false if not empty.
     */
    public boolean checkIfProjectNameEmpty(String projectName) {
        boolean isProjectNameEmpty = projectName.equals("");
        return isProjectNameEmpty;
    }

    //@@author jovanhuang
    /**
     * This method will print divider.
     */
    private void printDivider() {
        System.out.print(DIVIDER + NEW_LINE);
    }

    //@@author jovanhuang
    /**
     * This method will print the resource list for all projects.
     */
    public void printResourceListForAllProjects() {
        int projectCount = 0;
        System.out.print("Here is the list of all project(s) and it's resource(s)!" + NEW_LINE);
        printDivider();
        for (Project project : projects) {
            projectCount += 1;
            System.out.print("Project " + projectCount + ": " + project + NEW_LINE);
            ArrayList<Resource> resources = project.getResources();
            printResourceList(resources);
            printDivider();
        }
        assert true;
    }

    //@@author jovanhuang
    /**
     * This is a helper method that loops through a resource list and print it out.
     *
     * @param resources an arraylist containing resources for a project.
     */
    public static void printResourceList(ArrayList<Resource> resources) {
        System.out.print("Resource(s):" + NEW_LINE);
        int resourceCount = 1;
        for (Resource resource : resources) {
            System.out.print(resourceCount + "): " + resource + NEW_LINE);
            resourceCount += 1;
        }
        assert true;
    }

    //@@author
    public void listAllCommands() {
        MainUi.listAllCommands();
    }

    //@@author Yan Yi Xue
    private void processInputBeforeFinding() {
        String[] keywords = {"k/", "p/"};
        int firstOptionalKeyword = 1;
        String[] keywordInfo;
        try {
            keywordInfo = CommandParser.decodeInfoFragments(infoFragments, keywords, firstOptionalKeyword);
        } catch (InvalidArgumentException e) {
            printErrorMsg("Resource failed to be found. (Reason: " + e.getErrorMsg() + ")");
            return;
        }

        findResources(keywordInfo);
    }

    //@@author Yan Yi Xue
    private void findResources(String[] keywordInfo) {
        if (keywordInfo[1] == null) {
            String keyword = keywordInfo[0];
            System.out.print("Here is the list of all project(s) and its resource(s) matching the keyword!" + NEW_LINE);
            printDivider();
            ProjectManager.getAllProjectsAndResourcesMatchingKeyword(keyword, projects);
            printDivider();
        } else {
            String keyword = keywordInfo[0];
            String projectName = keywordInfo[1];
            int projectIndex = ProjectManager.searchExistingProjectIndex(projectName);
            printDivider();
            if (projectIndex != -1) {
                System.out.print("Project: " + projectName + NEW_LINE);
                ResourceManager.printResourcesMatchingKeyword(projects.get(projectIndex).getResources(), keyword);
            } else {
                System.out.print("Project cannot be found! Please enter a valid project name!" + NEW_LINE);
            }
            printDivider();
        }
    }

    private void printErrorMsg(String errorMsg) {
        System.out.print("Error: " + errorMsg + "\n");
    }

    private void promptUserInvalidInput() {
        System.out.print("Invalid input! Please type \"help\" for more details." + NEW_LINE);
    }

}
