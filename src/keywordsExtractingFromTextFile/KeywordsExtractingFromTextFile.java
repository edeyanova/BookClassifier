package keywordsExtractingFromTextFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import keywords.Keyword;
import keywordsExtracting.KeywordsExtracting;

public class KeywordsExtractingFromTextFile {
	
	public static List<Keyword> getKeywordsFromFile(String path) throws IOException{
		List<Keyword> keywords = new ArrayList<Keyword>();
		try(Scanner reader = new Scanner(new File(path))){
			while (reader.hasNext()) {
				String line = reader.nextLine();
				List<Keyword> keywordsInCurrentLine = KeywordsExtracting.guessFromString(line);
				
				for (Keyword keyword : keywordsInCurrentLine) {
					if (keywords.contains(keyword)) {
						int index = keywords.indexOf(keyword);
						int newFrequency = keywords.get(index).getFrequency()+
								keyword.getFrequency();
						keywords.get(index).setFrequency(newFrequency);
					}
					else{
						keywords.add(keyword);
					}
				}
			}
		}
		return keywords;
	}
}
