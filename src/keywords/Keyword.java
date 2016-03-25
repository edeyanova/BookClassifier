package keywords;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Keyword implements Comparable<Keyword> {

	private String stem;
	private Set<String> terms;
	private int frequency;
	
	public Keyword(String stem){
		this(stem, new HashSet<String>(), 0);
	}
	
	public Keyword(String stem, Set<String> terms, int frequency) {
		super();
		this.stem = stem;
		this.terms = terms;
		this.frequency = frequency;
	}
	
	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public Set<String> getTerms() {
		return terms;
	}

	public void setTerms(Set<String> terms) {
		this.terms = terms;
	}
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}
	public int getFrequency(){
		return frequency;
	}

	public void add(String term) {
	    terms.add(term);
	    frequency++;
	  }

//	@Override
//	public int compareTo(Keyword other) {
//		
//		return Integer.valueOf(getFrequency()).compareTo(other.getFrequency());
//	}
	@Override
	public int compareTo(Keyword other){
		return stem.compareToIgnoreCase(other.stem);
	}
	
	@Override
	  public boolean equals(Object obj) {
	    if (this == obj) {
	      return true;
	    } else if (!(obj instanceof Keyword)) {
	      return false;
	    } else {
	      return compareTo((Keyword)obj) == 0;
	    }
	  }

	  @Override
	  public int hashCode() {
	    return Arrays.hashCode(new Object[] { stem });
	  }

}
