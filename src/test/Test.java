package test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import book.Book;
import bookReader.BookReader;
import bookWriter.BookWriter;
import keywords.Keyword;
import keywordsComparatorByFrequency.KeywordsComparatorByFrequency;
import keywordsExtractingFromTextFile.KeywordsExtractingFromTextFile;
import multinomialNaiveBayesClassifier.MultinomialNaiveBayesClassifier;
import naiveBayesClassifier.NaiveBayesClassifier;

public class Test {

	
	public static Book getKeywordsOfBook(String pathString,String genre){
		Book book = null;
		
		try {
			List<Keyword> keywords = KeywordsExtractingFromTextFile.getKeywordsFromFile(pathString);
			Collections.sort(keywords, new KeywordsComparatorByFrequency());
			
			book = new Book(keywords.subList(0, 40), genre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
	public static List<Book> getKeywords(String pathString, String genre){
		Path path = Paths.get(pathString);
		List<Book> books = new ArrayList<Book>();
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
			for (Path file : stream) {
				Book book = getKeywordsOfBook(file.toString(),genre); 
				books.add(book);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return books;
	}
	public static List<Book> getBooks(String pathString){
		Path path = Paths.get(pathString);
		List<Book> books = new ArrayList<Book>();
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
			
			for (Path file : stream) {
				String genre = file.getFileName().toString();
				List<Book> currentListOfBooks = getKeywords(file.toString(),genre);
				for (Book book : currentListOfBooks) {
					books.add(book);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return books;
	}
	public static void main(String[] args) throws IOException {

		List<Book> trainData = 
				getBooks("D:\\Dataset\\TrainData");
		List<Book> testData = 
				getBooks("D:\\Dataset\\TestData");

		System.out.println("Accuracy: "+MultinomialNaiveBayesClassifier.getAccuracy(testData, trainData)+"%");
		System.out.println("Accuracy: "+NaiveBayesClassifier.getAccuracy(testData, trainData)+"%");
		System.out.println(i);

	}

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}
	
}
