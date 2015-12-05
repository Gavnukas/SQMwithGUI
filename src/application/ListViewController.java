package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import model.List;;

public class ListViewController {
	// PersonView components
    @FXML private Button quitButton;
   @FXML private TextArea chatLog = new TextArea();
    

    private Main main;
   
   
    // Default constructor
    public ListViewController(){}

    @FXML private void initialize(){
        // Initialise the person table with the two columns.
    	chatLog.appendText("Hello");
       
        
    }
    public void appendChatLog(String msg){
		
    	chatLog.appendText(msg);
    }
    public void getUsernames(){
    	//main.listData.add(new List());
    }
    public void handleQuit(){
    	main.sendToServer("IDEN fag");
    	System.exit(0);
    }
    
    public void setMainApp(Main main){
        this.main = main;
        // Add observable list data to the table
      //  userNames.setItems(main.getListData());
    }
}