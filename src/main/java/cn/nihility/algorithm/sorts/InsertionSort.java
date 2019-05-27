package cn.nihility.algorithm.sorts;

import static cn.nihility.algorithm.sorts.SortUtils.*;

/**
 * Created by yzx on 2019/5/24.
 */
public class InsertionSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int len = array.length;
        int keyIndex;

        for (int index = 1; index < len; index++) {
            T key = array[index];
            keyIndex = index - 1;
            while (keyIndex >= 0 && less(key, array[keyIndex])) {
                array[keyIndex + 1] = array[keyIndex];
                keyIndex--;
            }
            array[keyIndex + 1] = key;
        }

        return array;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        String[] strings = {"c", "a", "e", "b","d"};

        print(insertionSort.sort(randomFloatArray(20, 9, 100)));
        print(insertionSort.sort(randomIntegerArray(20, 9, 100)));
        print(insertionSort.sort(strings));

    }

}
