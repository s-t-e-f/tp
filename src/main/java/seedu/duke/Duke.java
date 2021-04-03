package seedu.duke;

import seedu.duke.command.CommandHandler;
import seedu.duke.parser.InputParser;
import seedu.duke.project.Project;
import seedu.duke.ui.MainUi;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Project> projects;
    private static Scanner scan;

    public static void main(String[] args) {
        initializeDuke();
        MainUi.printWelcomeText();
        startProgram();
    }

    private static void startProgram() {
        boolean isLoop = true;
        do {
            MainUi.printSignalForUserToEnterInput();
            InputParser userInput = getUserInput();
            CommandHandler commandHandler = new CommandHandler(userInput, projects);
            isLoop = commandHandler.processCommand();
        } while (isLoop);
    }

    private static void initializeDuke() {
        projects = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    private static InputParser getUserInput() {
        String userInput = "";
        if (scan.hasNextLine()) {
            userInput = scan.nextLine();
        }
        return new InputParser(userInput);
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    public static void setProjects(ArrayList<Project> projects) {
        Duke.projects = projects;
    }
}
