package spellchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
	//public HashEntry[] hashtable = new HashEntry[tablesize];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable h = new HashTable(53);
		h.searchWord();
	}
	
	public HashTable(int tablesize){
		this.tablesize = tablesize;
		this.hashtable = new String[tablesize];
		String[] str = readStringfromFile("words.txt");
		for (int i = 0; i < str.length; i++) {
			System.out.println(i + " - " + str[i] );
		}
		
	}
	public String[] readStringfromFile(String filename) {
		File file = new File(filename);
		String str = null;
		//int hashcode = 0;
		int collisions = 0;
		try {
			Scanner sc = new Scanner(file);
			for (; sc.hasNextLine() ;) {
				str = sc.nextLine();
				//collisions = linearprobing(str, collisions);
				collisions = quadraticprobing(str, collisions);
				
			}
			System.out.println("Collisions = " + collisions);
			sc.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashtable;
	}
	
	public int linearprobing(String value, int collisions) {
		int hashcode = hashFunction(value);
		//System.out.println(hashcode);
		int initialhash = hashcode;
		if (hashtable[initialhash] == null) {
			hashtable[initialhash] = value;
			return 0;
		}
		while (hashtable[hashcode] != null) {
			hashcode = (hashcode + 1) % tablesize;
			collisions++;
			if(initialhash == hashcode) {
				//System.out.println("Table full");
				return collisions;
			}
		}
		hashtable[hashcode] = value;
		return collisions;
	}
	
	public int quadraticprobing(String value, int collisions) {
		int hashcode = hashFunction(value);
		int i = 1;
		//System.out.println(hashcode);
		int initialhash = hashcode;
		if (hashtable[initialhash] == null) {
			hashtable[initialhash] = value;
			return 0;
		}
		while (hashtable[hashcode] != null) {
			hashcode = (hashcode + i*i) % (tablesize - 1);
			i++;
			collisions++;
			if(initialhash == hashcode) {
				//System.out.println("Table full");
				return collisions;
			}
		}
		hashtable[hashcode] = value;
		return collisions;
	}
	
	public int hashFunction(String str) {
		int hash = 0;
		for (int i = 0; i < 2 ; i++) {
			hash = (hash + str.charAt(i)) % (tablesize - 1);
		}
		//System.out.println(hash);
		return hash;
	}
	
	public boolean spellcheck (String word) {
		int hashcode = hashFunction(word);
		int initialhash = hashcode;
		int i = 1;
		System.out.println();
		System.out.println(hashcode + " - " + hashtable[hashcode]);
		if (hashtable[initialhash] == word) {
			return true;
		}
		while (!hashtable[hashcode].equals(word)) {
			hashcode = (hashcode + i) % (tablesize - 1);
			System.out.println(i);
			i++;
			if(initialhash == hashcode) {
				System.out.println(hashcode + " - " + hashtable[hashcode]);
				return false;
			}
		}
		System.out.println(hashcode + " - " + hashtable[hashcode]);
		return true;
	}
	
	public void searchWord() {
		//Scanner input = new Scanner(System.in);
		//String word = "croesus";
		if(spellcheck("modicum")) {
			System.out.println("Correct");
		}
		else {
			System.out.println("Wrong");
		}
		/*while(input.hasNext()) {
			word = input.next();
			input = new Scanner(System.in);
			spellcheck(word);
			
		}*/
	}
}
