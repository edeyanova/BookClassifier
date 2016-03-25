package keywordsComparatorByFrequency;

import java.util.Comparator;

import keywords.Keyword;

public class KeywordsComparatorByFrequency implements Comparator<Keyword> {

	@Override
	public int compare(Keyword firstKeyword, Keyword secondKeyword) {
		
		return Integer.valueOf(secondKeyword.getFrequency()).
				compareTo(firstKeyword.getFrequency());
	}

}
