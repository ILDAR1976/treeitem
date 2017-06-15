package edu.treeitem.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
	   public static int line = 1;
	   private static int level = 0;
	   private static int id = 0;
	   private char peek = ' ';
	   Hashtable words = new Hashtable();
	   private FileReader reader = null;
	   
	   public Lexer(FileReader fr){
		   reader = fr;
		   id = 0;
		   level = 0;
   }

	   private void readch() throws IOException { 
		   int symbol = reader.read();
		   
		   if (symbol != -1) {
			   peek = (char) symbol;
		   } else {
			   peek = '$';
		   }
	   }
	   
	   private boolean readch(char c) throws IOException {
	      readch();
	      if( peek != c ) return false;
	      peek = ' ';
	      return true;
	   }
	   
	   public Token scan() throws IOException {
	      
		  for(;;readch() ) {
	         
			  if ( peek == ' ' || peek == '\t' || peek == '\r' || peek == '>' ) continue;
			  
			  else if ( peek == '\n' ) line = line + 1;
	         
			  else break;
	      }
	      
	      if ( peek == '$' ) return null;
	      
	      id++; //set the id all tokens

	      
	      if ( peek == '<' ) {
	    	 
	    	 StringBuffer b = new StringBuffer();
	    	 
	    	 readch();
	    	 
	    	 do {
	         
	    		 b.append(peek); readch();
	         
	    	 } while( peek != '>' );
	         
	         String s = b.toString();
	         
	        	 
	         Block bt = (Block) words.get(s);
	         
	         if( bt != null ) return bt;
	         
	         if (s.contains("/")){
	        	 bt = new Block(s, Tag.ENDBLOCK);
	         } else {
	        	 bt = new Block(s, Tag.BEGINBLOCK);
	         }	 
	         
	        
	         if (!s.contains("/")) {
	        	 level++;
	         }
	        	 
	         
	         bt.setLevel(level);

	         if (s.contains("/")) {
	        	 level--;
	         }
	         
	         
	         bt.setId(id);
	         
	         words.put(s, bt);
	         
	         return bt;
	        		 
	      } else {
	    	  
			 StringBuffer b = new StringBuffer();
			 
			 do {
				 
				 if (!(peek == '\t' || peek == '\r' || peek == '\n' )) b.append(peek); readch();
			 
			 } while( peek != '<' && peek != '>');
			 
			 String s = b.toString();
			 
			 Word w = (Word) words.get(s);
			 
			 if( w != null ) return w;
			 
			 w = new Word(s, Tag.ID);
		 
			 w.setId(id);
			 
			 words.put(s, w);

			 return w;
		      
	      }
	   

	   }
}
