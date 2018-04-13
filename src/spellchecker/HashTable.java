package spellchecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
For this assignment, you will create a hash table to implement spell checker.
1. Create a file of 100 words of varying length (max 8 characters).
2. All the words are in lower case.
3. design a hash table.
4. enter each word in the hash table.
Once the hash table is created, run it as a spell checker.  
You enter a word (interactive), find the word in your hash table. 
If not found enter an error message.  Then prompt for next word.  End the session by some control character like ctrl-c or something.
1. use the linear probing first.  Count the number of collisions and print it.
2. Then use quadratic probing.  Count the number of collisions and print it.
3. Start with a table size that is about 53.  
So 100 words would still have a load factor of less than 0.5  Now add 10 more words.  
The program should automatically determine that table size needs to increase.
Print out - increasing table size to <size>
rehash all the old words also and the new words to the new hash table.
You already have linear probing and quadratic probing functions.  Print the collisions in each case for the new table size.
*/

public class HashTable {
	private int tablesize;
	public String[] hashtable;
	public ProbingType probingType;
	public enum ProbingType {
	    LINEAR, QUADRATIC;
	}
	//public HashEntry[] hashtable = new HashEntry[tablesize];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeFile("", false);
		HashTable h = new HashTable(100, "words.txt", ProbingType.QUADRATIC);
		//h.spellcheck("craning");
		h.searchWord();
	}
	
	public static void writeFile(String data, boolean isAppended) {
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hashtable.csv", isAppended)))) {
		    out.print(data);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashTable(int tablesize, String filename, ProbingType probingType){
		this.tablesize = tablesize;
		this.hashtable = new String[tablesize];
		this.probingType = probingType;
		createHashtablefromWords(filename);
	}
	public void createHashtablefromWords(String filename) {
		File file = new File(filename);
		String word = null;
		int collisions = 0;
		try {
			Scanner sc = new Scanner(file);
			for (; sc.hasNextLine() ;) {
				word = sc.nextLine();
				if (probingType == ProbingType.LINEAR) {
					collisions = linearprobing(word);
				}
				else if (probingType == ProbingType.QUADRATIC)
					collisions = quadraticprobing(word);
			}
			sc.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < hashtable.length; i++) {
			writeFile(i + " , " + hashtable[i] + "\n", true);
		}
	}
	
	public int linearprobing(String value) {
		int hashcode = hashFunction(value);
		//System.out.println(hashcode);
		int i = 1;
		int initial = hashcode;
		for (i = 1 ; hashtable[hashcode] != null ; i++) {
			hashcode = (initial + i) % tablesize;
			if(i == tablesize) {
				System.out.println("Table full");
				//tablesize = tablesize*2;
				return -1;
			}
		}
		hashtable[hashcode] = value;
		System.out.println(initial + " + " + (i - 1) + " = " + hashcode + " for " + value);
		return i-1;
	}
	
	public int quadraticprobing(String value) {
		int hashcode = hashFunction(value);
		int i = 1;
		int initial = hashcode;
		for (i = 1 ; hashtable[hashcode] != null ; i++) {
			hashcode = (initial + (i*i)) % tablesize;
			if(i == tablesize) {
				System.out.println("Table full");
				return -1;
			}
		}
		hashtable[hashcode] = value;
		System.out.println(initial + " + " + (i-1)*(i-1) + " = " + hashcode + " for " + value);
		return i-1;
	}
	
	public int hashFunction(String str) {
		int hash = 0;
		for (int i = 0; i < 3 ; i++) {
			try {
				hash = (hash + str.charAt(i)) % tablesize;
			} catch (StringIndexOutOfBoundsException e) {
				// ignore strings with less than 3 characters
			}
		}
		return hash;
	}
	
	public boolean spellcheck (String word) {
		int hashcode = hashFunction(word);
		System.out.println("hashcode " + hashcode + " for " + word);
		int i = 1;
		int initial = hashcode;
		if(hashtable[hashcode] == null) {
			System.out.println("Word not found - Wrong");
			return false;
		}
		for (i = 1 ; !hashtable[hashcode].equals(word) ; i++) {
			if (probingType == ProbingType.LINEAR)
				hashcode = (initial + i) % tablesize;
			else if (probingType == ProbingType.QUADRATIC)
				hashcode = (initial + i*i) % tablesize;
			if(hashtable[hashcode] == null) {
				System.out.println("Word not found - Wrong");
				return false;
			}
		}
		System.out.println("Word Found - Correct");
		return true;
	}
	
	public void searchWord() {
		Scanner input = null;
		String word = null;
		while (true) {
			System.out.print("Enter word : ");
			input = new Scanner(System.in);
			word = input.nextLine();
			if (word.equals("exit")) {
				System.out.println("Program exited");
				break;
			}
			if (word.isEmpty()) {
				continue;
			}
			spellcheck(word);	
		} 
		input.close();
	}
}
