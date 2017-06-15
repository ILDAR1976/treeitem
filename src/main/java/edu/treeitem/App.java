package edu.treeitem;


import java.io.FileNotFoundException;
import java.io.IOException;

import edu.treeitem.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import edu.treeitem.view.Controller;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	private Stage primaryStage;
	
	public static void main( String[] args )
    {
    	launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
	    this.primaryStage = primaryStage;
	    this.primaryStage.setTitle("Job with files application");
	
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        
	        loader.setLocation(getClass().getResource("/" + getPackageNamePattern() + "/view/Main.fxml"));
	        
	        Parent mainView = loader.load();
	
	        Controller controller = loader.getController();
	        
	        controller.setApp(this);
	        
	        Scene scene = new Scene(mainView);
	        
	        this.primaryStage.setScene(scene);
            
	        this.primaryStage.show();
	    
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void copyText(TextArea ta, TextField tf) throws IOException{
		Model m = new Model();
		m.copyText(ta, tf);
	}

	private String getPackageNamePattern(){
		return getClass().getPackage().getName().replace(".", "/");
	}

}
