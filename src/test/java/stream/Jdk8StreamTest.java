package stream;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shz
 * @Description: ${todo}
 * @date 2019/8/26
 */
public class Jdk8StreamTest {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("1", "", "6", "4", "2");
        stream.filter((e) -> !e.isEmpty()).forEach(System.out::println);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(30);
        list.add(300);
        Set<Integer> collect = list.stream().sorted().filter((e) -> e < 100).map(e -> e.hashCode() * 10).collect(Collectors.toSet());
        collect.forEach(System.out::println);
    }
}
