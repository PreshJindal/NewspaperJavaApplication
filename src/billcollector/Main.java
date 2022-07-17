package billcollector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	
 public void start(@SuppressWarnings("exports") Stage primaryStage) 
   {
		try {
				Parent root=(Parent) FXMLLoader.load(getClass().getResource("BillCollector.fxml")); 
				Scene scene = new Scene(root,486,539);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

