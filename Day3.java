import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.lang.Integer;
import java.lang.Boolean;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ListIterator;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
    
class Day3 {

    public static boolean[] decodeLine(String a) {
	LinkedList<Boolean> rv = new LinkedList<Boolean>();
	for(int i=0 ; i!=a.length() ; i++) {
	    if(a.charAt(i) == '.') {
		rv.addLast(new Boolean(true));
	    }
	    else if(a.charAt(i) == '#') {
		rv.addLast(new Boolean(false));
	    }
	}

	boolean[] res = new boolean[rv.size()];
	ListIterator<Boolean> li = rv.listIterator(0);
	int i=0;
	while(li.hasNext()) {
	    res[i++] = li.next().booleanValue();
	}

	return res;
    }

    public static boolean[][] decodeInput(String[] a) {
	LinkedList<boolean[]> rv = new LinkedList<boolean[]>();

	for(String i : a) {
	    rv.addLast(decodeLine(i));
	}

	boolean[][] res = new boolean[0][0];
	res = rv.toArray(res);
	
	return res;
    }

    public static int checkAngle(boolean[][] board, int dx, int dy) {
	    int x = 0;
	    int y = 0;
	    int trees = 0;
	    while(y<board.length) {
		if(!board[y][x%board[y].length]) {
		    trees++;
		}

		x += dx;
		y += dy;
	    }

	    return trees;
    }
    
    public static void main(String args[]) {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day3.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    boolean[][] input = decodeInput(lines);

	    for(int i=0 ; i!=input.length ; i++) {
		for(int j=0 ; j!=input[i].length ; j++) {
		    if(input[i][j]) {
			System.out.print("-");
		    }
		    else {
			System.out.print("X");
		    }
		}
		System.out.println("");
	    }

	    System.out.println("Part 1:");
	    int trees = 0;
	    trees = checkAngle(input, 3, 1);

	    System.out.println("Trees : " + trees);

	    System.out.println("Part 2:");
	    long[] results = new long[5];
	    results[0] = checkAngle(input, 1, 1);
	    results[1] = checkAngle(input, 3, 1);
	    results[2] = checkAngle(input, 5, 1);
	    results[3] = checkAngle(input, 7, 1);
	    results[4] = checkAngle(input, 1, 2);

	    System.out.println("Total : " + (results[0] * results[1] * results[2] * results[3] * results[4]));
	}
	catch(FileNotFoundException e) {

	}
    }
}
