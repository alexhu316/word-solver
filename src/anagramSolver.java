import java.util.*;
import java.io.*;

public class anagramSolver {


	public static int charBreak = 70;

	public static void addLetter (String starting, String lettersLeft, int n, ArrayList<String> list) {

		if (starting.length()==n) {
			boolean go = true;
			for (int i = 0; i<list.size(); i++) {
				if (isAnagram(list.get(i),starting)) {
					go = false;
					break;
				}
			}
			if(go) {
				list.add(starting);
			}
		}
		else {
			for (int i = 0; i<lettersLeft.length(); i++) {
				addLetter(starting+lettersLeft.charAt(i), lettersLeft.substring(0,i)+lettersLeft.substring(Math.min(lettersLeft.length(), i+1),lettersLeft.length()), n, list);

			}
		}
	}

	public static void removeLetter (String lettersLeft, int n, ArrayList<String> list) {

		if (lettersLeft.length()==n) {
			boolean go = true;
			for (int i = 0; i<list.size(); i++) {
				if (isAnagram(list.get(i),lettersLeft)) {
					go = false;
					break;
				}
			}
			if(go) {
				list.add(lettersLeft);
			}
		}

		else {
			for (int i = 0; i<lettersLeft.length(); i++) {
				removeLetter(lettersLeft.substring(0,i)+lettersLeft.substring(Math.min(lettersLeft.length(), i+1),lettersLeft.length()), n, list);

			}
		}
	}
	public static void sortList(ArrayList<String> words) {

		for (int i = 0; i<words.size()-1; i++) {
			for (int j = 0; j<words.size()-i; j++) {
				if (words.get(j).compareTo(words.get(j+1))>0) {
					String store = words.get(j);
					words.set(j,words.get(j+1));
					words.set(j+1,store);
				}
			}
		}	
	}

	public static boolean isAnagram (String dict, String word) {
		if (dict.length()!=word.length()) {
			return false;
		}
		int[] dictLetters = new int[26];
		int[] wordLetters = new int[26];

		for (int i = 0; i<dict.length(); i++) {
			dictLetters[(int)dict.charAt(i)-97]++;
			wordLetters[(int)word.charAt(i)-97]++;
		}

		for (int i = 0; i<26; i++) {
			if (dictLetters[i]!=wordLetters[i]) {
				return false;
			}
		}
		return true;
	}



	public static void printAnas(String word, int n, List<String>words){


		ArrayList<String> anagrams = new ArrayList<String>(); //contains all real words anagrams of 'n' letters 
		ArrayList<String> combos = new ArrayList<String>(); //contains all sets of letters of 'word' of 'n' letters 

		if ((word.length())-n>word.length()/2) {
			addLetter("",word,n,combos);
		}
		else {
			removeLetter(word,n,combos);
		}

		for (int i = 0; i<combos.size(); i++) {
			for (int j = 0; j<words.size(); j++) {
				if (isAnagram(combos.get(i),words.get(j))) {
					anagrams.add(words.get(j));
				}
			}
		}		

		Collections.sort(anagrams);

		int counter =0;
		if (anagrams.size()>0) {
			System.out.println("\n");

			System.out.println(n+" letters:");
			System.out.print("\t");

			for (int i = 0 ; i<anagrams.size(); i++) {
				System.out.print(anagrams.get(i)+"  ");
				counter+=anagrams.get(i).length()+2;
				if (counter>charBreak&&i+1<anagrams.size()) {
					counter=0;
					System.out.println();
					System.out.print("\t");
				}
			}
		}
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {

		Scanner in = new Scanner (System.in);
		BufferedReader inFile = new BufferedReader(new FileReader("dictionary.txt"));

		List<String> words = new ArrayList<String>();

		String curr="";
		while ((curr=inFile.readLine())!=null) {
			words.add(curr);
		}
		inFile.close();


		System.out.println("What word would you like to find the anagram(s) of?");
		System.out.print("\t");

		String word = in.nextLine().toLowerCase();

		System.out.print("Max letters:  ");
		int high = in.nextInt();

		System.out.print("Min letters:  ");
		int low = in.nextInt();

		for(int i = Math.min(high, word.length()); i>=low; i--) {
			printAnas(word,i,words);

		}
	}

}
