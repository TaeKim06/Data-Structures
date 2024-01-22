package prereqchecker;

import java.util.*;

import javax.sound.midi.MidiSystem;

import prereqchecker.StdIn;
import prereqchecker.StdOut;


public class HelperClass {
    private HashMap<String, ArrayList<String>> myMap;
    private HashMap<String, Boolean> myMapBools;
    // private HashSet<String> deepCopy;



    // public static void main(String[] args) {

    // }

    public HashMap makeMyMap(String inputFile){
        HashMap<String, ArrayList<String>> classesList = new HashMap<>();
        HashMap<String, Boolean> boolList = new HashMap<>();

        myMap = classesList;
        myMapBools = boolList;

        StdIn.setFile(inputFile);
        int n = Integer.parseInt(StdIn.readLine());
        for(int i = 0; i < n; i++){
            String key = StdIn.readLine();
            ArrayList<String> emptyList = new ArrayList<>();
            myMap.put(key, emptyList);
            myMapBools.put((key), false);
        }

        return myMap;
    }

    public HashMap addPreReqs(String inputFile){

        // StdIn.setFile(inputFile);
        int classes = StdIn.readInt();
        // System.out.print(classes);

        for(int i = 0; i < classes; i++){
            String key = StdIn.readString();
            String courseID = StdIn.readString();
            myMap.get(key).add(courseID);
        }
            // myMap.get(key);
        return myMap;
    }


    // finds the required prereqs for the given course ID
    public HashSet findReqs(String courseID){
        HashSet<String> mySet = new HashSet<>();
        
        // depth first search
        DFS(courseID, mySet);
        return mySet;

        /* for(int i = 0; i < myMap.get(course).size(); i++){
        //     mySet.add(myMap.get(course).get(i));
        }*/ 
    }

    public void DFS(String courseID, HashSet<String> mySet){
        
        // reset all bools in hashmap to false
        for(String x: myMapBools.keySet()){
            myMapBools.put(x, false);
        }
             

        // find new prereqs and add them to the hashset and then go through the map recursively
        for(int i = 0; i < myMap.get(courseID).size(); i++){
            if(myMapBools.get(courseID) == false){
                mySet.add(myMap.get(courseID).get(i));
                DFS(myMap.get(courseID).get(i), mySet);
            }            
        }
        myMapBools.put(courseID, true);
    }


    // returns a hashset of all the courses that have been taken according to an arraylist of taken courses
    public HashSet findTaken(ArrayList<String> myList){
        HashSet<String> mySet = new HashSet<>();
        HashSet<String> holder = new HashSet<>();
        
        for(int i = 0; i < myList.size(); i++){
            String courseID = myList.get(i);
            mySet.add(courseID);
            holder = findReqs(courseID);
            for(String x: holder){
                mySet.add(x);
            }
        }
        return mySet;
    }


    // Checks if all the courseID's prereqs have been satisfied in the coursesTaken hashset
    // returns true if eligible and false if not 
    public boolean findEligible(String CourseID, HashSet coursesTaken){
        int counter = 0;

        if(coursesTaken.contains(CourseID) == false){
            for(int i = 0; i < myMap.get(CourseID).size(); i++){
                String preReq = myMap.get(CourseID).get(i);
                if(coursesTaken.contains(preReq)){
                    counter++;
                }
            }
            if(counter == myMap.get(CourseID).size()){
                return true;
            }
        }
        return false;
    }


    // finds the courses needed to fulfill the prereqs to take a target course
    public HashSet findNeedToTake(String courseID, ArrayList<String> allTakenCourses){

        // instatiate hashsets
        HashSet<String> taken = new HashSet<>();
        HashSet<String> needToTake = new HashSet<>();
        HashSet<String> holder = new HashSet<>();
        

        // create hashset of all courses taken
        taken = findTaken(allTakenCourses);


        // create hashset of all prereqs required for target class
        for(int i = 0; i < myMap.get(courseID).size(); i++){
            String preReqID = myMap.get(courseID).get(i);
            needToTake.add(preReqID);
            holder = findReqs(preReqID);
            for(String x: holder){
                needToTake.add(x);
            }
        }

        needToTake.removeAll(taken);

        return needToTake;
    }


    // public HashMap planSchedule(String targetCourse, ArrayList<String> takenCourses){

        // // hashset for courses you need to take
        // HashSet<String> requiredCourses = new HashSet<>();
        // requiredCourses = findNeedToTake(targetCourse, takenCourses);
        // // hashset for courses for all prereq courses needed for the target course
        // HashSet<String> prereqCourses = new HashSet<>();
        // // hashset to hold courses you can take in a semester
        // HashSet<String> coursesInSemester = new HashSet<>();
        // // Hashmap key will be the semester number starting at 1
        // // data is a hashsset holding the courseID of all the courses you can take that semester
        // HashMap<Integer, HashSet<String>> allPreReqCourses = new HashMap<>();
        // int semesterCount = 1;
        
        
        // while(!requiredCourses.isEmpty()){
        //     for(String x: requiredCourses){
        //         prereqCourses = findNeedToTake(x, takenCourses);
        //         if(prereqCourses.isEmpty()){
        //             coursesInSemester.add(x);
        //         }
        //     }
        //     if(!coursesInSemester.isEmpty()){
        //         HashSet<String> deepCopy = new HashSet<>();
        //         for(String x: coursesInSemester){
        //             deepCopy.add(x);
        //         }
        //         allPreReqCourses.put(semesterCount, deepCopy);
        //         semesterCount++;
        //         requiredCourses.removeAll(coursesInSemester);
        //         for(String x: deepCopy){
        //             takenCourses.add(x);
        //             coursesInSemester.clear();
        //         }
        //     }

        // }

        // // StdOut.println(allPreReqCourses.size());
        // // for(int x: allPreReqCourses.keySet()){
        // //     for(int i = 0; i < allPreReqCourses.get(x).size(); i++){
        // //         StdOut.print(allPreReqCourses.get(x).get(i) + " ");
        // //         StdOut.println();
        // //     }
        // // }


        // return allPreReqCourses;


        

    // }

}