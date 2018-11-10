package com.nicolas_bettenburg.cirrostratus;
/**
 * The Tag class represent one single tag in the tag cloud.
 * @author Nicolas Bettenburg
 */

public class TermFrequency implements Comparable<TermFrequency> {
	private String term;
	private int frequency;

	public TermFrequency(String term, int frequency) {
		this.term = term;
		this.frequency = frequency;
	}
	
	public String getTerm() {
		return this.term;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
	
	public void setNewFrequency(int f) {
		this.frequency = f;
	}

	public int compareTo(TermFrequency that) {
		if (this.frequency < that.frequency)
			return 1;
		else if (this.frequency > that.frequency)
			return -1;
		else
			return 0;
	}
	
	public String toString() {
		return (Integer.toString(this.frequency) + " " + this.term);
	}
	
}
