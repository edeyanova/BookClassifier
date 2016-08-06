# BookClassifier
Classification of books by genre.

Курсова работа по Извличане  на знания от данни


Софийски университет „Св. Климент Охридски“ 2015/2016



 




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

Добавени са два jar файла - /lucene-core-3.6.2.jar и  /contrib/analyzers/common/lucene-analyzers-3.6.2.jar, който съдържа специфичните анализатори на езика. 
	
Една ключова дума е за един корен. Различни думи могат да имат еднакъв корен. 
От текста, от който ще се извличат ключовите думи, се махат пунктуационните знаци. Всички букви се правят малки чрез класа LowerClassFilter. Чрез класа ASCIIFoldingFilter всеки символ се преобразува до ASCII. Чрез класа StopFilter се отделят стоп-думи в английския език. За всяка дума се извлича коренът ѝ с помощта на метода stem(String term). За всеки корен се създава нов обект от тип Keyword.  Думата, от която е извлечен корена се прибавя към множеството terms на обекта от тип Keyword, в което се съдържат всички думи, които имат един и същи корен.
Използван е Java String Tokenizer, за да се раздели низ от символи на отделни думи. Методите на String Tokenizer не правят разлика между идентификатори, цифри и цитирани низове, нито разпознават и пропускат коментари. След като се получи списък с максимално изчистени отделни думи, от тях се отделят известните стоп думи в английския език. 



Алгоритми за класифициране

Първият алгоритъм прави следното:

	Имаме данни за обучение, които използваме да класифицираме книгите

	Брои колко ключови думи на книгата, която ще класифицираме, се срещат в някоя от книгите за обучение от един и същи жанр.

	Това се прави за всички жанрове

	Взема този жанр, за когото книгата има максимален брой срещания на ключови думи

Вторият алгоритъм е Multinomial Naive Bayes 

Multinomial Naive Bayes е версия на Naive Bayes, която е предназначена за предимно за класификация на текстови документи. 

	supervised learning method

	probabilistic learning method


Обяснение на работата на алгоритъма чрез конкретен пример:

		Doc			Words			    Class
		
Training	 1	 Mountain Rock Mountain	                 Adventure

		 2 	 Mountain Mountain Climbing	Adventure
		
		 3	 Mountain Power	           Adventure
		
	 	 4	 Hat Cat Mountain	   Children
		 
Test		 5 	 Mountain Mountain Mountain Hat Cat ?

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
Използвана е библиотеката Apache Lucene 3.6.2.
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




