package spellchecker;

import spellchecker.HashTable.ProbingType;

public class SpellChecker {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable h = new HashTable(100, "words.txt", ProbingType.LINEAR);
		if (h.createHashtablefromWords())
			h.searchWord();
		else {
			h.changeTableSize();
			h.createHashtablefromWords();
			h.searchWord();
		}
	}
}
