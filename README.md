# Arrays, Lists and Maps lab: a stockroom index

## Goal

Build the lookup tool of a small shop's stockroom, `Inventory.java`, in seven linked tasks.
The shop keeps its products in two parallel arrays - `skus[i]` names a product, `counts[i]` says how many are on the shelf - and the tool answers the questions the shopkeeper asks every day: how much stock is there, what needs reordering, and where is one product?
You start on plain arrays, move changing data into an `ArrayList`, index it with a `HashMap`, and finish with linear and binary search.
You build and test it with the real JDK and Maven on your own computer.

## Files

**Edit exactly one file.** Every `TODO` comment in it marks a task; the test failure lines carry the same task numbers:

- `starter/src/main/java/inventory/Inventory.java` - Tasks 1-7, one method per task; together they become the shop's complete lookup tool.

**Do not change anything else.** In particular:

- `tests/inventory/InventoryTest.java` - the public tests your methods must pass
- `starter/pom.xml` - the build is prewired
- `expected/test-summary.txt` - the finished-state reference

## Prerequisites

You need JDK 25 and Apache Maven 3.9 or later, the same setup as the earlier Java labs.
Check both in a new terminal:

```
java -version
mvn -v
```

- `java -version` must report version 25.
- `mvn -v` must report Apache Maven 3.9 or later, and its `Java version:` line must also say 25.

This lab works the same on macOS, Linux and Windows.
On Windows, use PowerShell or Command Prompt; the commands are the same.
No Docker or other container tool is needed.

## Setup (once, needs the internet)

Open a terminal in the lab's root folder - the one that holds this README, `starter/` and `tests/` - and change into `starter` with `cd starter`; every command in this lab runs there. Then run:

```
mvn -q dependency:go-offline
```

It prints nothing when it succeeds.
If you finished an earlier Java lab on this machine, it finds everything already downloaded.
After this step, everything else in the lab runs offline.

## Tasks

Open the lab's root folder in your IDE or editor; it holds one Maven project, `starter/pom.xml`, and if your editor asks, open or import that file as a Maven project, then open `starter/src/main/java/inventory/Inventory.java`. Whatever your editor shows, the test command below, run in a terminal in `starter`, is the result that counts.
Each task is one method with a `TODO` comment; its comment says exactly what the method must return or change.
The tests call every method with several inputs, including edge cases, and check only the results.

After each task, run the one test command from the `starter` folder:

```
mvn -q -o test
```

Every failure line starts with its task number, so you can work through the tasks in order.

### Task 1: totalStock

1. Find the method `totalStock(int[] counts)` and its `TODO`.
2. Sum the whole array with an enhanced for loop (`for (int count : counts)`); you need every element and no index.
3. While it is wrong, you see lines such as `Task 1: totalStock({3, 0, 5}) ==> expected: <8> but was: <-1>`.
4. Done when no line starting with `Task 1` appears.

### Task 2: reverseInPlace

1. Find `reverseInPlace(int[] counts)`. It returns nothing: it rearranges the caller's array, which it can do because the parameter is a reference to that same array.
2. Use two indexes, one from each end: swap the elements, move both inward, and stop when they meet or cross.
   Here the enhanced for loop cannot help - swapping needs the positions themselves.
3. Check your condition against a one-element and an empty array; a wrong bound throws `ArrayIndexOutOfBoundsException` with the bad index in its message.
4. Done when no line starting with `Task 2` appears.

### Task 3: reorderList

1. Find `reorderList(String[] skus, int[] counts, int threshold)`.
2. You cannot know in advance how many products are low, and an array cannot grow - that is the reason this method returns an `ArrayList`.
   Create one, walk the arrays by index, `add` each sku whose count is below the threshold, and return the list.
3. Keep shelf order: the tests expect `["candle", "notebook"]`, not any other order.
4. Done when no line starting with `Task 3` appears.

### Task 4: buildIndex

1. Find `buildIndex(String[] skus, int[] counts)`.
2. Walking an array to find one product reads every element; a map jumps straight to it.
   Create a `HashMap<String, Integer>` and `put` every pair `skus[i] -> counts[i]` into it.
3. Done when no line starting with `Task 4` appears.

### Task 5: stockOf

1. Find `stockOf(HashMap<String, Integer> index, String sku)`.
2. `get` returns `null` for a missing sku, and the shopkeeper's tool must never show "null items".
   Return the count with `getOrDefault(sku, 0)` so a missing product reads as 0.
3. Done when no line starting with `Task 5` appears.

### Task 6: findSku

1. Find `findSku(String[] skus, String target)`.
2. Walk the array from the front with one index and return the position of the first match, or -1 after the loop when nothing matched.
   Compare strings with `equals`, not `==`.
3. Done when no line starting with `Task 6` appears.

### Task 7: binaryFindSku

1. Find `binaryFindSku(String[] sortedSkus, String target)`. The parameter name states the precondition: binary search only works on sorted data.
2. Keep `low` and `high` bounds. Probe the middle element and compare it to the target with `compareTo`: a negative result means the middle sorts before the target, so the match can only be in the upper half; positive means the lower half.
3. Narrow the correct half by moving `low` or `high` past the middle, and stop with -1 when `low` passes `high`.
4. While it is wrong, you see lines such as `Task 7: binaryFindSku(pantry, "milk")`. An endless run (the command hits its timeout) means your bounds never close - make sure each step excludes the middle element.
5. Done when no line starting with `Task 7` appears and the whole run prints nothing.
6. **See the halving.** Set a breakpoint on your probe line, debug the test, and watch `low` and `high` close in on `"milk"` in the eight-product pantry: three probes instead of seven reads - that gap grows with every doubling of the shelf. If your editor cannot start that test in its debugger, debug `main` instead: its `binaryFindSku(skus, "tea")` call shows the same halving.

## Using an AI assistant

If you use an AI assistant in this lab, ask it to explain, not to answer: "why does my binary search loop forever?" teaches you the invariant; "write binaryFindSku for me" does not.
Verify any explanation against a run of the tests or the debugger before you trust it.

## Test

The one test command, run from the `starter` folder, is:

```
mvn -q -o test
```

- Before you start, all seven tests fail, and Maven ends with `Tests run: 7, Failures: 7`.
- Each test lists every input that gave a wrong result. A passing run shows that each method returns the right results for those inputs; it does not check which Java constructs your code uses, so compare your code with each task's steps yourself.
- If your code does not compile, Maven prints `COMPILATION ERROR` with the file, then `[line,column]`, then the compiler's message. Fix that line and run the test again.

You can also run the tool itself, which prints one line per method:

```
mvn -q -o compile
java -cp target/classes inventory.Inventory
```

## Expected output

When every task is complete, `mvn -q -o test` prints nothing and exits with status 0.
`expected/test-summary.txt` shows the full expected result, including the eight lines the program prints.

## Offline note

Only the setup step uses the network.
The test command uses `-o` (offline), so it never downloads anything.
If it reports that an artifact is missing from the local repository, run the setup step once more while online.

## Cleanup

The lab writes only the `starter/target` folder (compiled classes and test reports). Delete it from the `starter` folder:

- macOS and Linux: `rm -rf target`
- Windows PowerShell: `Remove-Item -Recurse -Force target`

The JUnit and plugin downloads stay in your local Maven repository (`~/.m2/repository` by default) so later Java labs can reuse them.

`starter/.gitignore` already keeps Maven's `starter/target` folder out of Git. Your editor and operating system may also create local files in the lab's root folder, such as `.idea/`, `.vscode/` or `.DS_Store`; they are not part of the lab, so if `git status` lists any of them, add them to a `.gitignore` file in the lab's root folder instead of committing them.

## Troubleshooting

- **`mvn: command not found`, or `'mvn' is not recognized`.** Maven's `bin` folder is not on your `PATH`. Add it, then open a new terminal.
- **`mvn -v` shows a Java version other than 25.** Maven uses the JDK named by `JAVA_HOME` when it is set, otherwise the `java` on your `PATH`. Set `JAVA_HOME` to your JDK 25 folder, or unset it, then open a new terminal.
- **`release version 25 not supported`.** Maven is running on an older JDK. Fix it as in the previous item.
- **`Cannot access central in offline mode`, or a missing artifact.** The setup step has not completed. Run `mvn -q dependency:go-offline` once while online.
- **`ArrayIndexOutOfBoundsException`.** The message names the index that does not exist; check the loop bound or swap positions in Task 2 and Task 7.
- **The test run hangs on Task 7.** The search never narrows. Move `low` to `mid + 1` or `high` to `mid - 1`, never to `mid` itself.
