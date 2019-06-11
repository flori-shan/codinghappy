package cn.nihility.alogrithm.sort;

import static cn.nihility.alogrithm.sort.SortUtils.*;
/**
 * 选择排序
 * @author muscari
 * @date 2019-06-11 15:39
 */
public class SelectionSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unSortedArray) {
        int len = unSortedArray.length;
        int min;

        for (int count = 0, loop = len - 1; count < loop; count++) {
            min = count;
            for (int i = count + 1; i < len; i++) {
                if (less(unSortedArray[i], unSortedArray[min])) {
                    min = i;
                }
            }
            if (min != count) {
                swap(unSortedArray, min, count);
            }
        }

        return unSortedArray;
    }
}
