package edu.treeitem.model;

public class Token {
	
	public int id = 0;
	public int parent = 0;
	public final int tag;
	public Token(int t) { tag = t; }
	public String toString() {return "" + (char)tag;}

	public void setParent(int parent){
		this.parent = parent;
	}
	
	public int getParent(){
		return this.parent;
	}

}
