package seedu.duke;

import seedu.duke.command.CommandHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Project> projects = new ArrayList<>();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print(" _____                                                  ___              _" + "\n");
        System.out.print("/__   \\ _ __  __ _   ___  ___ /\\_/\\ ___   _   _  _ __  / _ \\ _ __  ___  (_)" + "\n");
        System.out.print("  / /\\/| '__|/ _` | / __|/ _ \\\\_ _// _ \\ | | | || '__|/ /_)/| '__|/ _ \\ | |" + "\n");
        System.out.print(" / /   | |  | (_| || (__|  __/ / \\| (_) || |_| || |  / ___/ | |  | (_) || |" + "\n");
        System.out.print(" \\/    |_|   \\__,_| \\___|\\___| \\_/ \\___/  \\__,_||_|  \\/     |_|   \\___/_/ |" + "\n");
        System.out.print("                                                                      |__/" + "\n");
        System.out.print("Team Project of CS2113-W10-3." + "\n");
        System.out.print("TraceYourProj - v0.1" + "\n");
        System.out.print("Type 'help' for a list of command and related usage." + "\n");

        boolean isLoop;
        do {
            System.out.print("Duke>");
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
        case "delete":
            processInputBeforeDeleting(userInput);
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
            projects.add(new Project(projectName, projectUrl, descriptionOfUrl));
            System.out.printf("The resource of the project \"%s\" is updated.\n", projectName);
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

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectName().equals(projectName)) {
                targetedProj = projects.get(i);
                if (idx >= targetedProj.getResources().size() || idx < 0) {
                    isResourceExist = false;
                }
                break;
            }
        }
        if (targetedProj == null) {
            System.out.println("Project is not found ... ");
            return;
        }
        if (!isResourceExist) {
            System.out.println("Resource is not found. Please enter a valid index. ");
        } else {
            targetedProj.getResources().remove(idx);
            System.out.printf("The resource is deleted from the project \"%s\".\n", projectName);
        }
    }

    private static void promptUserInvalidInput() {
        System.out.print("Invalid input! Please type \"help\" for more details." + "\n");
    }
}
