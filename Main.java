package comp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {
	
	public static int numOfDiffBooks;
	public static int numOfLibraries;
	public static int numOfDays;
	public static ArrayList<Book> allBooks;
	public static ArrayList<Library> allLibraries;
	
	public static int currentDay = 0;
	
	
	public static String in = "/Users/stergios/Desktop/gco/comp/a_example.txt";
	public static String out = "/Users/stergios/Desktop/gco/comp/out.txt";

	public static  void main(String[] args) throws Exception {
		// Read dataset
		File file = new File(in);
		Scanner scanner = new Scanner(file);
		
		//Read information of the problem instance from the dataset
		int numOfDiffBooks = scanner.nextInt();
		int numOfLibraries = scanner.nextInt();
		int numOfDays = scanner.nextInt();


		// Skip line
		scanner.nextLine();
		
		allBooks = new ArrayList<Book>();
		
		
		for (int i = 0; i < numOfDiffBooks; i++) {
			int scr = scanner.nextInt();
			allBooks.add(new Book(i,scr));
		}
		
		scanner.nextLine();
		
		
		allLibraries = new ArrayList<Library>();
		
		for (int i = 0; i < numOfLibraries; i++) {
			int books = scanner.nextInt();
			int signup = scanner.nextInt();
			int parallel = scanner.nextInt();

			scanner.nextLine();
			
			Library tempLib = new Library(i,signup,parallel, books);
			
			for (int j = 0; j < books; j++) {
				
				int bookid = scanner.nextInt();

				tempLib.addBook(findBookByID(bookid));
			}
			
			allLibraries.add(tempLib);
			
			
			scanner.nextLine();
		}
		
		
		
		
		for (int i = 0; i < numOfLibraries; i++) {
			
			int score = 0;
			double brst = 0;
			double sending = allLibraries.get(i).nrOfBooks/allLibraries.get(i).parallel;
			
			brst = sending / allLibraries.get(i).signupTime;
			
			
			for (int j = 0; j < allLibraries.get(i).setOfBooks.size(); j++) {
				score +=  allLibraries.get(i).setOfBooks.get(j).score;
			}
			
			
			allLibraries.get(i).totalScore = score;
			allLibraries.get(i).burst = brst;
			
			
			
		}
		
		
		for (int i = 0; i < numOfLibraries; i++) {
		
			allLibraries.get(i).compareScore = true;
		}
		
		Collections.sort(allLibraries);
		
		
		
		
		
		
		
		for (int i = 0; i < numOfLibraries; i++) {
		
			allLibraries.get(i).rankScore = i;
		}
		
		for (int i = 0; i < numOfLibraries; i++) {
		
			allLibraries.get(i).compareScore = false;
		}
		
		Collections.sort(allLibraries);
		
		for (int i = 0; i < numOfLibraries; i++) {
		
			allLibraries.get(i).rankBurst = i;
		}
		
		
		
		//compute the formula
		for (int i = 0; i < numOfLibraries; i++) {
		
			allLibraries.get(i).totalScore = (allLibraries.get(i).rankScore + allLibraries.get(i).rankBurst)/2;
		}
		
		
		for (int i = 0; i < numOfLibraries; i++) {
		
			allLibraries.get(i).compareScore = true;
			Collections.sort(allLibraries.get(i).setOfBooks);
		}
		
		Collections.sort(allLibraries);
		
		boolean terminate = false;
		int finalLibraries = 0;
		while(true){
			
			if(terminate){
				break;
			}
			
			for (int i = 0; i < numOfLibraries; i++) {
		
				if(allLibraries.get(i).signupTime<(numOfDays-currentDay)){
					finalLibraries++;
					currentDay += allLibraries.get(i).signupTime;
					if(currentDay!=numOfDays){
						allLibraries.get(i).toSend = new ArrayList<Book>();
						for (int k = 0; k < (numOfDays-currentDay)*allLibraries.get(i).parallel; k++){
							
							allLibraries.get(i).toSend.add(allLibraries.get(i).setOfBooks.get(k));
							
						}
					}
					
				}
				else{
					terminate = true;
					break;
				}
					
				Collections.sort(allLibraries.get(i).setOfBooks);
			}
		
			
		}
		
		
		PrintWriter writer = new PrintWriter(out);
		
		//Write the number of slices
		writer.println(finalLibraries);
		
		for (int i = 0; i < finalLibraries; i++) {
			
			writer.println(allLibraries.get(i).id +" " +allLibraries.get(i).toSend.size());
			String boks = "";
			for (int k = 0; k < allLibraries.get(i).toSend.size(); k++) {
				boks += allLibraries.get(i).toSend.get(k).id + " ";
			}
			writer.println(boks);
			
			
		}
		
		writer.close();
		
		
		

	}
	
	
	public static Book findBookByID(int id){
		
		for (int i = 0; i < allBooks.size(); i++) {
			if(allBooks.get(i).id == id){
				return allBooks.get(i);
			}
		}
		return null;
		
		
	}
	
	
}