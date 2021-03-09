package seedu.duke;

import seedu.duke.command.CommandHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Project> projects = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(" _____                                                  ___              _");
        System.out.println("/__   \\ _ __  __ _   ___  ___ /\\_/\\ ___   _   _  _ __  / _ \\ _ __  ___  (_)");
        System.out.println("  / /\\/| '__|/ _` | / __|/ _ \\\\_ _// _ \\ | | | || '__|/ /_)/| '__|/ _ \\ | |");
        System.out.println(" / /   | |  | (_| || (__|  __/ / \\| (_) || |_| || |  / ___/ | |  | (_) || |");
        System.out.println(" \\/    |_|   \\__,_| \\___|\\___| \\_/ \\___/  \\__,_||_|  \\/     |_|   \\___/_/ |");
        System.out.println("                                                                      |__/");
        System.out.println("Team Project of CS2113-W10-3.");
        System.out.println("TraceYourProj - v0.1");
        System.out.println("Type 'help' for a list of command and related usage.");

        boolean isLoop;
        do {
            System.out.print("Duke> ");
            CommandHandler userInput = getUserInput();
            isLoop = processCommand(userInput);
        } while (isLoop);
        exit();
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
        case "add":
            processInputBeforeAdding(userInput);
            break;
        case "exit":
            showExitMessage();
            isLoop = false;
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

    private static void showExitMessage() {
        System.out.println("Thank you for using TraceYourProj!");
        System.out.println("Hope you have a wonderful day.");
    }

    private static void exit() {
        System.exit(0);
    }

    private static void promptUserInvalidInput() {
        System.out.print("Invalid input! Please type \"help\" for more details." + "\n");
    }
}
