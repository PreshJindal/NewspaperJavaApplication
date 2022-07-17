package hawker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class HawkerManagerController {

	Connection getCon;
	PreparedStatement pst;
	String filepath;
	
	public HawkerManagerController()
	{
		getCon = GetConnection();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboName;

    @FXML
    private Button btnFetch;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private TextArea txtArea;

    
    @FXML
    private ImageView imageHawker;

    @FXML
    private Button BtnBrowse;

    @FXML
    private TextField txtAadhaar;

    @FXML
    private Button btnRegister;

    @FXML
    private Button BtnChange;

    @FXML
    private Button BtnLeft;

    static Connection GetConnection()
    {
    	Connection con = null;
    	try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/2021-java","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return con;
    }
    @FXML
    void DoBrowsePic(ActionEvent event) {
    	FileChooser chooser=new FileChooser();
    	
    	chooser.setTitle("Select Profile Pic:");
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	filepath=file.getAbsolutePath();
    	
    	try {
			imageHawker.setImage(new Image(new FileInputStream(file)));
		} 
    	catch (FileNotFoundException e) {	e.printStackTrace();}
    }

    @FXML
    void DoChange(ActionEvent event) {
    	String hname = comboName.getSelectionModel().getSelectedItem();
    	String add =  txtAddress.getText();
    	String cont = txtContact.getText();
    	String selare = txtArea.getText();
    	String Acard = txtAadhaar.getText();
    	try {
    		pst = getCon.prepareStatement("update hawkers set address=?,contact=?,SelAreas=?,pic=?,acardno=? where name = ?");
    		pst.setString(6, hname);
    		pst.setString(1, add);
    		pst.setString(2,cont);
    		pst.setString(3, selare);
    		pst.setString(4, filepath);
    		pst.setString(5, Acard);
    		int r = pst.executeUpdate();
    		
    		if(r==0)
				System.out.println("Invalid record");
			else
				System.out.println("Record Updated");
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void DoDelete(ActionEvent event) {
    	try {                                                // In Parameters
			pst=getCon.prepareStatement("delete from hawkers where name=?");
			pst.setString(1,comboName.getSelectionModel().getSelectedItem());
			
			int count=pst.executeUpdate();
			if(count==0)
				System.out.println("Invalid record!");
			else
				System.out.println("Record Deleted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void DoFetch(ActionEvent event) {
    	try {
			pst = getCon.prepareStatement("select * from hawkers where name=?");
			pst.setString(1, comboName.getSelectionModel().getSelectedItem());
			ResultSet records = pst.executeQuery();
			
			if(records.next()) {
				String name = records.getString("name");
				String add = records.getString("address");
				String conta = records.getString("contact");
				String sear = records.getString("SelAreas");
				String pic = records.getString("pic");
				String aca = records.getString("acardno");
				comboName.getEditor().setText(name);
				txtAddress.setText(add);
				txtContact.setText(conta);
				txtArea.setText(sear);
				txtAadhaar.setText(aca);
				filepath = pic;
				try {
					imageHawker.setImage(new Image(new FileInputStream(pic)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("invalid  record");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void DoRegister(ActionEvent event) {
    	String hname = comboName.getSelectionModel().getSelectedItem();
    	String add =  txtAddress.getText();
    	String cont = txtContact.getText();
    	String areaa = comboArea.getSelectionModel().getSelectedItem();
    	String selare = txtArea.getText();
    	String Acard = txtAadhaar.getText();
    	
    	try {
    		pst = getCon.prepareStatement("Insert Into hawkers Values(?,?,?,?,?,?,?)");
    		pst.setString(1, hname);
    		pst.setString(2, add);
    		pst.setString(3,cont);
    		pst.setString(4,areaa);
    		pst.setString(5, selare);
    		pst.setString(6, filepath);
    		pst.setString(7, Acard);
    		pst.executeUpdate();
    		System.out.println("SAVED");
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void SetSelArea(ActionEvent event) {
    	String selar =", "+ txtArea.getText();
    	String aa = comboArea.getSelectionModel().getSelectedItem() + selar;
    	txtArea.setText(aa);
    	String arr= comboArea.getEditor().getText();
    	
    	if(arr=="")
    		return;
    	else {
    		try {
				pst = getCon.prepareStatement("Insert into area (areas) values (?)");
				pst.setString(1, arr);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    void initialize() {
    	ArrayList<String> hnames=new ArrayList<String>();
    	try {
			pst=getCon.prepareStatement("select distinct name from hawkers" );
			ResultSet records=	pst.executeQuery();
			
			while(records.next())//checks more records
			{
				String nname=records.getString("name");
				hnames.add(nname);
			}
			comboName.getItems().addAll(hnames);
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ArrayList<String> areass=new ArrayList<String>();
    	try {
			pst=getCon.prepareStatement("select distinct areas from area" );
			ResultSet records=	pst.executeQuery();
			
			while(records.next())//checks more records
			{
				String areaa=records.getString("areas");
				areass.add(areaa);
			}
			comboArea.getItems().addAll(areass);
			
		} 
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
