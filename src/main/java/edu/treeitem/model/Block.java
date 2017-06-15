package edu.treeitem.model;

public class Block extends Token {

	public int level = 0;
	public boolean close = false;
	public String lexeme = "";
	
	public Block(String s, int tag) {
		super(tag);
		lexeme = s;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public void setLevel(int level){
		this.level = level;
	}

	public int getLevel(){
		return this.level;
	}

	public String toString() { return lexeme; }

}
