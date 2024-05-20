import java.io.*;
import java.util.*;

public class Counter {
	
	/*
	 * This method reads the text from a file and returns a List of the 
	 * distinct strings. The strings are converted to lowercase and any
	 * whitespace is removed, but punctuation is not removed (which is fine).
	 * 
	 * TODO: Modify this method so that it populates and returns a Set
	 * instead of a List.
	 * You should take advantage of the Set structure and remove any
	 * unnecessary code!
	 */
	public static List<String> getWords(String filename) {
		
		List<String> allWords = new ArrayList<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					word = word.trim().toLowerCase();
					if (word.length() > 0 && allWords.contains(word) == false) {
						allWords.add(word);
					}
				}
			}
			return allWords;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	/*
	 * This method reads the text from a file and returns an array of two Lists:
	 * the first List holds the individual bigrams (pairs of letters) that occur 
	 * in the file; the second holds the corresponding number of occurrences of
	 * each bigram.
	 * 
	 * For example, the value held at element #N of the second List indicates
	 * the number of occurrences of the bigram at element #N of the first List.
	 * 
	 * Yes, this is terrible code. =)
	 * 
	 * TODO: Modify this method so that it populates and returns a Map
	 * instead of an array of Lists. The Map key should be the bigram, and the 
	 * value should be the number of occurrences.
	 */
	public static List[] countBigrams(String filename) {
		LinkedList<String> bigrams = new LinkedList<>();
		LinkedList<Integer> count = new LinkedList<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					
					for (int i = 0; i < word.length() - 1; i++) {
						char c0 = word.charAt(i);
						char c1 = word.charAt(i+1);
						if (c0 >= 'a' && c0 <= 'z' && c1 >= 'a' && c1 <= 'z') {
							String bigram = Character.toString(c0) + Character.toString(c1);
							int index = bigrams.indexOf(bigram);
							if (index == -1) {
								bigrams.add(bigram);
								count.add(1);
							}
							else {
								count.set(index, count.get(index) + 1);
							}
						}
					}
				}				
			}
			LinkedList[] result = new LinkedList[2];
			result[0] = bigrams;
			result[1] = count;
			return result;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	 

	/*
	 * This method reads the text from a file and returns a Map of each
	 * letter ('a' through 'z') and the number of occurrences of that letter.
	 * This is case-insensitive so uppercase letters are converted to
	 * lowercase.
	 * 
	 * TODO: Modify this function so that it populates and returns 26-element
	 * int array instead of a Map.
	 * Index #0 of the array should hold the number of occurrences of 'a', 
	 * index #1 of the array should hold the number of occurrences of 'b',
	 * and so on.
	 */
	public static Map<Character, Integer> countLetters(String filename) {
		
		Map<Character, Integer> letterCount = new HashMap<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					for (char c : word.toCharArray()) {
						if (c >= 'a' && c <= 'z') {
							if (letterCount.containsKey(c) == false) {
								letterCount.put(c, 1);
							}
							else {
								int count = letterCount.get(c);
								letterCount.put(c, count + 1);
							}
						}
					}
				}
			}
			return letterCount;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	/*
	 * The code here is provided in order to test the methods above.
	 */
	public static void main(String[] args) {
		
		/*
		 * This allows the user to change the name of the file,
		 * but uses "alice.txt" as the default.
		 */
		String filename = "alice.txt";
		if (args.length > 0) {
			filename = args[0];
		}
				
		/*
		 * This part tests the getWords method
		 */
		
		/* comment out the following line after changing the method */ 
		List<String> words = getWords(filename);
		
		/* then uncomment the following line */
		//Set<String> words = getWords(filename);
		
		/* DO NOT CHANGE THE FOLLOWING TEST CODE */
		if (words == null) {
			System.out.println("ERROR! getWords return null");
			System.exit(1);			
		}
		int numWords = words.size(); 
		if (numWords != 5022) {
			System.out.println("ERROR! Number of words should be 5022 but is " + numWords);
			System.exit(1);
		}
		else {
			System.out.println("getWords test passed");
		}
		
		
		/*
		 * This part tests the countBigrams method
		 */

		/* comment out the following three lines after changing the method */
		List[] bigramCount = countBigrams(filename);
		int numBigrams = bigramCount[0].size(); 
		int countAL = (Integer)(bigramCount[1].get(bigramCount[0].indexOf("al"))); 
		
		/* then uncomment the following three lines */
		//Map<String, Integer> bigramCount = countBigrams(filename);
		//int numBigrams = bigramCount.size();
		//int countAL = bigramCount.get("al");
		
		/* DO NOT CHANGE THE FOLLOWING TEST CODE */
		if (numBigrams != 375) {
			System.out.println("ERROR! Number of bigrams should be 375 but is " + numBigrams);
			System.exit(1);
		}
		else if (countAL != 521) {
			System.out.println("ERROR! Bigram count for 'al' should be 521 but is " + countAL);			
			System.exit(1);
		}
		else {
			System.out.println("countBigrams tests passed");
		}
		
		/*
		 * This part tests the countLetters method
		 */

		/* comment out the following two lines after changing the method */
		Map<Character, Integer> count = countLetters(filename);
		int countE = count.get('e');
		
		/* then uncomment the following two lines */
		//int[] count = countLetters(filename);
		//int countE = count[4];

		/* DO NOT CHANGE THE FOLLOWING TEST CODE */
		if (countE != 13518) {
			System.out.println("ERROR! Count for 'e' should be 13518 but is " + countE);
			System.exit(1);
		}
		else {
			System.out.println("countLetters test passed");
		}

		
		System.out.println("ALL TESTS PASSED!");

		
		
	}

}
