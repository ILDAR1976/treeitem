package edu.treeitem.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;

public class Model{
	
	private FileReader reader = null; 
	private String fileContent = ""; 
	public TreeItem tree = null;
	
	public Model() throws IOException{

		try {
			
			reader = new FileReader(getCurrentDir() + "/testfile.txt");
		
		} catch (Exception e) {
			
			reader.close();
		
		}

	}
	
	public String getFileContent() throws IOException{
		getLexeme();
		return this.fileContent;
	}
	
	private String getCurrentDir(){
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath().toString();
	}
	
	private void getLexeme() throws IOException{
		
		Lexer lex = new Lexer(reader);
		
		Parser parse = new Parser(lex);
		
		parse.program();
		
		tree = parse.tree;
	}
	
	private static Comparator<Token> compareByTag = new Comparator<Token>() {
	    @Override
	    public int compare(Token o1, Token o2) {
	 
	        if(o1.tag < o2.tag) {
	            return -1;
	        } else if(o1.tag > o2.tag) {
	            return 1;
	        } else {
	            return 0;
	        }
	    }
	};
	
 	public void copyText(TextArea area, TextField field){
		area.setText(area.getText() + "\n" + field.getText());
	}
	
}