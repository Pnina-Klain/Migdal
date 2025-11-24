package Part_1;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;

// Basic action with support on JSON file
public class TaskRepository {

    private static final String FILE_PATH = "tasks.json"; // JSON file
    private Gson gson = new Gson(); // Create an object for GSON library

    // Constructor
    TaskRepository(){
        try{
            File file = new File(FILE_PATH);
            if(file.exists()){
                System.out.println("the file exists");
            }
            file.createNewFile();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Add To the JSON file
    public void Add(Task task){
        List<Task> taskList = load(); // load the existing data
        if(taskList == null){
            taskList = new ArrayList<>();
        }
        taskList.add(task); // add new task to the data
        save(taskList); // save the data in JSON file
    }

    public boolean Update(Task t){
        List<Task> list = load(); // load the existing data
        // loop over the data and update by id of 't' to 't' object
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).getId() == t.getId()) {
                list.set(i,t);
                save(list); // save the data in JSON file
                return true;
            }
        }
        return false;
    }

    public boolean Delete(int id){
        List<Task> list = load(); // load the existing data
        boolean removed = list.removeIf(t -> t.getId() == id); // lambda function that remove by id
        if(removed)
            save(list); //save the data in JSON file
        return removed;
    }

    public Task GetById(int id){
        // lambda function that give by id the task
        return load().stream().filter(t -> t.getId()==id).findFirst().orElse(null);
    }

    public List<Task> ListAll(){
        return load();
    }

    // private function that load the data from JSON to List with tasks
    // using google library GSON
    private List<Task> load(){
        try (FileReader file = new FileReader(FILE_PATH)) {
            List<Task> list = gson.fromJson(file, new TypeToken<List<Task>>(){}.getType());
            if(list == null) {
                list = new ArrayList<>();
            }
            return list;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    // private function that save the data from List of task to JSON
    // using google library GSON
    private void save(List<Task> taskList){
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(taskList, writer);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
