package com.nicolas_bettenburg.cirrostratus;

import java.util.List;

/**
 * This Interface describes a TagCloud
 * @author Nicolas Bettenburg
 *
 */
public interface Cloud {
	/**
	 * Output the Tag Cloud as a valid HTML file
	 * @param documents a set of documents to create the cloud from
	 * @return a tag cloud as html file
	 */
	public String createCloud(List<Document> documents);
}
