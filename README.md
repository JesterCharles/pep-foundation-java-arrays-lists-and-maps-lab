# Arrays, Lists and Maps lab: a stockroom index

A small shop keeps its products in two parallel arrays: `skus[i]` names a product, and `counts[i]` says how many are on the shelf.
You build the shopkeeper's lookup tool, `Inventory.java`, in seven linked tasks. It answers the questions asked every day: how much stock is there, what needs reordering, and where is a product?
You start with plain arrays, move changing data into an `ArrayList`, index it with a `HashMap`, and finish with linear and binary search.

The full learner instructions are in [`.evolv/instructions.md`](.evolv/instructions.md), which the lab platform shows as the lab's instructions.

## Layout

This repository follows the Exa coding-lab scaffold for Java.

| Path | What it is |
|---|---|
| `src/main/java/inventory/Inventory.java` | A file learners edit |
| `src/test/java/inventory/InventoryTest.java` | Visible tests |
| `.evolv/instructions.md` | Learner instructions |
| `.evolv/grade.yml` | Grading manifest, used by Run Tests and by the grader |
| `pom.xml` | Maven build: Java 17 and JUnit 5.11.3, matching the scaffold |
| `Dockerfile` | Image for local Run Tests; warms the Maven cache |
| `expected/test-summary.txt` | The result of a finished lab |

There are no held-out tests in this lab: every test is visible.

## Run the tests

On the platform, select **Run Tests**. From a terminal with JDK 17 or later and Apache Maven 3.9:

```
mvn -q test
```

All 7 tests fail until the `TODO`s are done. That is expected.
