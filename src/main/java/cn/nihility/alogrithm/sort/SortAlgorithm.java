package cn.nihility.alogrithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * Common interface of most sorting algorithms
 * @author muscari
 * @date 2019-06-11 10:59
 */
public interface SortAlgorithm {

    /**
     * Main method arrays sorting algorithms
     * @param unSortedArray - an array should be sorted
     * @return sorted array
     */
    <T extends  Comparable<T>> T[] sort(T[] unSortedArray);

    /**
     * auxiliary method for algorithms what wanted to work list from JCF
     * @param unSortedList - a list should be sorted
     * @return a sorted list
     */
    default <T extends Comparable<T>> List<T> sort(List<T> unSortedList) {
        return Arrays.asList(sort(unSortedList.toArray((T[]) new Comparable[unSortedList.size()])));
    }

}
