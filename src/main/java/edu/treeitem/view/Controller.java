package edu.treeitem.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.treeitem.App;
import edu.treeitem.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
public class Controller 
{
	@FXML
	private TextArea outText;
	@FXML
	private TextField edit;
	@FXML
	private TreeView treeView;
	
	private App App;
	
	public Controller(){}
	
	@FXML
    private void initialize() {
		outText.setText("Add text here!");
	}
	
    @FXML
    private void handleAddText() throws IOException {
        App.copyText(outText,edit);
    }

    @SuppressWarnings("restriction")
	@FXML
    private void handleReadFile() throws IOException {
        Model m = new Model();
        
        m.getFileContent();
        treeView.setRoot(m.tree);
        //outText.setText(m.getFileContent());
        
    }
    
    @FXML
    private void handleExit() {
        System.exit(0);
    }

	public void setApp(App App) {
        this.App = App;
    }

}