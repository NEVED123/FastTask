
package pkgnew;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
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
    public static File taskFile;
    public static PrintWriter printWriter;

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
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory is created!");
                //not much else to do here, it is a blank folder
                try{
                    printWriter = new PrintWriter(System.getProperty("user.home") + "\\FastTask\\fasttask.txt");
                    taskFile = new File(System.getProperty("user.home") + "\\FastTask\\fasttask.txt"); 
                }
                catch(Exception e){
                    e.getStackTrace();
                }        
            } else {
                System.out.println("Directory failed to create");
                //add code in here that creates an error
            }
        }
        else{
            File file = new File(System.getProperty("user.home") + "\\FastTask\\fasttask.txt");
            if(!file.exists()){
                printWriter = new PrintWriter(System.getProperty("user.home") + "\\FastTask\\fasttask.txt"); //creates txt file if it is not there
                taskFile = new File(System.getProperty("user.home") + "\\FastTask\\fasttask.txt");
            }
            else{ 
                taskFile = new File(System.getProperty("user.home") + "\\FastTask\\fasttask.txt"); 
                todoList = new ArrayList<>();
                doingList = new ArrayList<>();
                doneList = new ArrayList<>();
                todayList = new ArrayList<>();
                //we have a the FastTask folder and an existing txt file, now we must load info from the document
                HashMap<String, ArrayList<Task>> columnKey = new HashMap();
                columnKey.put("todo", todoList);
                columnKey.put("doing", doingList);
                columnKey.put("done", doneList);
                columnKey.put("today", todayList);
                ArrayList<String> taskStrings = (ArrayList)Files.readAllLines(taskFile.toPath());
                for(String taskString : taskStrings){
                    String[] split = taskString.split(",");
                    /*System.out.println(split.length);
                    for(String i : split){
                        System.out.println(i);
                    }*/
                    if(split.length == 5 && columnKey.containsKey(split[0])){
                        //System.out.println(columnKey.get(split[0]));
                        columnKey.get(split[0]).add(new Task(split[1], split[2], split[3], split[4]));
                    }
                    //else System.out.println(split.length); //ELSE STATEMENT FOR DEBUG
                }
                
            }
        }
 
        //fasttask.txt
        //if there's already the folder, load the information from it 
        Parent root;
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        FlowPane todoFlowPane = (FlowPane)root.lookup("#todo");
        /*FlowPane doingFlowPane = (FlowPane)root.lookup("doing");
        FlowPane doneFlowPane = (FlowPane)root.lookup("#done");
        FlowPane todayFlowPane = (FlowPane)root.lookup("#today");*/
        
        for(Task task : todoList){
            todoFlowPane.getChildren().add(task.getTask());
        }
        /*for(Task task : doingList){
            doingFlowPane.getChildren().add(task.getTask());
        }
        for(Task task : doneList){
            doneFlowPane.getChildren().add(task.getTask());
        }
        for(Task task : todayList){
            todayFlowPane.getChildren().add(task.getTask());
        }*/
        
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
