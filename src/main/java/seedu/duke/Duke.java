package seedu.duke;

import seedu.duke.command.CommandHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Project> projects = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
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

        boolean isLoop;
        do {
            System.out.print("Duke> ");
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
        switch (userInput.getCommand()) {
        case "add":
            processInputBeforeAdding(userInput);
            return true;
        case "shutdownForDebug":
            return false;
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
            projects.add(new Project(projectName, projectUrl, descriptionOfUrl));
            System.out.printf("The resource of the project \"%s\" is updated.\n", projectName);
        } else {
            projects.get(targetProjectIndex).addResources(projectUrl, descriptionOfUrl);
            System.out.printf("The resource is added to the existing project \"%s\".\n", projectName);
        }
    }

    private static void promptUserInvalidInput() {
        System.out.println("Invalid input! Please type \"help\" for more details.");
    }
}
