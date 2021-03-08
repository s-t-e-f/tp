package seedu.duke;

import seedu.duke.command.CommandHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Project> projects = new ArrayList<>();

    public static void main(String[] args) {
        boolean isLoop = true;
        String logo = " _____                                                  ___              _\n"
                + "/__   \\ _ __  __ _   ___  ___ /\\_/\\ ___   _   _  _ __  / _ \\ _ __  ___  (_)\n"
                + "  / /\\/| '__|/ _` | / __|/ _ \\\\_ _// _ \\ | | | || '__|/ /_)/| '__|/ _ \\ | |\n"
                + " / /   | |  | (_| || (__|  __/ / \\| (_) || |_| || |  / ___/ | |  | (_) || |\n"
                + " \\/    |_|   \\__,_| \\___|\\___| \\_/ \\___/  \\__,_||_|  \\/     |_|   \\___/_/ |\n"
                + "                                                                      |__/ ";
        System.out.println(logo);
        System.out.println("Team Project of CS2113-W10-3.");
        System.out.println("TraceYourProj - v0.1");
        System.out.println("Type 'help' for a list of command and related usage.");
        while (isLoop) {
            System.out.print("System> ");
            Scanner scan = new Scanner(System.in);
            CommandHandler inputHandler = new CommandHandler(scan.nextLine());
            isLoop = processCommand(inputHandler);
        }
    }

    private static boolean processCommand(CommandHandler userInput) {
        switch (userInput.getCommand()) {
        case "add":
            processInputBeforeAdding(userInput);
            return true;
        default:
            promptUserInvalidInput();
            return true;
        }
    }

    private static void processInputBeforeAdding(CommandHandler userInput) {
        String[] keywords = {"i/", "url/", "d/"};
        int firstOptionalKeyword = 2;
        String[] projectInfo = userInput.decodeInfoFragments(keywords, firstOptionalKeyword);

        if (projectInfo == null) {
            System.out.println("Resource is failed to be added!");
            return;
        }
        addResource(projectInfo);
    }

    public static void addResource(String[] projectInfo) {
        int targetProjectIndex = -1;
        boolean isURLAlreadyExist = false;
        String projectName = projectInfo[0];
        String projectURL = projectInfo[1];
        String URLDescription = projectInfo[2];

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectName().equals(projectName)) {
                targetProjectIndex = i;
                isURLAlreadyExist = projects.get(i).isURLAlreadyExist(projectURL);
                break;
            }
        }

        if (targetProjectIndex == -1) {
            projects.add(new Project(projectName, projectURL, URLDescription));
            System.out.printf("The resource is added into the new project \"%s\".\n", projectName);
            return;
        }

        if (isURLAlreadyExist) {
            projects.remove(targetProjectIndex);
            projects.add(new Project(projectName, projectURL, URLDescription));
            System.out.printf("The resource of the project \"%s\" is updated.\n", projectName);
        } else {
            projects.get(targetProjectIndex).addResources(projectURL, URLDescription);
            System.out.printf("The resource is added to the existing project \"%s\".\n", projectName);
        }
    }

    private static void promptUserInvalidInput() {
        System.out.println("Invalid input! Please type \"help\" for more details.");
    }
}
