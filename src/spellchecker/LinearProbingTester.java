package spellchecker;

import spellchecker.HashTable.ProbingType;

public class LinearProbingTester {
	public static void main(String[] args) {
		int InitialTablesize = 53;
		
		System.out.println("With 100 words");
		HashTable h1 = new HashTable(InitialTablesize, "list_1.txt", ProbingType.LINEAR);
		h1.searchWord();
		System.out.println("\n\n");
		
		System.out.println("Adding 10 more words (110 words) ");
		HashTable h2 = new HashTable(InitialTablesize, "list_2.txt", ProbingType.LINEAR);
		h2.searchWord();
		System.out.println("\n\n");
		
		System.out.println("Tester 2 terminated");	
	}
}
