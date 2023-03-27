package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	//test if file is being read in
	public void checkFile() {

		Path path = Paths.get("ravenpoem.txt");
		
		//convert the path to file and call absolute path 
		//to find the complete directory
		String absolutePath = path.toFile().getAbsolutePath();
				
		assertTrue(absolutePath.endsWith("ravenpoem.txt"));
	}

	@Test
	//test if word occurence program runs and returns a certain key and it's value
	public void checkMainClass() {
		HashMap<String, Integer> freq = new HashMap<>();
		
    		//getting path to the file
        	Path path = Paths.get("ravenpoem.txt");
        	
        	try {
        		//put the file into a string
        		String poem = Files.readString((path));
        		
        		//convert string to lowercase
        		poem = poem.toLowerCase();
        		
        		//use Regex to only keep letters
      	      	Pattern p = Pattern.compile("[a-z]+");
      	      	Matcher m = p.matcher(poem);

      	      	//each call to find() will find the next word in the string
      	      	while (m.find()) {
      	      		//seperated and formatted words are put into a string
      	      		String word = m.group();	      		
      	        
      	        	//get the words
      	            Integer f = freq.get(word);
      	          
      	            //if word is found
      	            if  (f == null)
      	            {
      	            	freq.put(word, 1);
      	            } 
      	        //if same word is found, increase count
      	            else 
      	            {
      	            	freq.put(word, f+1);
      	            }
      	      	}
      	      
      	      //sort the HashMap to top 20 and in descending order
      	      freq.entrySet().stream()
              .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) 
              .limit(20)
      	      .forEach(System.out::println);
      	    
      	     
        	} catch (IOException xIo) {
                xIo.printStackTrace();
        	}
        	assertTrue(freq.containsKey("the") && freq.containsValue(57));
		}
	}