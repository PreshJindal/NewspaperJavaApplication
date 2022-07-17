package billcollector;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BillCollectorController {

	Connection getCon;
	PreparedStatement pst;
	
	public BillCollectorController(){
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
    private Button btnFetch;

    @FXML
    private TextField txtAmount;

    @FXML
    private ImageView jsg;
    @FXML
    private DatePicker DateDoS;

    @FXML
    private DatePicker DateUpto;

    @FXML
    void DoFetch(ActionEvent event) {
    	try {
    		pst = getCon.prepareStatement("select * from billgen where mobile=?");
    		pst.setString(1, comboMobNo.getSelectionModel().getSelectedItem());
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			Date doss = rs.getDate("dos");
    			Date duptto = rs.getDate("dupto");
    			int billl = rs.getInt("bill");
    			DateDoS.setValue(doss.toLocalDate());
    			DateUpto.setValue(duptto.toLocalDate());
    			txtAmount.setText(String.valueOf(billl));
    		}
    	}
    	catch(SQLException e){
    		e.printStackTrace();
    	}
    }

    @FXML
    void DoSetPaid(ActionEvent event) {
    	try {
    		pst = getCon.prepareStatement("update billgen set status=? where mobile=?");
    		pst.setInt(1, 1);
    		pst.setString(2, comboMobNo.getSelectionModel().getSelectedItem());
    		int s = pst.executeUpdate();
    		if(s==0)
				System.out.println("Invalid record");
			else
				System.out.println("Record Updated");
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	File file = new File("C:\\Users\\USER\\Desktop\\Stuff\\Paid.jpg");
        Image image = new Image(file.toURI().toString());
        jsg.setImage(image);
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
