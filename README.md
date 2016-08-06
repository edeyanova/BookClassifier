# BookClassifier
Classification of books by genre.

Курсова работа
по
Извличане  на знания от данни


Софийски университет
„Св. Климент Охридски“
2015/2016



 




Тема
Класификация на книги по жанр


Съдържание
	Мотивация
	Кратък обзор
	Предишна работа по темата
	Решение на задачата
	Програмна реализация
	Резултати от експерименти
	Литература

Мотивация - Да се улесни дейността по класификация на всяка нова книга преди да се качи за свободно ползване. Нашата курсова работа определеля жанра на книгите според съдържанието им. 
Кой ще има полза? – Сайтове, където се качват книги. За да се определи жанрът, трябва да се прочете книгата или по-голямата част от нея и да се следи за ключови думи, които ще ни подскажат какъв е жанрът. Това е времеотнемащо и трудно. 

Кратък обзор – Задачата е решена с използване на алгоритми за изкуствен интелект с учител. Избират се данни за тренировка и с тяхна помощ се тренира модел на задачата. Този модел се тества с тестово множество данни. Използвани са текстове със свободен достъп от интернет, разделени в отделни жанрове. 

Предишна работа по темата – Темата за класифициране на текст е много популярна и има доста публикувани трудове върху нея, предимно на английски език. Системата за класификация на жанрове на Amazon.com оказва влияние върху продажбите и откриваемостта на книгите онлайн. 

Решение на задачата – Решението на задачата се състои от две фази: 
	Извличане на ключови думи
	Алгоритми за класифициране 



Извличане на ключови думи

Добавяме два jar файла - /lucene-core-3.6.2.jar и  /contrib/analyzers/common/lucene-analyzers-3.6.2.jar, който съдържа специфичните анализатори на езика. Имаме модел на данните: 
	public class Keyword implements Comparable<Keyword> {

	private String stem;
	private Set<String> terms;
	private int frequency;
	...
	}
Една ключова дума е за един корен. Различни думи могат да имат еднакъв корен. 
От текста, от който ще извличаме ключовите думи, махаме пунктуационните знаци. Всички букви ги правим малки чрез класа LowerClassFilter. Чрез класа ASCIIFoldingFilter преобразуваме всеки символ до ASCII. Чрез класа StopFilter се отделят стоп-думи в английския език. За всяка дума извличаме корена ѝ с помощта на метода stem(String term). За всеки корен се създава нов обект от тип Keyword.  Думата, от която е извлечен корена я прибавяме към множеството terms на обекта от тип Keyword, в което се съдържат всички думи, които имат един и същи корен.
Използваме Java String Tokenizer, за да разделим низ от символи на отделни думи. Методите на String Tokenizer не правят разлика между идентификатори, цифри и цитирани низове, нито разпознават и пропускат коментари. След като получим списък с максимално изчистени отделни думи, от тях се отделят известните стоп думи в английския език. 



Алгоритми за класифициране

Първият алгоритъм прави следното:
	Имаме данни за обучение, които използваме да класифицираме книгите
	Брои колко ключови думи на книгата, която ще класифицираме, се срещат в някоя от книгите за обучение от един и същи жанр.
	Това се прави за всички жанрове
	Взема този жанр, за когото книгата има максимален брой срещания на ключови думи

Ако текущата книга съдържа поне една ключова дума се връща резултат true.
private static boolean containsKeyword(Keyword keyword, String genre, int index, List<Book> books){
	Book book = books.get(index);
	Collection<Keyword> keywords = book.getKeywords();
	if(genre.equalsIgnoreCase(book.getGenre())){
	 for (Keyword currentKeyword : keywords) {
			    if(keyword.getStem().equalsIgnoreCase(currentKeyword.getStem())) {
					return true;
				}
			}
		}
	
		return false;
	}
	Ако една книга съдържа ключова дума, връща резултат true.

	private static boolean containsKeyword(Keyword keyword, String genre, List<Book> books){
		for (int i = 0; i < books.size(); i++) {
			if (containsKeyword(keyword,genre,i,books)) {
				return true;
			}
		}
		return false;
	}
	
Брои колко ключови думи на книгата се срещат в книгите за обучение.


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

За всеки жанр взема колко ключови думи на книгата се съдържат в книгите за обучение. Жанрът е този с най-много ключови думи на книгата, която се класифицира.



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

Извлича всички жанрове от книгите и взема уникалните от тях.

public static String getGenre(Book book,List<Book> books){
		Set<String> genres = new HashSet<String>();
		
		for (Book currentBook : books) {
			genres.add(currentBook.getGenre());
		}
		String result = getGenre(book,books,genres);
		
		return result;
	}
	

Тества класификатора
	public static String[] testBayesClassifier(List<Book> testData, List<Book> trainData){
		String[] genres = new String[testData.size()];
		for (int i = 0; i < genres.length; i++) {
			genres[i] = getGenre(testData.get(i),trainData);
		}
		
		return genres;
	}

Брои вярно класифицираните книги
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

Пресмята точността на класификатора
	
	public static double getAccuracy(List<Book> testData, List<Book> trainData){
		int correctClassifiedBooks = countCorrectClassifiedBooks(testData, trainData);
		double accuracy = ((double)correctClassifiedBooks/testData.size())*100;
		
		return accuracy;
	}


Вторият алгоритъм е Multinomial Naive Bayes 
Multinomial Naive Bayes е версия на Naive Bayes, която е предназначена за предимно за класификация на текстови документи. 
	supervised learning method
	probabilistic learning method
Вероятността документ d да бъде в клас c се изчислява по формулата:  , 
където P(tk | c) е условната вероятност tk да се срещне в класа c. P(c) е вероятността на класа c. Ще обясним работата на алгоритъма чрез конкретен пример:
				Doc			Words			Class
Training	1	Mountain Rock Mountain	Adventure
	2	Mountain Mountain Climbing	Adventure
	3	Mountain Power	Adventure
	4	Hat Cat Mountain	Children
Test	5	Mountain Mountain Mountain Hat Cat	?

•	P(Mountain | Adventure) = (5 + 1) / (8 + 6) = 6/14 = 3/7
Вероятността ключовата дума Mountain да бъде в класа Adventure се изчислява по следния начин: 
броят на срещанията на ключовата дума в класа + 1 , за да не получим резултат 0, разделени на общият брой на думите в класа + уникалните думи във всички класове. 
По аналогичен начин се пресмятат: 
•	P(Hat | Adventure) = 1/14
•	P(Cat | Adventure) = 1/14
•	P(Mountain | Children) = 2/9
•	P(Hat | Children) = 2/9
•	P(Cat | Children) = 2/9

•	P ( Adventure ) = 3/4

•	P (Cat ) = 1/4
Вероятността Doc 5 да бъде в класа Adventure се изчислява по следната формула: 
	P ( Adventure | d5) = 3/4* (3/7)3 * 1/14 * 1/14 ≈ 0.003 , където 
3/4 е вероятността на класа Adventure (броят на благоприятните случаи – 3 върху броя на всички възможни – 4),
 3/7 е P(Mountain | Adventure), което се умножава по себе си три пъти, тъй като толкова често се среща думата Mountain в тестовия пример, 
1/14 е P(Hat | Adventure) = 1/14,
P(Cat | Adventure) = 1/14. 
По аналогочен начин се изчислява: 
	P ( Children | d5 ) = 1/4 * (2/9)3 * 2/9 *2/9 ≈ 0.0001
Тестовият пример взема класа, чиято вероятност е по-голяма, т.е. – Adventure.
	
Програмна реализация – Курсовата работа е написана на Java. 
Използваме библиотеката Apache Lucene 3.6.2.
Apache Lucene е високо производителна библиотека за търсене в текст, написана изцяло на Java. Това е технология подходяща за почти всяко приложение, което изисква пълно текстово търсене. Apache Lucene  е проект с отворен код, който е на разположение за свободно изтегляне. 

Резултати от експерименти – Извличането на ключовите думи и алгоритмите за класификация работят сравнително точно. 

Литература 
	Библиотека, написана на Java – Apache Lucene
http://lucene.apache.org/core/5_4_1/core/index.html

Multinomial Naïve Bayes: A worked example
https://class.coursera.org/nlp/lecture/28


Naive Bayes text classification
http://nlp.stanford.edu/IR-book/html/htmledition/naive-bayes-text-classification-1.html


Read Free Books Online and Download eBooks for Free
http://www.bookrix.com/books.html




