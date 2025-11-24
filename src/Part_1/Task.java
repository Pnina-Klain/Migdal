package Part_1;


public class Task {
    private int id;
    private String title;
    private String description;
    private Status status;
    private static int counter = 1;

    // Constructors
    Task(){
        id = counter++;
        System.out.println(counter);
        setTitle("---");
        setDescription("---");
        setStatus(Status.NEW);
    }

    Task(String title, String description){
        id = counter++;
        setTitle(title);
        setDescription(description);
        setStatus(Status.NEW);
    }

    // GET - SET
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

