package naiveBayesClassifier;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import book.Book;
import keywords.Keyword;

public class NaiveBayesClassifier {
	
	private static boolean containsKeyword(Keyword keyword, String genre, int index,
			List<Book> books){
		Book book = books.get(index);
		Collection<Keyword> keywords = book.getKeywords();
		if(genre.equalsIgnoreCase(book.getGenre())){
			for (Keyword currentKeyword : keywords) {
				if (keyword.getStem().equalsIgnoreCase(currentKeyword.getStem())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean containsKeyword(Keyword keyword, String genre, List<Book> books){
		for (int i = 0; i < books.size(); i++) {
			if (containsKeyword(keyword,genre,i,books)) {
				return true;
			}
		}
		return false;
	}
	
	private static int containsKeywords(Book book, String genre, List<Book> books){
		Collection<Keyword> keywords = book.getKeywords();
		int count = 0;
		
		for (Keyword keyword : keywords) {
			if (containsKeyword(keyword, genre,books)) {
				count++;
			}
		}
		
		return count;
	}
	
	
//	private  static int countKeywordOnCurrentIndex(Keyword keyword,
//			String genre,int index,List<Book> books){
//		int count = 0;
//		Book book = books.get(index);
//		Collection<Keyword> keywords = book.getKeywords();
//		if(genre.equalsIgnoreCase(book.getGenre())){
//			for (Keyword currentKeyword : keywords) {
//				if (keyword.getStem().equalsIgnoreCase(currentKeyword.getStem())) {
//					count++;
//				}
//			}
//		}
//		
//		return count;
//	}
	
//	private static int countKeyword(Keyword keyword,String genre,List<Book> books){
//		int count = 0;
//		for (int i = 0; i < books.size(); i++) {
//			count+=countKeywordOnCurrentIndex(keyword, genre, i, books);
//		}
//		return count;
//	}
	
//	private static int countGenre(String genre,List<Book> books){
//		int count = 0;
//		for (Book book : books) {
//			if (genre.equalsIgnoreCase(book.getGenre())) {
//				count++;
//			}
//		}
//		
//		return count;
//	}
	
//	private static double getProbabilityOfCurrentKeyword(Keyword keyword, String genre,
//			List<Book> books){
//		double probability = (double)countKeyword(keyword,genre,books)/countGenre(genre, books);
//		
//		return probability;
//	}
	
//	private static double getProbabilityCurrentGenre(List<Keyword> keywords, String genre,
//			List<Book> books){
//		double result = 1;
//		for (Keyword keyword : keywords) {
//			result*=getProbabilityOfCurrentKeyword(keyword, genre, books);
//		}
//		
//		return result;
//	}
	
//	private static double getGenreProbability(String genre,List<Book> books){
//		double result = (double)countGenre(genre, books)/books.size();
//		
//		return result;
//	}
//	
//	private static double getProbability(List<Keyword> keywords, String genre, List<Book> books){
//		return getProbabilityCurrentGenre(keywords, genre, books)*getGenreProbability(genre, books);
//	}
	
//	private static String getGenre(List<Keyword> keywords,List<Book> books, Collection<String> genres){
//		double maxProbability = 0;
//		String result = "";
//		
//		for (String genre : genres) {
//			double probability = getProbability(keywords,genre,books);
//			if (probability>maxProbability) {
//				maxProbability = probability;
//				result = genre;
//			}
//		}
//		
//		return result;
//	}
	
	private static String getGenre(Book book,List<Book> books, Collection<String> genres){
		String result = "";
		int maxCount = 0;
		for (String genre : genres) {
			//double probability = getProbability(keywords,genre,books);
			int count = containsKeywords(book, genre, books);
			if (count>maxCount) {
				maxCount = count;
				result = genre;
			}
		}
		
		return result;
	}
	
//	public static String getGenre(List<Keyword> keywords,List<Book> books){
//		Set<String> genres = new HashSet<String>();
//		
//		for (Book book : books) {
//			genres.add(book.getGenre());
//		}
//		String result = getGenre(keywords,books,genres);
//		
//		return result;
//	}
	
	public static String getGenre(Book book,List<Book> books){
		Set<String> genres = new HashSet<String>();
		
		for (Book currentBook : books) {
			genres.add(currentBook.getGenre());
		}
		String result = getGenre(book,books,genres);
		
		return result;
	}
	
	public static String[] testBayesClassifier(List<Book> testData, List<Book> trainData){
		String[] genres = new String[testData.size()];
		for (int i = 0; i < genres.length; i++) {
			genres[i] = getGenre(testData.get(i),trainData);
		}
		
		return genres;
	}
	
//	public static String[] testBayesClassifier(List<Book> testData, List<Book> trainData){
//		String[] genres = new String[testData.size()];
//		for (int i = 0; i < genres.length; i++) {
//			genres[i] = getGenre((List<Keyword>)testData.get(i).getKeywords(),trainData);
//		}
//		
//		return genres;
//	}
	
	private static int countCorrectClassifiedBooks(List<Book> testData, List<Book> trainData){
		int count = 0;
		String[] genres = testBayesClassifier(testData, trainData);
		
		for (int i = 0; i < genres.length; i++) {
			if (genres[i].equalsIgnoreCase(testData.get(i).getGenre())) {
				count++;
			}
		}
		
		return count;
	}
	
	public static double getAccuracy(List<Book> testData, List<Book> trainData){
		int correctClassifiedBooks = countCorrectClassifiedBooks(testData, trainData);
		double accuracy = ((double)correctClassifiedBooks/testData.size())*100;
		
		return accuracy;
	}
	
}
