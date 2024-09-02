

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LimitedTimeTask extends Task {

    private LocalDateTime deadline;

    public LimitedTimeTask(String userName, String taskName, String taskDescription, LocalDateTime deadline) {
        super(userName, taskName, taskDescription);
        this.deadline = deadline;
    }


    @Override
    public void update(Scanner scanner) {

        System.out.print("Enter new description: ");
        setDescription(scanner.nextLine());
        System.out.print("Enter new deadline (yyyy-MM-dd HH:mm): ");
        String deadlineStr = scanner.nextLine();
        this.deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

    @Override
    public String getDetails() {
        return String.format("Name: %s, Description: %s, Creator: %s, Deadline: %s",
                getName(), getDescription(), getCreator(), deadline.toString());
    }
}
