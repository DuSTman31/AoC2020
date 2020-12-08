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
    
class Day6 {

    static class Group{
	public Group(){
	    for(int i=0 ; i!=26 ; i++) {
		hasAnswer[i] = false;
		allAnswered[i] = true;
	    }
	}
	
	public void addLine(String a) {
	    boolean[] answered = new boolean[26];
	    for(int i=0 ; i!=a.length() ; i++) {
		char c = a.charAt(i);
		hasAnswer[(int)c - (int)'a'] = true;
		answered[(int)c - (int)'a'] = true;
	    }

	    for(int i=0 ; i!=26 ; i++) {
		if(!answered[i]) {
		    allAnswered[i] = false;
		}
	    }
	}

	public int countAnsweredQuestions() {
	    int tot = 0;

	    for(boolean b : hasAnswer) {
		if(b) {
		    tot++;
		}
	    }

	    return tot;
	}

	public int countAllAnswered() {
	    int tot = 0;

	    for(boolean a : allAnswered) {
		if(a) {
		    tot++;
		}
	    }
	    
	    return tot;
	}

	private boolean[] hasAnswer = new boolean[26];
	private boolean[] allAnswered = new boolean[26];
    }
   
    public static void main(String args[]) {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day6.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    Pattern wsPat = Pattern.compile("^(\\s*)$");
	    LinkedList<Group> groups = new LinkedList<Group>();
	    
	    boolean lineInGroup = false;
	    Group g = new Group();
	    for(String line : lines) {
		Matcher wsMatch = wsPat.matcher(line);

		if(wsMatch.matches()) {
		    if(lineInGroup) {
			groups.addLast(g);
			g = new Group();
			lineInGroup = false;
		    }
		}
		else {
		    g.addLine(line);
		    lineInGroup = true;
		}
	    }
	    if(lineInGroup) {
		groups.addLast(g);
	    }

    	    System.out.println("Part 1:");
	    int total = 0;
	    ListIterator<Group> gi = groups.listIterator();
	    while(gi.hasNext()) {
		Group group = gi.next();

		total += group.countAnsweredQuestions();
	    }


	    System.out.println(total);

	    System.out.println("Part 2:");

	    gi = groups.listIterator();
	    int total2 = 0;
	    while(gi.hasNext()) {
		Group group = gi.next();

		total2 += group.countAllAnswered();
	    }

	    System.out.println(total2);
	}
	catch(FileNotFoundException e) {

	}
    }
}
