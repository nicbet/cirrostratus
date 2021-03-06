package com.nicolas_bettenburg.cirrostratus.data;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import com.nicolas_bettenburg.cirrostratus.Document;
import com.nicolas_bettenburg.cirrostratus.Stemmer;
import com.nicolas_bettenburg.cirrostratus.Stopwords;

/**
 * Create a Document from an HTML Document source.
 * @author Nicolas Bettenburg
 */
public class HTMLDocumentReader {
	
	private static final int MIN_TOKLENGTH = 2;
	
	public static Document createDocumentFromURL(String url) {
		try {
			StringWriter buffer = new StringWriter();
			PrintWriter stringPrinter = new PrintWriter(buffer);
			
		        URL theURL = new URL(url);
		        URLConnection uc = theURL.openConnection();
		        BufferedReader in = new BufferedReader(
		                                new InputStreamReader(
		                                uc.getInputStream()));
		        String inputLine;

		        while ((inputLine = in.readLine()) != null) 
		            stringPrinter.println(inputLine);
		        in.close();
		        
		       // System.out.println();
		        Stopwords stopwords = new Stopwords();
		        
		       String bodyText = HTMLTagStripper.getStripped(buffer.toString());
		       String[] tokens = bodyText.split("[^a-zA-Z0-9_\\-']");
		       Stemmer porterStemmer = new Stemmer();
		       
		       Document aDocument = new Document(url);
		       for (String token : tokens) {
		    	   if (token.length() > MIN_TOKLENGTH && !stopwords.isStopword(token.toLowerCase())) {
		    		   String stemmedtoken = porterStemmer.process(token).toLowerCase().trim();
		    		   aDocument.addTerm(stemmedtoken, token);
		    	   }
		       }
		       
		       return aDocument;
		        
		} catch (Exception e) {
			e.printStackTrace();
			return new Document(url);
		} 
		
	}
}
