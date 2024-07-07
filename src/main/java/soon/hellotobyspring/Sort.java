package soon.hellotobyspring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<String> scores = Arrays.asList("z", "x", "spring", "java");
        scores.sort((o1, o2) -> o1.length() - o2.length());

        scores.forEach(System.out::println);
    }

}
