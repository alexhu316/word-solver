import java.util.*;
import java.io.*;

public class boggleSolver {

	public static void traverse (char[][] board, String word, int x, int y, boolean[][]avail) {

		char[][]board2 = board;
		word=word+board[x][y];
		System.out.println(word);
		avail[x][y]=false;
		
		boolean[][]availCopy = avail;

		if (x>0&&avail[x-1][y]) { //L
			traverse(board2,word,x-1,y,availCopy);
		} 

		
		if (x>0&&y>0&&avail[x-1][y-1]) { //LU
			traverse(board2,word,x-1,y-1,availCopy);
		} 

		if (y>0&&avail[x][y-1]) { //U
			traverse(board2,word,x,y-1,availCopy);
		} 

		
		if (y>0&&x<3&&avail[x+1][y]) { //RU
			traverse(board2,word,x+1,y,availCopy);
		}


		if (x<3&&avail[x+1][y]) { //R
			traverse(board2,word,x+1,y,availCopy);
		}
		if (y<3&&x<3&&avail[x+1][y+1]) { //RD
			traverse(board2,word,x+1,y+1,availCopy);
		}

		if (y<3&&avail[x][y+1]) { //D
			traverse(board2,word,x,y+1,availCopy);
		} 
		if (y<3&&x>0&&avail[x-1][y+1]) { //DL
			traverse(board2,word,x-1,y+1,availCopy);
		} 
		return;


	}
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Scanner in = new Scanner (System.in);
		BufferedReader inFile = new BufferedReader(new FileReader("dictionary.txt"));

		Map<Integer, String> words = new HashMap<>();

		String curr="";
		int m = 0;
		while ((curr=inFile.readLine())!=null) {
			words.put(m, curr);
			m++;
		}
		inFile.close();


		System.out.println("Enter your board");
		String input = in.nextLine();

		char[][] board = new char[4][4];
		for (int i = 0; i<4; i++) {
			for (int j = 0; j<4; j++) {
				board [j][i]=input.charAt(0);
				input=input.substring(1);
			}
		}
		boolean[][] avail = new boolean[4][4];
		for (int i = 0; i<4; i++) {
			for (int j = 0; j<4; j++) {
				avail [j][i]=true;
			}
		}
		
//		for (int i = 0; i<4; i++) {
//			for (int j = 0; j<4; j++) {
//				traverse(board,"",j,i,avail);
//			}
//		}
		traverse(board,"",0,0,avail);
	}
}
