package spellchecker;

import java.util.Scanner;

import spellchecker.HashTable.ProbingType;

public class SpellChecker {
	public static void main(String[] args) {
		SpellChecker spellchecker = new SpellChecker();
		int InitialTablesize = 53;
		
		System.out.println("Using List_1 which has 100 words");
		HashTable h1 = new HashTable(InitialTablesize, "list_1.txt", ProbingType.LINEAR);
		spellchecker.searchWord(h1);
		System.out.println("Program 1 exited\n\n\n");
		
		HashTable h2 = new HashTable(InitialTablesize, "list_1.txt", ProbingType.QUADRATIC);
		spellchecker.searchWord(h2);
		System.out.println("Program 2 exited\n\n\n");
		
		System.out.println("Adding 10 more words");
		HashTable h3 = new HashTable(InitialTablesize, "list_2.txt", ProbingType.LINEAR);
		spellchecker.searchWord(h3);
		System.out.println("Program 3 exited\n\n\n");
		
		HashTable h4 = new HashTable(InitialTablesize, "list_2.txt", ProbingType.QUADRATIC);
		spellchecker.searchWord(h4);
		System.out.println("Program 4 exited");
		System.out.println("All programs terminated");
	}
	public void searchWord(HashTable h) {
		Scanner input = new Scanner(System.in);
		String line;
		System.out.print("Enter word : ");
		while ((line = input.nextLine()) != null) {
			if (line.equals("exit")) {
				return;
			}
			h.spellcheck(line);
			System.out.print("Enter word : ");
		}
		input.close();
	}
}
