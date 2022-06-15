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
        
        setStarOpacity(priority, finStarOne, finStarTwo, finStarThree, finStarFour, finStarFive);
           
        return root;        
    }
    
    public static void setStarOpacity(String priority, ImageView one, ImageView two, ImageView three, ImageView four, ImageView five){
    if (priority == "One") {
        one.setOpacity(1);
        two.setOpacity(.5);
        three.setOpacity(.5);
        four.setOpacity(.5);
        five.setOpacity(.5);
    } else if (priority == "Two") {
        one.setOpacity(1);
        two.setOpacity(1);
        three.setOpacity(.5);
        four.setOpacity(.5);
        five.setOpacity(.5);
    } else if (priority == "Three") {
        one.setOpacity(1);
        two.setOpacity(1);
        three.setOpacity(1);
        four.setOpacity(.5);
        five.setOpacity(.5);
    } else if (priority == "Four") {
        one.setOpacity(1);
        two.setOpacity(1);
        three.setOpacity(1);
        four.setOpacity(1);
        five.setOpacity(.5);
    } else if (priority == "Five") {
        one.setOpacity(1);
        two.setOpacity(1);
        three.setOpacity(1);
        four.setOpacity(1);
        five.setOpacity(1);
    } 
}

    

    
    
}
