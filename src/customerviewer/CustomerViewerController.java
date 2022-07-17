package customerviewer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

public class CustomerViewerController {

	Connection getCon;
	PreparedStatement pst;
	ObservableList<CustomerBean> list;
	
	public CustomerViewerController() {
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
    private ComboBox<String> comboPaper;

    @FXML
    private TableView<CustomerBean> tableCustomer;

    @FXML
    private Button btnSaveExcel;

    @FXML
    void DoDetchPaper(ActionEvent event) {
    	list=FXCollections.observableArrayList();//creation of object
 		try {
 			pst=getCon.prepareStatement("select * from customers where selpaper like ?" );
 			pst.setString(1, "%"+comboPaper.getSelectionModel().getSelectedItem()+"%");
 			ResultSet records=	pst.executeQuery();
 				
 			while(records.next())//checks more records
 			{
 				String cname=records.getString("cname");
 				String address=records.getString("address");
 				String area=records.getString("area");
 				String hawker=records.getString("hawker");
 				String mobile=records.getString("mobile");
 				String selpaper=records.getString("selpaper");
 				String selprice=records.getString("selprice");
 				String dos=records.getString("dos");
 				//creation of Object
 				CustomerBean obj=new CustomerBean(cname, address, area, hawker, mobile, selpaper, selprice, dos);
 				//add in observable List
 				list.add(obj);
 			}
 			System.out.println(list.size());
 			
 			//************************************************
 			tableCustomer.setItems(list);//fill data in table
 			//*******************************************
 			
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }

    @FXML
    void DoFetchArea(ActionEvent event) {
    	list=FXCollections.observableArrayList();//creation of object
 		try {
 			pst=getCon.prepareStatement("select * from customers where area like ?" );
 			pst.setString(1, "%"+comboArea.getSelectionModel().getSelectedItem()+"%");
 			ResultSet records=	pst.executeQuery();
 				
 			while(records.next())//checks more records
 			{
 				String cname=records.getString("cname");
 				String address=records.getString("address");
 				String area=records.getString("area");
 				String hawker=records.getString("hawker");
 				String mobile=records.getString("mobile");
 				String selpaper=records.getString("selpaper");
 				String selprice=records.getString("selprice");
 				String dos=records.getString("dos");
 				//creation of Object
 				CustomerBean obj=new CustomerBean(cname, address, area, hawker, mobile, selpaper, selprice, dos);
 				//add in observable List
 				list.add(obj);
 			}
 			System.out.println(list.size());
 			
 			//************************************************
 			tableCustomer.setItems(list);//fill data in table
 			//*******************************************
 			
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
    
    @FXML
    void DoSave(ActionEvent event) throws IOException {
    	Writer writer = null;
        try {
        	File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="cust. name,address,area,hawker,mobile,selpaper,selprice,dos\n";
            writer.write(text);
            for (CustomerBean p : list)
            {
    			text = p.getCname()+ "," + p.getAddress()+ "," + p.getArea()+ "," + p.getHawker()+","+p.getMobile()+","+p.getSelpaper()+","+p.getSelprice()+","+p.getDos()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    
    @FXML
    void DoFetchAll(ActionEvent event) {
    	 list=FXCollections.observableArrayList();//creation of object
 		try {
 			pst=getCon.prepareStatement("select * from customers" );
 			ResultSet records=	pst.executeQuery();
 				
 			while(records.next())//checks more records
 			{
 				String cname=records.getString("cname");
 				String address=records.getString("address");
 				String area=records.getString("area");
 				String hawker=records.getString("hawker");
 				String mobile=records.getString("mobile");
 				String selpaper=records.getString("selpaper");
 				String selprice=records.getString("selprice");
 				String dos=records.getString("dos");
 				//creation of Object
 				CustomerBean obj=new CustomerBean(cname, address, area, hawker, mobile, selpaper, selprice, dos);
 				//add in observable List
 				list.add(obj);
 			}
 			System.out.println(list.size());
 			
 			//************************************************
 			tableCustomer.setItems(list);//fill data in table
 			//*******************************************
 			
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }

    @SuppressWarnings("unchecked")
	void addColumns() {
    	TableColumn<CustomerBean,String> nameCol = new TableColumn<CustomerBean,String>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("cname"));
    	nameCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> addressCol = new TableColumn<CustomerBean,String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("address"));
    	addressCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> areaCol = new TableColumn<CustomerBean,String>("Area");
    	areaCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("area"));
    	areaCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> hawkerCol = new TableColumn<CustomerBean,String>("Hawker");
    	hawkerCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("hawker"));
    	hawkerCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> mobileCol = new TableColumn<CustomerBean,String>("Mobile");
    	mobileCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("mobile"));
    	mobileCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> paperCol = new TableColumn<CustomerBean,String>("Selected Papers");
    	paperCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("selpaper"));
    	paperCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> priceCol = new TableColumn<CustomerBean,String>("Prices");
    	priceCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("selprice"));
    	priceCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean,String> dosCol = new TableColumn<CustomerBean,String>("Date Of Start");
    	dosCol.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("dos"));
    	dosCol.setMinWidth(100);
    	
    	tableCustomer.getColumns().addAll(nameCol, addressCol, areaCol, hawkerCol, mobileCol, paperCol, priceCol, dosCol);
    }
    @FXML
    void initialize() {
    	addColumns();
    	
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
			comboPaper.getItems().addAll(nnews);
    	}
    	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
