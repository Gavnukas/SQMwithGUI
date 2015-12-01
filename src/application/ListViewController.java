package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.List;;

public class ListViewController {
	// PersonView components
    @FXML private Button quitButton;
   
    

    private Main main;

    // Default constructor
    public ListViewController(){}

    @FXML private void initialize(){
        // Initialise the person table with the two columns.
       
        
    }
    public void getUsernames(){
    	//main.listData.add(new List());
    }
    public void handleQuit(){
    	System.out.println("Quitting app");
    	System.exit(0);
    }
    public void setMainApp(Main main){
        this.main = main;
        // Add observable list data to the table
      //  userNames.setItems(main.getListData());
    }
}