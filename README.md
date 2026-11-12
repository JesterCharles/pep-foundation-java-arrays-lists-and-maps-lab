# Arrays, Lists and Maps lab: a stockroom index

A small shop keeps its products in two parallel arrays: `skus[i]` names a product, and `counts[i]` says how many are on the shelf.
You build the shopkeeper's lookup tool, `Inventory.java`, in seven linked tasks. It answers the questions asked every day: how much stock is there, what needs reordering, and where is a product?
You start with plain arrays, move changing data into an `ArrayList`, index it with a `HashMap`, and finish with linear and binary search.

## Quick start

The lab needs JDK 25 and Apache Maven 3.9 or later in the terminal. Troubleshooting shows how to check.

1. Open the lab project you were given in VS Code, then open its integrated terminal: **Terminal > New Terminal**.
2. Go into the project folder. Every command in this lab runs there:

   ```
   cd starter
   ```

3. Before the first test run, download the lab's libraries once. This needs the internet, and prints nothing when it works:

   ```
   mvn -q dependency:go-offline
   ```

4. Run the tests before you change anything:

   ```
   mvn -q -o test
   ```

   **All 7 tests fail at first. That is expected: the TODOs are not done yet.**
   The output ends with `Tests run: 7, Failures: 7`, and every failure line starts with its task number, such as `Task 1:`.
5. Work through the checklist below in order. After each task, run `mvn -q -o test` again and read the remaining `Task N:` lines.
6. **You are finished when `mvn -q -o test` prints nothing at all.**

```
OPEN project -> cd starter -> setup (once) -> RUN tests: 7 fail (expected)
  -> READ the "Task N:" lines -> EDIT the next TODO -> RUN tests again
  -> arrays (Tasks 1-2) -> list (3) -> map (4-5) -> search (6-7)
  -> STOP when the test run prints nothing
```

In words: open the project, run the tests, read which tasks fail, complete one task's TODO, run the tests again, and stop when they print nothing.

## Checklist

All seven tasks are in `starter/src/main/java/inventory/Inventory.java`. Each one is a method with a `TODO` comment.

- [ ] Task 1: `totalStock` (the enhanced for loop)
- [ ] Task 2: `reverseInPlace` (two indexes on an array)
- [ ] Task 3: `reorderList` (an `ArrayList` that grows)
- [ ] Task 4: `buildIndex` (a `HashMap`)
- [ ] Task 5: `stockOf` (a map lookup with a default)
- [ ] Task 6: `findSku` (linear search)
- [ ] Task 7: `binaryFindSku` (binary search)

A task is done when no failure line starts with its number.
Edit only `Inventory.java`. The tests in `tests/inventory/`, the build file `starter/pom.xml` and `expected/test-summary.txt` stay as they are.

## Task steps

Each method's `TODO` comment says exactly what it must return or change.
The tests call every method with several inputs, including edge cases, and check only the results.

### Task 1: totalStock

1. Find the method `totalStock(int[] counts)` and its `TODO`.
2. Sum the whole array with an enhanced for loop (`for (int count : counts)`). You need every element and no index.
3. While it is wrong, you see lines such as `Task 1: totalStock({3, 0, 5}) ==> expected: <8> but was: <-1>`.

### Task 2: reverseInPlace

1. Find `reverseInPlace(int[] counts)`. It returns nothing: it rearranges the caller's array. It can do that because the parameter is a reference to that same array.
2. Use two indexes, one from each end. Swap the elements, move both indexes inward, and stop when they meet or cross.
   The enhanced for loop cannot help here, because swapping needs the positions themselves.
3. Check your loop condition against a one-element array and an empty array. A wrong bound throws `ArrayIndexOutOfBoundsException`, and its message names the index that does not exist.

### Task 3: reorderList

1. Find `reorderList(String[] skus, int[] counts, int threshold)`.
2. You cannot know in advance how many products are low, and an array cannot grow. That is why this method returns an `ArrayList`.
   Create one, walk the arrays by index, `add` each sku whose count is below the threshold, and return the list.
3. Keep shelf order: the tests expect `["candle", "notebook"]`, not any other order.

### Task 4: buildIndex

1. Find `buildIndex(String[] skus, int[] counts)`.
2. Finding one product in an array means reading element after element; a map jumps straight to it.
   Create a `HashMap<String, Integer>` and `put` every pair `skus[i] -> counts[i]` into it.

### Task 5: stockOf

1. Find `stockOf(HashMap<String, Integer> index, String sku)`.
2. `get` returns `null` for a missing sku, and the shopkeeper's tool must never show "null items".
   Return the count with `getOrDefault(sku, 0)`, so a missing product reads as 0.

### Task 6: findSku

1. Find `findSku(String[] skus, String target)`.
2. Walk the array from the front with one index, and return the position of the first match. Return -1 after the loop when nothing matched.
   Compare strings with `equals`, not `==`.

### Task 7: binaryFindSku

1. Find `binaryFindSku(String[] sortedSkus, String target)`. The parameter name states the precondition: binary search only works on sorted data.
2. Keep `low` and `high` bounds. Look at the middle element and compare it to the target with `compareTo`.
   A negative result means the middle sorts before the target, so the match can only be in the upper half; a positive result means the lower half.
3. Narrow to the correct half by moving `low` or `high` past the middle. Stop with -1 when `low` passes `high`.
4. While it is wrong, you see lines such as `Task 7: binaryFindSku(pantry, "milk")`.
   If the test run hangs, your bounds never close. Make sure each step leaves the middle element out of the range.
5. Optional: see the halving. Set a breakpoint on the line that looks at the middle element, start the debugger, and watch `low` and `high` close in on `"milk"` in the eight-product pantry.
   It takes three looks instead of seven reads, and that gap grows every time the shelf doubles.
   If your editor cannot start the test in its debugger, debug `main` instead: its `binaryFindSku(skus, "tea")` call shows the same halving.

### About the test results

A passing run shows that each method returns the right results for the inputs the tests try.
It does not check which Java constructs your code uses, so compare your code with each task's steps yourself.

## Using an AI assistant

If you use an AI assistant, ask it to explain, not to answer.
"Why does my binary search loop forever?" teaches you why the bounds must close; "write binaryFindSku for me" does not.
Check any explanation with a test run or the debugger before you trust it.

## Run the program

The tool prints one line per method. From `starter`:

```
mvn -q -o compile
java -cp target/classes inventory.Inventory
```

`expected/test-summary.txt` shows the finished result, including the eight lines the program prints.

## Troubleshooting

- **`there is no POM in this directory`.** The terminal is not in `starter`. From the lab folder, run `cd starter` and try again.
- **`COMPILATION ERROR`.** Your code does not compile yet. Maven names the file, then `[line,column]`, then the problem. Fix that line and run the tests again.
- **`mvn: command not found`, or `'mvn' is not recognized`.** Maven's `bin` folder is not on your `PATH`. Add it, then open a new terminal.
- **`mvn -v` shows a Java version other than 25, or you see `release version 25 not supported`.** Maven uses the JDK named by `JAVA_HOME` when it is set, otherwise the `java` on your `PATH`. Set `JAVA_HOME` to your JDK 25 folder, or unset it, then open a new terminal. Check with `java -version` and `mvn -v`.
- **`Cannot access central in offline mode`, or a missing artifact.** The setup step has not finished. Run `mvn -q dependency:go-offline` once while online.

## Cleanup (optional)

Maven writes only the `starter/target` folder, and Git already ignores it.
To delete it, run `rm -rf target` from `starter`. In Windows PowerShell, run `Remove-Item -Recurse -Force target`.
