import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		List<Sentence> sentences = new ArrayList<Sentence>();
		
		if (filename == null) return sentences;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while((line = reader.readLine()) != null) {
				Sentence s = parseSentence(line);
				if (s != null) sentences.add(s);
			}
		}
		catch (IOException ex) {
			return sentences;
		}
		
		return sentences;
	}
	
	public static Sentence parseSentence(String line) {
		int pos = line.indexOf(" ");
		if (pos <= 0) return null;
		
		String candidateScore = line.substring(0,  pos);
		String text = line.substring(pos+1, line.length());
		try {
			int score = Integer.parseInt(candidateScore);
			return isScoreValid(score)
				? new Sentence(score, text)
				: null;
		}
		catch(NumberFormatException ex) {
			return null; // skip
		}
	}
	
	public static boolean isScoreValid(int score) {
		return score >= -2 && score <= 2;
	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		HashMap<String,Word> words = new HashMap<String,Word>();
		
		if (sentences == null || sentences.isEmpty()) return new HashSet<Word>(words.values());

		for (Sentence sentence : sentences) {
			if (sentence == null) continue;
			for(String token: sentence.getText().split(" ")) {
				token = token.toLowerCase();
				if (token.matches("^[a-z].*")) {
					Word word;
					if (words.containsKey(token)) {
						word = words.get(token);
						word.increaseTotal(sentence.score);
					}
					else {
						word = new Word(token);
						word.increaseTotal(sentence.score);
						words.put(token,  word);
					}
				}
			}
		}

		return new HashSet<Word>(words.values());
	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		HashMap<String, Double> scores = new HashMap<String, Double>();

		if (words == null || words.isEmpty()) return scores;
		
		for (Word word : words) {
			if (word != null)
				scores.put(word.getText(),  word.calculateScore());
		}
		return scores;
	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		double score = 0;
		int wordCount = 0;
		
		if (wordScores == null || sentence == null || wordScores.isEmpty() || sentence.length() == 0) return score;
		
		for (String word : sentence.split(" ")) {
			word = word.toLowerCase();
			if(word.matches("^[a-z].*")) {
				score += wordScores.containsKey(word) ? wordScores.get(word) : 0;
				wordCount++;
			}
		}

		return wordCount > 0 ? score / wordCount : score;
	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
