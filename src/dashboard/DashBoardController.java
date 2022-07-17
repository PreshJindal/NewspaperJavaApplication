package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void openBillCollector(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billcollector/BillCollector.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openBillGenerator(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billgen/BillGenerator.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openBillTable(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billtable/BillStatusChecker.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openCustomerPanel(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerpan/CustomerPanel.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openCustomerTable(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerviewer/CustomerViewer.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openHawkerManager(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/hawker/HawkerManager.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			stage.setResizable(false);
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openHawkerView(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/hawkertable/HawkerViewPanel.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void openPaperMaster(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/newsagency/PaperMaster.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
    		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}
