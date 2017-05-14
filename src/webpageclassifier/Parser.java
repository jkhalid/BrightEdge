//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  Khalid Jahangeer
 *  @version 1.0
 *  @date    Sun May 14 14:16:20 EDT 2017
 *  @see     LICENSE (MIT style license file).
 *
 *  The code has been written as Assignment for BrightEdge Interview process
 */
package webpageclassifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.io.BufferedReader;

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/**
* The Parser class is the third executable of in the project hierarchy 
* The parser class is used to
* 1. retrieve the essential content from the url
* 2. read and process the stopwords file
*/

public class Parser {
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** parser to class that reads the url, connects to it and retrieves content
     *  @param  url input String
     *  @return Document : variable type of the java Jsoup library  
     */  
	public Document parser(String url)
	{
		Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Unable to retrieve content from the url: "+url.toString());
            e.printStackTrace();
        }
        return document;
	}
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** method that accesses the stopwords.txt
     *  @return ArrayList<String> : collection that has all the stopwords
     */
	public ArrayList<String> getStopWords()
	{
	    ArrayList<String> stopwords = new ArrayList<String>();
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("stopwords.txt");
	    try{
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br= new BufferedReader(isr);
			String stopword;
			while((stopword = br.readLine()) != null)
				stopwords.add(stopword);
			br.close();
		}catch (IOException e) {
			System.out.println("Unable to read StopWords file");
		}
		return stopwords;
	}

}
