package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        StdIn.setFile(fileName);

	/* Your code goes here */

    ArrayList<CharFreq> base = new ArrayList<CharFreq>(128);
        double length = 0.0;
        Boolean newChar = true;
 
 
       
        while(StdIn.hasNextChar()){
            char holder = StdIn.readChar();
            newChar = true;
            length++;
            for(int i = 0; i < base.size(); i++){
                if(holder == (base.get(i)).getCharacter()){
                    (base.get(i)).setProbOcc((base.get(i)).getProbOcc() + 1);
                    newChar = false;
                }
            }
            if(newChar == true){
                CharFreq temp = new CharFreq(holder, 1);
                base.add(temp);
            }
        }
        // System.out.println(base.size());
        for(int i = 0; i < base.size(); i++){
           
            (base.get(i)).setProbOcc(((base.get(i)).getProbOcc() / length));
            // System.out.println(prob);
        }
       

        if(base.size() == 1){
            int nextValue = (int)base.get(0).getCharacter() + 1;
            char c = (char)nextValue;
            CharFreq nullProb = new CharFreq(c, 0);
            base.add(nullProb);
        }

        Collections.sort(base);
        this.sortedCharFreqList = base;

    }

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

	/* Your code goes here */

        // initializing 3 queues of tree nodes
        Queue<TreeNode> source = new Queue<TreeNode>();
        Queue<TreeNode> target = new Queue<TreeNode>();
        Queue<TreeNode> dequeued = new Queue<TreeNode>();



        // System.out.print(sortedCharFreqList.size());   

        for(int i = 0; i < sortedCharFreqList.size() ; i++){
            TreeNode newNode = new TreeNode();
            newNode.setData(sortedCharFreqList.get(i));
            source.enqueue(newNode);
            // (sortedCharFreqList.get(i)
            // System.out.print(source.dequeue().getData().getCharacter());   
        }

        // source = getSortedCharFreqList();

        while(source.isEmpty() == false || target.size() != 1){
            while(dequeued.size() < 2){   // dequeued needs 2 elements to proceed with the rest of the outer while
                if(target.isEmpty() && !source.isEmpty()){
                    dequeued.enqueue(source.dequeue());
                }
                else if(!source.isEmpty() && !target.isEmpty()){
                    if(source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()){  
                        dequeued.enqueue(source.dequeue());
                    }
                    else {  //if(source.peek().getData().getProbOcc() > target.peek().getData().getProbOcc())
                        dequeued.enqueue(target.dequeue());
                    }
                }
                else if(source.isEmpty()){
                    dequeued.enqueue(target.dequeue());
                }
            }

            TreeNode smallNode = new TreeNode();
            TreeNode smallerNode = new TreeNode();

            // checks for empty queue but if not sets smallerNode to the smallest node
            if(dequeued.isEmpty()){
                smallerNode = null;
            }
            else{
                smallerNode = dequeued.dequeue();
            }

            // checks for empty queue but if not sets smallerNode to the second smallest node
            if(dequeued.isEmpty()){
                smallNode = null;
            }
            else{
                smallNode = dequeued.dequeue();
            }


            double prob1 = smallerNode.getData().getProbOcc();
            double prob2 = smallNode.getData().getProbOcc();

            CharFreq temp = new CharFreq(null, prob1 + prob2);
            TreeNode holder = new TreeNode(temp, null, null);

            holder.setLeft(smallerNode);
            holder.setRight(smallNode);
            target.enqueue(holder);

            // creates top branch for the time being
            
            // huffmanRoot = holder;
            
        }
        // dequeued.enqueue(target.dequeue());
        // target.dequeue();
        // huffmanRoot = dequeued.peek();
        
        huffmanRoot = target.dequeue();

    }
    /**
        // TreeNode first = new TreeNode();
        // TreeNode ptr = new TreeNode();
    //     for(int i = 0; i < sortedCharFreqList.size(); i++){
    //         TreeNode newNode = new TreeNode();
    //         newNode.setData((sortedCharFreqList.get(i)));
    //         source.enqueue(newNode);
    //         // (sortedCharFreqList.get(i)
    //         // System.out.print(newNode.getData().getCharacter());   
    //     }
    //     TreeNode top = new TreeNode();
    //     top = createTree(source, target);
    //     System.out.print(source.size());
    //     huffmanRoot = top;
    

    // public TreeNode smallestNode(Queue<TreeNode> source, Queue<TreeNode> target){
    //     TreeNode small = new TreeNode();
    //     TreeNode holder1 = new TreeNode();
    //     TreeNode holder2 = new TreeNode();
    //     CharFreq charHolder1 = new CharFreq();
    //     CharFreq charHolder2 = new CharFreq();

    //     if(source.size() == 0){
    //         small = target.peek();
    //         return small;
    //     }
    //     // else{
    //     //     return ;
    //     // }

    //     holder1 = source.peek();
    //     holder2 = target.peek();
    //     charHolder1 = holder1.getdata();
    //     charHolder2 = holder2.getdata();
    //     if(charHolder1.getProbOcc() <= charHolder2.getProbOcc()){
    //         small = source.peek();
    //         source.dequeue();
    //     }
    //     else{
    //         small = target.peek();
    //         target.dequeue();
    //     }

    //     return small;
    // }


    // public TreeNode createTree(Queue<TreeNode> source, Queue<TreeNode> target){
    //     if(source.isEmpty() && target.size() == 1){
    //         return target;
    //     }

    //     TreeNode dequeuedOne = new TreeNode();
    //     TreeNode dequeuedTwo = new TreeNode();

    //     dequeuedOne = smallestNode(source, target);
    //     dequeuedTwo = smallestNode(source, target);

    //     double sumProb = dequeuedOne.getdata().getProbOcc() + dequeuedTwo.getdata().getProbOcc();

    //     TreeNode newNode = new TreeNode();
    //     CharFreq branch = new CharFreq();
    //     branch.setProbOcc(sumProb);
    //     newNode.setData(branch);
    //     newNode.setLeft(dequeuedOne);
    //     newNode.setRight(dequeuedTwo);

    //     target.enqueue(newNode);

    //     return(createTree(source, target));




    // }
     */


    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {

	/* Your code goes here */
        String[] strArray = new String[128];
        TreeNode ptr = huffmanRoot;
        String bitString = "";

        travel(strArray, huffmanRoot, bitString);

        encodings = strArray;

    }

    private void travel(String[] strArray, TreeNode ptr, String bitString){

        int holder = 0;
        if(ptr.getData().getCharacter() != null){
            holder = (int)ptr.getData().getCharacter();
            strArray[holder] = bitString;
        }
        

        // base case
        if(ptr.getLeft() == null && ptr.getRight() == null){
            return;
        }

        // checks for left branches
        if(ptr.getLeft() != null){
            travel(strArray, ptr.getLeft(), bitString + "0");
        }

        // checks for right branches
        if(ptr.getRight() != null){
            travel(strArray, ptr.getRight(), bitString + "1");
        }
    }

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);

	/* Your code goes here */

        String[] strArray = encodings;
        String holder = "";
        int index = 0;
        while(StdIn.hasNextChar()){
            char nextChar = StdIn.readChar();
            String s = encodings[nextChar];
            
            for(int i = 0; i < strArray.length; i++){
                if(s == strArray[i]){
                    index = i;
                }
            }
            holder += strArray[index];
        }

        writeBitString(encodedFile, holder);

    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);

	/* Your code goes here */

        String encodedString = readBitString(encodedFile);

        String decodedString = decoder(encodedString);

        StdOut.print(decodedString);

    }

    private String decoder(String encodedString){
        TreeNode ptr = new TreeNode();
        ptr = huffmanRoot;
        int charIndex = 0;
        String decodedString = "";
        String enc = encodedString;
        // System.out.print(enc);

        while(charIndex < enc.length()){
            while(ptr.getData().getCharacter() == null){
                String holder = "" + enc.charAt(charIndex);
                if(holder.equals("0")){
                    ptr = ptr.getLeft();
                }
                else if(holder.equals("1")){
                    ptr = ptr.getRight();
                }
                charIndex++;
            }

            decodedString = decodedString + ptr.getData().getCharacter();
            // System.out.print(decodedString);
            ptr = huffmanRoot;
        }
        return decodedString;
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
