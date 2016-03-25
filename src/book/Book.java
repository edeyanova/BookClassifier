package book;

import java.util.ArrayList;
import java.util.Collection;

import keywords.Keyword;

public class Book {
	private String title;
	private Collection<Keyword> keywords;
	private String genre;
	
	public Book(String title, Collection<Keyword> keywords, String genre) {
		super();
		this.title = title;
		this.keywords = keywords;
		this.genre = genre;
	}
	public Book(String title, String genre){
		this(title, new ArrayList<Keyword>(), genre);
	}
	public Book(Collection<Keyword> keywords, String genre){
		this.keywords = keywords;
		this.genre = genre;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Collection<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(Collection<Keyword> keywords) {
		this.keywords = keywords;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
