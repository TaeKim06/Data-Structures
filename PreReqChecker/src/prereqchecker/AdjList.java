package prereqchecker;

import java.util.*;
import prereqchecker.StdIn;
import prereqchecker.StdOut;
import prereqchecker.HelperClass;


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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }


        HashMap<String, ArrayList<String>> myMap = new HashMap<>();
        HelperClass helper = new HelperClass();

        myMap = helper.makeMyMap(args[0]);
        myMap = helper.addPreReqs(args[0]);

        StdOut.setFile(args[1]);
        for(String x: myMap.keySet()){
            StdOut.print(x + " ");
            for(int i = 0; i < myMap.get(x).size(); i++){
                ArrayList<String> holder = myMap.get(x);
                StdOut.print(holder.get(i) + " ");
            }
            StdOut.println();
        }

        // int classes = Integer.parseInt(StdIn.readLine());
    }
}
