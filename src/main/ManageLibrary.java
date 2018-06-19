package main;
import java.io.*;
import java.util.*;

import Book.*;

public class ManageLibrary {
	
		
		// Store book related information
		static Map<Long, BookDetails> BookMap = new TreeMap<Long, BookDetails>();
		
		
		//input
		static InputStreamReader isr = new InputStreamReader(System.in);
		static BufferedReader bufferedReader = new BufferedReader(isr);
		
		//Instantiate Book object to call methods
		static Book BookObj = new Database();
		
		//Declare variable to generate unique number
		static Long index = 0l;
		

public static void main(String[] args)  {
	
	System.out.println("********************************************");
	System.out.println("  Welcome to KathmanduUniversity Library  ");
	System.out.println("********************************************");
	System.out.println("**Please select you option to take action ** ");

	mainMenu();		
	
	Boolean isNotExit = true;
	
	while(isNotExit){
		System.out.println("** Please Enter your Option **");
		try{
			
		Integer selectedOption = Integer.valueOf(bufferedReader.readLine());
		
			action(selectedOption);
		}catch (Exception e) {
			System.out.println("Error Input : "+e.getMessage());	
			continue;
		}
					
	}	
}

private static void mainMenu() {
	//Instantiate FormattedColumns Object to print line
	
	FormattedColumns formattedColumnsObj = new FormattedColumns();
	
	formattedColumnsObj
			.addLine(
					"** MAIN MENU ***")	
			.addLine("Press '1' To Display Existing Available Books")
			.addLine("Press '2' To Display Existing Issued Books")
			.addLine("Press '3' To Add New Book")
			.addLine("Press '4' Issue a Book")
			.addLine("Press '5' Delete a Book")
	        .addLine("Press '6' Update a Book");
		
	//print the output
	formattedColumnsObj.print();
	
}

private static void action(Integer selectedOption) throws IOException {	

	switch(selectedOption){	
	
	case 1:
		System.out.println("Display Existing Available Books");
		System.out.println("......|.....|.....|.....|");
		displayAvailableBookInfo(BookMap);
		break;
	case 2:
		System.out.println("Display Existing Issued Books");
		System.out.println("......|.....|.....|......|");
		displayIssuedBookInfo(BookMap);
		mainMenu();
		break;
		
	case 3:
		System.out.println("Add New Book");
		addBook();
		mainMenu();
		break;		
	case 4:
		System.out.println("Issue a Book");
		issueBook();
		mainMenu();
		break;
	case 5:
		System.out.println("Delete a Book*");
		deleteBook(BookMap);
		mainMenu();
		break;
	case 6:
		System.out.println("Update a Book*");
		updateBook(BookMap);
		mainMenu();
		break;
	
	default:
		System.out.println("Wrong entry!");
		mainMenu();
		break;
		
	
	}
}




private static Boolean addBook() throws IOException{
	    try {
		//Set default values
		BookDetails BookDetails = new BookDetails();
		BookDetails.setBookID(++index);
		BookDetails.setIssued(false);	
		
		//get Book details as input		
		System.out.println("Enter Book Title:");
		BookDetails.setTitle(bufferedReader.readLine());
		System.out.println("Enter Book Author:");
		BookDetails.setAuthor(bufferedReader.readLine() );		
		System.out.println("Enter Publisher:");
		BookDetails.setPublisher(bufferedReader.readLine());
		
		return BookObj.addBook(BookDetails,BookMap);
	    }catch(NumberFormatException nfe){
			nfe.getMessage();
			
		}		
		return Boolean.FALSE.booleanValue();
}

private static Boolean updateBook(Map<Long, BookDetails> bookMap2) throws IOException{
    try {
	//Set default values
	BookDetails BookDetails = new BookDetails();
	BookDetails.setIssued(false);	
	
	System.out.println("Enter id from above  you want to update:");
	BookDetails.setTitle(bufferedReader.readLine());
	
	if(BookMap.containsKey(index))
	{	
	System.out.println("Enter Book Title:");
	BookDetails.setTitle(bufferedReader.readLine());
	System.out.println("Enter Book Author:");
	BookDetails.setAuthor(bufferedReader.readLine() );		
	System.out.println("Enter Publisher:");
	BookDetails.setPublisher(bufferedReader.readLine());
	
	
	return BookObj.addBook(BookDetails,BookMap);
	}
    }catch(NumberFormatException nfe){
		nfe.getMessage();
		
	}
    return Boolean.FALSE.booleanValue();
    
}



public static void displayAvailableBookInfo(Map<Long, BookDetails> BookMap) {

	if(BookMap.isEmpty()){
		System.out.println("No Book Added Yet!");		

		return;
	}else{
		
		BookObj.displayAvailableBookInfo(BookMap);

	}
}

private static Boolean deleteBook(Map<Long,BookDetails> BookMap) throws IOException{

	if(BookMap.isEmpty()){
		System.out.println("No Book Added Yet!");		
		
		return Boolean.FALSE.booleanValue();
	}else{			
		try{
			displayAvailableBookInfo(BookMap);
			System.out.println("Enter BookID from above to delete");
			Long bookID = Long.valueOf(bufferedReader.readLine());
			if(BookMap.containsKey(bookID)){
				
				BookObj.deleteBook(bookID, BookMap);
			}else{
				System.out.println("BookID Not available!");
				//return true once book issues
				return Boolean.TRUE.booleanValue();
			}			
			
			//return true once book issues
			return Boolean.TRUE.booleanValue();
		}catch(NumberFormatException nfe){
			nfe.getMessage();
			
		}
	}			
	
	return Boolean.FALSE.booleanValue();
	
}

private static Boolean issueBook() throws IOException{
	
	//Check Map empty
	if(BookMap.isEmpty()){
		System.out.println("No Book Added Yet!");		
		
		return Boolean.FALSE.booleanValue();
	}else{			
		try{
			displayAvailableBookInfo(BookMap);
			System.out.println("Enter BookID from above to issue");
			Long bookID = Long.valueOf(bufferedReader.readLine());
			if(BookMap.containsKey(bookID)){					
				BookMap.get(bookID).setIssued(Boolean.TRUE.booleanValue());
			}else{
				System.out.println("BookID Not available!");
				return Boolean.TRUE.booleanValue();
			}				
			return Boolean.TRUE.booleanValue();
		}catch(NumberFormatException nfe){
			nfe.getMessage();
			
		}
	}		
	return Boolean.FALSE.booleanValue();
	
}

public static void displayIssuedBookInfo(Map<Long, BookDetails> BookMap) {
	if(BookMap.isEmpty()){
		System.out.println("No Book Added Yet!");		

		return;
	}else{
		BookObj.displayIssuedBookInfo(BookMap);

	}
}


}



