package two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Two {

    public static void main(String[] args) throws IOException {
        System.out.println(safeReports());
    }

    private static long safeReports() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("two/2.txt"));
        return lines.stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList()))
                .filter(Two::isSafe)
                .count();
    }

    private static boolean isSafe(List<Integer> report) {
        return IntStream.range(0, report.size())
                .anyMatch(skipIndex -> {
                    List<Integer> modifiedReport = new ArrayList<>(report);
                    modifiedReport.remove(skipIndex);
                    return isValid(modifiedReport);
                });
    }

    private static boolean isValid(List<Integer> report) {
        var first = report.getFirst();
        var second = report.get(1);

        if (first > second) {  // Descending order
            return isInOrder(report, (x, y) -> {
                int diff = x - y;
                return diff >= 1 && diff <= 3;
            });
        } else if (first < second) {  // Ascending order
            return isInOrder(report, (x, y) -> {
                int diff = y - x;
                return diff >= 1 && diff <= 3;
            });
        }
        return false;  // Equal elements
    }

    private static boolean isInOrder(List<Integer> report, BiPredicate<Integer, Integer> predicate) {
        return IntStream.range(0, report.size() - 1)
                .allMatch(i -> predicate.test(
                        report.get(i),
                        report.get(i + 1)
                ));
    }
}
