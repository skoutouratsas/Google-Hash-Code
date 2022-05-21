package comp;



import java.util.*;

public class Book implements Comparable<Book>{
	
	
	public int id;
	
	public int score;
	

	public Book(int id,int score  ){
		
		
		this.id = id;
		
		this.score = score;
		
		
	}
	
	
	
	public int compareTo(Book compareBk){
		
		
		
			int compareScr = (compareBk.score);
			
			return compareScr-this.score;
			
		
		
		
		
	}

	
}
