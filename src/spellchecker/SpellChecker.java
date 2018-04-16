package spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import spellchecker.HashTable.ProbingType;

public class SpellChecker {
	public static void main(String[] args) {
		SpellChecker spellchecker = new SpellChecker();
		int InitialTablesize = 53;
		
		System.out.println("Using List_1 which has 100 words");
		HashTable h1 = new HashTable(InitialTablesize, "list_1.txt", ProbingType.LINEAR);
		spellchecker.searchWord(h1);
		
		HashTable h2 = new HashTable(InitialTablesize, "list_1.txt", ProbingType.QUADRATIC);
		spellchecker.searchWord(h2);
		
		System.out.println("Using List_1 which has 110 words");
		HashTable h3 = new HashTable(InitialTablesize, "list_2.txt", ProbingType.LINEAR);
		spellchecker.searchWord(h3);
		
		HashTable h4 = new HashTable(InitialTablesize, "list_2.txt", ProbingType.QUADRATIC);
		spellchecker.searchWord(h4);
	}
	public void searchWord(HashTable h) {
		Scanner input = new Scanner(System.in);
		String line;
		while ((line = input.nextLine()) != null) {
			if (line.equals("exit")) {
				System.out.println("Program exited");
				return;
			}
			h.spellcheck(line);
		}
		input.close();
	}
}
