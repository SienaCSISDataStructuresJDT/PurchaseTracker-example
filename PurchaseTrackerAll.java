/**
 * Example PurchaseTrackerAll: test out a class that stores items
 * purchased and their quantities, this time remembering all
 * of the items we read in and determining the "winners" at the end.
 *
 * Jim Teresco, The College of Saint Rose, CSC 523, Summer 2014
 * Updated for CSIS 210, Siena College, Fall 2018
 *
 * @version Fall 2019
 */

import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseTrackerAll {

    /**
     A private method to prompt for and read in a valid item from the
     given Scanner, returning a PurchasedItem object, returns null if
     no item is entered.  The existence of this method avoids some
     repeated code in main and also would be able to be used for any
     Scanner, not just ones based on the standard input, and would be
     a convenient place to add error checking on the input values (for
     example, ensure positive values for price and quantity or the
     addition of some exception handling to recover from invalid
     inputs).

     @param s A Scanner from which to read an item's information
     @return a PurchasedItem object representing that item.
    */
    private static PurchasedItem readPurchasedItem(Scanner s) {

        System.out.println("Enter the item name, unit price, and quantity purchased, \"done\" to stop:");
        String itemName = s.next();
        // if someone typed "done", we return null and don't try to read the other fields
        if (itemName.equals("done")) return null;
        double unitPrice = s.nextDouble();
        int quantity = s.nextInt();

        return new PurchasedItem(itemName, unitPrice, quantity);
    }

    /**
       Read a series of information about purchased items, and print
       the ones with the most, least expensive items, and the most and
       fewest numbers purchased.  

       @param args not used
    */
    public static void main(String[] args) {

        // create a keyboard Scanner to read lines that describe the items
        Scanner keyboard = new Scanner(System.in);

        // create an ArrayList of PurchasedItem objects to store all of them
        ArrayList<PurchasedItem> items = new ArrayList<PurchasedItem>();

        PurchasedItem item;
        // now read them all in
        do {
            item = readPurchasedItem(keyboard);
            if (item != null) {
                items.add(item);
            }
        } while (item != null);

        // if there was no valid item entered, we just quit
        if (items.isEmpty()) {
            System.out.println("No valid item purchases entered, exiting...");
            System.exit(0);
        }

        // so we know we have one valid item in the ArrayList, so we
        // initialize the "leader" in each category to be the first item (position 0)
        item = items.get(0);
        PurchasedItem leastExpensiveItem = item;
        PurchasedItem mostExpensiveItem = item;
        PurchasedItem largestQuantity = item;
        PurchasedItem highestTotalCost = item;

        System.out.println("All items:");
        System.out.println(item);

        // now we can loop over the remaining, printing each and deciding
        // which are the "winners"
        for (int itemNum = 1; itemNum < items.size(); itemNum++) {

            item = items.get(itemNum);
            System.out.println(item);
            // do we have any new leaders?
            if (item.getUnitPrice() < leastExpensiveItem.getUnitPrice()) {
                leastExpensiveItem = item;
            }
            if (item.getUnitPrice() > mostExpensiveItem.getUnitPrice()) {
                mostExpensiveItem = item;
            }
            if (item.getQuantity() > largestQuantity.getQuantity()) {
                largestQuantity = item;
            }
            if (item.getTotalCost() > highestTotalCost.getTotalCost()) {
                highestTotalCost = item;
            }

        }

        // now report our winners:
        System.out.println("Least expensive item:    " + leastExpensiveItem);
        System.out.println("Most expensive item:     " + mostExpensiveItem);
        System.out.println("Largest quantity item:   " + largestQuantity);
        System.out.println("Highest total cost item: " + highestTotalCost);

    }
}
