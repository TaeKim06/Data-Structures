package prereqchecker;

import java.util.*;
import prereqchecker.StdIn;
import prereqchecker.StdOut;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }

        // constructs the hashmap
        HashMap<String, ArrayList<String>> myMap = new HashMap<>();
        HelperClass helper = new HelperClass();

        myMap = helper.makeMyMap(args[0]);
        myMap = helper.addPreReqs(args[0]);

        // Start of ValidPrereq
        StdIn.setFile(args[1]);
        String course1 = StdIn.readString();
        String course2 = StdIn.readString();
        
        

        StdOut.setFile(args[2]);
        
        if(!helper.findReqs(course2).contains(course1)){     StdOut.print("YES");        }
        else{       StdOut.print("NO");        }

    }
}
