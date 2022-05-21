package comp;


import java.util.*;

public class Library implements Comparable<Library>{
	
	public int id;
	
	public ArrayList<Book> setOfBooks;
	
	public ArrayList<Book> toSend;
	
	public int signupTime;
	
	public int parallel;
	
	//------------------------
	
	public int nrOfBooks;
	
	public int value;
	
	public int rankBurst;
	
	public int rankScore;
	
	public int totalScore;
	
	public boolean compareScore;
	
	public double burst;

	

	public Library(int id,int signupTime,int parallel , int nrOfBooks ){
		
		this.id = id;
		
		this.setOfBooks = new ArrayList<Book>();
		
		this.signupTime = signupTime;
		
		this.parallel = parallel;
		
		this.nrOfBooks = nrOfBooks;
		
		
	}
	
	public void addBook( Book bk){
		
		
		
		this.setOfBooks.add(bk);
		
		
	}
	
	
	public int compareTo(Library compareLib){
		
		if(compareScore){
			int compareScr = (compareLib.totalScore);
			
			return compareScr-this.totalScore;
			
		}
		else{
			double compareBrst = (compareLib.burst);
			
			if(compareBrst>this.burst){
				return 1;
			}
			else{
				return -1;
			}
			
			
		}
		
		
	}
	
	

	
}
