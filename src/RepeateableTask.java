

import java.util.Scanner;

public class RepeateableTask extends Task {

    private int repeatCount;
    private String repeatInterval;

    public RepeateableTask(String userName, String taskName, String taskDescription, int repeatCount, String repeatInterval) {
        super(userName, taskName, taskDescription);
        this.repeatCount = repeatCount;
        this.repeatInterval = repeatInterval;
    }


    @Override
    public void update(Scanner scanner) {
        System.out.println("enter new description: ");
        setDescription(scanner.nextLine());
        System.out.print("Enter new repeat count: ");
        this.repeatCount = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new repeat interval: ");
        this.repeatInterval = scanner.nextLine();
    }

    @Override
    public String getDetails() {
        return String.format("Name: %s, Description: %s, Creator: %s, Repeat Count: %d, Repeat Interval: %s",
                getName(), getDescription(), getCreator(), repeatCount, repeatInterval);
    }
}
