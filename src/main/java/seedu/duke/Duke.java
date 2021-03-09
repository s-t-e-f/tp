package seedu.duke;

import seedu.duke.command.CommandHandler;

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

    private static final ArrayList<Project> projects = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeText();
        boolean isLoop;
        do {
            printSignalForUserToEnterInput();
            CommandHandler userInput = getUserInput();
            isLoop = processCommand(userInput);
        } while (isLoop);
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
        case HELP_COMMAND:
            listAllCommands();
            break;
        default:
            promptUserInvalidInput();
            break;
        }
        return isLoop;
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
            System.out.print("Resource is failed to be deleted!" + "\n");
            return;
        }
        deleteResource(projectInfo);
    }

    public static void deleteResource(String[] projectInfo) {
        Project targetedProj = null;
        boolean isResourceExist = true;
        String projectName = projectInfo[0];
        int idx;
        try {
            idx = Integer.parseInt(projectInfo[1]) - 1;
        } catch (Exception e) {
            promptUserInvalidInput();
            return;
        }

        for (Project project : projects) {
            if (project.getProjectName().equals(projectName)) {
                targetedProj = project;
                if (idx >= targetedProj.getResources().size() || idx < 0) {
                    isResourceExist = false;
                }
                break;
            }
        }
        if (targetedProj == null) {
            System.out.print("Project is not found ... " + "\n");
            return;
        }
        if (!isResourceExist) {
            System.out.print("Resource is not found. Please enter a valid index. " + "\n");
        } else {
            targetedProj.getResources().remove(idx);
            System.out.printf("The resource is deleted from the project \"%s\".\n", projectName);
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
    }

    private static void printResourcesForAProject(int resourceCount, ArrayList<Resource> resources) {
        System.out.print("Resource(s):" + "\n");
        for (Resource resource : resources) {
            System.out.print(resourceCount + "): " + resource + "\n");
        }
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

}
