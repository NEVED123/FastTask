
package pkgnew;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author danie
 */
public class Main extends Application {
    
    public static ArrayList<Task> todoList;
    public static ArrayList<Task> doingList;
    public static ArrayList<Task> doneList;
    public static ArrayList<Task> todayList;
    private static File directory;
    public static String path;
    /**
     *
     * @param stage
     * @throws Exception
     */
    
    /*
        THE TODAY COLUMN WILL BE DIFFERENT, IT WILL HAVE TO BE GENERATED
        ON THE FLY BY PARSING THE DATES OF THE OTHER TASKS AND COMPARING
        THEM TO THE SYSTEM TIME
    */
    @Override
    public void start(Stage stage) throws Exception {
        //create folder C:\FastTask
        directory = new File(System.getProperty("user.home") + "\\FastTask");
        path = System.getProperty("user.home") + "\\FastTask\\fasttask.txt";
        if (!directory.exists()) {
            try{
                directory.mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }       
        File file = new File(path);
        if(!file.exists()){
            try{
                FileWriter createFile = new FileWriter(path);
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        //we have a the FastTask folder and an existing txt file, now we must load info from the document
        
        todoList = new ArrayList<>();
        doingList = new ArrayList<>();
        doneList = new ArrayList<>();
        todayList = new ArrayList<>();
        
        HashMap<String, ArrayList<Task>> columnKey = new HashMap();
        columnKey.put("todo", todoList);
        columnKey.put("doing", doingList);
        columnKey.put("done", doneList);
        ArrayList<String> taskStrings = (ArrayList)Files.readAllLines(Paths.get(path));
        for(String taskString : taskStrings){
            String[] split = taskString.split(",");
            if(split.length == 5 && columnKey.containsKey(split[0])){
                ArrayList columnToAddTo = columnKey.get(split[0]);                
                columnToAddTo.add(new Task(split[1], split[2], split[3], split[4]));                
            }
            //else System.out.println(split.length); //ELSE STATEMENT FOR DEBUG
        }

        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        FlowPane todoFlowPane = (FlowPane)root.lookup("#todo");
        FlowPane doingFlowPane = (FlowPane)root.lookup("#doing");
        FlowPane doneFlowPane = (FlowPane)root.lookup("#done");
        FlowPane todayFlowPane = (FlowPane)root.lookup("#today");
        
        for(Task task : todoList){
            todoFlowPane.getChildren().add(task.getTask());
        }
        for(Task task : doingList){
            doingFlowPane.getChildren().add(task.getTask());
        }        
        for(Task task : doneList){
            doneFlowPane.getChildren().add(task.getTask());
        }     
        for(Task task : todayList){
            todayFlowPane.getChildren().add(task.getTask());
        } 
        
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
