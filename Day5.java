import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.lang.Integer;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ListIterator;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
    
class Day5 {

    static class Coord {
	public int x = 0;
	public int y = 0;
    }

    public static Coord narrow(String a) {
	int x = 0;
	int dx = 8;
	int y = 0;
	int dy = 128;

	for(int i=0 ; i!=a.length() ; i++) {
	    char c = a.charAt(i);
	    if(c == 'F') {
		dy /= 2;
	    }
	    else if(c == 'B') {
		dy /= 2;
		y += dy;
	    }
	    else if(c == 'L') {
		dx /= 2;
	    }
	    else if(c == 'R') {
		dx /= 2;
		x += dx;
	    }
	}

	Coord c = new Coord();
	c.x = x;
	c.y = y;
	return c;
    }
   
    public static void main(String args[]) {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day5.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    int highestId = 0;
	    for(String line : lines) {
		Coord c = narrow(line);

		int id = c.y * 8 + c.x;
		if(id > highestId) {
		    highestId = id;
		}
	    }

	    System.out.println("Part 1:");
	    System.out.println("Highest ID : " + highestId);


	    System.out.println("Part 2:");
	    boolean[][] seatsTaken = new boolean[128][8];
	    for(int y=0 ; y!=128 ; y++) {
		for(int x=0 ; x!=8 ; x++) {
		    seatsTaken[y][x] = false;
		}
	    }
	    
	    for(String line : lines) {
		Coord c = narrow(line);

		seatsTaken[c.y][c.x] = true;
	    }

	    for(int sid=1 ; sid!=(128*8-1) ; sid++) {
		    
		if(seatsTaken[(sid-1)/8][(sid-1)%8] && !seatsTaken[sid/8][sid%8] && seatsTaken[(sid+1)/8][(sid+1)%8]) {
		    System.out.println("ID : " + sid);
		}

	    }
	}
	catch(FileNotFoundException e) {

	}
    }
}
