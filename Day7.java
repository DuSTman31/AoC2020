import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.lang.Integer;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ListIterator;
import java.io.IOException;
import java.util.HashMap;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
    
class Day7 {
    private Rule[] rules;
    private HashMap<String, Rule> ruleMap;
    private int highMark = 0;
    
    private class RulePart{
	public RulePart(String bt, int no) {
	    bagType = bt;
	    noBags = no;
	}

	public String getBagType() {
	    return bagType;
	}

	public int getNoBags() {
	    return noBags;
	}

	public void print() {
	    System.out.println("  " + noBags + " : " + bagType);
	}

	private String bagType;
	private int noBags;
    }
    
    private class Rule{
	public Rule(String bt) {
	    bagType = bt;
	}

	public void addContainedType(String cont, int no) {
	    contents.addLast(new RulePart(cont, no));
	}

	public void print() {
	    System.out.println("A: " + bagType);

	    ListIterator<RulePart> ls = contents.listIterator();
	    while(ls.hasNext()) {
		ls.next().print();
	    }
	}

	public String getBagType() {
	    return bagType;
	}

	public void addPotentialContainer(Rule r) {
	    canContain.addLast(r);
	}

	public void associateContainers(HashMap<String, Rule> ruleSet) {
	    ListIterator<RulePart> li = contents.listIterator();
	    while(li.hasNext()) {
		RulePart rp = li.next();

		if(ruleSet.containsKey(rp.getBagType())) {
		    Rule r = ruleSet.get(rp.getBagType());

		    r.addPotentialContainer(this);
		}
	    }
	}

	private int countContainingBags(int m) {
	    if(mark == m) {
		return 0;
	    }
	    else {
		mark = m;
		int tot = 1;

		ListIterator<Rule> li = canContain.listIterator();
		while(li.hasNext()) {
		    tot += li.next().countContainingBags(m);
		}
		return tot;
	    }
		
	}
	
	public int countContainingBags() {
	    int newMark = ++highMark;

	    return countContainingBags(newMark)-1;
	}

	private int countContainedBags_int() {
	    int total = 1;
	    ListIterator<RulePart> li = contents.listIterator();
	    while(li.hasNext()) {
		RulePart rp = li.next();

		total += (rp.getNoBags() * ruleMap.get(rp.getBagType()).countContainedBags_int());
	    }

	    return total;
	}

	public int countContainedBags() {
	    return countContainedBags_int()-1; // Don't include current bag in count.
	}
	
	private LinkedList<Rule> canContain = new LinkedList<Rule>();
	private LinkedList<RulePart> contents = new LinkedList<RulePart>();
	private String bagType;
	private int mark = 0;
    }

    public void processInput() {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day7.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    // Go through the input generating an initial list of Rules.
	    LinkedList<Rule> bagRules = new LinkedList<Rule>();
	    Pattern lineRe = Pattern.compile("^(.*?) bags contain(.*)\\.$");
	    Pattern addBagRe = Pattern.compile("(\\d+) (.+?) bag");
	    for(String line : lines) {
		Matcher lineMatch = lineRe.matcher(line);

		if(lineMatch.matches()) {
		    Rule nDat = new Rule(lineMatch.group(1));
		    bagRules.addLast(nDat);
		    Matcher addBagMatch = addBagRe.matcher(lineMatch.group(2));

		    while(addBagMatch.find()) {
			nDat.addContainedType(addBagMatch.group(2), Integer.parseInt(addBagMatch.group(1)));
		    }
		    
		}
	    }

	    int noRules = 0;
	    ListIterator<Rule> li = bagRules.listIterator();
	    while(li.hasNext()) {
		li.next();
		noRules++;
	    }

	    rules = new Rule[noRules];
	    li = bagRules.listIterator();
	    int pos = 0;
	    while(li.hasNext()) {
		rules[pos++] = li.next();
	    }
	    
	}
	catch(FileNotFoundException e) {

	}

    }
    
    public void Part1() {
	System.out.println("Part 1:");
	processInput();
	    
	// Generate a mapping of bag names to the rule about what they can hold.
	ruleMap = new HashMap<String, Rule>();
	for(Rule r : rules) {
	    ruleMap.put(r.getBagType(), r);
	}


	for(Rule r : rules) {
	    r.associateContainers(ruleMap);
	}
	    

	// Find Shiny gold.
	if(ruleMap.containsKey("shiny gold")) {
	    Rule base = ruleMap.get("shiny gold");

	    
	    System.out.println(base.countContainingBags() + " bags can indirectly contain a shiny gold bag.");
	}
	    
    }

    public void Part2() {
	System.out.println("Part 2:");
	if(ruleMap.containsKey("shiny gold")) {
	    Rule base = ruleMap.get("shiny gold");
	    System.out.println(base.countContainedBags() + " bags are needed to fill a shiny gold bag.");
	}
	
    }
    
    public static void main(String args[]) {
	Day7 nDay = new Day7();
	nDay.Part1();
	nDay.Part2();
    }
}
