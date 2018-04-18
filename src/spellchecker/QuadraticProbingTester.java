package spellchecker;

import spellchecker.HashTable.ProbingType;

public class QuadraticProbingTester {

	public static void main(String[] args) {
		int InitialTablesize = 53;
		
		System.out.println("With 100 words");
		HashTable h3 = new HashTable(InitialTablesize, "list_1.txt", ProbingType.QUADRATIC);
		h3.searchWord();
		System.out.println("");
		
		System.out.println("Adding 10 more words (110 words) and re-hashing to a new hashtable");
		HashTable h4 = new HashTable(h3.tablesize, "list_2.txt", ProbingType.QUADRATIC);
		h4.searchWord();
		System.out.println("");
		
		System.out.println("Quadratic Probing Tester terminated");
	}

}
