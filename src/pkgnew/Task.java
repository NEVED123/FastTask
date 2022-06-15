/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
        //label being correlated with the named label in the scenebuilder fxml
        Label dueLabel = (Label)root.lookup("#dueLabel");
        Label priorityLabel = (Label)root.lookup("#priorityLabel");
        ImageView finStarOne = (ImageView)root.lookup("#finStarOne");
        ImageView finStarTwo = (ImageView)root.lookup("#finStarTwo");
        ImageView finStarThree = (ImageView)root.lookup("#finStarThree");
        ImageView finStarFour = (ImageView)root.lookup("#finStarFour");
        ImageView finStarFive = (ImageView)root.lookup("#finStarFive");
                
        taskNameLabel.setText(taskName);
        ownerLabel.setText(owner);
        categoryLabel.setText(category);
        dateLabel.setText(date);
        
        String newId = "" + count;
        next.setId(newId);
        
        dueLabel.setText(due);        
        priorityLabel.setText("Priority Level: " + priority);
        
        setStarOpacity(priority, finStarOne, finStarTwo, finStarThree, finStarFour, finStarFive);
           
        return root;        
    }
    
    public static void setStarOpacity(String priority, ImageView one, ImageView two, 
            ImageView three, ImageView four, ImageView five){
        if (priority.contentEquals("One")){
            one.setOpacity(1);
            two.setOpacity(.5);
            three.setOpacity(.5);
            four.setOpacity(.5);
            five.setOpacity(.5);
        } else if (priority.contentEquals("Two")) {
            one.setOpacity(1);
            two.setOpacity(1);
            three.setOpacity(.5);
            four.setOpacity(.5);
            five.setOpacity(.5);
        } else if (priority.contentEquals("Three")) {
            one.setOpacity(1);
            two.setOpacity(1);
            three.setOpacity(1);
            four.setOpacity(.5);
            five.setOpacity(.5);
        } else if (priority.contentEquals("Four")) {
            one.setOpacity(1);
            two.setOpacity(1);
            three.setOpacity(1);
            four.setOpacity(1);
            five.setOpacity(.5);
        } else if (priority.contentEquals("Five")) {
            one.setOpacity(1);
            two.setOpacity(1);
            three.setOpacity(1);
            four.setOpacity(1);
            five.setOpacity(1);
        }
    }
    
    public static String generateDueInLabel(String dateString, DatePicker datePicker) throws ParseException{
        String due;
        
        LocalDate rightNow = LocalDate.now(); //local date class
        String formattedNowDate = rightNow.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")); //creating a string format class for THE local date (now)
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy" , Locale.ENGLISH); //creating an object using the same string format class idea as before for the local date (probably won't need)

        if (datePicker.getValue() == null) { //if datepicker's value that it holds is nothing, then make 'due' string say something
            due = "Due date is a mystery";
        }
        else { 
            Date datePickerDate = sdf.parse(dateString); //parsing the date that is picked in the date picker and name it 'firstDate'
            Date currentDate = sdf.parse(formattedNowDate); //parsing the date that is RIGHT NOW and naming it 'secondDate'
            long dateSubtraction = datePickerDate.getTime() - currentDate.getTime(); //using long and naming is diff, it will equal to the date picker date (firstDate) minus the current local date (secondDate)
            TimeUnit timeInDays = TimeUnit.DAYS; //create a time unit object using days and naming it time
            //TimeUnit timeInHours = TimeUnit.HOURS; //May not be needed
            long dueDateConversion = timeInDays.convert(dateSubtraction, TimeUnit.MILLISECONDS); //a long variable called 'difference' and converting 'diff' (the days) from milliseconds
            //long dueDateConvers = timeInHours.convert(dateSubtraction, TimeUnit.MILLISECONDS); //May not be needed
            if ((dueDateConversion) == 1) {
                due = "Task Is Due In the Next 24 Hours!";
            }
            else
                due = "Due in: " + (String.valueOf(dueDateConversion)) + " Days!"; //having the FXML line 'due' spit out "Due in: _____ Days!"
            System.out.println(timeInDays); //printing in console
            System.out.println(dateSubtraction); //printing in console -> shows milliseconds in how many days
            System.out.println(dueDateConversion);
            //System.out.println(dueDateConvers);
        }       
        
        return due;
    }
    

}
    

    

    

