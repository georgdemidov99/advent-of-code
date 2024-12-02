package one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class One {

    public static void main(String[] args) throws IOException {
        TwoLists sortedLists = sortLists();
        int answer1 = calculateSum(sortedLists);
        System.out.println(answer1);
        int similarity = findSimilarity(sortedLists);
        System.out.println(similarity);
    }

    private static TwoLists sortLists() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("one/1.txt"));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String line : lines) {
            String[] formatted = line.split("(\\s+)");
            list1.add(Integer.valueOf(formatted[0]));
            list2.add(Integer.valueOf(formatted[1]));
        }
        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());
        return new TwoLists(list1, list2);
    }

    private static int calculateSum(TwoLists lists) {
        return IntStream.range(0, lists.list1.size())
                .map(i -> Math.abs(lists.list1.get(i) - lists.list2.get(i)))
                .sum();
    }

    private static int findSimilarity(TwoLists lists) {
        Map<Integer, Integer> frequencyMap =
                lists.list2.stream()
                        .collect(Collectors.toMap(
                                num -> num,
                                _ -> 1,
                                Integer::sum
                        ));
        return lists.list1.stream()
                .mapToInt(num -> num * frequencyMap.getOrDefault(num, 0))
                .sum();
    }

    private record TwoLists(List<Integer> list1, List<Integer> list2) {
    }
}
