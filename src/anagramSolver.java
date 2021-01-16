import java.util.*;
import java.io.*;

public class anagramSolver {


	public static int CHARBREAK = 70;
	public static int MAXLETTERS = 12;
	public static int MINLETTERS = 4;

	//	//Recursive algorithm which chooses one letter from the set of letters 
	//	//at a time, eventually resulting in every 'n' letter combination
	//	//used when the letters wanted is closer to 0
	//	public static void addLetter (String starting, String lettersLeft, int n, ArrayList<String> list) {
	//		if (starting.length()==n) {
	//			boolean go = true;
	//			for (int i = 0; i<list.size(); i++) {
	//				if (isAnagram(list.get(i),starting)) {
	//					go = false;
	//					break;
	//				}
	//			}
	//			if(go) {
	//				list.add(starting);
	//			}
	//		}
	//		else {
	//			for (int i = 0; i<lettersLeft.length(); i++) {
	//				addLetter(starting+lettersLeft.charAt(i), lettersLeft.substring(0,i)+lettersLeft.substring(Math.min(lettersLeft.length(), i+1),lettersLeft.length()), n, list);
	//			}
	//		}
	//	}
	//
	//	//Recursive algorithm which removes one letter from the set of letters 
	//	//at a time, eventually resulting in every 'n' letter combination
	//	//used when the letters wanted is closer to the total number of letters
	//	public static void removeLetter (String lettersLeft, int n, ArrayList<String> list) {
	//
	//		if (lettersLeft.length()==n) {
	//			boolean go = true;
	//			for (int i = 0; i<list.size(); i++) {
	//				if (isAnagram(list.get(i),lettersLeft)) {
	//					go = false;
	//					break;
	//				}
	//			}
	//			if(go) {
	//				list.add(lettersLeft);
	//			}
	//		}
	//
	//		else {
	//			for (int i = 0; i<lettersLeft.length(); i++) {
	//				removeLetter(lettersLeft.substring(0,i)+lettersLeft.substring(Math.min(lettersLeft.length(), i+1),lettersLeft.length()), n, list);
	//
	//			}
	//		}
	//	}
	//
	//
	//	//determines whether two series of letters are anagrams
	//	//by recording the number of each letter and comparing
	//	public static boolean isAnagram (String dict, String word) {
	//		if (dict.length()!=word.length()) {
	//			return false;
	//		}
	//		int[] dictLetters = new int[26];
	//		int[] wordLetters = new int[26];
	//
	//		for (int i = 0; i<dict.length(); i++) {
	//			dictLetters[(int)dict.charAt(i)-97]++;
	//			wordLetters[(int)word.charAt(i)-97]++;
	//		}
	//
	//		for (int i = 0; i<26; i++) {
	//			if (dictLetters[i]!=wordLetters[i]) {
	//				return false;
	//			}
	//		}
	//		return true;
	//	}
	//
	//
	//	//Finds and prints all anagrams of 'n' letters 
	//	//that can me made from the input word.
	//	public static void printAnas(String word, int n, List<String>words){
	//
	//		ArrayList<String> anagrams = new ArrayList<String>(); //contains all real words anagrams of 'n' letters 
	//		ArrayList<String> combos = new ArrayList<String>(); //contains all sets of letters of 'word' of 'n' letters 
	//
	//		if ((word.length())-n>word.length()/2) {
	//			addLetter("",word,n,combos);
	//		}
	//		else {
	//			removeLetter(word,n,combos);
	//		}
	//
	//		for (int i = 0; i<combos.size(); i++) {
	//			for (int j = 0; j<words.size(); j++) {
	//				if (isAnagram(combos.get(i),words.get(j))) {
	//					anagrams.add(words.get(j));
	//				}
	//			}
	//		}		
	//
	//		Collections.sort(anagrams);
	//
	//		int counter =0;
	//		if (anagrams.size()>0) {
	//			System.out.println("\n");
	//
	//			System.out.println(n+" letters:");
	//			System.out.print("\t");
	//
	//			for (int i = 0 ; i<anagrams.size(); i++) {
	//				System.out.print(anagrams.get(i)+"  ");
	//				counter+=anagrams.get(i).length()+2;
	//				if (counter>CHARBREAK&&i+1<anagrams.size()) {
	//					counter=0;
	//					System.out.println();
	//					System.out.print("\t");
	//				}
	//			}
	//		}
	//	}


	//determines whether a dictionary word is contained within a given word
	public static boolean isAnagram (String dict, String word) {

		int[] dictLetters = new int[26];
		int[] wordLetters = new int[26];

		for (int i = 0; i<dict.length(); i++) {
			dictLetters[(int)dict.charAt(i)-97]++;
		}
		for (int i = 0; i<word.length(); i++) {
			wordLetters[(int)word.charAt(i)-97]++;
		}
		for (int i = 0; i<26; i++) {
			if (dictLetters[i]>wordLetters[i]) {
				return false;
			}
		}
		return true;
	}


	//
	public static void printAnas(String word, List<String>words){

		ArrayList<String> anagrams = new ArrayList<String>();

		for (int i = 0; i<words.size();i++) {
			if (words.get(i).length()<=MAXLETTERS&&words.get(i).length()>=MINLETTERS) {
				if (isAnagram(words.get(i),word)){
					anagrams.add(words.get(i));
				}
			}
		}

		Collections.sort(anagrams);
		Collections.sort(anagrams, C);

		int counter=0;
		int formatLetters=MAXLETTERS+1;

		for (int i = 0 ; i<anagrams.size(); i++) {
			if (anagrams.get(i).length()<formatLetters) {
				counter=0;
				formatLetters=anagrams.get(i).length();
				System.out.println("\n\n"+anagrams.get(i).length()+" letters:");
				System.out.print("\t");
			}
			System.out.print(anagrams.get(i)+"  ");
			counter+=anagrams.get(i).length()+2;
			if (counter>CHARBREAK&&i+1<anagrams.size()) {
				counter=0;
				System.out.println();
				System.out.print("\t");
			}
		}
	}

	//Allows the ArrayList to be sorted by size
	static Comparator<String> C = new Comparator <String>(){
		public int compare(String s1, String s2) {
			return Integer.compare(s2.length(), s1.length());
		}
	};

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Scanner in = new Scanner (System.in);

		//Initializes the dictionary into an ArrayList
		BufferedReader inFile = new BufferedReader(new FileReader("dictionary.txt"));
		ArrayList<String> words = new ArrayList<String>();
		String curr="";
		while ((curr=inFile.readLine())!=null) {
			words.add(curr);
		}
		inFile.close();


		System.out.println("What word would you like to find the anagram(s) of?");
		System.out.print("\t");

		String word = in.nextLine().toLowerCase();

		System.out.print("Max letters:  ");
		MAXLETTERS = in.nextInt();

		System.out.print("Min letters:  ");
		MINLETTERS = in.nextInt();

		long start = System.currentTimeMillis();

		//finds anagrams of the word for every letter size
		//between the maximum and minimum number of letters 
		//		for(int i = Math.min(high, word.length()); i>=low; i--) {
		//			printAnas(word,i,words);
		//		}


		printAnas(word,words);

		long finish = System.currentTimeMillis();



		System.out.println("\n\nElapsed time: "+((finish-start)/1000.0)+"s");

	}



}
