import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.lang.Integer;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ListIterator;
import java.io.IOException;

    
class Day1 {

    public static String[] SplitToLines(String a) {
	Scanner sc = new Scanner(a);

	LinkedList<String> lines = new LinkedList<String>();
	try {
	    while(true) {
		String nl = sc.nextLine();
		lines.addLast(nl);
	    }
	}
	catch (Exception e) {
	}

	int i = 0;
	String[] lineArray = new String[lines.size()];
	ListIterator<String> li = lines.listIterator(0);
	while(li.hasNext()) {
	    lineArray[i++] = li.next();
	}
	
	return lineArray;
    }

    public static String ReadWholeFile(String fp) throws FileNotFoundException {
	FileReader fr = new FileReader(fp);
	StringBuffer inbuf = new StringBuffer();	
	try{


	    int c = fr.read();
	    while(c != -1) {
		inbuf.append((char)c);
		c = fr.read();
	    }
	}
	catch(IOException e) {
	    System.out.println("Couldn't read file. \n");
	}

	return inbuf.toString();
    }
    
    public static void main(String args[]) {
	try {
	    String buf = ReadWholeFile("Data/Day1.txt");
	    String[] lines = SplitToLines(buf);

	    int[] il = new int[lines.length];
	    for(int i=0 ; i!=lines.length ; i++) {
		il[i] =  Integer.parseInt(lines[i]);
	    }

	    System.out.println("Part 1:");
	    for(int i=0 ; i != il.length ; i++) {
		for(int j=i ; j != il.length ; j++) {
		    if(i != j) {
			if(il[i] + il[j] == 2020) {
			    System.out.println("Sum of " + il[i] + " + " + il[j] + " is " + il[i]*il[j]);
			}
		    }
		}
	    }

	    System.out.println("Part 2:");
	    for(int i=0 ; i != il.length ; i++) {
		for(int j=i ; j != il.length ; j++) {
		    if(i != j) {
			for(int k=j ; k != il.length ; k++) {
			    if(k != i && k != j) {
				if(il[i] + il[j] + il[k] == 2020) {
				    System.out.println("Sum of " + il[i] + " + " + il[j] + " + " + il[k] + " is " + il[i]*il[j]*il[k]);
				}

			    }
			}
		    }

		}
	    }
	    
	}
	catch (FileNotFoundException e) {
	    System.err.println("Could not find file.");
	}

    }
}
