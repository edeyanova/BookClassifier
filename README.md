# BookClassifier
Classification of books by genre.

Курсова работа по Извличане  на знания от данни

Софийски университет „Св. Климент Охридски“ 2015/2016

Решение на задачата – Решението на задачата се състои от две фази: 

	Извличане на ключови думи

	Алгоритми за класифициране 


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




