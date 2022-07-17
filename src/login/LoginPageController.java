package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnNewUser;

    @FXML
    void AlertNewUser(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("New User");
    	alert.setHeaderText("Entry Denied!");
    	alert.setContentText("Please contact admin for registering a new user!");
    	
    	alert.showAndWait();
		System.out.println("New User!");
    }

    @FXML
    void DoLogin(ActionEvent event) {
    	if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
    		try{
        		Parent root=FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml")); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.setResizable(false);
    			stage.show();
        		
    			Scene scene1=(Scene)btnLogin.getScene();
 			    scene1.getWindow().hide();
 			    
 			    System.out.println("opening wait!");
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("wrong!");
    		Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("Wrong Information entered!");
        	alert.setContentText("Please ensure you have entered correct Username/Password !");
        	
        	alert.showAndWait();
			System.out.println("Wrong!");
    	}
    }

    @FXML
    void initialize() {
        
    }
}
