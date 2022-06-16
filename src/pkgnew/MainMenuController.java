
package pkgnew;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import static pkgnew.Main.todayList;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;    

    @FXML
    FlowPane todo;
    @FXML
    FlowPane doing;
    @FXML
    FlowPane done;
    @FXML
    FlowPane today;
 
    public void addTask(ActionEvent event) throws IOException {  
        root = FXMLLoader.load(getClass().getResource("AddTaskMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    
    public void deleteTask(ActionEvent event) throws Exception {

        Button deleteBtn = (Button)event.getSource(); //yields complete string
        String taskId = deleteBtn.getParent().getId();

        //cursed code gets the column
        String columnId = deleteBtn.getParent().getParent().getParent().getId();

        ArrayList<Task> listToUpdate = Main.columnKey.get(columnId);

        Task taskToMove = Task.BLANK;
        int x = Integer.parseInt(taskId);
        for (Task task : listToUpdate) {
            if (task.count == x){
                taskToMove = task;
            }
        }
        
        Task possibleTodayTask = Task.BLANK;
        for(Task task : Main.todayList){
            if(task.count == x){
                possibleTodayTask = task;
            }
        }

        listToUpdate.remove(taskToMove);
        Main.todayList.remove(possibleTodayTask);
        Main.deleteStoredTask(taskToMove.count);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskMenu.fxml"));
        root = loader.load();
        addTaskMenuController addTaskController = loader.getController();
        addTaskController.viewMainMenu(event);
    }
    
    public void moveTaskForward(ActionEvent event) throws Exception{
        Button moveBtn = (Button)event.getSource();
        String taskId = moveBtn.getParent().getId();
        String columnId = moveBtn.getParent().getParent().getParent().getId();
        ArrayList<Task> listToUpdate = Main.columnKey.get(columnId);
        int x = Integer.parseInt(taskId);
        Task taskToMove = Task.BLANK;
        for (Task task : listToUpdate) {
            if (task.count == x){
                taskToMove = task;
            }
        }
        if(listToUpdate == Main.todoList){
            Main.doingList.add(taskToMove);
            Main.todoList.remove(taskToMove);
            Main.moveStoredTask(taskToMove.count, "doing");

        }
        if(listToUpdate == Main.doingList){
            Main.doneList.add(taskToMove);
            Main.doingList.remove(taskToMove);
            Main.moveStoredTask(taskToMove.count, "done");

        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskMenu.fxml"));
        root = loader.load();
        addTaskMenuController addTaskController = loader.getController();
        addTaskController.viewMainMenu(event);
    }
    
    public void moveTaskBack(ActionEvent event) throws Exception{
        Button moveBtn = (Button)event.getSource();
        String taskId = moveBtn.getParent().getId();
        String columnId = moveBtn.getParent().getParent().getParent().getId();
        ArrayList<Task> listToUpdate = Main.columnKey.get(columnId);
        int x = Integer.parseInt(taskId);
        Task taskToMove = Task.BLANK;
        for (Task task : listToUpdate) {
            if (task.count == x){
                taskToMove = task;
            }
        }
        if(listToUpdate == Main.doingList){
            Main.todoList.add(taskToMove);
            Main.doingList.remove(taskToMove);
            Main.moveStoredTask(taskToMove.count, "todo");

        }
        if(listToUpdate == Main.doneList){
            Main.doingList.add(taskToMove);
            Main.doneList.remove(taskToMove);
            Main.moveStoredTask(taskToMove.count, "doing");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskMenu.fxml"));
        root = loader.load();
        addTaskMenuController addTaskController = loader.getController();
        addTaskController.viewMainMenu(event);
    }
    
    public void displayTasks() throws IOException{         
        todo.getChildren().clear();
        doing.getChildren().clear();
        done.getChildren().clear();
        today.getChildren().clear();
        
        for(Task task : Main.todoList){           
            Parent taskPane = task.getTask();
            Button backBtn = (Button)taskPane.lookup("#backBtn");            
            backBtn.setDisable(true);
            todo.getChildren().add(taskPane); 
        }
        for(Task task : Main.doingList){
            Parent taskPane = task.getTask();
            doing.getChildren().add(taskPane); 
        }
        for(Task task : Main.doneList){
            Parent taskPane = task.getTask();
            Button fwrdBtn = (Button)taskPane.lookup("#fwrdBtn");
            fwrdBtn.setDisable(true);
            done.getChildren().add(taskPane); 
        }
        for(Task task : Main.todayList){
            Parent taskPane = task.getTask();
            Button moveForward = (Button)taskPane.lookup("#fwrdBtn");
            Button backBtn = (Button)taskPane.lookup("#backBtn");
            
            moveForward.setDisable(true);
            backBtn.setDisable(true);
            
            moveForward.setOpacity(0);
            backBtn.setOpacity(0);
            //deleteBtn, fwrdBtn, backBtn
            
            today.getChildren().add(taskPane); 
        }
    }
    
    
          
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
}
