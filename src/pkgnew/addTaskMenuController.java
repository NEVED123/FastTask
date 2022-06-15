
package pkgnew;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 *
 * @author danie
 */
public class addTaskMenuController  {
    
    @FXML  
    TextField nameTaskField;
    @FXML
    TextField ownerField;
    @FXML
    TextField categoryField;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField dueLabel;
    @FXML
    TextField priorityLabel;
    
    @FXML
    public ImageView ratingOne;
    @FXML
    public ImageView ratingTwo;
    @FXML
    public ImageView ratingThree;
    @FXML
    public ImageView ratingFour;
    @FXML
    public ImageView ratingFive;

    public Stage stage;
    private Scene scene;
    private Parent root;
    public String starClicked;

    public void createTask(ActionEvent event) throws Exception, ParseException {

        String taskName = nameTaskField.getText();
        String owner = ownerField.getText();
        String category = categoryField.getText();
        String date;
        String due = null;
        String priority = starClicked;
        if(datePicker.getValue() == null)
            date = "No date selected";
        else
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")); //converts LocalDate -> String
        
        LocalDate rightNow = LocalDate.now(); //local date class
        String formattedNowDate = rightNow.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")); //creating a string format class for THE local date (now)
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy" , Locale.ENGLISH); //creating an object using the same string format class idea as before for the local date (probably won't need)

        if (datePicker.getValue() == null) { //if datepicker's value that it holds is nothing, then make 'due' string say something
            due = "Due date is a mystery";
        }
        else { //else...
            Date datePickerDate = sdf.parse(date); //parsing the date that is picked in the date picker and name it 'firstDate'
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
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
        root = loader.load();	

        MainMenuController mainMenuController = loader.getController();
        
        mainMenuController.finalizeTask(taskName, owner, category, date, Main.count, due, priority); 
        Main.count++;
        
        //NOTE: DUE IS NOT STORED ON LOCAL MACHINE, AS IT DIFFERS FROM DAY TO DAY.
        //RATHER, IT IS GENERATED BASED ON THE DATE OF THE TASK.
        storeTask(taskName, owner, category, date, Main.count, priority);
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
        root = loader.load();
        
        MainMenuController mainMenuController = loader.getController();       
        mainMenuController.displayTasks();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
        
     public void mousePressedStarOne () {
        System.out.println("Star One is Pressed.");
        starClicked = "One";
    }
    
    public void mousePressedStarTwo () {
        System.out.println("Star Two is Pressed.");
        starClicked = "Two";
    }
    
    public void mousePressedStarThree () {
        System.out.println("Star Three is Pressed.");
        starClicked = "Three";
    }
    
    public void mousePressedStarFour () {
        System.out.println("Star Four is Pressed.");
        starClicked = "Four";
    }
    
    public void mousePressedStarFive () {
        System.out.println("Star Five is Pressed.");
        starClicked = "Five";
    }
    
    public void mouseReleased() {
        Task.setStarOpacity(starClicked, ratingOne, ratingTwo, ratingThree, ratingFour, ratingFive);
    }
    
    
    private void storeTask(String taskName, String owner, String category, String date, int count, String priority) throws IOException{
        //Main.decrypt();
        FileWriter taskWriter = new FileWriter(Main.path, true);
        taskWriter.append("todo," + taskName + "," + owner + "," + category + "," + date + "," + count + "," + priority + "\n");//store information in the folder
        taskWriter.close();
        //Main.encrypt();
    }
    
}
