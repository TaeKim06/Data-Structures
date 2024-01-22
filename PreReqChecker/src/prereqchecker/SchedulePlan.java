package prereqchecker;

import java.util.*;

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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

        // // constructs the hashmap
        // HashMap<String, ArrayList<String>> myMap = new HashMap<>();
        // HelperClass helper = new HelperClass();

        // myMap = helper.makeMyMap(args[0]);
        // myMap = helper.addPreReqs(args[0]);
        // StdIn.setFile(args[1]);
        // StdOut.setFile(args[2]);


        // // Scheduleplan starts
        // // find information from the file
        // String targetCourse = StdIn.readLine();
        // int numCourses = Integer.parseInt(StdIn.readLine());


        // // taken courses are added to a collection
        // ArrayList<String> takenCourses = new ArrayList<>();
        // for(int i = 0; i < numCourses; i++){
        //     takenCourses.add(StdIn.readLine());
        // }


        // HashMap<Integer, HashSet<String>> holder = new HashMap<>();
        // HashSet<String> holder2 = new HashSet<>();

        // holder = helper.planSchedule(targetCourse, takenCourses);


        // StdOut.println(holder.size());
        // for(int x: holder.keySet()){
        //     holder2 = holder.get(x);
        //     for(String y: holder2){
        //         StdOut.print(y + " ");
        //     }
        //     StdOut.println();
        // }
    }
}
