

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #11
 * Improve the efficiency of the code below according to the guidelines in the assignment description.
 * Please be sure not to change the signature of the detectPlagiarism method!
 * However, you may modify the signatures of any of the other methods as needed.
 */

public class PlagiarismDetector {

	public static Map<String, Integer> detectPlagiarism(String dirName, int windowSize, int threshold) {
		File dirFile = new File(dirName);
		String[] files = dirFile.list();
		
		Map<String, Integer> numberOfMatches = new HashMap<String, Integer>();
		Map<String, Set<String>> phrases = new HashMap<String, Set<String>>();
		
		for (int i = 0; i < files.length; i++) {
			String file1 = files[i];

			for (int j = 0; j < files.length; j++) { 
				String file2 = files[j];
				
				// Don't compare file to itself
				if (file1.equals(file2)) continue;
				
				Set<String> file1Phrases = getPhrases(dirName + "/" + file1, windowSize, phrases);

				if (file1Phrases == null)
					return null;
				
				Set<String> file2Phrases = getPhrases(dirName + "/" + file2, windowSize, phrases);
				
				if (file2Phrases == null)
					return null;
				
				int matches = findMatchCount(file1Phrases, file2Phrases);
				
				if (matches > threshold) {
					String key = file1 + "-" + file2;
					if (!numberOfMatches.containsKey(file2 + "-" + file1)) {
						numberOfMatches.put(key,matches);
					}
				}				
			}
			
		}		
		
		return sortResults(numberOfMatches);

	}

	
	/*
	 * This method reads the given file and then converts it into a Collection of Strings.
	 * It does not include punctuation and converts all words in the file to uppercase.
	 */
	protected static List<String> readFile(String filename) {
		if (filename == null) return null;
		
		List<String> words = new ArrayList<String>();
		Scanner in = null;
		
		try {
			in = new Scanner(new File(filename));
			while (in.hasNext()) {
				words.add(in.next().replaceAll("[^a-zA-Z]", "").toUpperCase());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			if (in != null)
				in.close();
		}
		
		return words;
	}
	
	/*
	 * Use cached phrases map to prevent repeated file reads in createPhrases method.
	 */
	protected static Set<String> getPhrases(String filename, int window, Map<String, Set<String>> cachedPhrases) {
		if (cachedPhrases.containsKey(filename))
			return cachedPhrases.get(filename);
		else {
			Set<String> phrases = createPhrases(filename, window);
			cachedPhrases.put(filename, phrases);
			return phrases;
		}
	}
/*
	protected static List<String> getPhrases(String filename, int window, Map<String, List<String>> cachedPhrases) {
		if (cachedPhrases.containsKey(filename))
			return cachedPhrases.get(filename);
		else {
			List<String> phrases = createPhrases(filename, window);
			cachedPhrases.put(filename, phrases);
			return phrases;
		}
	}
	*/
	
	/*
	 * This method reads a file and converts it into a Set/List of distinct phrases,
	 * each of size "window". The Strings in each phrase are whitespace-separated.
	 */
	/*
	protected static List<String> createPhrases(String filename, int window) {
		if (filename == null || window < 1) return null;
				
		List<String> words = readFile(filename);
		
		List<String> phrases = new ArrayList<String>();
		
		for (int i = 0; i < words.size() - window + 1; i++) {
			String phrase = "";
			for (int j = 0; j < window; j++) {
				phrase += words.get(i+j) + " ";
			}

			phrases.add(phrase);

		}
		
		return phrases;		
	}
    */
	protected static Set<String> createPhrases(String filename, int window) {
		if (filename == null || window < 1) return null;
				
		List<String> words = readFile(filename);
		
		Set<String> phrases = new HashSet<String>();
		
		for (int i = 0; i < words.size() - window + 1; i++) {
			String phrase = "";
			for (int j = 0; j < window; j++) {
				phrase += words.get(i+j) + " ";
			}

			phrases.add(phrase);

		}
		
		return phrases;		
	}
	

	
	/*
	 * Returns a Set of Strings that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	protected static Set<String> findMatches(List<String> myPhrases, List<String> yourPhrases) {
	
		Set<String> matches = new HashSet<String>();
		
		for (String mine : myPhrases) {
			for (String yours : yourPhrases) {
				if (mine.equals(yours)) {
					matches.add(mine);
				}
			}
		}
		return matches;
	}
	
	/*
	 * Returns a count of matches that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	/*
	protected static int findMatchCount(List<String> myPhrases, List<String> yourPhrases) {
	
		int count = 0;
		
		for (String mine : myPhrases) {
			for (String yours : yourPhrases) {
				if (mine.equals(yours)) {
					count++;
				}
			}
		}
		return count;
	}
*/
	protected static int findMatchCount(Set<String> myPhrases, Set<String> yourPhrases) {
		
		int count = 0;
		
		for (String mine : myPhrases) {
			if (yourPhrases.contains(mine))
				count++;
		}
		return count;
	}

	/*
	 * Returns a LinkedHashMap in which the elements of the Map parameter
	 * are sorted according to the value of the Integer, in non-ascending order.
	 */
	protected static LinkedHashMap<String, Integer> sortResults(Map<String, Integer> possibleMatches) {
		
		// Because this approach modifies the Map as a side effect of printing 
		// the results, it is necessary to make a copy of the original Map
		Map<String, Integer> copy = new HashMap<String, Integer>();

		for (String key : possibleMatches.keySet()) {
			copy.put(key, possibleMatches.get(key));
		}	
		
		LinkedHashMap<String, Integer> list = new LinkedHashMap<String, Integer>();

		for (int i = 0; i < copy.size(); i++) {
			int maxValue = 0;
			String maxKey = null;
			for (String key : copy.keySet()) {
				if (copy.get(key) > maxValue) {
					maxValue = copy.get(key);
					maxKey = key;
				}
			}
			
			list.put(maxKey, maxValue);
			
			copy.put(maxKey, -1);
		}

		return list;
	}
	
	/*
	 * This method is here to help you measure the execution time and get the output of the program.
	 * You do not need to consider it for improving the efficiency of the detectPlagiarism method.
	 */
    public static void main(String[] args) {
    	if (args.length == 0) {
    		System.out.println("Please specify the name of the directory containing the corpus.");
    		System.exit(0);
    	}
    	String directory = args[0];
    	long start = System.currentTimeMillis();
    	Map<String, Integer> map = PlagiarismDetector.detectPlagiarism(directory, 4, 5);
    	long end = System.currentTimeMillis();
    	double timeInSeconds = (end - start) / (double)1000;
    	System.out.println("Execution time (wall clock): " + timeInSeconds + " seconds");
    	Set<Map.Entry<String, Integer>> entries = map.entrySet();
    	for (Map.Entry<String, Integer> entry : entries) {
    		System.out.println(entry.getKey() + ": " + entry.getValue());
    	}
    }

}
