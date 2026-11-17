package exercise;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Visible tests for the Arrays, Lists and Maps lab: one test per task. Each test
 * calls one method with a few inputs and checks only the results.
 * Restored over the learner's checkout at grade time.
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class VisibleTests {

    private static final String[] SKUS = {"candle", "mug", "notebook", "pen", "tea"};
    private static final int[] COUNTS = {2, 9, 0, 14, 3};

    @Test
    void task1TotalStock() {
        assertAll(
                () -> assertEquals(8, Inventory.totalStock(new int[] {3, 0, 5}),
                        "Task 1: totalStock({3, 0, 5})"),
                () -> assertEquals(28, Inventory.totalStock(COUNTS),
                        "Task 1: totalStock({2, 9, 0, 14, 3})"));
    }

    @Test
    void task2ReverseInPlace() {
        int[] four = {1, 2, 3, 4};
        Inventory.reverseInPlace(four);
        assertArrayEquals(new int[] {4, 3, 2, 1}, four, "Task 2: reverseInPlace({1, 2, 3, 4})");
        int[] odd = {5, 6, 7};
        Inventory.reverseInPlace(odd);
        assertArrayEquals(new int[] {7, 6, 5}, odd, "Task 2: reverseInPlace({5, 6, 7})");
    }

    @Test
    void task3ReorderList() {
        ArrayList<String> low = Inventory.reorderList(SKUS, COUNTS, 3);
        assertNotNull(low, "Task 3: reorderList must return a list, not null");
        assertEquals(List.of("candle", "notebook"), low,
                "Task 3: reorderList(shop, threshold 3) in shelf order");
        assertEquals(List.of("candle", "mug", "notebook", "pen", "tea"),
                Inventory.reorderList(SKUS, COUNTS, 99),
                "Task 3: reorderList(shop, threshold 99) keeps shelf order");
    }

    @Test
    void task4BuildIndex() {
        HashMap<String, Integer> index = Inventory.buildIndex(SKUS, COUNTS);
        assertNotNull(index, "Task 4: buildIndex must return a map, not null");
        assertAll(
                () -> assertEquals(5, index.size(), "Task 4: buildIndex(shop) holds all five products"),
                () -> assertEquals(9, index.get("mug"), "Task 4: index.get(\"mug\")"),
                () -> assertEquals(0, index.get("notebook"), "Task 4: index.get(\"notebook\")"));
    }

    @Test
    void task5StockOf() {
        HashMap<String, Integer> index = new HashMap<>();
        index.put("tea", 3);
        index.put("mug", 9);
        assertAll(
                () -> assertEquals(3, Inventory.stockOf(index, "tea"), "Task 5: stockOf(index, \"tea\")"),
                () -> assertEquals(0, Inventory.stockOf(index, "soap"),
                        "Task 5: stockOf(index, \"soap\"), a missing sku means 0, never null"));
    }

    @Test
    void task6FindSku() {
        assertAll(
                () -> assertEquals(3, Inventory.findSku(SKUS, "pen"), "Task 6: findSku(shop, \"pen\")"),
                () -> assertEquals(0, Inventory.findSku(SKUS, "candle"), "Task 6: findSku(shop, \"candle\")"),
                () -> assertEquals(-1, Inventory.findSku(SKUS, "soap"),
                        "Task 6: findSku(shop, \"soap\") is not there"));
    }

    @Test
    void task7BinaryFindSku() {
        String[] sorted = {"candle", "mug", "notebook", "pen", "tea"};
        String[] bigger = {"apple", "bread", "candle", "flour", "honey", "jam", "milk", "oats"};
        assertAll(
                () -> assertEquals(2, Inventory.binaryFindSku(sorted, "notebook"),
                        "Task 7: binaryFindSku(sorted shop, \"notebook\")"),
                () -> assertEquals(-1, Inventory.binaryFindSku(sorted, "soap"),
                        "Task 7: binaryFindSku(sorted shop, \"soap\") is not there"),
                () -> assertEquals(6, Inventory.binaryFindSku(bigger, "milk"),
                        "Task 7: binaryFindSku(pantry, \"milk\")"));
    }
}
