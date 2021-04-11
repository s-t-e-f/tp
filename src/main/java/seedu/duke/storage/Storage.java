package seedu.duke.storage;

<<<<<<< HEAD
=======
import java.util.logging.Logger;
import java.util.logging.Level;
>>>>>>> master
import seedu.duke.project.Project;
import seedu.duke.project.ProjectManager;
import seedu.duke.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents a class that reads from and writes to a storage text file.
 */
public class Storage {
    private static ArrayList<Project> projects = new ArrayList<>();
    private static final Logger logger = Logger.getLogger("Foo");

    /**
     * Updates the storage based on the projects list that is passed into the method.
     * @param projects The list of projects to be updated into the storage.
     */
    public static void updateStorage(ArrayList<Project> projects) {

        if (projects.isEmpty()) {
            System.out.println("No projects to save!");
            return;
        }

        try {
            java.io.FileWriter fileWriter  = new java.io.FileWriter("data.txt");
            for (Project project: projects) {
                fileWriter.write(projectAsWritableStr(project));
                fileWriter.write(System.lineSeparator());

                for (Resource resource: project.getResources()) {
                    fileWriter.write(resourceAsWritableStr(resource));
                    fileWriter.write(System.lineSeparator());
                }
            }
            fileWriter.close();
            System.out.println("Saved projects to storage");
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Error in reading in data file.");
        }
    }

    private static String projectAsWritableStr(Project project) {
        return String.format("Project|%s|%d",
                project.getProjectName(),
                project.getNumberOfResources());
    }

    private static String resourceAsWritableStr(Resource resource) {
        return String.format("%s|%s|%s",
                resource.getResourceLink(),
                resource.getResourceDescriptionOnly(),
                resource.getResourceDate().toString());
    }

    /**
     * Reads the projects from the storage text file and returns it.
     * @return The list of projects from the storage text file.
     */
    public static ArrayList<Project> readFromStorage() {
        createProjectsFromStorage();
        System.out.println("Loaded projects from storage");
        return projects;
    }

    private static void createProjectsFromStorage() {
        clearProjects();
        assert projects.isEmpty();
        try {
            File dataFile = new File("data.txt");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("Project")) {
                    Project project = createProject(line);
                    int numberOfResources = getNumberOfResources(line);
                    for (int i = 0; i < numberOfResources; i++) {
                        line = scanner.nextLine();
                        Resource resource = createResource(line);
                        project.loadResource(resource);
                    }
                    projects.add(project);
                }
            }
            updateProjects();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Error in reading in data file.");
            e.printStackTrace();
        }
    }

    private static void clearProjects() {
        projects = new ArrayList<>();
    }

    private static void updateProjects() {

        ArrayList<Project> dukeProjects = ProjectManager.getProjects();

        for (Project project: dukeProjects) {
            if (projects.contains(project)) {
                updateExistingProject(project);
            } else {
                projects.add(project);
            }
        }
    }

    private static void updateExistingProject(Project project) {
        Project projectToUpdate = projects.get(projects.indexOf(project));
        ArrayList<Resource> projectToUpdateResources = projectToUpdate.getResources();
        for (Resource resource: project.getResources()) {
            if (!projectToUpdateResources.contains(resource)) {
                projectToUpdate.addResourceObj(resource);
            }
        }
    }


    private static Project createProject(String input) {
        String[] parts = input.split(Pattern.quote("|"));
        String projectName = parts[1];
        return new Project(projectName);
    }

    private static int getNumberOfResources(String input) {
        String[] parts = input.split(Pattern.quote("|"));
        int numberOfResources = Integer.parseInt(parts[2]);
        return numberOfResources;
    }

    private static Resource createResource(String input) {
        String[] parts = input.split(Pattern.quote("|"));
        String link = parts[0];
        String description = parts[1].equals("") ? null : parts[1];
        LocalDate dateOfCreation = LocalDate.parse(parts[2]);
        return new Resource(link, description, dateOfCreation);
    }



}
