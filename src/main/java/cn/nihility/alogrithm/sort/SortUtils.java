package cn.nihility.alogrithm.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Contains sort utils method
 * @author muscari
 * @date 2019-06-11 11:10
 */
public class SortUtils {

    public static <T> boolean swap(T[] array, int idx, int idy) {
        T swap = array[idx];
        array[idx] = array[idy];
        array[idy] = swap;
        return true;
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> boolean lessEqual(T v, T w) {
        return less(v, w) || equal(v, w);
    }

    public static <T extends Comparable<T>> boolean equal(T v, T w) {
        return v.compareTo(w) == 0;
    }

    public static <T extends Comparable<T>> boolean gt(T v, T w) {
        return v.compareTo(w) > 0;
    }

    public static <T extends Comparable<T>> boolean gtEqual(T v, T w) {
        return gt(v, w) || equal(v, w);
    }

    /**
     * just print List
     */
    public static void print(List<?> toPrint) {
        toPrint.stream().map(Object::toString)
                .map(str -> str + " : ")
                .forEach(System.out::print);
        System.out.println();
    }

    /**
     * just to print Array
     */
    public static void print(Object[] toPrint) {
        System.out.println(Arrays.toString(toPrint));
    }

    public static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
        while (left <= right) {
            swap(array, left++, right--);
        }
    }

    public static Integer[] randomIntegerArray(int len, int minValue, int maxValue) {
        Integer[] array = new Integer[len];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < len; i++) {
            array[i] = random.nextInt(maxValue - minValue) + minValue;
        }
        return array;
    }

    public static Double[] randomDoubleArray(int len, int minValue, int maxValue) {
        Double[] array = new Double[len];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < len; i++) {
            array[i] = random.nextDouble() * (maxValue - minValue) + minValue;
        }
        return array;
    }

    public static String[] randomStringArray(int len) {
        String[] array = new String[len];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < len; i++) {
            array[i] = String.valueOf((char) (random.nextInt(26) + 97));
        }
        return array;
    }

    public static void main(String[] args) {
        print(randomDoubleArray(10, 10, 20));
        print(randomIntegerArray(10, 10, 20));
        print(randomStringArray(10));


        Integer[] array = randomIntegerArray(10, 10, 20);
        print(array);

        List<Integer> list = Arrays.asList(array);
        print(list);

        array[2] = 200;
        print(array);
        print(list);
    }

}
