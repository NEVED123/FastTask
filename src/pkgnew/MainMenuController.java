
package pkgnew;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public FlowPane todo;
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
    
    public void finalizeTask(String taskName, String owner, String category, String date) throws IOException {
        //get text from text fields,
        //store them in variables
        
        Main.todoList.add(new Task(taskName, owner, category, date)); //placeholder args
        for(Task task : Main.todoList){
            todo.getChildren().add(task.getTask()); //must call getTask on each task class
        }
    }
    
        public void revert()throws IOException{
        for(Task task : Main.todoList){
            todo.getChildren().add(task.getTask()); //must call getTask on each task class
        }
         for(Task task : Main.doingList){
            doing.getChildren().add(task.getTask()); //must call getTask on each task class
        }
          for(Task task : Main.doneList){
            done.getChildren().add(task.getTask()); //must call getTask on each task class
        }
           for(Task task : Main.todayList){
            today.getChildren().add(task.getTask()); //must call getTask on each task class
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
}
