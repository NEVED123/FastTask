
package pkgnew;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public static int shift = 3;
    public static int count = 0;
  
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

        
        decrypt();
        
        ArrayList<String> taskStrings = (ArrayList)Files.readAllLines(Paths.get(path));

        encrypt();

        for(String taskString : taskStrings){
            String[] split = taskString.split(",");
            for(String s : split){
                System.out.println(s);
            }
            if(split.length == 7 && columnKey.containsKey(split[0])){
                ArrayList columnToAddTo = columnKey.get(split[0]);
                String count = split[1];
                String taskName = split[2];
                String owner = split[3];
                String category = split[4];
                String date = split[5];
                String priority = split[6];
                int taskCount = Integer.parseInt(count);
                String due = "placeholder";                             
                String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));               
                columnToAddTo.add(new Task(taskName, owner, category, date, taskCount, due, priority));
                if(dateNow.contentEquals(date)){
                    todayList.add(new Task(taskName, owner, category, date, taskCount, due, priority));
                }
            }
            //else System.out.println(split.length); //ELSE STATEMENT FOR DEBUG
        }

        Parent root;
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
        root = loader.load();
        
        MainMenuController mainMenuController = loader.getController();       
        mainMenuController.displayTasks();
        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("Fast->Task"); //sets text at top of menu        
        stage.show();
        
        //THIS IS A CHANGE IN MAIN BRANCH
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void encrypt() throws Exception {        
        ArrayList<String> taskStrings = (ArrayList)Files.readAllLines(Paths.get(path));
        FileWriter clearer = new FileWriter(path, false);
        clearer.write("");
        FileWriter encryptedTaskWriter = new FileWriter(path, true);
        
        for(String taskString : taskStrings){
            
            StringBuilder encryptedTask = new StringBuilder();
            
            for(char ch : taskString.toCharArray()){
                if(ch == ','){
                    encryptedTask.append(',');
                }
                else if(ch == '/'){
                    encryptedTask.append('/');
                }
                else if(Character.isDigit(ch)){
                    int dateNum = Character.digit(ch, 10);
                    if(dateNum <= 6){
                        dateNum += 3;
                    }
                    else{
                       dateNum -= 7; 
                    }

                    Character ceasarChar = Character.forDigit(dateNum, 10);
                    encryptedTask.append(ceasarChar);
                }
                else if(Character.isUpperCase(ch)){
                    char ceasarChar = (char)(((int)ch + shift - 65) % 26 + 65);
                    encryptedTask.append(ceasarChar);                    
                }
                else{
                    char ceasarChar = (char)(((int)ch + (shift) - 97) % 26 + 97);
                    encryptedTask.append(ceasarChar);
                }     
                
            }
            
            String encryptedTaskString = encryptedTask.toString();
            encryptedTaskWriter.append(encryptedTaskString + "\n");
        }
        
        encryptedTaskWriter.close();
    }
    
    public static void decrypt() throws Exception{
        ArrayList<String> taskStrings = (ArrayList)Files.readAllLines(Paths.get(path));
        FileWriter clearer = new FileWriter(path, false);
        clearer.write("");
        FileWriter decryptedTaskWriter = new FileWriter(path, true);
        
        for(String taskString : taskStrings){
            
            StringBuilder decryptedTask = new StringBuilder();
            
            for(char ch : taskString.toCharArray()){               
                if(ch == ','){
                    decryptedTask.append(','); 
                }else if(ch == '/'){
                    decryptedTask.append('/');
                }else if(Character.isDigit(ch)){ 
                    int dateNum = Character.digit(ch, 10);
                    if(dateNum >= 3){
                        dateNum -= 3;
                    }else{
                        dateNum += 7;
                    }
                    
                    Character ceasarChar = Character.forDigit(dateNum, 10);  
                    decryptedTask.append(ceasarChar);
                    
                }else if(Character.isUpperCase(ch)){
                    char ceasarChar = (char)(((int)ch + (26-shift) - 65) % 26 + 65);
                    decryptedTask.append(ceasarChar);                    
                }
                else{
                    char ceasarChar = (char)(((int)ch + (26-shift) - 97) % 26 + 97);
                    decryptedTask.append(ceasarChar);
                }
                

            }
            
            String decryptedTaskString = decryptedTask.toString();
            decryptedTaskWriter.append(decryptedTaskString + "\n");
        }
        
        decryptedTaskWriter.close();
    }
   
    //DOES NOT MOVE COLUMNS YET, WE WILL GET THERE
    public static void updateTask(Task targetTask, Task updatedTask) throws Exception{
        decrypt();
        ArrayList<String> taskStrings = (ArrayList)Files.readAllLines(Paths.get(path));
        FileWriter clearer = new FileWriter(path, false);
        clearer.write("");
        FileWriter taskWriter = new FileWriter(path, true);
        for(String taskString : taskStrings){
            String[] split = taskString.split(",");
            if(split.length == 5){
                String column = split[0];
                String taskName = split[1];
                String owner = split[2];
                String category = split[3];
                String date = split[4];
                //SEARCH BY COLUMN AND PRIORITY WHEN POSSIBLE
                if(taskName == targetTask.taskName 
                        && owner == targetTask.owner 
                        && category == targetTask.category
                        && date == targetTask.date){
                    taskWriter.append(column + "," 
                            + updatedTask.taskName + "," 
                            + updatedTask.owner + "," 
                            + updatedTask.category + "," 
                            + updatedTask.date + "\n");
                }
                else{
                    taskWriter.append(taskString);
                }
            }
        }
        
        encrypt();
    }
    
    
    
    
}
