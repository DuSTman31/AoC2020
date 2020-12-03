import java.io.FileReader;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.lang.Integer;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Vector;
import java.util.ListIterator;
import java.io.IOException;

    
class Lib {

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
    

}
