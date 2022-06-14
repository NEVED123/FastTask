
package pkgnew;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public Stage stage;
    private Scene scene;
    private Parent root;

    public void createTask(ActionEvent event) throws Exception {

        String taskName = nameTaskField.getText();
        String owner = ownerField.getText();
        String category = categoryField.getText();
        String date;
        if(datePicker.getValue() == null)
            date = "No date selected";
        else
            date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //converts LocalDate -> String
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
        root = loader.load();	

        MainMenuController mainMenuController = loader.getController();
        
        mainMenuController.finalizeTask(taskName, owner, category, date); 
        
        Main.decrypt();
        FileWriter taskWriter = new FileWriter(Main.path, true);
        taskWriter.append("todo," + taskName + "," + owner + "," + category + "," + date + "\n");//store information in the folder
        taskWriter.close();
        Main.encrypt();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
        public void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
        root = loader.load();
        MainMenuController mainMenuController = loader.getController();
        
        mainMenuController.revert();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
