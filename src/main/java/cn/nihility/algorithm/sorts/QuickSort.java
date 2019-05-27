package cn.nihility.algorithm.sorts;

import static cn.nihility.algorithm.sorts.SortUtils.*;

/**
 * Created by yzx on 2019/5/24.
 */
public class QuickSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
//        doSort(array, 0, array.length - 1);
        doSort1(array, 0, array.length - 1);
        return array;
    }

    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            doSort(array, left, pivot - 1);
            doSort(array, pivot, right);
        }
    }

    private static <T extends Comparable<T>> void doSort1(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition1(array, left, right);
            doSort1(array, left, pivot - 1);
            doSort1(array, pivot + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) / 2;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                ++left;
            }
            while (less(pivot, array[right])) {
                --right;
            }
            if (left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }

        return left;
    }

    private static <T extends Comparable<T>> int partition1(T[] array, int left, int right) {
        T pivot = array[left];

        while (left <= right) {
            while (left <= right && lessEqual(array[left], array[right])) {
                right--;
            }
            array[left] = array[right];
            while (left <= right && moreEqual(array[left], array[right])) {
                left++;
            }
            array[right] = array[left];

        }
        array[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        String[] stringArray =  {"c", "a", "e", "b", "d"};

        print(quickSort.sort(randomFloatArray(20, 8, 100)));
        print(quickSort.sort(randomIntegerArray(20, 8, 100)));
        print(quickSort.sort(stringArray));

    }
}
