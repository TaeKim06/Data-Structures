package warehouse;

// import huffman.StdIn;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
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

            wares.addProduct(id, name, stock, day, demand);

            counter++;
        }


        System.out.println(wares);
	// Use this file to test addProduct


    }
}
