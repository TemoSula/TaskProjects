import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, Task> tasks = new HashMap<>();
    private static String currentUser;

    public static void main(String[] args) {

        //let's write code here
        /*
       Დაწერეთ Command-line აპლიკაცია. Აპლიკაციის გაშვების შემდეგ იუზერს უნდა შეეძლოს ბრძანების მითითება. Კონკრეტულს ბრძანებს უნდა ქონდეს თავისი ფუნქციონალი.

Აპლიკაციას უნდა ქონდეს 5 ბრძანება:
Tasks.Task-ის შენახვა
Tasks.Task-ების წამოღება
Კონკრეტული Tasks.Task -ის განახლება
Კონრეტული ტასკის წაშლა.
Კონკრეტული ტასკის წამოღება

Აპლიკაციის გაშვების შემდეგ მომხმარებელმა უნდა შეიყვანოს username რითაც ემუშავება ჩვენს სისტემას.
	Tasks.Task -ს გააჩნია სამი ფილდი: სახელი(რომელიც არის უნიკალური, აპლიკაციაში არ უნდა შეგვეძლოს გვქონდეს ორი ტასკი ერთნაირი სახელით), Tasks.Task -ის განმარტება, შემქმნელი იუზერის სახელი.

Შექმნის ბრძანების არჩევის შემდეგ იუზერმა უნდა შეიყვანოს თითოეული ფილდი(გარდა შემქმნეული იუზერის სახელისა..

Უნდა არსებობდეს 3 განსხვავებული ტიპის ტასკი: LimitedTimeTask(დამატებით ექნება დედლაინის თარიღი(გამოიყენეთ LocalDateTime), RepeateableTask(დამტებით ექნება რამდენჯერ უნდა შესრულდეს და როდის) და BasicTask(რომელსაც არ ექნება დამატებითი ფილდები.

1.Tasks.Task -ის შენახვა:
	Შენახვისას იუზერმა უნდა გადმოგვცეს ტასკის ტიპის სახელი რომლის მიხედვითაც შევაყვანინებთ ტასკის ტიპის მიხედვით განსხვავებულ ფილდებს
2.Tasks.Task - ების წამოღება:
	Ამ ბრძანების გამოძახების შემდეგ იუზერს უნდა დაუბრუნდეს ყველა ტასკის სახელი.
3.კონკრეტული ტასკის განახლება:
 	Ბრძანების არჩევის შემდეგ უნდა მიუთითოს იუზერმა ტასკის სახელი და მან უნდა შეძლოს ტასკის განმარტების და დედლაინის შეცვლა და ა.შ. Ტასკის არარსებობის შემთხვევაში უნდა დავუბრუნოთ მესიჯი: ტასკი არ არსებობს.
4. Კონკრეტული ტასკის წაშლა:
	Ბრძანების მითითების შემდეგ იუზერი ირჩევს ტასკის სახელს, რომლის წაშლაც უნდა. Ტასკის არ არსებობის შემთხვევაშ ვუბრუნებით მესიჯს: ტასკი არ არსებობს.
5. Კონკრეტულის ტასკის წამოღება:
	Კომანდის არჩევის შემდეგ უთითებს ტასკის ტიპს , ტასკის სახელს, რომელზეც დაუბრუნდება დეტალური ინფორმაცია( განმარტება,დედლაინი, შემქმნელი იუზერის სახელი და ა.შ)

*/

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
                    String deadlineStr = scanner.nextLine();
                    LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    task = new LimitedTimeTask(name, description, currentUser, deadline);
                    break;
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
            System.out.println("Tasks.Task added successfully.");
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
                System.out.println("Tasks.Task does not exist.");
                return;
            }

            task.update(scanner);
            System.out.println("Tasks.Task updated successfully.");
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
