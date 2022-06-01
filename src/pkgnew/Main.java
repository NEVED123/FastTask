
package pkgnew;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author danie
 */
public class Main extends Application {
    
    public static ArrayList<Task> tasks = new ArrayList<>();
    private static File directory;
    public static PrintWriter storage;

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //create folder C:\FastTask
        directory = new File(System.getProperty("user.home") + "\\FastTask");
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory is created!");
                //not much else to do here, it is a blank folder
                try{
                    storage = new PrintWriter(System.getProperty("user.home") + "\\FastTask\\fasttask.txt");
                }
                catch(Exception e){
                    e.getStackTrace();
                }        
            } else {
                System.out.println("Directory failed to create");
                //add code in here that creates an error
            }
        }
 
        //fasttask.txt
        //if there's already the folder, load the information from it 
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Fast->Task"); //sets text at top of menu
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
