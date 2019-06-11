package cn.nihility.alogrithm.sort;

import static cn.nihility.alogrithm.sort.SortUtils.*;

/**
 * 冒泡排序
 * 稳定排序
 * @author muscari
 * @date 2019-06-11 11:26
 */
public class BubbleSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unSortedArray) {
        int lastIndex = unSortedArray.length - 1;
        boolean swap;

        /* Method 1 */
        /*do {
            swap = false;
            for (int index = 0; index < lastIndex; index++) {
                if (gt(unSortedArray[index], unSortedArray[index + 1])) {
                    swap = swap(unSortedArray, index, index + 1);
                }
            }
            lastIndex--;
        } while (swap);*/

        /* Method 2 */
        for (int count = 0; count < lastIndex; count++) {
            swap = false;
            for (int index = 0; index < lastIndex - count; index++) {
                if (gt(unSortedArray[index], unSortedArray[index + 1])) {
                    swap = swap(unSortedArray, index, index + 1);
                }
            }
            if (!swap) { break; }
        }

        return unSortedArray;
    }

}
