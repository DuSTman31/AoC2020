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
    
class Day2 {

    static class Data {
	public int start;
	public int end;
	public char c;
	public String pw;
    }

    static int countChars(char needle, String haystack) {
	int tot = 0;

	for(int i=0 ; i!=haystack.length() ; i++) {
	    if(haystack.charAt(i) == needle) {
		tot++;
	    }
	}
	
	return tot;
    }

    static boolean isValidP1(Data d) {
	int count = countChars(d.c, d.pw);

	if(count >= d.start && count <= d.end) {
	    return true;
	}
	else {
	    return false;
	}
    }

    static boolean isValidP2(Data d) {
	int noMatched = 0;
	if(d.pw.charAt(d.start-1) == d.c) {
	    noMatched++;
	}
       	if(d.pw.charAt(d.end-1) == d.c) {
	    noMatched++;
	}

	if(noMatched == 1) {
	    return true;
	}
	else {
	    return false;
	}
    }
    
    public static void main(String args[]) {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day2.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    Vector<Data> decoded = new Vector<Data>();
	    Pattern p = Pattern.compile("^(\\d+)-(\\d+) (.*): (.*)$");
	    for(String line : lines) {
		Matcher m = p.matcher(line);

		if(m.matches()) {
		    Data d = new Data();
		    d.start = Integer.parseInt(m.group(1));
		    d.end = Integer.parseInt(m.group(2));
		    d.c = m.group(3).charAt(0);
		    d.pw = m.group(4);

		    decoded.add(d);
		}
	    }

	    int valid = 0;
	    for(Data d : decoded) {
		if(isValidP1(d)) {
		    valid++;
		}
	    }

	    System.out.println("Part 1:");
	    System.out.println(valid + " passwords are valid.");

	    int valid2 = 0;
	    for(Data d : decoded) {
		if(isValidP2(d)) {
		    valid2++;
		}
	    }

	    System.out.println("Part 2:");
	    System.out.println(valid2 + " passwords are valid.");
	    
	}
	catch(FileNotFoundException e) {

	}
    }
}
