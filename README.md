# java-programming-project1
## Part I Required Files

Your repository must include:

* `NumberProcessor.java` — contains the class that stores and processes the numbers
* `PartIRunner.java` — contains `main()` and tests the `NumberProcessor` class
* `number.txt` — contains the input data
* A screenshot showing the complete output

### NumberProcessor Class Requirements

The `NumberProcessor` class must:

1. Use an array to store exactly 20 valid integers.
2. Include appropriate instance variables.
3. Include at least one constructor.
4. Include appropriate accessor and mutator methods.
5. Read data from `number.txt`.
6. Reject or skip invalid tokens.
7. Reject numbers outside the range `-100` through `100`.
8. Reject zero.
9. Continue reading until 20 valid integers have been stored.
10. Provide methods that calculate or return:

* The numbers in ascending order
* The product of all 20 numbers
* The minimum number
* The maximum number

Because the product may be too large for an `int` or `long`, use Java's `BigInteger` class to calculate and store the product.

Your `main()` method must only create objects, call methods, and display results. A program written entirely inside `main()` will receive a zero.


## Part II Required Files

Your repository must include:

* `ArrayQueue.java` — contains your queue implementation
* `PartIIRunner.java` — performs the required operations
* A screenshot showing the complete output

### Queue Requirements

Create an `ArrayQueue` class that uses an array to store integers.

Your class must include, at minimum:

```java
public void enqueue(int value)
public int dequeue()
public boolean isEmpty()
public String toString()
```

Do not place the complete queue implementation inside `main()`. The `main()` method in `PartIIRunner.java` should create an `ArrayQueue` object and call the queue methods in the required order.

Display the value removed by every `dequeue()` operation. You must also display the state of the queue after each operation.

The dequeue operations should remove values in this order:

```text
5
3
2
8
9
1
7
6
```

After all operations are complete, the queue should contain:

```text
[4]
```
