import java.util.Scanner;

public class BasicTask extends Task {

    public BasicTask(String creator, String name, String description) {
        super(creator, name, description);
    }

    @Override
    public void update(Scanner scanner) {
        System.out.print("Enter new description: ");
        setDescription(scanner.nextLine());
    }

    @Override
    public String getDetails() {
        return String.format("Name: %s, Description: %s, Creator: %s",
                getName(), getDescription(), getCreator());
    }


}
