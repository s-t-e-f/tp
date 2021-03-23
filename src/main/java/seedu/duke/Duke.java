package seedu.duke;

import seedu.duke.command.CommandHandler;
import seedu.duke.resource.Resource;
import seedu.duke.resource.ResourceManager;
import seedu.duke.parser.InputParser;
import seedu.duke.storage.Storage;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.MainUi;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LOGO_STRING = " _____                                 "
            + "                 ___              _\n"
            + "/__   \\ _ __  __ _   ___  ___ /\\_/\\ ___   _   _  _ __  / _ \\ _ __  ___  (_)\n"
            + "  / /\\/| '__|/ _` | / __|/ _ \\\\_ _// _ \\ | | | || '__|/ /_)/| '__|/ _ \\ | |\n"
            + " / /   | |  | (_| || (__|  __/ / \\| (_) || |_| || |  / ___/ | |  | (_) || |\n"
            + " \\/    |_|   \\__,_| \\___|\\___| \\_/ \\___/  \\__,_||_|  \\/     |_|   \\___/_/ |\n"
            + "                                                                      |__/\n";
    public static final String PROJECT_TEAM_ID = "Team Project of CS2113-W10-3.";
    public static final String APP_NAME_AND_VERSION = "TraceYourProj - v0.1";
    public static final String HOW_TO_GET_HELP = "Type 'help' for a list of command and related usage.";
    public static final String SIGNAL_FOR_USER_TO_INPUT = "Duke> ";
    public static final String ALL_COMMANDS_STRING = "-----------------------------------------"
            + "-------------------------------\n"
            + "Here are the available commands:\n"
            + "add: Adds a resource to a project\n"
            + "\tFormat: add p/PROJECT_NAME url/URL_LINK [d/LINK_DESCRIPTION]\n"
            + "delete: Deletes a resource from the resource list based on the project.\n"
            + "\tFormat: delete p/PROJECT_NAME [i/INDEX]\n"
            + "list-all: Shows a list of all resources used in all projects.\n"
            + "exit: Exits the program.\n"
            + "------------------------------------------------------------------------\n";

    public static final String EXIT_MESSAGE = "Thank you for using TraceYourProj!\n"
            + "Hope you have a wonderful day.\n";

    private static final String ADD_COMMAND = "add";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "exit";
    private static final String LIST_ALL_COMMAND = "list-all";
    private static final String HELP_COMMAND = "help";
    private static final String LIST_ONE_PROJECT_COMMAND = "list";
    private static final String FIND_COMMAND = "find";
    private static final String EDIT_COMMAND = "edit";

    private static ArrayList<Project> projects;
    private static Scanner scan;

    public static void main(String[] args) {
        initializeDuke();
        printWelcomeText();
        boolean isLoop;
        do {
            printSignalForUserToEnterInput();
            CommandHandler userInput = getUserInput();
            isLoop = processCommand(userInput);
        } while (isLoop);
    }

    private static void initializeDuke() {
        projects = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    private static CommandHandler getUserInput() {
        String userInput = "dummy";
        if (scan.hasNextLine()) {
            userInput = scan.nextLine();
        }
        return new CommandHandler(userInput);
    }

    private static boolean processCommand(CommandHandler userInput) {
        boolean isLoop = true;
        switch (userInput.getCommand()) {
        case ADD_COMMAND:
            processInputBeforeAdding(userInput);
            break;
        case DELETE_COMMAND:
            processInputBeforeDeleting(userInput);
            break;
        case EXIT_COMMAND:
            showExitMessage();
            isLoop = false;
            break;
        case LIST_ALL_COMMAND:
            printAllProjectsAndResources();
            break;
        case LIST_ONE_PROJECT_COMMAND:
            String projectName = processProjectName(userInput);
            printProjectResources(projectName);
            break;
        case EDIT_COMMAND:
            processInputBeforeEditing(userInput);
            break;
        case FIND_COMMAND:
            processInputBeforeFinding(userInput);
            break;
        case HELP_COMMAND:
            listAllCommands();
            break;
        default:
            promptUserInvalidInput();
            break;
        }
        return isLoop;
    }

    /**
     * This method will return the project name from userInput.
     * @param userInput Input received from user in command line
     * @return Project Name
     */
    private static String processProjectName(CommandHandler userInput) {
        String[] projectNameArray = userInput.getInfoFragments();
        String projectName = String.join(" ", projectNameArray);
        return projectName;
    }

    /**
     * This method will print the resources for a particular project.
     * @param projectName input Project Name
     */
    private static void printProjectResources(String projectName) {
        if (projectName.equals("")) {
            System.out.println("You did not key in the Project Name! Please type \"help\" for more details.");
            return;
        }
        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                System.out.print("--------------------------------------------------------" + "\n");
                System.out.println("Project: " + projectName);
                ArrayList<Resource> resources = project.getResources();
                int resourceCount = 0;
                resourceCount += 1;
                printResourcesForAProject(resourceCount, resources);
                System.out.print("--------------------------------------------------------" + "\n");
                return;
            }
        }
        System.out.println("Project not found!");
    }

    private static void processInputBeforeAdding(CommandHandler userInput) {
        String[] keywords = {"p/", "url/", "d/"};
        int firstOptionalKeyword = 2;
        String[] projectInfo = userInput.decodeInfoFragments(keywords, firstOptionalKeyword);

        if (projectInfo == null) {
            System.out.print("Resource is failed to be added!" + "\n");
            return;
        }

        addResource(projectInfo);
    }

    public static void addResource(String[] projectInfo) {
        int targetProjectIndex = -1;
        boolean isUrlAlreadyExist = false;
        assert projectInfo != null;
        String projectName = projectInfo[0];
        String projectUrl = projectInfo[1];
        String descriptionOfUrl = projectInfo[2];

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectName().equals(projectName)) {
                targetProjectIndex = i;
                isUrlAlreadyExist = projects.get(i).isUrlAlreadyExist(projectUrl);
                break;
            }
        }

        if (targetProjectIndex == -1) {
            projects.add(new Project(projectName, projectUrl, descriptionOfUrl));
            System.out.printf("The resource is added into the new project \"%s\".\n", projectName);
            return;
        }

        if (isUrlAlreadyExist) {
            projects.remove(targetProjectIndex);
            projects.add(targetProjectIndex, new Project(projectName, projectUrl, descriptionOfUrl));
            System.out.printf("The resource of the project \"%s\" is overwritten.\n", projectName);
        } else {
            projects.get(targetProjectIndex).addResources(projectUrl, descriptionOfUrl);
            System.out.printf("The resource is added to the existing project \"%s\".\n", projectName);
        }
    }

    private static void processInputBeforeDeleting(CommandHandler userInput) {
        String[] keywords = {"p/", "i/"};
        int firstOptionalKeyword = 1;
        String[] projectInfo = userInput.decodeInfoFragments(keywords, firstOptionalKeyword);

        if (projectInfo == null) {
            System.out.print("Resource failed to be deleted!" + "\n");
            return;
        }
        deleteResource(projectInfo);
    }

    public static void deleteResource(String[] projectInfo) {
        Project targetedProj = null;
        String projectName = projectInfo[0];
        int idx = -1;

        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                targetedProj = project;
                break;
            }
        }
        if (targetedProj == null) {
            System.out.print("Project is not found ... " + "\n");
            return;
        }

        try {
            if (projectInfo[1] != null) {
                idx = Integer.parseInt(projectInfo[1]) - 1;
                targetedProj.getResources().remove(idx);
                System.out.printf("The resource is deleted from the project \"%s\".\n", projectName);
            } else {
                // If index is not indicated, remove all resources from the specified project.
                targetedProj.getResources().removeAll(targetedProj.getResources());
                System.out.printf("All the resources in %s has been deleted.\n", projectName);
                return;
            }
        } catch (Exception e) {
            System.out.print("Resource is not found. Please enter a valid index. " + "\n");
            return;
        }
    }

    private static void processInputBeforeEditing(CommandHandler userInput) {
        String[] keywords = {"p/", "i/", "url/", "d/"};
        int firstOptionalKeyword = 1;
        String[] projectInfo = userInput.decodeInfoFragments(keywords, firstOptionalKeyword);

        if (projectInfo == null) {
            System.out.print("Resource failed to be edited!" + "\n");
            return;
        }
        editResource(projectInfo);
    }

    public static void editResource(String[] projectInfo) {
        Project targetedProj = null;
        Resource targetedResource = null;
        String projectName = projectInfo[0];
        int idx = -1;

        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                targetedProj = project;
                break;
            }
        }
        if (targetedProj == null) {
            System.out.print("Project is not found ... " + "\n");
            return;
        }

        try {
            idx = Integer.parseInt(projectInfo[1]) - 1;
            targetedResource = targetedProj.getResources().get(idx);
            if (projectInfo[2] != null) {
                targetedResource.setResourceLink(projectInfo[2]);
                System.out.printf("The resource is successfully edited to : \n");
                System.out.printf("    " + targetedResource.toString() + "\n");
            }
            // GOT ERROR HERE. -- cannot edit description without editing url
            // edit p/Jester's jokes i/1 d/test will be read as :
            // projectInfo[0] = 'Jester's jokes'
            // projectInfo[1] = '1 d/test'
            if (projectInfo[3] != null) {
                targetedResource.setResourceDescription(projectInfo[3]);
            }
            if (projectInfo[2] == null & projectInfo[3] == null) {
                System.out.println("The resource is not edited.");
            }
        } catch (Exception e) {
            System.out.printf("Resource is not found. Please enter a valid index. " + "\n");
            return;
        }



    }

    private static void showExitMessage() {
        System.out.print(EXIT_MESSAGE + "\n");
    }

    private static void promptUserInvalidInput() {
        System.out.print("Invalid input! Please type \"help\" for more details." + "\n");
    }

    private static void printAllProjectsAndResources() {
        int projectCount = 0;
        System.out.print("Here is the list of all project(s) and it's resource(s)!" + "\n");
        System.out.print("--------------------------------------------------------" + "\n");
        for (Project project : projects) {
            projectCount += 1;
            System.out.print("Project " + projectCount + ": " + project + "\n");
            ArrayList<Resource> resources = project.getResources();
            int resourceCount = 0;
            resourceCount += 1;
            printResourcesForAProject(resourceCount, resources);
            System.out.print("--------------------------------------------------------" + "\n");
        }
        assert true;
    }

    private static void printResourcesForAProject(int resourceCount, ArrayList<Resource> resources) {
        System.out.print("Resource(s):" + "\n");
        for (Resource resource : resources) {
            System.out.print(resourceCount + "): " + resource + "\n");
            resourceCount += 1;
        }
        assert true;
    }

    public static void listAllCommands() {
        System.out.print(ALL_COMMANDS_STRING + "\n");
    }

    private static void printWelcomeText() {
        System.out.print(LOGO_STRING + "\n");
        System.out.print(PROJECT_TEAM_ID + "\n");
        System.out.print(APP_NAME_AND_VERSION + "\n");
        System.out.print(HOW_TO_GET_HELP + "\n");
    }

    private static void printSignalForUserToEnterInput() {
        System.out.print(SIGNAL_FOR_USER_TO_INPUT);
    }

    private static void processInputBeforeFinding(CommandHandler userInput) {
        String[] keywords = {"k/", "p/"};
        int firstOptionalKeyword = 1;
        String[] keywordInfo = userInput.decodeInfoFragments(keywords, firstOptionalKeyword);

        if (keywordInfo == null) {
            System.out.print("Resources matching keyword failed to be found!" + "\n");
            return;
        }
        findResources(keywordInfo);
    }

    private static void findResources(String[] keywordInfo) {
        if (keywordInfo[1] == null) {
            String keyword = keywordInfo[0];
            printAllProjectsAndResourcesMatchingKeyword(keyword);
        } else {
            String keyword = keywordInfo[0];
            String projectName = keywordInfo[1];
            printResourcesInProjectMatchingKeyword(projectName, keyword);
        }
    }

    private static void printAllProjectsAndResourcesMatchingKeyword(String keyword) {
        int projectCount = 0;
        System.out.print("Here is the list of all project(s) and its resource(s) matching the keyword!" + "\n");
        System.out.print("--------------------------------------------------------" + "\n");
        for (Project project : projects) {
            projectCount += 1;
            System.out.print("Project " + projectCount + ": " + project + "\n");
            ArrayList<Resource> resources = project.getResources();
            printResourcesMatchingKeyword(resources, keyword);
            System.out.print("--------------------------------------------------------" + "\n");
        }
    }

    private static void printResourcesInProjectMatchingKeyword(String projectName, String keyword) {
        Boolean isProject = Boolean.FALSE;
        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                isProject = Boolean.TRUE;
                System.out.print("--------------------------------------------------------" + "\n");
                System.out.println("Project: " + projectName);
                ArrayList<Resource> resources = project.getResources();
                printResourcesMatchingKeyword(resources, keyword);
                System.out.print("--------------------------------------------------------" + "\n");
            }
        }
        if (!isProject) {
            System.out.println("Project cannot be found! Please enter a valid project name!");
        }
    }

    private static void printResourcesMatchingKeyword(ArrayList<Resource> resources, String keyword) {
        int resourceCount = 1;
        for (Resource resource : resources) {
            if (checkKeywordMatch(resource, keyword)) {
                System.out.print(resourceCount + "): " + resource + "\n");
                resourceCount += 1;
            }
        }
        if (resourceCount == 1) {
            System.out.printf("No resources matching keyword \"%s\" found!\n", keyword);
        }
    }

    private static Boolean checkKeywordMatch(Resource resource, String keyword) {
        if (resource.getResourceDescriptionOnly().toLowerCase().contains(keyword.toLowerCase())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
