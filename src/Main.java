import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, Task> tasks = new HashMap<>();
    private static String currentUser;

    public static void main(String[] args) {


            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your username: ");
            currentUser = scanner.nextLine();

            while (true) {
                System.out.println("\nAvailable Commands: ");
                System.out.println("1. Add Tasks.Task");
                System.out.println("2. List Tasks");
                System.out.println("3. Update Tasks.Task");
                System.out.println("4. Delete Tasks.Task");
                System.out.println("5. Get Tasks.Task Details");
                System.out.println("6. Exit");

                System.out.print("Enter command number: ");
                int command = Integer.parseInt(scanner.nextLine());

                switch (command) {
                    case 1:
                        addTask(scanner);
                        break;
                    case 2:
                        listTasks();
                        break;
                    case 3:
                        updateTask(scanner);
                        break;
                    case 4:
                        deleteTask(scanner);
                        break;
                    case 5:
                        getTaskDetails(scanner);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid command.");
                }
            }
        }

        private static void addTask (Scanner scanner){
            System.out.print("Enter task type (LimitedTimeTask, RepeatableTask, BasicTask): ");
            String type = scanner.nextLine();
            System.out.print("Enter task name: ");
            String name = scanner.nextLine();

            if (tasks.containsKey(name)) {
                System.out.println("Tasks.Task with this name already exists.");
                return;
            }

            System.out.print("Enter task description: ");
            String description = scanner.nextLine();

            Task task = null;
            switch (type) {
                case "LimitedTimeTask":
                    System.out.print("Enter deadline (yyyy-MM-dd HH:mm): ");

                   //this is fixed

                    try {
                        String deadlineStr = scanner.nextLine();
                        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        task = new LimitedTimeTask(name, description, currentUser, deadline);
                        break;
                    }
                    catch (Exception e)
                    {
                        System.out.println("arasworia mititeba");
                        return;
                    }

                case "RepeatableTask":
                    System.out.print("Enter repeat count: ");
                    int repeatCount = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter repeat interval: ");
                    String repeatInterval = scanner.nextLine();
                    task = new RepeateableTask(name, description, currentUser, repeatCount, repeatInterval);
                    break;
                case "BasicTask":
                    task = new BasicTask(name, description, currentUser);
                    break;
                default:
                    System.out.println("Invalid task type.");
                    return;
            }

            tasks.put(name, task);
            System.out.println("Tasks added successfully.");
        }

        private static void listTasks () {
            if (tasks.isEmpty()) {
                System.out.println("No tasks available.");
                return;
            }

            System.out.println("Tasks:");
            for (String taskName : tasks.keySet()) {
                System.out.println("- " + taskName);
            }
        }

        private static void updateTask (Scanner scanner){
            System.out.print("Enter task name to update: ");
            String name = scanner.nextLine();

            Task task = tasks.get(name);
            if (task == null) {
                System.out.println("Tasks does not exist.");
                return;
            }

            task.update(scanner);
            System.out.println("Tasks updated successfully.");
        }

        private static void deleteTask (Scanner scanner){
            System.out.print("Enter task name to delete: ");
            String name = scanner.nextLine();

            if (tasks.remove(name) == null) {
                System.out.println("Tasks.Task does not exist.");
            } else {
                System.out.println("Tasks.Task deleted successfully.");
            }
        }

        private static void getTaskDetails (Scanner scanner){
            System.out.print("Enter task name to get details: ");
            String name = scanner.nextLine();

            Task task = tasks.get(name);
            if (task == null) {
                System.out.println("Tasks.Task does not exist.");
            } else {
                System.out.println("Tasks.Task Details:");
                System.out.println(task.getDetails());
            }
        }
    }
