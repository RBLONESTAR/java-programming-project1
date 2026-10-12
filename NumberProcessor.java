import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads integers from a text file, keeps up to 20 valid values
 * (non-zero, between -100 and 100), and provides sorted order,
 * product (BigInteger), minimum and maximum.
 */
public class NumberProcessor {

    public static final int SIZE = 20;
    public static final int MIN_VALUE = -100;
    public static final int MAX_VALUE = 100;

    private int[] numbers;
    private int count;
    private int rejectedCount;
    private String fileName;
    private BigInteger product;

    // Default constructor: reads from number.txt
    public NumberProcessor() {
        this("number.txt");
    }

    // Constructor that takes the file name
    public NumberProcessor(String fileName) {
        this.fileName = fileName;
        this.numbers = new int[SIZE];
        this.product = BigInteger.ONE;
        loadNumbers();
    }

    /** Reads tokens until 20 valid integers are stored or the file ends. */
    public void loadNumbers() {
        numbers = new int[SIZE];
        count = 0;
        rejectedCount = 0;

        try (Scanner input = new Scanner(new File(fileName))) {
            while (count < SIZE && input.hasNext()) {
                String token = input.next();
                try {
                    int value = Integer.parseInt(token);
                    if (isValid(value)) {
                        numbers[count++] = value;
                    } else {
                        rejectedCount++;
                    }
                } catch (NumberFormatException e) {
                    rejectedCount++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: could not find file " + fileName);
        }
        calculateProduct();
    }

    /** A value is valid if it is in [-100, 100] and not zero. */
    public static boolean isValid(int value) {
        return value != 0 && value >= MIN_VALUE && value <= MAX_VALUE;
    }

    private void calculateProduct() {
        product = BigInteger.ONE;
        for (int i = 0; i < count; i++) {
            product = product.multiply(BigInteger.valueOf(numbers[i]));
        }
    }

    /** Reads the given file, preserving the original project API. */
    public void readNumbers(String filename) {
        setFileName(filename);
    }

    // ---------- Accessors ----------

    /** Returns a copy of the stored numbers in original (file) order. */
    public int[] getNumbers() {
        return Arrays.copyOf(numbers, count);
    }

    public int getCount() {
        return count;
    }

    public int getRejectedCount() {
        return rejectedCount;
    }

    public String getFileName() {
        return fileName;
    }

    public BigInteger getProduct() {
        return product;
    }

    /** Returns a new array with the numbers in ascending order. */
    public int[] getSortedNumbers() {
        int[] sorted = getNumbers();
        Arrays.sort(sorted);
        return sorted;
    }

    public int getMinimum() {
        ensureNumbersAvailable();
        int min = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    public int getMaximum() {
        ensureNumbersAvailable();
        int max = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    private void ensureNumbersAvailable() {
        if (count == 0) {
            throw new IllegalStateException("No valid numbers are available.");
        }
    }

    // ---------- Mutators ----------

    /** Replaces stored values with up to 20 valid values from the array. */
    public void setNumbers(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Numbers cannot be null.");
        }
        numbers = new int[SIZE];
        count = 0;
        rejectedCount = 0;
        for (int value : values) {
            if (count == SIZE) {
                break;
            }
            if (isValid(value)) {
                numbers[count++] = value;
            } else {
                rejectedCount++;
            }
        }
        calculateProduct();
    }

    /** Changes the input file and re-reads the data. */
    public void setFileName(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null.");
        }
        this.fileName = fileName;
        loadNumbers();
    }

    /** Replaces one stored number (must be valid) and updates the product. */
    public boolean setNumber(int index, int value) {
        if (index < 0 || index >= count || !isValid(value)) {
            return false;
        }
        numbers[index] = value;
        calculateProduct();
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(getNumbers());
    }
}
