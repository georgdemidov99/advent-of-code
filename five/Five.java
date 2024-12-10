package five;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Five {

    private static final Map<Integer, Set<Integer>> preceeding = new HashMap<>();
    private static final Map<Integer, Set<Integer>> proceeding = new HashMap<>();

    public static void main(String[] args) throws IOException {
        System.out.println(validOrders(initializeRulesAndProcessOrderings()));
    }

    private static List<List<Integer>> initializeRulesAndProcessOrderings() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("five/5.txt"));
        boolean afterEmptyLine = false;
        List<List<Integer>> output = new ArrayList<>();

        for (String line : lines) {
            if (line.isEmpty()) {
                afterEmptyLine = true;
                continue;
            }

            if (!afterEmptyLine) {
                var processedLine = line.split("\\|");
                var firstNumber = Integer.valueOf(processedLine[0]);
                var secondNumber = Integer.valueOf(processedLine[1]);
                proceeding.computeIfAbsent(firstNumber, _ -> new HashSet<>(secondNumber)).add(secondNumber);
                preceeding.computeIfAbsent(secondNumber, _ -> new HashSet<>(secondNumber)).add(firstNumber);
            } else {
                output.add(Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }
        }
        return output;
    }

    private static long validOrders(List<List<Integer>> orderings) {
        long sum = 0;
        for (List<Integer> order : orderings) {
            boolean valid = true;
            for (int i = 0; i < order.size(); i++) {
                Set<Integer> proceedingRules = proceeding.get(order.get(i));
                Set<Integer> preceedingRules = preceeding.get(order.get(i));
                if ((proceedingRules != null && !Collections.disjoint(order.subList(0, i), proceedingRules)) ||
                        (preceedingRules != null && !Collections.disjoint(order.subList(i, order.size() - 1), preceedingRules))) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                sum += order.get(order.size() / 2);
            }
        }
        return sum;
    }
}
