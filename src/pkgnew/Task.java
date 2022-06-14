/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    int count;
    String due;
    String priority;
    
    public Task(String taskName, String owner, String category, String date, int count, String due, String priority){
        this.taskName = taskName;
        this.owner = owner;
        this.category = category;
        this.date = date;
        this.count = count;
        this.due = due;
        this.priority = priority;
    }
    
    public Parent getTask() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("finalTask.fxml"));     
        Label taskNameLabel = (Label)root.lookup("#taskName");
        Label ownerLabel = (Label)root.lookup("#ownerLabel");
        Label categoryLabel = (Label)root.lookup("#categoryLabel");
        Label dateLabel = (Label)root.lookup("#ddLabel");
        Button next = (Button)root.lookup("#moveBtn");
        String newId = "" + count;
        next.setId(newId);
        
        taskNameLabel.setText(taskName);
        ownerLabel.setText(owner);
        categoryLabel.setText(category);
        dateLabel.setText(date);
        
        Label dueLabel = (Label)root.lookup("#dueLabel"); //label being correlated with the named label in the scenebuilder fxml
        dueLabel.setText(due);
        
        Label priorityLabel = (Label)root.lookup("#priorityLabel");
        priorityLabel.setText("Priority Level: " + priority);
        
        ImageView finStarOne = (ImageView)root.lookup("#finStarOne");
        ImageView finStarTwo = (ImageView)root.lookup("#finStarTwo");
        ImageView finStarThree = (ImageView)root.lookup("#finStarThree");
        ImageView finStarFour = (ImageView)root.lookup("#finStarFour");
        ImageView finStarFive = (ImageView)root.lookup("#finStarFive");
        
        if (priority == "One") {
            finStarOne.setOpacity(1);
            finStarTwo.setOpacity(.5);
            finStarThree.setOpacity(.5);
            finStarFour.setOpacity(.5);
            finStarFive.setOpacity(.5);
        } else if (priority == "Two") {
            finStarOne.setOpacity(1);
            finStarTwo.setOpacity(1);
            finStarThree.setOpacity(.5);
            finStarFour.setOpacity(.5);
            finStarFive.setOpacity(.5);
        } else if (priority == "Three") {
            finStarOne.setOpacity(1);
            finStarTwo.setOpacity(1);
            finStarThree.setOpacity(1);
            finStarFour.setOpacity(.5);
            finStarFive.setOpacity(.5);
        } else if (priority == "Four") {
            finStarOne.setOpacity(1);
            finStarTwo.setOpacity(1);
            finStarThree.setOpacity(1);
            finStarFour.setOpacity(1);
            finStarFive.setOpacity(.5);
        } else if (priority == "Five") {
            finStarOne.setOpacity(1);
            finStarTwo.setOpacity(1);
            finStarThree.setOpacity(1);
            finStarFour.setOpacity(1);
            finStarFive.setOpacity(1);
        } else {
            System.out.println("nothing");
        }
                
        return root;        
    }
    
    
}
