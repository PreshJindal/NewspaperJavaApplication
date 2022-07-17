package billtable;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillStatusCheckerController {

	Connection getCon;
	PreparedStatement pst;
	ObservableList<BillBean> list;
	
	public BillStatusCheckerController(){
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
    private RadioButton radPaidBill;

    @FXML
    private ToggleGroup grp;

    @FXML
    private Button btnGetBill;

    @FXML
    private RadioButton radDueBill;

    @FXML
    private TableView<BillBean> tableBill;

    @FXML
    void DoFetchBill(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	tableBill.getItems().clear();
    	if(radDueBill.isSelected()) {
    		try {
        		pst = getCon.prepareStatement("select * from billgen where status=0");
        		ResultSet records = pst.executeQuery();
        		
        		while(records.next()) {
        			String mobile=records.getString("mobile");
    				String dos=records.getString("dos");
    				String dupto=records.getString("dupto");
    				int bill=records.getInt("bill");
    				
    				BillBean obj=new BillBean(mobile, dos, dupto, bill);
    				
    				list.add(obj);
    				tableBill.setItems(list);
        		}
        	}
        	catch(SQLException e) {
        		e.printStackTrace();
        	}
    	}
    	else if(radPaidBill.isSelected()) {
    		try {
        		pst = getCon.prepareStatement("select * from billgen where status=1");
        		ResultSet records = pst.executeQuery();
        		
        		while(records.next()) {
        			String mobile=records.getString("mobile");
    				String dos=records.getString("dos");
    				String dupto=records.getString("dupto");
    				int bill=records.getInt("bill");
    				
    				BillBean obj=new BillBean(mobile, dos, dupto, bill);
    				
    				list.add(obj);
    				
    				tableBill.setItems(list);
        		}
        	}
        	catch(SQLException e) {
        		e.printStackTrace();
        	}
    	}
    	System.out.println("kk");
    }

    @SuppressWarnings("unchecked")
	void addColumns() {
    	TableColumn<BillBean,String> mobCol = new TableColumn<BillBean,String>("MOBILE");
    	mobCol.setCellValueFactory(new PropertyValueFactory<BillBean,String>("mobile"));
    	mobCol.setMinWidth(70);
    	
    	TableColumn<BillBean,String> dosCol = new TableColumn<BillBean,String>("Date Of Start");
    	dosCol.setCellValueFactory(new PropertyValueFactory<BillBean,String>("dos"));
    	dosCol.setMinWidth(100);
    	
    	TableColumn<BillBean,String> duptoCol = new TableColumn<BillBean,String>("Upto Date");
    	duptoCol.setCellValueFactory(new PropertyValueFactory<BillBean,String>("dupto"));
    	duptoCol.setMinWidth(100);
    	
    	TableColumn<BillBean,Integer> billCol = new TableColumn<BillBean,Integer>("Bill(in Rs.)");
    	billCol.setCellValueFactory(new PropertyValueFactory<BillBean,Integer>("bill"));
    	billCol.setMinWidth(100);
    	
    	tableBill.getColumns().addAll(mobCol, dosCol, duptoCol, billCol); 
    }
    @FXML
    void initialize() { 
    	addColumns();
    }
}
