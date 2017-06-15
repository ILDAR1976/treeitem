package edu.treeitem.model.inter;

public class Stmt extends Node {

	   public Stmt() { }

	   public static Stmt Null = new Stmt();

	   public static Stmt Enclosing = Stmt.Null;  // used for break stmts

}
