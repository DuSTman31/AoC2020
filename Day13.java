import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.lang.Integer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ListIterator;
import java.io.IOException;
import java.util.HashMap;
import java.math.BigInteger;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
    
class Day13 {
    private int startTime = 0;
    private int[] routeTimes;

    public void processInput() {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day13.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    startTime = Integer.parseInt(lines[0]);
	    Scanner sc = new Scanner(lines[1]);
	    sc.useDelimiter(",");

	    LinkedList<Integer> io = new LinkedList<Integer>();
	    while(sc.hasNext()) {
		String b = sc.next();
		if(b.compareTo("x") == 0) {
		    io.addLast(-1);
		}
		else {
		    io.addLast(new Integer(b));
		}
		
	    }

	    ListIterator<Integer> li = io.listIterator();
	    int noInts = 0;
	    while(li.hasNext()) {
		noInts++;
		li.next();
	    }

	    routeTimes = new int[noInts];
	    li = io.listIterator();
	    int ind = 0;
	    while(li.hasNext()) {
		routeTimes[ind++] = li.next().intValue();
	    }
	    
	}
	catch(FileNotFoundException e) {

	}
    }

    public int tas(int start, int period) {
	return ((((start/period) + 1)*period) - start);
    }
    
    public void Part1() {
	System.out.println("Part 1:");

	boolean hasFoundBus = false;
	int route = 0;
	long timeAfterStart = 0;
	for(int i : routeTimes) {
	    if(!hasFoundBus && i!=-1) {
		hasFoundBus = true;
		route = i;
		timeAfterStart = tas(startTime, i);
	    }
	    else {
		if(i!=-1) {
		    int newTas = tas(startTime, i);
		    if(newTas < timeAfterStart) {
			route = i;
			timeAfterStart = newTas;
		    }
		}
	    }
	}

	System.out.println("Soonest route after " + startTime + " is " + route);
	System.out.println("Solution : " + (route*timeAfterStart));
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
	BigInteger product = a.multiply(b);
	return product.divide(a.gcd(b));
    }
    
    private BigInteger searchNext(BigInteger start, BigInteger jump, int indToMatch, int[] routes, int[] routeOffset) {
	BigInteger s = start;
	while(true) {
	    BigInteger m = s.add(BigInteger.valueOf(routeOffset[indToMatch]));
	    if(m.mod(BigInteger.valueOf(routes[indToMatch])).equals(BigInteger.ZERO)){
		return s;
	    }
	    else {
		s = s.add(jump);
	    }
	}
    }
    
    public void Part2() {
	System.out.println("Part 2:");

	int noFixedRoutes = 0;
	for(int i : routeTimes) {
	    if(i!=-1) {
		noFixedRoutes++;
	    }
	}

	int[] fixedRoutes = new int[noFixedRoutes];
	int ind = 0;
	for(int i : routeTimes) {
	    if(i!=-1) {
	    fixedRoutes[ind++] = i;
	    }
	}

	Arrays.sort(fixedRoutes);
	for(int i=0 ; i<fixedRoutes.length/2 ; i++) {
	    int elem = fixedRoutes[i];
	    fixedRoutes[i] = fixedRoutes[fixedRoutes.length-1-i];
	    fixedRoutes[fixedRoutes.length-1-i] = elem;
	}

	//  Now that we've got our routes in descending order of
	// frequency, create a parallel array of how much after the
	// nominal timestamp the route is.
	int[] minutesAfter = new int[noFixedRoutes];
	for(int i=0 ; i!=fixedRoutes.length ; i++ ) {
	    for(int j=0 ; j!=routeTimes.length ; j++) {
		if(fixedRoutes[i] == routeTimes[j]) {
		    minutesAfter[i] = j;
		}
	    }
	}

	BigInteger start = BigInteger.valueOf(fixedRoutes[0]);
	start = start.subtract(BigInteger.valueOf(minutesAfter[0]));
	BigInteger jump = BigInteger.valueOf(fixedRoutes[0]);
	ind = 1;
	BigInteger t = BigInteger.ZERO;;
	while(ind < fixedRoutes.length){
	    t = searchNext(start, jump, ind, fixedRoutes, minutesAfter);
	    start = t;
	    jump = lcm(jump, BigInteger.valueOf(fixedRoutes[ind]));	    
	    ind++;
	}
	
	System.out.println(t.toString());
    }
    
    public static void main(String args[]) {
	Day13 nDay = new Day13();
	nDay.processInput();
	nDay.Part1();
	nDay.Part2();
    }
}
