package Part_1;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Advanced functions - base on TaskRepository
public class TaskService {

    // Object of TaskRepository - the basic actions
    TaskRepository taskRepository = new TaskRepository();

    // change the task status to done
    public void changeToDone(int id){
        Task t = taskRepository.GetById(id);
        t.setStatus(Status.DONE);
        taskRepository.Update(t);
    }

    // look after the string if it is in the title or the description
    public List<Task> findByText(String s){
        List<Task> tasks = taskRepository.ListAll();
        List<Task> result = new ArrayList<>();
        for (Task t : tasks){
            if(t.getTitle().contains(s)||t.getDescription().contains(s)){
                result.add(t);
            }
        }
        return result;
    }

    // sort all the task by status by Comparator
    public List<Task> sortByStatus(){
        List<Task> tasks = taskRepository.ListAll();
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
