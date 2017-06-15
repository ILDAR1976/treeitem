package edu.treeitem.model;

import java.io.IOException;

import edu.treeitem.model.inter.Seq;
import edu.treeitem.model.inter.Stmt;

import javafx.scene.control.TreeItem;

public class Parser {
	private Lexer lex;    // lexical analyzer for this parser
	private Token look;   // lookahead tagen
	private TreeItem currentTree = null;
	private int currentLevel = 0;
	public TreeItem tree = null;
	
	public Parser(Lexer l) throws IOException { 
        lex = l; 
		move();
		tree = new TreeItem<String> (((Block)look).lexeme);
        tree.setExpanded(true);
        currentTree = tree;
        currentLevel = ((Block)look).level;
	}
	
	void move() throws IOException { look = lex.scan(); }
	
	void error(String s) { throw new Error("near line " + lex.line + ": " + s); }
	
	void match(int t) throws IOException {
	   if( look.tag == t ) move();
	   else error("syntax error");
	}
	
    public void program() throws IOException {  // program -> block
	      Stmt s = block();
	}
    
    Stmt block() throws IOException {  // block -> { stmts }
        match(Tag.BEGINBLOCK);  
        	Stmt s = stmts();
        match(Tag.ENDBLOCK);
        return s;
     }
    
    Stmt stmts() throws IOException {
        if ( look.tag == Tag.ENDBLOCK ) {
        	return Stmt.Null;
        } else {
        	return new Seq(stmt(), stmts());
        }
     }

    @SuppressWarnings({ "restriction", "unchecked" })
	Stmt stmt() throws IOException {

        switch( look.tag ) {

        case Tag.ID:
      	   TreeItem<String> item1 = new TreeItem<String> (((Word)look).lexeme);            
           currentTree.getChildren().add(item1);
           
           move();
           return Stmt.Null;


        case Tag.BEGINBLOCK:
        	TreeItem<String> item2 = new TreeItem<String> (((Block)look).lexeme);            
        	
        	int blook = ((Block)look).level; 
            
        	if ( blook < currentLevel ){
            	
        		for (int i = blook; i <= currentLevel; i++) {
            		currentTree = currentTree.getParent();
				}
            	
            	currentTree.getChildren().add(item2);
            	currentTree = item2;
            	currentLevel = ((Block)look).level;
            
        	} else {
            	
        		currentTree.getChildren().add(item2);
            	currentTree = item2;
            	currentLevel = blook;
            
        	}
            
           return block();

        }
		return null;
     }
}
