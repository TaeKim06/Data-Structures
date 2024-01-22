package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        
        // Use this file to test betterAddProduct

        int numProducts = Integer.parseInt(StdIn.readLine()); // reads number of products from the input file
        // System.out.print(numProducts);

        Warehouse wares = new Warehouse();
        int counter = 0;

        while(counter < numProducts){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();

            wares.betterAddProduct(id, name, stock, day, demand);

            counter++;
        }


        System.out.println(wares);

    }
}
