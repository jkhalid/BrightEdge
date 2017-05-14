//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  Khalid Jahangeer
*  @version 1.0
*  @date    Sun May 14 15:16:20 EDT 2017
*  @see     LICENSE (MIT style license file).
*
*  The code has been written as Assignment for BrightEdge Interview process
*/
package webpageclassifier;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Collections;

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/**
* The Sort class is the fifth executable of in the project hierarchy 
* The Sorter class is used to sort a the data structure based on frequencies
* Input : unsorted HashMap
* Output : Sorted HashMap based on frequency values in descending order
*/

public class Sorter{
	
	private Map<String,Integer> unsorted;
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** constructor to class
     *  @param  Map<String, Integer> : unsorted Map
     */  
	Sorter(Map<String, Integer> unsorted){
	this.unsorted = unsorted;
	}
	
	public Map<String,Integer> SortedWordMap()
	{
		List<Entry<String,Integer>> list = new LinkedList<Entry<String,Integer>>(this.unsorted.entrySet());
		Collections.sort(list, new Comparator<Entry<String,Integer>>() {
            public int compare(Entry<String, Integer> first,
                    Entry<String, Integer> second) {
                return first.getValue().compareTo(second.getValue());
            }
        });
		// the current list of values is in ascending order
		Collections.reverse(list);
        Map<String,Integer> sorted = new LinkedHashMap<String, Integer>();
        for(Entry<String,Integer> entry: list) {
            sorted.put(entry.getKey(), entry.getValue());
        }
	
        return sorted;
	}
}
