package exercise.hidden;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import exercise.Inventory;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/** Held-out tests: graded on submit, never shown to the learner. Edge cases for Tasks 1 and 3-7. */
@TestMethodOrder(MethodOrderer.MethodName.class)
class HeldOutTests {

    private static final String[] SKUS = {"candle", "mug", "notebook", "pen", "tea"};
    private static final int[] COUNTS = {2, 9, 0, 14, 3};

    @Test
    void task1TotalStockEdgeCases() {
        assertAll(
                () -> assertEquals(7, Inventory.totalStock(new int[] {7}), "Task 1: totalStock({7})"),
                () -> assertEquals(0, Inventory.totalStock(new int[] {}),
                        "Task 1: totalStock({}), an empty shelf holds nothing"));
    }

    @Test
    void task3ReorderListEdgeCases() {
        assertAll(
                () -> assertEquals(List.of(), Inventory.reorderList(SKUS, COUNTS, 0),
                        "Task 3: reorderList(shop, threshold 0), nothing is below zero"),
                () -> assertEquals(List.of(), Inventory.reorderList(new String[] {}, new int[] {}, 5),
                        "Task 3: reorderList with no products"));
    }

    @Test
    void task4BuildIndexEdgeCases() {
        assertEquals(0, Inventory.buildIndex(new String[] {}, new int[] {}).size(),
                "Task 4: buildIndex with no products is an empty map");
    }

    @Test
    void task5StockOfEdgeCases() {
        assertEquals(0, Inventory.stockOf(new HashMap<>(), "tea"),
                "Task 5: stockOf(empty index, \"tea\")");
    }

    @Test
    void task6FindSkuEdgeCases() {
        assertAll(
                () -> assertEquals(4, Inventory.findSku(SKUS, "tea"), "Task 6: findSku(shop, \"tea\")"),
                () -> assertEquals(-1, Inventory.findSku(new String[] {}, "tea"),
                        "Task 6: findSku on an empty shelf"));
    }

    @Test
    void task7BinaryFindSkuEdgeCases() {
        String[] sorted = {"candle", "mug", "notebook", "pen", "tea"};
        assertAll(
                () -> assertEquals(0, Inventory.binaryFindSku(sorted, "candle"),
                        "Task 7: binaryFindSku(sorted shop, \"candle\"), first element"),
                () -> assertEquals(4, Inventory.binaryFindSku(sorted, "tea"),
                        "Task 7: binaryFindSku(sorted shop, \"tea\"), last element"),
                () -> assertEquals(-1, Inventory.binaryFindSku(new String[] {}, "tea"),
                        "Task 7: binaryFindSku on an empty shelf"));
    }
}
