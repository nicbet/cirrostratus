package com.nicolas_bettenburg.cirrostratus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represent a Document in a text corpus.
 * @author Nicolas Bettenburg
 */
public class Document {
	/**
	 * terms is a list of tags that occurr in the document
	 */
	private List<String> terms = null;
	/**
	 * documentname is the unique identifier of the document
	 */
	private String documentname;
	
	/**
	 * frequencyMap keeps an updated Map of terms
	 */
	private Map<String, Integer> frequencyMap;
	
	/**
	 * stemmedMap keeps track of the stemmed tokens and their lonform
	 */
	private Map<String, String> stemmedMap;
	
	/**
	 * Constructor
	 * @param documentname A unique identifier for this document
	 */
	public Document(String documentname) {
		super();
		this.documentname = documentname;
		this.terms = new ArrayList<String>();
		this.frequencyMap = new HashMap<String, Integer>();
		this.stemmedMap = new HashMap<String, String>();
	}

	/**
	 * Adds a single Tag to the Document
	 * @param t the Tag to add.
	 */
	public void addTerm(String t, String lt) {
		// Add the tag to the term vector
		this.terms.add(t);
		
		// Update the frequency map
		if (this.frequencyMap.containsKey(t)) {
			Integer tf = this.frequencyMap.get(t);
			tf += 1;
			this.frequencyMap.put(t, tf);
		} else {
			// Add the term to the frequency map
			this.frequencyMap.put(t, 1);
			// And remember the long and short forms of the term
			this.stemmedMap.put(t, lt);
		}
	}
	
	/**
	 * Retrieves the list of all Tags in the Document
	 * @return A list of Tags
	 */
	public Set<String> getTermSet() {
		return this.frequencyMap.keySet();
	}
	
	public List<String> getTerms() {
		return this.terms;
	}
	
	/**
	 * Calculate the tf-idf value for a Tag in the Document.
	 * @param t A Tag t for which the tf-idf is to be calculated.
	 * @return the tf-idf for Tag t.
	 */
	public float getIDF(String t) {
		return 0;
	}
	
	/**
	 * Calculate the term frequency for a Tag in the Document.
	 * @param t A Tag t for which the term frequency is to be calculated.
	 * @return the term frequency for Tag t.
	 */
	public float getTF(String t) {
		return this.frequencyMap.get(t).floatValue();
	}
	
	/**
	 * Get the Document unique name.
	 * @return the unique identifier of the document.
	 */
	public String getDocumentName() {
		return this.documentname;
	}
	
	/**
	 * Retrieve an unique term list sorted by frequency descending
	 * @return An array of an unique term list sorted by frequency descending
	 */
	public TermFrequency[] getSorted() {
		
		int numTerms = this.getTermSet().size();
		Object[] termSet = this.getTermSet().toArray();
		
		TermFrequency[] mostFrequent = new TermFrequency[numTerms];
		for (int i=0; i < numTerms; i++)
			mostFrequent[i] = new TermFrequency((String) termSet[i], this.frequencyMap.get(termSet[i]));
		
		java.util.Arrays.sort(mostFrequent);
		
		return mostFrequent;
	}
	
	/**
	 * Retrieve the long form of the corresponding stemmed term
	 * @param stemmedTerm the stemmed Term
	 * @return the long form of the corresponding stemmed term
	 */
	public String getLongForm(String stemmedTerm) {
		return stemmedMap.get(stemmedTerm);
	}
	
	
	/**
	 * Override the toString method for formatted output of the Document
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String term : terms) {
			sb.append(term + " ");
		}
		sb.append(System.getProperty("line.separator"));
		
		return sb.toString();
	}
	
}
