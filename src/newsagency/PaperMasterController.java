package newsagency;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PaperMasterController {

	Connection connect;
	PreparedStatement pst;
	public PaperMasterController()
	{
		connect  = getConnection();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboPapers;

    @FXML
    private TextField txtRate;

    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button BtnRemove;

    @FXML
    void DoRemove(ActionEvent event) {
        String SelNews = comboPapers.getSelectionModel().getSelectedItem();
        if(SelNews==null || txtRate.getText()==null)
        {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("Empty field found");
        	alert.setContentText("Please fill all the fields!");
        	
        	alert.showAndWait();
        }
        else {
        	try {
				pst = connect.prepareStatement("delete from papers where paper=? ");
				pst.setString(1, SelNews);
				int up = pst.executeUpdate();
				if(up==1) {
					System.out.println("DELETED!");
					Alert alert = new Alert(AlertType.INFORMATION);
		        	alert.setTitle("DELETED!");
		        	alert.setHeaderText("Field Removed!");
		        	alert.setContentText(SelNews+" Has been successfully Removed!");
		        	
		        	alert.showAndWait();
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
		        	alert.setTitle("ERROR");
		        	alert.setHeaderText("No Field Entry!");
		        	alert.setContentText("This Entry does not exist!");
		        	
		        	alert.showAndWait();
					System.out.println("INVALID!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    @FXML
    void DoSave(ActionEvent event) {
        String SelNews = comboPapers.getSelectionModel().getSelectedItem();
        if(SelNews.equals("") || txtRate.getText().length()==0)
        {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("Empty field found");
        	alert.setContentText("Please fill all the fields!");
        	
        	alert.showAndWait();
        }
        else {
        	try {
				pst = connect.prepareStatement("insert into papers values(?,?) ");
				pst.setString(1, SelNews);
				pst.setInt(2, Integer.parseInt(txtRate.getText().trim()));
				pst.executeUpdate();
				System.out.println("SAVED!");
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("SAVED!");
	        	alert.setHeaderText("New Field Created");
	        	alert.setContentText("new field "+SelNews+ " has been Created with rate = "+txtRate.getText());
	        	
	        	alert.showAndWait();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    @FXML
    void DoUpdate(ActionEvent event) {
        String SelNews = comboPapers.getSelectionModel().getSelectedItem();
        if(SelNews==null || txtRate.getText()==null)
        {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("Empty field found");
        	alert.setContentText("Please fill all the fields!");
        	
        	alert.showAndWait();
        }
        else {
        	try {
				pst = connect.prepareStatement("update papers set rate=? where paper=? ");
				pst.setString(2, SelNews);
				pst.setInt(1, Integer.parseInt(txtRate.getText()));
				int up = pst.executeUpdate();
				if(up==1) {
					System.out.println("UPDATED!");
					Alert alert = new Alert(AlertType.INFORMATION);
		        	alert.setTitle("UPDATED!");
		        	alert.setHeaderText(SelNews+" has been successfully Updated with rate = "+txtRate.getText());
		        	alert.setContentText("");
		        	
		        	alert.showAndWait();
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
		        	alert.setTitle("ERROR");
		        	alert.setHeaderText("Duplicate Entry!");
		        	alert.setContentText("This Entry already exists! For updation , field change is required.");
		        	
		        	alert.showAndWait();
					System.out.println("INVALID!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    static Connection getConnection()
    {
    	
    	Connection getCon=null;
    	try {
    		getCon = DriverManager.getConnection("jdbc:mysql://localhost/2021-java","root","");
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	return getCon;
    }
    
    @FXML
    void SetRate(ActionEvent event) {
    	String n = comboPapers.getSelectionModel().getSelectedItem();
//        if(n=="Dainik Bhaskar") {
//        txtRate.setText("990");
//        }
//        else if(n=="The Tribune") {
//        	txtRate.setText("1210");
//        }
//        else if(n=="The Hindu") {
//        	txtRate.setText("1120");
//        }
//        else if(n=="Punjab Kesari") {
//        	txtRate.setText("660");
//        }
//        else if(n=="The Hindustan Times") {
//        	txtRate.setText("1290");
//        }
//        else if(n=="The Times Of India") {
//        	txtRate.setText("1340");
//        }
    	try {
			pst = connect.prepareStatement("Select rate from papers where paper = ?");
			pst.setString(1, n);
			ResultSet record = pst.executeQuery();
			
			if(record.next()) {
				int ra = record.getInt("rate");
				txtRate.setText(String.valueOf(ra));
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("ERROR");
	        	alert.setHeaderText("NO ENTRY FOUND!");
	        	alert.setContentText("Please ensure you have already saved that entry in DataBase");
	        	
	        	alert.showAndWait();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        ArrayList<String> nnews=new ArrayList<String>();
    	try {
			pst=connect.prepareStatement("select distinct paper from papers" );
			ResultSet records=	pst.executeQuery();
			
			while(records.next())//checks more records
			{
				String news=records.getString("paper");
				nnews.add(news);
			}
			comboPapers.getItems().addAll(nnews);
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
