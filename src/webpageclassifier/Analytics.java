//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  Khalid Jahangeer
*  @version 1.0
*  @date    Sun May 14 15:16:20 EDT 2017
*  @see     LICENSE (MIT style license file).
*
*  The code has been written as Assignment for BrightEdge Interview process
*/
package webpageclassifier;

import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/**
* The Analytics class is the fourth executable of in the project hierarchy 
* The Analytics class is used to
* 1. clean the content from the url
* 2. obtain the <word,frequency> association from the url content
* 3. manipulate the frequency giving weight to content present on the webpage title  
* 4. return the best words as ArrayList<String>
*/

public class Analytics {
	private Document document;
	private ArrayList<String> stopwords;
	private String data;
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** constructor to class
     *  @param  document : variable of type Jsoup
     *  @param  ArrayList<String> : stopwords 
     */  
	
	Analytics(Document document, ArrayList<String> stopwords)
	{
		this.document = document;
		this.stopwords = stopwords;
		this.data = "";
	}
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** method that serves as the cordinator of the Analytics class
     *  @return  ArrayList<String> : final result 
     */ 
	public ArrayList<String> getTopics()
	{
		ArrayList<String> bestWords = new ArrayList<String>();
		dataCleanser();
		Map<String, Integer> unsorted = wordFrequencies();         // unsorted elements
		Sorter sort = new Sorter(unsorted);                        
		Map<String, Integer> sorted = sort.SortedWordMap();       //sorted elements
		Iterator<String> it = sorted.keySet().iterator();
		while(bestWords.size() < Classify.k)
			bestWords.add(it.next().toString());                  // top k words
		return bestWords;
	}
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** method that performs the cleaning of input data
     *  @return  ArrayList<String> : final result 
     */ 
	public void dataCleanser()
	{
		Scanner input = new Scanner(document.text());
		while (input.hasNext())
		{
			// remove alphanumerics , except - , reduce to lowercase
			String word = input.next().replaceAll("[^a-zA-Z0-9-]", "").toLowerCase().trim();    
			data = data + word + " ";
		}
		input.close();
	}
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** method that generates a data structure Map<K,V> ~ <Word,Frequency>
     *  @return  Map<String,Integer> : unsorted
     */
	public Map<String,Integer> wordFrequencies()
	{
		Map<String, Integer> unsorted = new HashMap<String,Integer>();
		StringTokenizer st = new StringTokenizer(data," ");
		while(st.hasMoreTokens())
		{
			String word = st.nextToken();
			// ignore stop words and numbers and blank words
			if (!(stopwords.contains(word)) && !(word.matches("^-?\\d+$")) && word.length() != 0)   
			{
			if(unsorted.containsKey(word))
				unsorted.put(word, unsorted.get(word)+1);
			else
				unsorted.put(word,1);
			}
		}
		return reassignWeights(unsorted);
	}
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** method that manipulates the weights based on the fact that words present
     * on the title of the page are twice as important
     *  @return  Map<String,Integer> : unsorted 
     */
	public  Map<String,Integer> reassignWeights(Map<String, Integer> unsorted)
	{
		// retrieve title header of the webpage
		String text = document.title().toLowerCase().trim().replaceAll("[^a-zA-Z0-9-]"," ");
		StringTokenizer st = new StringTokenizer(text," ");
		while(st.hasMoreTokens())
		{
			String word = st.nextToken();
			if(unsorted.containsKey(word))
				unsorted.put(word, unsorted.get(word)*2);   // multiply by 2 to double to weights of header
		}
		return unsorted;
	}
}
