package cn.nihility.alogrithm.sort;

import static cn.nihility.alogrithm.sort.SortUtils.*;

/**
 * 归并排序
 * 归并的含义是将两个或两个以上的有序表合并成一个新的有序表
 * 归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序
 * 两路归并排序算法思路
 * 1. 把 n 个记录看成 n 个长度为 1 的有序子表；
 * 2. 进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表；
 * 3. 重复第②步直到所有记录归并成一个长度为 n 的有序表为止。
 * @author muscari
 * @date 2019-06-11 15:47
 */
public class MergeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unSortedArray) {
        T[] temp = (T[]) new Comparable[unSortedArray.length];
        /*doSort02(unSortedArray, temp, 0, unSortedArray.length - 1);*/
        doSort(unSortedArray, temp, 0, unSortedArray.length - 1);
        return unSortedArray;
    }

    /**
     * Recursively sorts the array in increasing order
     * @param array The array to be sorted
     * @param left  The first index of the array
     * @param right The last index of the array
     *
     */
    private static <T extends Comparable<T>> void doSort(T[] array, T[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            doSort(array, temp, left, mid);
            doSort(array, temp, mid + 1, right);
            merge(array, temp, left, mid, right);
        }
    }

    /**
     * The merge step of the merge sort
     */
    private static <T extends Comparable<T>> void merge(T[] array, T[] temp, int left, int mid, int right) {
        int mIndex = mid + 1;
        int lIndex = left;
        int cIndex = 0;

        /* 逐个归并*/
        while (lIndex <= mid && mIndex <= right) {
            if (lessEqual(array[lIndex], array[mIndex])) {
                temp[cIndex++] = array[lIndex++];
            } else {
                temp[cIndex++] = array[mIndex++];
            }
        }

        /* 左边剩余的归并 */
        while (lIndex <= mid) {
            temp[cIndex++] = array[lIndex++];
        }

        /* 右边剩余的归并 */
        while (mIndex <= right) {
            temp[cIndex++] = array[mIndex++];
        }

        /* 从临时数组拷贝到原数组 */
        for (int index = 0; index < cIndex; index++) {
            array[left + index]  = temp[index];
        }

    }

    /**
     * Recursively sorts the array in increasing order
     * @param array The array to be sorted
     * @param temp   The copy of the actual array
     * @param left  The first index of the array
     * @param right The last index of the array
     *
     */
    private static <T extends Comparable<T>> void doSort02(T[] array, T[] temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            doSort02(array, temp, left, mid);
            doSort02(array, temp, mid + 1, right);
            merge02(array, temp, left, mid, right);
        }
    }

    private static <T extends Comparable<T>> void merge02(T[] array, T[] temp, int left, int mid, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (lessEqual(temp[i], temp[j])) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            array[k++] = temp[i++];
        }

        while (j <= right) {
            array[k++] = temp[j++];
        }
    }

}
