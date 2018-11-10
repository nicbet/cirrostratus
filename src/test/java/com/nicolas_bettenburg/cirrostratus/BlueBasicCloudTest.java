package com.nicolas_bettenburg.cirrostratus;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.nicolas_bettenburg.cirrostratus.TagClouds.BlueBasicCloud;
import com.nicolas_bettenburg.cirrostratus.data.TextDocumentReader;

public class BlueBasicCloudTest {
	
	public static void main(String[] args) {
		List<Document> corpus = new ArrayList<Document>();
		Document myDocument = TextDocumentReader.createDocumentFromFile(args[0]);
		corpus.add(myDocument);
		
		BlueBasicCloud testCloud = new BlueBasicCloud();
		testCloud.setTEXT_COLOR("#10609B");
		testCloud.setMIN_TEXT_HEIGHT(10);
		testCloud.setMAX_TEXT_HEIGHT(30);
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
