package com.nicolas_bettenburg.cirrostratus;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.nicolas_bettenburg.cirrostratus.TagClouds.BlueBasicCloud;
import com.nicolas_bettenburg.cirrostratus.data.HTMLDocumentReader;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Document> corpus = new ArrayList<Document>();
		Document myDocument = HTMLDocumentReader.createDocumentFromURL(args[0]);
		corpus.add(myDocument);
		
		BlueBasicCloud testCloud = new BlueBasicCloud();
		testCloud.setMIN_TEXT_HEIGHT(10);
		testCloud.setMAX_TEXT_HEIGHT(50);
		testCloud.setNUM_TERMS(100);
		testCloud.setORDER(BlueBasicCloud.ORD_SHUFFLED);
		
		try {
			FileWriter fw = new FileWriter(new File("output.html"));
			fw.write(testCloud.createCloud(corpus));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
