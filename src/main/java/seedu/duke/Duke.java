package seedu.duke;

import seedu.duke.command.CommandHandler;
import seedu.duke.parser.InputParser;
import seedu.duke.project.ProjectManager;
import seedu.duke.ui.MainUi;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner scan;

    public static void main(String[] args) {
        initializeDuke();
        MainUi.printWelcomeText();
        startProgram();
    }

    private static void startProgram() {
        boolean isLoop;
        do {
            MainUi.printSignalForUserToEnterInput();
            InputParser userInput = getUserInput();
            CommandHandler commandHandler = new CommandHandler(userInput);
            isLoop = commandHandler.processCommand();
        } while (isLoop);
    }

    private static void initializeDuke() {
        scan = new Scanner(System.in);
        ProjectManager.setProjects(new ArrayList<>());
    }

    private static InputParser getUserInput() {
        String userInput = "";
        if (scan.hasNextLine()) {
            userInput = scan.nextLine();
        }
        return new InputParser(userInput);
    }
}
