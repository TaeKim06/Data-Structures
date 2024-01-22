package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

        // constructs the hashmap
        HashMap<String, ArrayList<String>> myMap = new HashMap<>();
        HelperClass helper = new HelperClass();

        myMap = helper.makeMyMap(args[0]);
        myMap = helper.addPreReqs(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);


        // Eligible method Starts
        ArrayList<String> inputs = new ArrayList<>();
        int num = Integer.parseInt(StdIn.readLine());

        for(int i = 0; i < num; i++){
            inputs.add(StdIn.readLine());
        }

        // instantiate hashset and find the eligible courses
        HashSet<String> mySet = new HashSet<>();
        mySet = helper.findTaken(inputs);
        
        for(int i = 0; i < num; i++){
            mySet.add(inputs.get(i));
        }

        for(String x: myMap.keySet()){
            if(helper.findEligible(x, mySet)){
                StdOut.println(x);
            }
        }

        //tester for printing the hashset
        // for(String x: mySet){
        //         StdOut.println(x);
        // }
    }
}
