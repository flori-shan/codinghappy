package cn.nihility.alogrithm.sort;

import static cn.nihility.alogrithm.sort.SortUtils.*;

/**
 * 快速排序
 * 非稳定排序
 * @author muscari
 * @date 2019-06-11 13:56
 */
public class QuickSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unSortedArray) {
        doSort02(unSortedArray, 0, unSortedArray.length - 1);
        return unSortedArray;
    }

    /* ========================== 方法一 ========================== */

    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            doSort(array, left, pivot - 1); /* 注意此处的中间变量 */
            doSort(array, pivot, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) / 2;
        T pivot = array[mid];

        while (left <= right) {
            while (less(array[left], pivot)) {
                left++;
            }
            while (less(pivot, array[right])) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    /* ========================== 方法二 ========================== */
    private static <T extends Comparable<T>> void doSort02(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition02(array, left, right);
            doSort(array, left, pivot - 1); /* 注意此处的中间变量 */
            doSort(array, pivot + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition02(T[] array, int left, int right) {
        T pivot = array[left];
        int low = left;
        int high = right;

        while (low < high) {
            /* left mid  <-- less -- right*/
            while (low < high && gtEqual(array[high], pivot)) {
                high--;
            }

            /* left -- more --> mid right */
            while (low < high && lessEqual(array[low], pivot)) {
                low++;
            }

            if (low < high) {
                swap(array, low, high);
            }
        }
        /* 注意： 要把开始的基准移动到中间 */
        swap(array, low, left);

        return left;
    }

}
