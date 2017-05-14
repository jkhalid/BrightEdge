//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  Khalid Jahangeer
 *  @version 1.0
 *  @date    Sun May 14 12:16:20 EDT 2017
 *  @see     LICENSE (MIT style license file).
 *
 *  The code has been written as Assignment for BrightEdge Interview process
 */

package webpageclassifier;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/**
* The Classify class is the main executable of the the project 
* The program has been tested on the following urls
* https://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster
* http://blog.rei.com/camp/how-to-introduce-your-indoorsy-friend-to-the-outdoors/
* http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/
* 
* For implementation purpose the Project currently returns Top- k Words
* @param k = 10
* 
* Input: valid webpage url
* 
* Output: Top K words
*/

public class Classify {
	
	public static int k = 10;                                        // k can be modified to return more words accordingly
	public static String url ="";

	public static void main(String[] args) throws IOException{

		url = args[0];
		if(url.length() == 0)
			throw new IOException("URL missing");
		else
		{
			Validator object = new Validator(url);                   // check url validity
			if(object.validate())
			{
				Parser parse = new Parser();
				Document doc = parse.parser(url);                    // retrieve details from WebPage USING JSOUP
				ArrayList<String> stopwords = parse.getStopWords();  // retrieve unnecessary stop words
				Analytics analytics = new Analytics(doc,stopwords);  
				ArrayList<String> bestWords = analytics.getTopics(); // perform data analytics
				for(String word : bestWords)
				System.out.print(word + " ");                        // print best describing words
			}
		}
	}
}
