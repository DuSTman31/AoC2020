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
    
class Day4 {

    static class Field {
	public String key;
	public String val;

	public boolean isNumeric() {
	    Pattern np = Pattern.compile("^(\\d+)$");
	    Matcher m = np.matcher(val);

	    if(m.matches()) {
		return true;
	    }
	    else {
		return false;
	    }
	}

    }

    static class Record {
	private LinkedList<Field> mv = new LinkedList<Field>();

	public boolean addField(String key, String val) {
	    Field mf = new Field();
	    mf.key = key;
	    mf.val = val;

	    mv.addLast(mf);

	    return true;
	}

	
	public boolean valid() {
	    ListIterator<Field> fi = mv.listIterator();

	    boolean hasByr = false;
	    boolean hasIyr = false;
	    boolean hasEyr = false;
	    boolean hasHgt = false;
	    boolean hasHcl = false;
	    boolean hasEcl = false;
	    boolean hasPid = false;
	    boolean hasCid = false;
		
	    while(fi.hasNext()) {
		Field f = fi.next();
		
		if(f.key.compareTo("byr") == 0) {
		    hasByr = true;
		}
		if(f.key.compareTo("iyr") == 0) {
		    hasIyr = true;
		}
		if(f.key.compareTo("eyr") == 0) {
		    hasEyr = true;
		}
		if(f.key.compareTo("hgt") == 0) {
		    hasHgt = true;
		}
		if(f.key.compareTo("hcl") == 0) {
		    hasHcl = true;
		}
		if(f.key.compareTo("ecl") == 0) {
		    hasEcl = true;
		}
		if(f.key.compareTo("pid") == 0) {
		    hasPid = true;
		}
		if(f.key.compareTo("cid") == 0) {
		    hasCid = true;
		}
	    }

	    if(hasByr && hasIyr && hasEyr && hasHgt && hasHcl && hasEcl && hasPid) {
		return true;
	    }
	    else {
		return false;
	    }
	}

	public boolean valid2() {
	    ListIterator<Field> fi = mv.listIterator();

	    boolean hasByr = false;
	    boolean hasIyr = false;
	    boolean hasEyr = false;
	    boolean hasHgt = false;
	    boolean hasHcl = false;
	    boolean hasEcl = false;
	    boolean hasPid = false;
	    boolean hasCid = false;
		
	    while(fi.hasNext()) {
		Field f = fi.next();
		
		if(f.key.compareTo("byr") == 0) {
		    if(f.isNumeric()) {
			int p = Integer.parseInt(f.val);
			if(p >= 1920 && p <= 2002) {
			    hasByr = true;
			}
		    }
		}
		if(f.key.compareTo("iyr") == 0) {
		    if(f.isNumeric()) {
			int p = Integer.parseInt(f.val);
			if(p >= 2010 && p <= 2020) {
			    hasIyr = true;
			}
			
		    }

		}
		if(f.key.compareTo("eyr") == 0) {
		    if(f.isNumeric()) {
			int p = Integer.parseInt(f.val);
			if(p >= 2020 && p <= 2030) {
			    hasEyr = true;
			}
		    }
		}
		if(f.key.compareTo("hgt") == 0) {
		    Pattern hgtPattern = Pattern.compile("^(\\d+)(cm|in)$");
		    Matcher hgtMatch = hgtPattern.matcher(f.val);
		    if(hgtMatch.matches()) {
			if(hgtMatch.group(2).compareTo("cm") == 0) {
			    int i = Integer.parseInt(hgtMatch.group(1));
			    if(i>=150 && i<=193) {
				hasHgt = true;
			    }
			}
			else if(hgtMatch.group(2).compareTo("in") == 0) {
			    int i = Integer.parseInt(hgtMatch.group(1));
			    if(i>=59 && i<=76) {
				hasHgt = true;
			    }
			}
			
		    }
		}
		if(f.key.compareTo("hcl") == 0) {
		    Pattern hclPattern = Pattern.compile("^#([0-9a-f]{6})$");
		    Matcher hclMatch = hclPattern.matcher(f.val);
		    if(hclMatch.matches()) {
			hasHcl = true;
		    }
		}
		if(f.key.compareTo("ecl") == 0) {
		    Pattern eclPattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
		    Matcher eclMatch = eclPattern.matcher(f.val);
		    if(eclMatch.matches()) {
			hasEcl = true;
		    }
		}
		if(f.key.compareTo("pid") == 0) {
		    Pattern pidPattern = Pattern.compile("^\\d{9}$");
		    Matcher pidMatch = pidPattern.matcher(f.val);
		    if(pidMatch.matches()) {
			hasPid = true;
		    }
		}
		if(f.key.compareTo("cid") == 0) {
		    hasCid = true;
		}
	    }

	    if(hasByr && hasIyr && hasEyr && hasHgt && hasHcl && hasEcl && hasPid) {
		return true;
	    }
	    else {
		return false;
	    }
	}
    }
   
    public static void main(String args[]) {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day4.txt");
	    String[] lines = Lib.SplitToLines(buf);

	    Pattern bp = Pattern.compile("^(\\s*)$"); //Match a blank line.
	    Pattern p = Pattern.compile("(\\S+):(\\S+)"); //Match a key:value pair.
	    Record nr = new Record();
	    LinkedList<Record> recs = new LinkedList<Record>();
	    for(String line : lines) {
		Matcher m = bp.matcher(line);

		if(m.matches()) {
		    recs.addLast(nr);
		    nr = new Record();
		    System.out.println("New record.");
		}
		else {
		    Matcher sp = p.matcher(line);

		    while(sp.find()) {
			nr.addField(sp.group(1), sp.group(2));
			System.out.println("added : " + sp.group(1) + " : " + sp.group(2));
		    }
		}

	    }
	    recs.addLast(nr);
	    

	    int noValid = 0;
	    ListIterator<Record> ri = recs.listIterator(0);
	    while(ri.hasNext()) {
		Record r = ri.next();

		if(r.valid()) {
		    noValid++;
		}
	    }

	    System.out.println("Part 1:");
	    System.out.println("No valid passports : "  + noValid);
	    
	    int noValid2 = 0;
	    ListIterator<Record> ri2 = recs.listIterator(0);
	    while(ri2.hasNext()) {
		Record r = ri2.next();

		if(r.valid2()) {
		    noValid2++;
		}
	    }

	    System.out.println("Part 2:");
	    System.out.println("No valid passports : "  + noValid2);
	    
	}
	catch(FileNotFoundException e) {

	}
    }
}
