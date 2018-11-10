package com.nicolas_bettenburg.cirrostratus.TagClouds;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.nicolas_bettenburg.cirrostratus.Document;
import com.nicolas_bettenburg.cirrostratus.TermFrequency;

/**
 * BlueBasicCloud is a simple TagCloud
 * as presented by tagcrowd.org
 * @author Nicolas Bettenburg
 *
 */
public class BlueBasicCloud implements com.nicolas_bettenburg.cirrostratus.Cloud {
	public static int ORD_SHUFFLED = 1;
	public static int ORD_ALPHABETIC =2;
	public static int ORD_WEIGHTED = 3;
	
	/**
	 * Some Settings
	 * NUM_TERMS is the default maximum amount of terms to keep
	 * MIN_FREQ is the default minimum term frequency
	 * TEXT_COLOR is the default output text color (blue)
	 */
	private int NUM_TERMS = 150;
	private int MIN_FREQ = 1;
	private String TEXT_COLOR = "#3300FF";
	private int MIN_TEXT_HEIGHT = 14;
	private int MAX_TEXT_HEIGHT = 50;
	private int ORDER = BlueBasicCloud.ORD_SHUFFLED;

	/**
	 * Method inherited from interface Cloud.
	 */
	public String createCloud(List<Document> documents) {
		
		// Create an output String Builder
		StringBuilder output = new StringBuilder();
		
		// Get the newline character(s) for the system
		String newline = System.getProperty("line.separator");
		
		// Create a Document for the complete corpus
		Document corpus = new Document("TextCorpus");
		for (Document doc :  documents) {
			for (String term : doc.getTerms()) {
				corpus.addTerm(term, doc.getLongForm(term));
			}
		}
		
		// Get all the set of unique terms, sorted descending by frequency
		TermFrequency[] topTerms = corpus.getSorted();
		
		// Determine minimum and maximum values
		double max = topTerms[0].getFrequency();
		double min = 0.0;
		
		// Also, if we have more than NUM_TERMS terms, truncate the term set
		TermFrequency[] topN;
		if (topTerms.length > NUM_TERMS) {
			topN = new TermFrequency[NUM_TERMS];
			for (int i=0; i < NUM_TERMS; i++)
				topN[i] = topTerms[i];
			min = topTerms[NUM_TERMS-1].getFrequency();
		} else {
			topN = topTerms;
			min = topTerms[topTerms.length].getFrequency();
		}
		
		// Scale the minimum and maximum term frequency values logarithmic.
		min = Math.log(min);
		max = Math.log(max);
		
		// Determine a normalization factor. We want to transform frequencies into
		// the range of 0-100 later on.
		double normFactor = 100 / (max - min);
		
		// Write the Basic HTML Output header
		output.append("<html><head>" + newline);
		output.append("<title>Blue Basic Cloud created by cirrustratus</title></head>");
		output.append("<body>" + newline);
		
		List<TermFrequency> outputList = java.util.Arrays.asList(topN);;
		if (ORDER == BlueBasicCloud.ORD_SHUFFLED) {
			// We want a random ordering of the terms - so we shuffle the term set a bit.
			Collections.shuffle(outputList);
		} else if (ORDER == BlueBasicCloud.ORD_ALPHABETIC) {
			// We want to sort the terms alphabetically
			Collections.sort(outputList, new TFAplhaComparator());
		} else if (ORDER == BlueBasicCloud.ORD_WEIGHTED) {
			// Do nothing because we are done already!
		}
		
		/*
		 * It is a total waste of information using ALL 3 dimensions (color, size, opacity)
		 * for the crappy frequency attribute!!!!
		 * CHANGE THIS IN FUTURE VERSIONS!
		 */
		for (TermFrequency tf : outputList) {
			// Calculate a text opacity value ... range 0.0 - 1.0
			double opacity = 0.1 + (normFactor * ((double) Math.log(tf.getFrequency()) - min) / 100.0);
			
			// Calculate a fontsize ... range 14 - 64 (= 100/2 + 14)
			int fontSize = MIN_TEXT_HEIGHT + (int) (normFactor * ((double) Math.log(tf.getFrequency()) - min) /100 * MAX_TEXT_HEIGHT) ;
			
			// Calculate a font width ... range 100 - 600 (= 100*5 + 100)
			//int weight = 100 + (int) (normFactor * ((double) Math.log(tf.getFrequency()) - min) * 5 ) ;
			int weight = 400;
			
			// Pretty print the term
			output.append("<span style=\"" 
					+ "font-family: arial;"
					+ "color: " + TEXT_COLOR + "; " 
					+ "font-size: " + Integer.toString(fontSize)  + "px;"
					+ "font-weight: " + weight + ";"
					+ "opacity: " + Double.toString(opacity) 
					+"\">" + corpus.getLongForm(tf.getTerm()) + "</span> &#160;&#160;" + newline);
		}
		
		// Write the HTML Closing information
		output.append("</body>" + newline);
		output.append("</html>");
		
		// Return the HTML Document as String
		return output.toString();
	}
	
	/**
	 * An alphabetic comparator for the TermFrequency Objects
	 * @author Nicolas Bettenburg
	 */
	public class TFAplhaComparator implements Comparator<TermFrequency> {
		  public int compare(TermFrequency t1, TermFrequency t2) {
		    int termComp = t1.getTerm().compareTo(t2.getTerm());
		    return termComp;
		  }
	}
	
	/*
	 * -----------------------------------------------------------------------
	 *  Getters and Setter
	 *  ----------------------------------------------------------------------
	 */
	
	/**
	 * @return the oRDER
	 */
	public int getORDER() {
		return ORDER;
	}

	/**
	 * @param order the oRDER to set
	 */
	public void setORDER(int order) {
		ORDER = order;
	}

	/**
	 * @return the nUM_TERMS
	 */
	public int getNUM_TERMS() {
		return NUM_TERMS;
	}

	/**
	 * @param num_terms the nUM_TERMS to set
	 */
	public void setNUM_TERMS(int num_terms) {
		NUM_TERMS = num_terms;
	}

	/**
	 * @return the mIN_FREQ
	 */
	public int getMIN_FREQ() {
		return MIN_FREQ;
	}

	/**
	 * @param min_freq the mIN_FREQ to set
	 */
	public void setMIN_FREQ(int min_freq) {
		MIN_FREQ = min_freq;
	}

	/**
	 * @return the tEXT_COLOR
	 */
	public String getTEXT_COLOR() {
		return TEXT_COLOR;
	}

	/**
	 * @param text_color the tEXT_COLOR to set
	 */
	public void setTEXT_COLOR(String text_color) {
		TEXT_COLOR = text_color;
	}

	/**
	 * @return the mIN_TEXT_HEIGHT
	 */
	public int getMIN_TEXT_HEIGHT() {
		return MIN_TEXT_HEIGHT;
	}

	/**
	 * @param min_text_height the mIN_TEXT_HEIGHT to set
	 */
	public void setMIN_TEXT_HEIGHT(int min_text_height) {
		MIN_TEXT_HEIGHT = min_text_height;
	}

	/**
	 * @return the mAX_TEXT_HEIGHT
	 */
	public int getMAX_TEXT_HEIGHT() {
		return MAX_TEXT_HEIGHT;
	}

	/**
	 * @param max_text_height the mAX_TEXT_HEIGHT to set
	 */
	public void setMAX_TEXT_HEIGHT(int max_text_height) {
		MAX_TEXT_HEIGHT = max_text_height;
	}
}
