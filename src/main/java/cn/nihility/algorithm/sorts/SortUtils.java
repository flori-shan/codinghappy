package cn.nihility.algorithm.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by yzx on 2019/5/24.
 */
final class SortUtils {

    /**
     * Helper method for swapping places in array
     * @param array The array which elements we want to swap
     * @param idx index of the first element
     * @param idy index of the second element
     * @return
     */
    static <T> boolean swap(T[] array, int idx, int idy) {
        T swap = array[idx];
        array[idx] = array[idy];
        array[idy] = swap;
        return true;
    }

    /**
     * This method checks if first element is less than the other element
     * @param v first element
     * @param w second element
     * @return true if the first element is less then the second element
     */
    static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    static <T extends Comparable<T>> boolean lessEqual(T v, T w) {
        return v.compareTo(w) <= 0;
    }

    static <T extends Comparable<T>> boolean equal(T v, T w) {
        return v.compareTo(w) == 0;
    }

    /**
     * This method checks if first element is more than the other element
     * @param v first element
     * @param w second element
     * @return true if the first element is more then the second element
     */
    static <T extends Comparable<T>> boolean more(T v, T w) {
        return v.compareTo(w) > 0;
    }

    static <T extends Comparable<T>> boolean moreEqual(T v, T w) {
        return v.compareTo(w) >= 0;
    }

    static void print(List<?> toPrint) {
        toPrint.stream()
                .map(Object::toString)
                .map(str -> str + " ")
                .forEach(System.out::print);
        System.out.println();
    }

    static void print(Object[] toPrint) {
        System.out.println(Arrays.asList(toPrint));
    }

    /**
     * Swaps all position from {@param left} to {@param right} for {@param array}
     * @param array is an array
     * @param left is a left flip border of the array
     * @param right is a right flip border of the array
     */
    static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
        while (left <= right) {
            swap(array, left++, right--);
        }
    }

    static Float[] randomFloatArray(int arraySize, int minValue, int maxValue) {
        Float[] array = new Float[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = (random.nextFloat() * maxValue + minValue);
        }

        return array;

    }

    static Integer[] randomIntegerArray(int arraySize, int minValue, int maxValue) {
        Integer[] array = new Integer[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; i++) {
            array[i] = (random.nextInt(maxValue) + minValue);
        }

        return array;

    }

    static <T> T[] randomNumberArray(int arraySize, int minValue, int maxValue) {
        T[] array = (T[]) new Object[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = (T) new Double(random.nextDouble() * maxValue + minValue);
        }
        return array;
    }

    public static void main(String[] args) {
        print(SortUtils.<Integer>randomNumberArray(20, 10, 100));
    }

}
