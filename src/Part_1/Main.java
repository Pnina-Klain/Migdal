package Part_1;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {

    static TaskRepository taskRepository = new TaskRepository();
    static TaskService taskService = new TaskService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI(); // Display the window UI
            }
        });
    }

    private static void createAndShowGUI() {
        // Create the main application frame (the window)
        JFrame frame = new JFrame("My Simple Swing UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500); // Set the initial size of the window

        // Create components
        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout());
        JButton addTask = new JButton("Add Task");
        JButton updateTask = new JButton("Update Task");
        JButton getById = new JButton("Get By Id");
        JButton listAll = new JButton("List All");
        JButton delete = new JButton("Delete");
        JButton markDone = new JButton("Add mark Done");
        JButton searchByString = new JButton("Search By String");
        JButton sortByStatus = new JButton("Sort By Status");

        // Create default table
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"ID", "Title", "Description", "Status"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add buttons
        buttonPanel.add(addTask);
        buttonPanel.add(updateTask);
        buttonPanel.add(getById);
        buttonPanel.add(listAll);
        buttonPanel.add(delete);
        buttonPanel.add(markDone);
        buttonPanel.add(searchByString);
        buttonPanel.add(sortByStatus);

        // Add the table and button to the frame
        frame.add(buttonPanel, java.awt.BorderLayout.NORTH);
        frame.add(scrollPane, java.awt.BorderLayout.CENTER);

        // Functionality of the buttons
        // The function create task, add it to the JSON file and view all the tasks
        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task t = new Task();
                taskRepository.Add(t);
                List<Task> tasks = taskRepository.ListAll();
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });
        // The function create task, add it to the JSON file, change one field and update in JSON file
        // After all view the update list
        updateTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task t = new Task();
                taskRepository.Add(t);
                t.setStatus(Status.IN_PROGRESS);
                taskRepository.Update(t);
                List<Task> tasks = taskRepository.ListAll();
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });
        // The function view only the task with id = 1 by the function GetById
        getById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task t2= taskRepository.GetById(1);
                List<Task> tasks = taskRepository.ListAll();
                tableModel.setRowCount(0); // Clear existing rows
                    tableModel.addRow(new Object[]{
                            t2.getId(),
                            t2.getTitle(),
                            t2.getDescription(),
                            t2.getStatus()
                    });

            }
        });

        // The function view all the tasks list by ListAll function
        listAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> tasks = taskRepository.ListAll();
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });
        // The function delete the task with id = 1 and view the update list 
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskRepository.Delete(1);
                List<Task> tasks = taskRepository.ListAll();
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });
        // The function change the status to DONE in task id = 2 and view the update list
        markDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskService.changeToDone(2);
                List<Task> tasks = taskRepository.ListAll();
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });
        // The task look after string "r" and view the tasks list with "r" in title or description
        searchByString.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> tasks = taskService.findByText("r");
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });
        // The function tasks list sort by status and view the sorted list
        sortByStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> tasks = taskService.sortByStatus();
                tableModel.setRowCount(0); // Clear existing rows
                for (Task task : tasks) {
                    tableModel.addRow(new Object[]{
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getStatus()
                    });
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}
