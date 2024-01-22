package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Use this file to test purchaseProduct

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
        else if(action.equals("purchase")){ // purchases item and updates quantities and demand etc
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            int amount = StdIn.readInt();

            wares.purchaseProduct(id, day, amount);
        }

        counter++;
    }

    System.out.println(wares);
    

    }
}
