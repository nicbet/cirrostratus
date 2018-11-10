package com.nicolas_bettenburg.cirrostratus.data;


public class HTMLTagStripper {

	public static String getStripped(String htmlText) {
		String strippedText = "";
		int bodyStart = htmlText.indexOf("<body>");
		int bodyEnd = htmlText.indexOf("</body>");
		
		strippedText = htmlText.substring(bodyStart + 6, bodyEnd).replaceAll("<.*?>", "");
		
		return strippedText;
	}
}
