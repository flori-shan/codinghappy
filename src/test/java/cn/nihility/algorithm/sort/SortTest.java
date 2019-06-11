package cn.nihility.algorithm.sort;

import cn.nihility.alogrithm.sort.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static cn.nihility.alogrithm.sort.SortUtils.*;

/**
 * @author muscari
 * @date 2019-06-11 14:14
 */
public class SortTest {

    @Test
    public void testBubbleSort() {
        sorts(new BubbleSort());
    }

    @Test
    public void testQuickSort() {
        sorts(new QuickSort());
    }

    @Test
    public void testInsertSort() {
        sorts(new InsertSort());
    }

    @Test
    public void testSelectionSort() {
        sorts(new SelectionSort());
    }

    @Test
    public void testMergeSort() {
        sorts(new MergeSort());
    }

    /* ========================= Random Sort Test ========================= */

    @Test
    public void testRandomBubbleSort() {
        randomSorts(new QuickSort(), 10, 10, 1000);
    }

    @Test
    public void testRandomQuickSort() {
        randomSorts(new QuickSort(), 10, 10, 1000);
    }

    @Test
    public void testRandomInsertSort() {
        randomSorts(new InsertSort(), 100, 10, 1000);
    }

    @Test
    public void testRandomSelectionSort() {
        randomSorts(new SelectionSort(), 100, 10, 1000);
    }

    @Test
    public void testRandomMergeSort() {
        randomSorts(new MergeSort(), 10, 10, 1000);
    }

    private Integer[] arrayInteger = new Integer[] {63, 81, 50, 20, 34, 95, 24, 57, 98, 55, 56, 85, 41, 18, 88, 93, 57, 60, 77, 64};
    private Double[] arrayDouble = new Double[] {47.58, 49.11, 25.70, 36.04, 59.01, 57.92, 97.89, 84.29, 65.87, 96.64, 80.48, 64.17, 95.81, 22.05, 22.36, 34.63, 27.79, 81.77, 10.75, 88.07};
    private String[] arrayString = new String[] {"j", "t", "q", "m", "j", "q", "t", "o", "g", "a", "k", "j", "b", "t", "b", "n", "j", "i", "q", "i"};

    private Integer[] arrayIntegerResult = new Integer[] {18, 20, 24, 34, 41, 50, 55, 56, 57, 57, 60, 63, 64, 77, 81, 85, 88, 93, 95, 98};
    private Double[] arrayDoubleResult = new Double[] {10.75, 22.05, 22.36, 25.70, 27.79, 34.63, 36.04, 47.58, 49.11, 57.92, 59.01, 64.17, 65.87, 80.48, 81.77, 84.29, 88.07, 95.81, 96.64, 97.89};
    private String[] arrayStringResult = new String[] {"a", "b", "b", "g", "i", "i", "j", "j", "j", "j", "k", "m", "n", "o", "q", "q", "q", "t", "t", "t"};

    @After
    public void destroy() {
        arrayInteger = null;
        arrayDouble = null;
        arrayString = null;
    }

    private void printItems() {
        print(arrayInteger);
        print(arrayDouble);
        print(arrayString);
    }

    private void innerAssert() {
        Assert.assertArrayEquals(arrayInteger, arrayIntegerResult);
        Assert.assertArrayEquals(arrayDouble, arrayDoubleResult);
        Assert.assertArrayEquals(arrayString, arrayStringResult);
    }

    private <T extends Comparable<T>> void doSort(SortAlgorithm sort, T[] array) {
        long sortTime = System.currentTimeMillis();
        sort.sort(array);
        System.out.println(System.currentTimeMillis() - sortTime);
    }

    private <T extends Comparable<T>> void sorts(SortAlgorithm sortAlgorithm) {
        System.out.println(sortAlgorithm.getClass().getName());
        doSort(sortAlgorithm, arrayInteger);
        doSort(sortAlgorithm, arrayDouble);
        doSort(sortAlgorithm, arrayString);
        System.out.println("================================================");
        printItems();
        System.out.println("================================================");
        innerAssert();
    }

    private <T extends Comparable<T>> void randomSorts(SortAlgorithm sortAlgorithm, int len, int minValue, int maxValue) {
        System.out.println(sortAlgorithm.getClass().getName());
        arrayInteger = randomIntegerArray(len, minValue, maxValue);
        arrayDouble = randomDoubleArray(len, minValue, maxValue);
        arrayString = randomStringArray(len);

        printItems();
        System.out.println("================================================");
        doSort(sortAlgorithm, arrayInteger);
        doSort(sortAlgorithm, arrayDouble);
        doSort(sortAlgorithm, arrayString);
        System.out.println("================================================");
        printItems();
    }

}
