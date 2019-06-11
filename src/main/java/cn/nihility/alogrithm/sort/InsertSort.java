package cn.nihility.alogrithm.sort;

import static cn.nihility.alogrithm.sort.SortUtils.*;

/**
 * 插入排序
 * @author muscari
 * @date 2019-06-11 15:26
 */
public class InsertSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unSortedArray) {
        int index;

        for (int i = 1; i < unSortedArray.length; i++) {
            T key = unSortedArray[i];
            index = i - 1;

            while (index >= 0 && less(key, unSortedArray[index])) {
                unSortedArray[index + 1] = unSortedArray[index];
                index--;
            }
            unSortedArray[index + 1] = key;
        }

        return unSortedArray;
    }

}
