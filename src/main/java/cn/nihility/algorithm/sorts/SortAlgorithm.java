package cn.nihility.algorithm.sorts;

import java.util.Arrays;
import java.util.List;

/**
 * The common interface of most sorting algorithms
 * Created by yzx on 2019/5/24.
 */
public interface SortAlgorithm {

    <T extends Comparable<T>> T[] sort(T[] array);

    default <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        return Arrays.asList(sort(unsorted.toArray((T[]) new Comparable[unsorted.size()])));
    }

}
