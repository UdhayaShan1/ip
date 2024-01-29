package utilities;

import exceptions.RyanGoslingException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The Storage class is responsible for loading and saving tasks to/from the hard drive.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses and loads tasks from the hard drive.
     *
     * @return An ArrayList of Task objects representing the loaded tasks.
     * @throws FileNotFoundException If the file specified by filePath is not found.
     * @throws RyanGoslingException  If the task data on the hard drive is not in the expected format.
     */
    public ArrayList<Task> parseAndLoadTasks() throws FileNotFoundException, RyanGoslingException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new RyanGoslingException("File not found: " + filePath);
        }
        Scanner scanner = new Scanner(inputStream);
        String pattern = "^\\s*(\\w+)\\s*\\|\\s*(\\w+)\\s*\\|\\s*(.*?)\\s*\\|\\s*(.*?)\\s*\\|\\s*(.*?)\\s*$";
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String currentLine = scanner.nextLine();
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(currentLine);
            if (!matcher.matches()) {
                throw new RyanGoslingException("Task lists stored in hard drive is not in the expected format!");
            } else {
                String typeOfTask = matcher.group(1);
                int isTaskDone = Integer.parseInt(matcher.group(2));
                String taskDescription = matcher.group(3);
                String timeFrom = matcher.group(4);
                String timeTo = matcher.group(5);
                switch (typeOfTask) {
                case "T":
                    listOfTasks.add(new Todo(taskDescription, isTaskDone));
                    break;
                case "D":
                    listOfTasks.add(new Deadline(taskDescription, timeFrom, isTaskDone));
                    break;
                case "E":
                    listOfTasks.add(new Events(taskDescription, timeFrom, timeTo, isTaskDone));
                    break;
                }
            }
        }
        return listOfTasks;
    }

    /**
     * Writes the provided task list to the hard drive.
     *
     * @param taskList The ArrayList of Task objects to be written to the hard drive.
     */
    public void writeToTaskList(ArrayList<Task> taskList) {
        Path path = Paths.get(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 0; i < taskList.size(); i += 1) {
                Task taskToAdd = taskList.get(i);
                writer.write(taskToAdd.getTaskType());

                int taskDone = taskToAdd.isTaskDone() ? 1 : 0;
                String[] possibleTimes = taskToAdd.getTimes();

                writer.write(" | " + taskDone + " | " + taskToAdd.getTaskName() +
                                     " | " + possibleTimes[0] + " | " + possibleTimes[1]);

                if (i != taskList.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error writing to the file!");
        }
    }
}
