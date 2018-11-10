package com.nicolas_bettenburg.cirrostratus.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.nicolas_bettenburg.cirrostratus.Document;
import com.nicolas_bettenburg.cirrostratus.Stemmer;
import com.nicolas_bettenburg.cirrostratus.Stopwords;

/**
 * Read in a text file
 * @author Nicolas Bettenburg
 *
 */
public class TextDocumentReader {
	private static int MINTOKLEN = 2;
	public static Document createDocumentFromFile(String filename) {
		try {
			// Create a String Buffer
			StringWriter buffer = new StringWriter();
			PrintWriter stringPrinter = new PrintWriter(buffer);
			
			// Open the input file
			BufferedReader in = new BufferedReader(new FileReader(filename));
	        String inputLine;

	        // Read the file
	        while ((inputLine = in.readLine()) != null) { 
	            stringPrinter.println(inputLine);
	        }
	        
	        // Close the File Stream
	        in.close();
	        
	       // Construct Stopwords
	        Stopwords stopwords = new Stopwords();
	       
	       // Process Tokens
	       String[] tokens = buffer.toString().split("[ .,!?(){}\\[\\];:!\\t\\n\\r]");
	       Stemmer porterStemmer = new Stemmer();
	       
	       Document aDocument = new Document(filename);
	       for (String token : tokens) {
	    	   if (token.length() >= MINTOKLEN && !stopwords.isStopword(token.toLowerCase())) {
	    		   String stemmedtoken = porterStemmer.process(token).toLowerCase().trim();
	    		   aDocument.addTerm(stemmedtoken, token);
	    	   }
	       }
	       
	       return aDocument;
		        
		} catch (Exception e) {
			e.printStackTrace();
			return new Document(filename);
		} 
	}
}
