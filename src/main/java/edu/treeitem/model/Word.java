package edu.treeitem.model;


public class Word extends Token {

	public String lexeme = "";
	public Word(String s, int tag) { super(tag); lexeme = s; }
	public String toString() { return lexeme; }
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

}
