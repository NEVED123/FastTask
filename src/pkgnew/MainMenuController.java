
package pkgnew;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

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
    
    public void finalizeTask(String taskName, String owner, String category, String date, int count, String due, String priority) throws IOException {   
        Main.todoList.add(new Task(taskName, owner, category, date, count, due, priority)); //placeholder args
        for(Task task : Main.todoList){
            todo.getChildren().add(task.getTask()); //must call getTask on each task class
        }
    }
    
    public void deleteTask(ActionEvent event) throws IOException {
        System.out.println("button pressed");
        Button source1 = (Button)event.getSource(); //yields complete string
        String id = source1.getId();
        //String source2 = event.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
        System.out.println("id: " + id);
       
        System.out.println(Main.todoList);
        Task taskToMove = new Task("", "", "", "", -1, "", "");
        int x = Integer.parseInt(id);
        for (Task task : Main.todoList) {
            if (task.count == x){
                taskToMove = task;
            }
        }
        
        Main.todoList.remove(taskToMove);
        System.out.println(Main.todoList);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        mainMenuController.displayTasks();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        
    }     
    
    public void displayTasks() throws IOException{         
        todo.getChildren().clear();
        doing.getChildren().clear();
        done.getChildren().clear();
        today.getChildren().clear();
        
        for(Task task : Main.todoList){
            todo.getChildren().add(task.getTask()); 
        }
         for(Task task : Main.doingList){
            doing.getChildren().add(task.getTask()); 
        }
          for(Task task : Main.doneList){
            done.getChildren().add(task.getTask()); 
        }
           for(Task task : Main.todayList){
            today.getChildren().add(task.getTask()); 
        }
    }
          
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
}
