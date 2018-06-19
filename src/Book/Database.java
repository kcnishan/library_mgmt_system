package Book;


import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import main.Constants;
import main.FormattedColumns;;

public class Database implements Book{
	
	
	//Add
	@Override
	public Boolean addBook(BookDetails BookDetails,Map<Long, BookDetails> BookMap) {
		
		//Add book to map
		BookMap.put(BookDetails.getBookID(), BookDetails);
		//Return true once book added
		return Boolean.TRUE.booleanValue();
	}
	
	//delete
	@Override
	public Boolean deleteBook(Long bookID,Map<Long, BookDetails> BookMap) {
		//Iterate Book map
		Iterator<Entry<Long, BookDetails>> itr = BookMap
		.entrySet().iterator();
		while (itr.hasNext()) {			
			Entry<Long, BookDetails> entry = itr.next();
		
			if(bookID == entry.getKey()){
				
				if(entry.getValue().getIssued()){
					System.out.println("You can not delete issued book!");
					break;
				}
				
				itr.remove();
			
				return Boolean.TRUE.booleanValue();
			}			
		}
		//Return false if book not deleted
		return Boolean.FALSE.booleanValue();
	}

	//update
	@Override
	public Boolean updateBook(BookDetails BookDetails,Map<Long, BookDetails> BookMap){
		// TODO Auto-generated method stub
		//Add book to map
				BookMap.put(BookDetails.getBookID(), BookDetails);
				//Return true once book added
				return Boolean.TRUE.booleanValue();
	}

	
	@Override
	public void displayAvailableBookInfo(Map<Long, BookDetails> BookMap) {
		
		//Iterate Book map
		Iterator<Entry<Long, BookDetails>> itr = BookMap
				.entrySet().iterator();
		
		FormattedColumns formattedColumnsObj = new FormattedColumns();		
		formattedColumnsObj.addLine(Constants.BOOKID,Constants.AUTHOR,Constants.TITLE,Constants.PUBLISHER);
		//Iterate Books object
		while (itr.hasNext()) {			
			Entry<Long, BookDetails> entry = itr.next();
			BookDetails BookDetails = entry.getValue();
		
			if(BookDetails!=null && !BookDetails.getIssued()){
				formattedColumnsObj.addLine(entry.getKey().toString(),
						BookDetails.getAuthor(), BookDetails.getTitle(),BookDetails.getPublisher());
			}			
		}
		
		formattedColumnsObj.print();		
	}

	
	@Override
	public void displayIssuedBookInfo(Map<Long, BookDetails> BookMap) {
		//Iterate Book map
		Iterator<Entry<Long, BookDetails>> itr = BookMap
		.entrySet().iterator();
	
		FormattedColumns formattedColumnsObj = new FormattedColumns();		
		formattedColumnsObj.addLine(Constants.BOOKID,Constants.AUTHOR,Constants.TITLE,Constants.PUBLISHER);
		//Iterate Books object
		while (itr.hasNext()) {			
			Entry<Long, BookDetails> entry = itr.next();
			BookDetails BookDetails = entry.getValue();
		
			if(BookDetails.getIssued()){				
				formattedColumnsObj.addLine(entry.getKey().toString(),
						BookDetails.getAuthor(), BookDetails.getTitle(),BookDetails.getPublisher());
			}			
		}
		
		formattedColumnsObj.print();
	}

}