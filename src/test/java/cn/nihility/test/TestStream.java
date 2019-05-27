package cn.nihility.test;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by yzx on 2019/5/24.
 */
public class TestStream {

    @Test
    public void testStream() {
        ArrayList<String> list = new ArrayList<>();

        list.add("one");
        list.add("one1");
        list.add("one2");
        list.add("one3");
        list.add("one4");
        list.add("one5");
        list.add("one6");
        list.add("one7");

        list.stream()
                .map(Object::toString)
                .map(str -> str + " ")
                .forEach(System.out::print);
    }

    @Test
    public void testStream01 () {
        Stream<String> stream = Stream.of("I", "Love", "Lambda", "Function", "Test");
        /*stream.filter(str -> str.length() == 4)
              .forEach(str -> System.out.print(str + " : " ));*/

        /*stream.sorted((String str1, String str2) -> (str1.length() - str2.length()))
                .forEach(str -> System.out.print(str + " : " ));*/

        stream.map(str -> str.toUpperCase())
                .forEach(System.out::print);

    }

}
