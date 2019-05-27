package cn.nihility.algorithm.sorts;

import static cn.nihility.algorithm.sorts.SortUtils.*;

/**
 * Created by yzx on 2019/5/24.
 */
public class BubbleSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int len = array.length;
        boolean swap;

         /* Method 01 */
        do {
            swap = false;
            for (int count = 0; count < len - 1; count++) {
                if (more(array[count], array[count + 1])) {
                    swap = swap(array, count, count + 1);
                }
            }
            len--;
        } while (swap);

        /* Method 02 */
        /*for (int count = 0; count < len - 1; count++) {
            swap = false;
            for (int index = 0; index < len - count - 1; index++) {
                if (more(array[index], array[index + 1])) {
                    swap = swap(array, index, index + 1);
                }
            }
            print(array);
            if (!swap) { break; }
        }*/

        return array;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        String[] strings = {"c", "a", "e", "b","d"};

        print(bubbleSort.sort(randomFloatArray(10, 10, 100)));

        print(bubbleSort.sort(randomIntegerArray(20, 10, 100)));

        print(bubbleSort.sort(strings));
    }

}
