package application;

import java.util.Arrays;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UserList;

public class ListViewController {
	// PersonView components
	private ObservableList<UserList> listData = FXCollections.observableArrayList();
    @FXML private Button quitButton;
    @FXML private ToggleButton login;
    @FXML private Button submit;
    @FXML private Button list;
  
    @FXML private ToggleButton pm;
    @FXML private ToggleButton broadcast;
    @FXML private TextField userText;
   @FXML private TextArea chatLog;
   
   	@FXML private TableView<UserList> list_table;
	@FXML private TableColumn<UserList, String> userName;
    private UserList selectedUser;

    private Main main;
    
   
    // Default constructor
    public ListViewController(){}

    @FXML private void initialize(){
        // Initialise the person table with the two columns.
    	pm.setDisable(true);
    	broadcast.setDisable(true);
    	list.setDisable(true);
    	
    	list_table.setItems(listData);

		userName.setCellValueFactory(new PropertyValueFactory<UserList, String>("username"));

		list_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserList>() {

			@Override
			public void changed(ObservableValue<? extends UserList> observable, UserList oldValue, UserList newValue) {
				selectedUser = newValue;// set current flight ref
			}
		});

    	
    	
       
        
    }
    public void stat(){
    	main.sendToServer("STAT");
    }
    public void disableOtherButtons(){
    	if(broadcast.isSelected()){
    		pm.setDisable(true);
    		
    	}else if(pm.isSelected()){
    		broadcast.setDisable(true);
    	}else if(broadcast.isSelected() == false && pm.isDisabled()){
    		pm.setDisable(false);
    	}else if(pm.isSelected() == false && broadcast.isDisabled()){
    		broadcast.setDisable(false);
    	}
    }
    public void onSubmit(){
    	
    	
    	if(login.isSelected() && userText.getText().length()>0){
    		main.sendToServer("IDEN "+ userText.getText().toString());
    		login.setSelected(false);
    		userText.clear();
    		login.setDisable(true);
    		pm.setDisable(false);
        	broadcast.setDisable(false);
        	list.setDisable(false);
        	
    		
    	}else if(broadcast.isSelected() && userText.getText().length() > 0){
    		main.sendToServer("HAIL "+ userText.getText().toString());
    		broadcast.setSelected(false);
    		userText.clear();
    		
    		pm.setDisable(false);
    	}else if(pm.isSelected() && userText.getText().length() > 0 ) {
    		main.sendToServer("MESG"+ selectedUser.getUsername()+" "+ userText.getText().toString());
    		pm.setSelected(false);
    		broadcast.setDisable(false);
    		userText.clear();
    	}
    		
    		else{
    	
    		chatLog.appendText("\n enter message please");
    	}
    }
    public void appendChatLog(String msg){
	   Platform.runLater(new Runnable(){
		   @Override
		   public void run() {
			   if(list.isFocused()){
				   listData.clear();// prepare list to be updated
					// update online users list
					List<String> tempList = Arrays.asList(msg.substring(2).split(","));
					for (String user : tempList) {
						listData.add(new UserList(user));
						System.out.println(user);
					}

			   }else{
			   chatLog.appendText(msg+"\n");
			   }
		   }
		   });
    	
    	  
    }
   public void listAction(){
	  main.sendToServer("LIST");
		
   }
    public void getUsernames(){
    	//main.listData.add(new List());
    }
    public void handleQuit(){
    	main.sendToServer("QUIT");
    	System.exit(0);
    }
    
    public void setMainApp(Main main){
        this.main = main;
        // Add observable list data to the table
      //  userNames.setItems(main.getListData());
    }
}