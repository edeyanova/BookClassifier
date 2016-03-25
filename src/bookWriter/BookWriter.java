package bookWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import book.Book;
import keywords.Keyword;

public class BookWriter {
//	public static void WriteBookToFile(Book book, String path) throws IOException{
//		File file = new File(path);
//		
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		
//		try(FileWriter fw = new FileWriter(file);
//				BufferedWriter bw = new BufferedWriter(fw)){
//			for (Keyword keyword : book.getKeywords()) {
//				bw.write(keyword.getStem());
//				bw.write(" ");
//			}
//			bw.write(book.getGenre());
//			bw.newLine();
//		}
//	}
	
	public static void writeBooksToFile(String path, Book... books){
		File file = new File(path);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try(FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw)){
			for (Book book : books) {
				for (Keyword keyword : book.getKeywords()) {
					bw.write(keyword.getStem());
					bw.write(" ");
				}
				bw.write(book.getGenre());
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
