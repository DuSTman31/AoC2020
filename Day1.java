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
    
    public static void main(String args[]) {
	try {
	    String buf = Lib.ReadWholeFile("Data/Day1.txt");
	    String[] lines = Lib.SplitToLines(buf);

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
