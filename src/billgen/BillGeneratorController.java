package billgen;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillGeneratorController {

	Connection getCon;
	PreparedStatement pst;
	
	public BillGeneratorController(){
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
    private ComboBox<String> comboMobNo;

    @FXML
    private Button btndoFetchDos;

    @FXML
    private DatePicker DateDoS;

    @FXML
    private DatePicker DateUtD;

    @FXML
    private TextField txtTotPrice;

    @FXML
    private Button btnDoGenTotBill;

    @FXML
    private TextField txtAmountPayable;

    @FXML
    private Button btnSave;

    @FXML
    void DoFetchDoS(ActionEvent event) {
    	try {
    		pst = getCon.prepareStatement("select dos from customers where mobile=?");
    		pst.setString(1, comboMobNo.getSelectionModel().getSelectedItem());
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			Date d = rs.getDate("dos");
    			DateDoS.setValue(d.toLocalDate());
    		}
    		
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void DoGenTotalBill(ActionEvent event) {
    	LocalDate lddos = DateDoS.getValue();
    	LocalDate ldud = DateUtD.getValue();
    	Period period = Period.between(lddos, ldud);
    	int diff = Math.abs(period.getDays());
    	String dayprice = txtTotPrice.getText();
    	int price = Integer.parseInt(dayprice);
    	txtAmountPayable.setText(String.valueOf(diff*price));
    }

    @FXML
    void DoSave(ActionEvent event) {
    	try {
    		pst = getCon.prepareStatement("insert into billgen values (?,?,?,?,?)");
    		pst.setString(1, comboMobNo.getSelectionModel().getSelectedItem());
    		pst.setDate(2, Date.valueOf(DateDoS.getValue()));
    		pst.setDate(3, Date.valueOf(DateUtD.getValue()));
    		pst.setInt(4, Integer.parseInt(txtAmountPayable.getText()));
    		pst.setInt(5, 0);
    		pst.executeUpdate();
    		System.out.println("SAVED");
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void DoTotalPrice(ActionEvent event) {
    	try {
    		pst = getCon.prepareStatement("select selprice from customers where mobile = ?");
    		pst.setString(1, comboMobNo.getSelectionModel().getSelectedItem());
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			String rat = rs.getString("selprice");
    			String r[] = rat.split(" ,");
    			int rww=0;
    			for(int i=0;i<r.length;i++) {
    				System.out.println(r[i]);
    			}
    			for (String string : r) {
					rww =rww+ Integer.parseInt(string);
				}
    			System.out.println(rww);
    			txtTotPrice.setText(String.valueOf(rww));
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    

    @FXML
    void initialize() {
    	ArrayList<String> mobs = new ArrayList<String>();
    	try {
    		pst = getCon.prepareStatement("select mobile from customers");
    		ResultSet recmob = pst.executeQuery();
    		
    		while(recmob.next())
    		{
    			String rr = recmob.getString("mobile");
    			mobs.add(rr);
    		}
    		comboMobNo.getItems().addAll(mobs);
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
        
    }
}
