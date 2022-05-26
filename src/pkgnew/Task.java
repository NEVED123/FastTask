/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author neved
 */
public class Task{

    String taskName;
    String owner;
    String category;   
    String date;
    
    public Task(String taskName, String owner, String category, String date){
        this.taskName = taskName;
        this.owner = owner;
        this.category = category;
        this.date = date;
    }
    
    public Parent getTask() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("finalTask.fxml"));     
        Label taskNameLabel = (Label)root.lookup("#taskName");
        Label ownerLabel = (Label)root.lookup("#ownerLabel");
        Label categoryLabel = (Label)root.lookup("#categoryLabel");
        Label dateLabel = (Label)root.lookup("#ddLabel");
        
        taskNameLabel.setText(taskName);
        ownerLabel.setText(owner);
        categoryLabel.setText(category);
        dateLabel.setText(date);
                
        return root;        
    }
    
    
}
