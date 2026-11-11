package inventory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Arrays, Lists and Maps lab: the stockroom index of a small shop.
 * The shop tracks its products as two parallel arrays: skus[i] names a
 * product and counts[i] says how many are on the shelf. Complete the methods
 * marked TODO, one task at a time; together they become the shop's lookup
 * tool. The public tests call each method with several inputs, including edge
 * cases, and check only the results. The README lists the steps for every
 * task. main is complete: it prints one line per finished task.
 */
public class Inventory {

    // Task 1 (arrays): the total number of items on the shelf, summed over
    // the whole array with an enhanced for loop. totalStock of {3, 0, 5} is 8.
    static int totalStock(int[] counts) {
        return -1; // TODO: sum the array with an enhanced for loop
    }

    // Task 2 (arrays, two indexes): reverse the array in place, swapping the
    // first and last elements with two indexes that move toward each other.
    // The method returns nothing: it changes the caller's array.
    static void reverseInPlace(int[] counts) {
        // TODO: swap elements with two indexes until they meet or cross
    }

    // Task 3 (ArrayList): the skus whose count is below threshold, collected
    // into an ArrayList in shelf order. An array cannot grow while you find
    // them; a list can, which is why this method returns one.
    // reorderList({"tea", "mug"}, {2, 9}, 3) is ["tea"].
    static ArrayList<String> reorderList(String[] skus, int[] counts, int threshold) {
        return null; // TODO: create an ArrayList, add each sku that is low, return it
    }

    // Task 4 (HashMap): an index from sku to count, so lookups stop walking
    // the arrays. Put every pair skus[i] -> counts[i] into a HashMap.
    static HashMap<String, Integer> buildIndex(String[] skus, int[] counts) {
        return null; // TODO: create a HashMap and put every pair into it
    }

    // Task 5 (HashMap, missing keys): the count for sku in the index, or 0
    // when the sku is not in the map at all. No null may escape this method;
    // getOrDefault handles the missing key in one call.
    static int stockOf(HashMap<String, Integer> index, String sku) {
        return -1; // TODO: read the count with getOrDefault
    }

    // Task 6 (linear search): the index of target in skus, or -1 when it is
    // not there. Walk the array from the front with one index.
    static int findSku(String[] skus, String target) {
        return -2; // TODO: linear search returning the index or -1
    }

    // Task 7 (binary search): the index of target in sortedSkus, which is
    // sorted alphabetically - that is the precondition binary search needs.
    // Keep low and high bounds, probe the middle, and narrow the half that
    // can still contain the target. Compare with compareTo:
    // negative means the middle word sorts before the target.
    static int binaryFindSku(String[] sortedSkus, String target) {
        return -2; // TODO: binary search with low and high bounds, or -1 if absent
    }

    public static void main(String[] args) {
        String[] skus = {"candle", "mug", "notebook", "pen", "tea"};
        int[] counts = {2, 9, 0, 14, 3};
        System.out.println("Total: " + totalStock(counts));
        int[] shelf = {1, 2, 3, 4};
        reverseInPlace(shelf);
        System.out.println("Reversed: " + shelf[0] + " " + shelf[1] + " " + shelf[2] + " " + shelf[3]);
        System.out.println("Reorder: " + reorderList(skus, counts, 3));
        HashMap<String, Integer> index = buildIndex(skus, counts);
        System.out.println("Index size: " + (index == null ? "none" : index.size()));
        System.out.println("Stock of tea: " + stockOf(index, "tea"));
        System.out.println("Stock of soap: " + stockOf(index, "soap"));
        System.out.println("Find pen: " + findSku(skus, "pen"));
        System.out.println("Binary find tea: " + binaryFindSku(skus, "tea"));
    }
}
