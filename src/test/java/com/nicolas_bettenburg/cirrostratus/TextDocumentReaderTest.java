package com.nicolas_bettenburg.cirrostratus;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.nicolas_bettenburg.cirrostratus.TagClouds.BlueBasicCloud;
import com.nicolas_bettenburg.cirrostratus.data.TextDocumentReader;

public class TextDocumentReaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Document> corpus = new ArrayList<Document>();
		
		for (String filename : args) {
			Document myDocument = TextDocumentReader.createDocumentFromFile(filename);
			corpus.add(myDocument);
		}
		
		BlueBasicCloud testCloud = new BlueBasicCloud();
		testCloud.setMIN_TEXT_HEIGHT(10);
		testCloud.setMAX_TEXT_HEIGHT(50);
		testCloud.setORDER(BlueBasicCloud.ORD_WEIGHTED);
		
		try {
			FileWriter fw = new FileWriter(new File("output.html"));
			fw.write(testCloud.createCloud(corpus));
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
