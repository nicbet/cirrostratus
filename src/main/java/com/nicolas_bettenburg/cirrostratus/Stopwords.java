package com.nicolas_bettenburg.cirrostratus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Stopwords {
	private Set<String> stopwords;
	
	public Stopwords() {
		stopwords = new HashSet<String>();
		
		try {
			BufferedReader fileInput = new BufferedReader(new InputStreamReader(com.nicolas_bettenburg.cirrostratus.Ressources.RESSOURCE.class.getResourceAsStream("stopwords.txt")));
			String buffer = "";
			while ((buffer = fileInput.readLine()) != null) 
				stopwords.add(buffer);
			fileInput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isStopword(String word) {
		return stopwords.contains(word);
	}
}
