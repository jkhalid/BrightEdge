//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/** @author  Khalid Jahangeer
 *  @version 1.0
 *  @date    Sun May 14 13:16:20 EDT 2017
 *  @see     LICENSE (MIT style license file).
 *
 *  The code has been written as Assignment for BrightEdge Interview process
 */
package webpageclassifier;

import java.net.URL;
import java.net.MalformedURLException;

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
/**
* The Validator class is the second executable of in the project hierarchy 
* The validator class is used to check if the url supplied  by the user is correct 
* 
* Input: String url
* Output: Boolean
*/

public class Validator {
	
	private String url;
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** constructor to class
     *  @param  url input String 
     */  
	Validator(String url)
	{
		this.url = url;
	}
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    /** validate method make use to the java.net.URL functionality to check validity of url
     *  @return Boolean
     */
	public Boolean validate()
	{
		try {
			 this.url = new URL(url).toString();
			 return true;
		} catch (MalformedURLException e) {
			System.out.println("Incorrect URL");
			return false;
		}
	}

}
