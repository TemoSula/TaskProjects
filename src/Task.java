import java.util.Scanner;

 abstract class Task {

    private String creator;
    private String name;
    private String description;


    public Task(String creator, String name, String description) {
        this.creator = creator;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCreator() {
        return creator;
    }


    public abstract void update(Scanner scanner);

    public abstract String getDetails();


}
