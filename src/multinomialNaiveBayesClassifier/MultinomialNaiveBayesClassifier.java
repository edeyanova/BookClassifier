package multinomialNaiveBayesClassifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import book.Book;
import keywords.Keyword;

public class MultinomialNaiveBayesClassifier {
	public static int getKeywordsNumberInGenre(String genre, List<Book> data){
		int count = 0;
		for (Book book : data) {
			if(book.getGenre().equalsIgnoreCase(genre)){
				count+=book.getKeywords().size();
			}
		}
		
		return count;
	}
	
	public static int getUniqueKeywords(List<Book> data){
		Set<Keyword> uniqueKeywords = new HashSet<Keyword>();
		for (Book book : data) {
			for (Keyword keyword : book.getKeywords()) {
				uniqueKeywords.add(keyword);
			}
		}
		
		return uniqueKeywords.size();
	}
	
	public static int getKeywordNumberInClass(Keyword keyword,List<Book> data,
			String genre){
		int count = 0;
		for (Book book : data) {
			if (book.getGenre().equalsIgnoreCase(genre)) {
				for (Keyword currentKeyword : book.getKeywords()) {
					if (keyword.getStem().equalsIgnoreCase(currentKeyword.getStem())) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	public static double getGenreProbability(String genre,List<Book> data){
		int count = 0;
		for (Book book : data) {
			if (book.getGenre().equalsIgnoreCase(genre)) {
				count++;
			}
		}
		
		return ((double)count)/data.size();
	}
	
	public static double getKeywordProbabilityInGenre(Keyword keyword,
			String genre, List<Book> data){
		int keywordCount = getKeywordNumberInClass(keyword, data, genre);
		int keywordInGenreCount = getKeywordsNumberInGenre(genre, data);
		int uniquesCount = getUniqueKeywords(data);
		
		return ((double)(keywordCount+1))/(keywordInGenreCount+uniquesCount);
	}
	
	public static double getBookProbabilityInGenre(String genre, Book book,
			List<Book> data){
		double genreProbability = getGenreProbability(genre, data);
		for (Keyword keyword : book.getKeywords()) {
			double keywordProbability = getKeywordProbabilityInGenre(keyword, genre, data);
			genreProbability*=keywordProbability;
		}
		
		return genreProbability;
	}
	
	public static String[] getUniqueGenres(List<Book> data){
		HashSet<String> uniqueGenresSet = new HashSet<String>();
		for (Book book : data) {
			uniqueGenresSet.add(book.getGenre());
		}
		
		String[] uniqueGenres = uniqueGenresSet.toArray(new String[0]);
		
		return uniqueGenres;
	}
	
	public static String getGenre(Book book, List<Book> data){
		String[] uniqueGenres = getUniqueGenres(data);
		double maxProbability = 0;
		String maxGenre = "";
		for (String genre : uniqueGenres) {
			double currentProbability = getBookProbabilityInGenre(genre, book, data);
			if (currentProbability>maxProbability) {
				maxProbability = currentProbability;
				maxGenre = genre;
			}
		}
		
		return maxGenre;
	}
	
	public static List<String> test(List<Book> testData,List<Book> trainData){
		List<String> genres = new ArrayList<>();
		for (Book book : testData) {
			String genre = getGenre(book,trainData);
			genres.add(genre);
		}
		
		return genres;
	}
	
	public static int getNumberOfCorrectClassifiedBooks(List<Book> testData,List<Book> trainData){
		List<String> genres = test(testData,trainData);
		int count = 0;
		for (int i = 0; i < genres.size(); i++) {
			if(genres.get(i).equalsIgnoreCase(testData.get(i).getGenre())){
				count++;
			}
		}
		
		return count;
	}
	
	public static double getAccuracy(List<Book> testData,List<Book> trainData){
		int count = getNumberOfCorrectClassifiedBooks(testData, trainData);
		double accuracy = ((double)count/testData.size())*100;
		
		return accuracy;
	}
}
