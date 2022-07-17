package customerpan;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CustomerPanelController {

	Connection getCon;
	PreparedStatement pst;
	String filepath;
	String SelPaper="";
	String SelRate="";
	
	public CustomerPanelController() {
		// TODO Auto-generated constructor stub
		getCon = GetConnection();
	}
	
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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtAddress;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private ComboBox<String> comboHawker;

    @FXML
    private DatePicker DateDoS;

    @FXML
    private TextField txtMobNo;

    @FXML
    private ListView<String> listAvailPapers;

    @FXML
    private ListView<Integer> listAvailPrices;

    @FXML
    private ListView<String> listSelPapers;

    @FXML
    private ListView<Integer> listSelPrices;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnLeft;

    @FXML
    private Button btnSearch;

    @FXML
    void DoLeft(ActionEvent event) {
    	try {                                                // In Parameters
			pst=getCon.prepareStatement("delete from customers where mobile=?");
			pst.setString(1, txtMobNo.getText());
			
			int count=pst.executeUpdate();
			if(count==0) {
				System.out.println("Invalid record!");
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("ERROR");
	        	alert.setHeaderText("NO Entry Exists");
	        	alert.setContentText("This entry may already have been deleted by the Admin!");
	        	
	        	alert.showAndWait();
				System.out.println("INVALID!");
			}
			else {
				System.out.println("Record Deleted");
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("ERROR");
	        	alert.setHeaderText("Record Deleted!");
	        	alert.setContentText("Record Corresponding to "+txtMobNo.getText()+" has been Successfully Deleted!");
	        	
	        	alert.showAndWait();
				System.out.println("INVALID!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void DoSave(ActionEvent event) {
    	LocalDate ld = DateDoS.getValue();
    	try {
    		pst = getCon.prepareStatement("Insert Into customers Values(?,?,?,?,?,?,?,?)");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtAddress.getText());
    		pst.setString(3,comboArea.getSelectionModel().getSelectedItem());
    		pst.setString(4, comboHawker.getSelectionModel().getSelectedItem());
    		pst.setString(5, txtMobNo.getText());
    		pst.setString(6, SelPaper);
    		pst.setString(7, SelRate);
    		pst.setDate(8, Date.valueOf(ld));
    		pst.executeUpdate();
    		System.out.println("SAVED");
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("ERROR");
        	alert.setHeaderText("New Field Created");
        	alert.setContentText("new Field record has been created!");
        	
        	alert.showAndWait();
			System.out.println("INVALID!");
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void DoSearch(ActionEvent event) {
    	try {
			pst = getCon.prepareStatement("select * from customers where mobile=?");
			pst.setString(1, txtMobNo.getText());
			ResultSet records = pst.executeQuery();
			
			if(records.next()) {
				String name = records.getString("cname");
				String add = records.getString("address");
				String area = records.getString("area");
				String hawk = records.getString("hawker");
				String selpa = records.getString("selpaper");
				String selpr = records.getString("selprice");
				String ary[]= selpa.split(" , ");
				Date d = records.getDate("dos");
				for (String string : ary) {
					System.out.println(string);
					listSelPapers.getItems().add(string);
				}
				String[] ary2 = selpr.split(" ,");
				for (String string : ary2) {
					listSelPrices.getItems().add(Integer.parseInt(string));
				}
				txtName.setText(name);
				txtAddress.setText(add);
				comboArea.getEditor().setText(area);
				comboHawker.getEditor().setText(hawk);
				DateDoS.setValue(d.toLocalDate());
			}
			else {
				System.out.println("invalid  record");
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("ERROR");
	        	alert.setHeaderText("No Record Found!");
	        	alert.setContentText("No record was Found corresponding to "+txtMobNo.getText());
	        	
	        	alert.showAndWait();
				System.out.println("INVALID!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void DoSetHawker(ActionEvent event) {
    	String areaSel = comboArea.getSelectionModel().getSelectedItem();
    	ArrayList<String>haww = new ArrayList<String>();
    	try {
    		pst = getCon.prepareStatement("select SelAreas from hawkers");
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			String areaa = rs.getString("SelAreas");
    			boolean isfound = areaa.contains(areaSel);
    			if(isfound==true) {
    				try {
    					pst = getCon.prepareStatement("Select name from hawkers where SelAreas=?");
    					pst.setString(1, areaa);
    					ResultSet naam = pst.executeQuery();
    					while(naam.next()) {
    						String aa = naam.getString("name");
    						haww.add(aa);
    					}
    				}
    				catch(SQLException ee) {
    					ee.printStackTrace();
    				}
    			}
    		}
    		comboHawker.getItems().clear();
    		comboHawker.getItems().addAll(haww);
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	System.out.println(haww);
    	
    }

    @FXML
    void DoShowPapRate(ActionEvent event) {
    	ObservableList<String> itms=	listAvailPapers.getSelectionModel().getSelectedItems(); 
    	
    	String items;
    	for (String srr : itms) {
			items = srr;
			listSelPapers.getItems().add(items);
			SelPaper+=srr+" , ";
			try {
				pst = getCon.prepareStatement("select rate from papers where paper=?");
				pst.setString(1, items);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					int r = rs.getInt("rate");
					listSelPrices.getItems().add(r);
					SelRate+=r+" ,";
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
    	
    	
    } 
    
    @FXML
    void DoUpdate(ActionEvent event) {
    	try {
    		pst = getCon.prepareStatement("update customers set cname=?,address=?,hawker=?,area=?,selpaper=?,selprice=? where mobile = ?");
    		pst.setString(7, txtMobNo.getText());
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtAddress.getText());
    		pst.setString(3, comboHawker.getEditor().getText());
    		pst.setString(4, comboArea.getEditor().getText());
    		pst.setString(5, SelPaper);
    		pst.setString(6, SelRate);
    		int r = pst.executeUpdate();
    		
    		if(r==0)
			{
    			System.out.println("Invalid record");
    			Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("ERROR");
	        	alert.setHeaderText("Cannot Update!");
	        	alert.setContentText("This Entry already exists! For updation , field change is required.");
	        	
	        	alert.showAndWait();
			}
			else
			{
				System.out.println("Record Updated");
				Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("Success!");
	        	alert.setHeaderText("RECORD UPDATED!");
	        	alert.setContentText("This Entry has been Updated!");
	        	
	        	alert.showAndWait();
			}
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
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
    	
    	ArrayList<String> nnews=new ArrayList<String>();
    	try {
			pst = getCon.prepareStatement("select distinct paper from papers" );
			ResultSet records=	pst.executeQuery();
			
			while(records.next())//checks more records
			{
				String news=records.getString("paper");
				nnews.add(news);
			}
			listAvailPapers.getItems().addAll(nnews);
			listAvailPapers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ArrayList<Integer> rates = new ArrayList<Integer>();
    	try {
    		pst = getCon.prepareStatement("select rate from papers");
    		ResultSet recrat = pst.executeQuery();
    		
    		while(recrat.next())
    		{
    			int rr = recrat.getInt("rate");
    			rates.add(rr);
    		}
    		listAvailPrices.getItems().addAll(rates);
    		listAvailPrices.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	
    	
    }
}
