package hawkertable;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HawkerViewPanelController {

	Connection getCon;
	PreparedStatement pst;
	ObservableList<HawkerBean> list;
	
	public HawkerViewPanelController(){
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
    private ComboBox<String> comboArea;

    @FXML
    private Button BtnFetch;

    @FXML
    private Button BtnFetchAll;

    @FXML
    private TableView<HawkerBean> TableHawker;

    @FXML
    void DoFetch(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	String ar = comboArea.getSelectionModel().getSelectedItem();
    	try {
    		pst = getCon.prepareStatement("select * from hawkers where SelAreas like ? ");
    		pst.setString(1, "%"+ar+"%");
    		ResultSet records = pst.executeQuery();
    		
    		while(records.next()) {
    			String name=records.getString("name");
				String address=records.getString("address");
				String contact=records.getString("contact");
				String selareas=records.getString("SelAreas");
				String acardno=records.getString("acardno");
				
				HawkerBean obj=new HawkerBean(name, address, contact, selareas, acardno);
				
				list.add(obj);
    		}
    		TableHawker.setItems(list);
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void DoFetchAll(ActionEvent event) {
    	 list=FXCollections.observableArrayList();//creation of object
    		try {
    			pst=getCon.prepareStatement("select * from hawkers" );
    			ResultSet records=	pst.executeQuery();
    				
    			while(records.next())//checks more records
    			{
    				String name=records.getString("name");
    				String address=records.getString("address");
    				String contact=records.getString("contact");
    				String selareas=records.getString("SelAreas");
    				String acardno=records.getString("acardno");
    				
    				//creation of Object
    				HawkerBean obj=new HawkerBean(name, address, contact, selareas, acardno);
    				//add in observable List
    				list.add(obj);
    			}
    			System.out.println(list.size());
    			
    			//************************************************
    			TableHawker.setItems(list);//fill data in table
    			//*******************************************
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    }
    
    @SuppressWarnings("unchecked")
	void AddColumns() {
    	TableColumn<HawkerBean,String> nameCol = new TableColumn<HawkerBean,String>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("name"));
    	nameCol.setMinWidth(70);
    	
    	TableColumn<HawkerBean,String> addressCol = new TableColumn<HawkerBean,String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("address"));
    	addressCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean,String> contCol = new TableColumn<HawkerBean,String>("Contact");
    	contCol.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("contact"));
    	contCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean,String> areaCol = new TableColumn<HawkerBean,String>("Areas Serving");
    	areaCol.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("selareas"));
    	areaCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean,String> acardCol = new TableColumn<HawkerBean,String>("Aadhaar Card No.");
    	acardCol.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("acardno"));
    	acardCol.setMinWidth(130);
    	
    	TableHawker.getColumns().addAll(nameCol, addressCol, contCol, areaCol, acardCol);

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
    	
    	AddColumns();
    }
}
