package bookReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import book.Book;
import keywords.Keyword;

public class BookReader {
	public static List<Book> readBooksFromFile(String path){
		List<Book> books = new ArrayList<Book>();
		try (
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
		){
			List<Keyword> keywords = new ArrayList<Keyword>();
			String line = br.readLine();
			while(line!=null){
				String[] attributes = line.split("[ ]+");
				for (int i = 0; i < attributes.length - 1; i++) {
					Keyword keyword = new Keyword(attributes[i]);
					keywords.add(keyword);
				}
				String genre = attributes[attributes.length - 1];
				
				Book book = new Book(keywords, genre);
				books.add(book);
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
		
		return books;
	}
}
