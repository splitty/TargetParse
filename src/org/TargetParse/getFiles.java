package org.TargetParse;
//Copyright for jSoup
//Copyright © 2009 - 2021 Jonathan Hedley (jonathan@hedley.net)

//Permission is hereby granted, free of charge, to any person obtaining a copy 
//of this software and associated documentation files (the "Software"), to deal 
//in the Software without restriction, including without limitation the rights 
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
//copies of the Software, and to permit persons to whom the Software is 
//furnished to do so, subject to the following conditions:


//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
//SOFTWARE.


//The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

//Luke Labenski

//First off, these are the imports.  Imports are designed to act as the system to tell the computer what to look for when picking resources.
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import static org.TargetParse.Downloader.sitesToParse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//This is the "main" class
public class getFiles{
	
	
	//Declaring a string to ensure usage is proper.
    static String src;    
    static String emailsrc;
    //Where the magic happens, this Swing Worker parses the HTML tags on the Webpage to obtain the the top level files to download.
    public static class retrieve extends SwingWorker<Void, Void>{
    	//Declaring a string to get the file names before download.
    	String myString;
    	@Override       
    	//What the Swing Worker will do while the loading bar is shown.
    	protected Void doInBackground() throws Exception {
    		
    		//For all of the URLs in the Array, do the following
    		for (String stringySites : sitesToParse)
    			//Attempts to parse the page, looking for the preset tags.
    			try {  
    				
//    				//The following lines are for grabbing all linked files - this will essentially act as an unbiased web scraper.
//    				//Obtains and connects to the site, acting like a user
//    				Document doc = Jsoup.connect(stringySites).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
//    				//INTECHANGABLE BASED UPON THE TAGS YOU WANT TO PARSE
//    				//The following is for parsing and downloading links to files with the <a href="WhateverTheFileIsCalled.AndItsExtension">
//    				//This will parse the entire page SOURCE, not just what is visible.  Please take note of that.
//    				Elements file = doc.getElementsByTag("a");
//    				for (Element el : file) {
//           
//    					src = el.absUrl("href");
//                
//    					System.out.println("File Found!");
//    					System.out.println("src attribute is : " + src);
//    					grabMe();	
//    				}
    				
    				
    				//This code is for the downloading all of the images off of the page
    				//Comment out everything below the "try" method and above this commented out code, then uncomment the following code.
    				Document doc = Jsoup.connect(stringySites).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
    				//selecting all of the image files from the page
    				Elements file = doc.select("img");
    				//iterate through each image file and grab the image source as well as downloading it.
    				for (Element el : file) {
    					//subset of the img tag - src is where the image file is found
	    				src = el.absUrl("src");
	    				//outputs for testing
	    				System.out.println("File Found!");
	    				System.out.println("src attribute is : " + src);
    				
    				
    				
    				//This small snippet is used for grabbing email addresses from runestone.org.  See the journalistic article for information on that.
//    				Elements email = doc.select("a[href][title]");
//    				for (Element e : email) {
//    					emailsrc = e.attr("title");
//    				}
//    				System.out.println("email src found at: " + emailsrc);
    				
    					//download method
    					grabMe();
    				}
    			} catch (IOException ex) {
    				System.err.println("There was an error");
    				Logger.getLogger(Downloader.class.getName()).log(Level.SEVERE, null, ex);
    			}    
      
        
    		return null;
    	}
       //grabMe Class which takes the src URL and downloads it.
       public void grabMe() throws Exception{
    	   	//Turns on the loading bar for the files being downloaded.
            Downloader.turnOnBar();
            //Take the last part of the URL as the name of the file
            int indexname = src.lastIndexOf("/");       
            String srcname = src.substring(indexname, src.length());
            //Labeling the path for the files
            String Path = Downloader.folderPath + "//";
            //Replaces all un-usable file name characters, feel free to add more if you run into them
            String srcname2 = srcname.replaceAll("[?:<>]", "");
            
            //the following lines are for runestone.org to associate email addresses with images downloaded.  See the journalistic article for that.
            //String srcname3 = srcname2.replace("&ssl=1", ".png");
            //String srcname4 = emailsrc + ".png";
            
            
            //Piecing the parts together
            myString = Path + srcname2;
            
            try {
            	//Opens new connection
            	URL url=new URL(src);
            	HttpURLConnection connection =
            			(HttpURLConnection) url.openConnection();
            	//Just getting the amount of information, in case you want to implement a progress bar that updates based upon the amount
            	//downloaded and how much is left
            	float totalDataRead=0;
            	//Input stream to obtain the file.
            	java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
            	//Output stream pointing towards the directory the user chose earlier
            	java.io.FileOutputStream fos = new java.io.FileOutputStream(myString);
            	//Buffered Output Stream, to reduce the work size at once and allow for the file to be downloaded onto the local disk.
            	java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
            	//Declaring the buffer size
            	byte[] data = new byte[1024];
            	//Pre-setup code for the progress bar option, it will also tell the system when to stop
            	int i=0;
            	while((i=in.read(data,0,1024))>=0)
            	{
            		totalDataRead=totalDataRead+i;
            		bout.write(data,0,i);
   			
            	}	
            	//Safely Closing the streams to avoid leakage
            	
            	bout.close();
            	in.close();
                        
            }
           
            catch (FileNotFoundException e) {
            	e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            }
       }
       @Override
       //Turn off the loading bar when done downloading.
       protected void done(){
    	   System.out.println(myString + "----  File Downloaded");
    	   Downloader.turnOffBar();
       }

    }
}
	
	