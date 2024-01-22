package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Use this file to test deleteProduct

    int numActions = Integer.parseInt(StdIn.readLine()); // reads number of products from the input file
    // System.out.print(numProducts);

    Warehouse wares = new Warehouse();
    int counter = 0;


    while(counter < numActions){
        String action = StdIn.readString();
        if(action.equals("add")){ // adds anything new to the warehouse
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();

            wares.addProduct(id, name, stock, day, demand);
        }
        else if(action.equals("delete")){ // deletes item
            int id = StdIn.readInt();

            wares.deleteProduct(id);
        }

        counter++;
    }

    System.out.println(wares);
    }
}
