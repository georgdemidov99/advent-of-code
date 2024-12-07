package three;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Three {

    private static final Pattern PATTERN = Pattern.compile(
            "(?:do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))");

    public static void main(String[] args) throws IOException {
        System.out.println(multiply());
    }

    private static long multiply() throws IOException {
        String content = Files.readString(Path.of("three/3.txt"));

        Matcher matcher = PATTERN.matcher(content);
        long sum = 0;
        boolean isEnabled = true;

        while (matcher.find()) {
            String match = matcher.group();

            switch (match) {
                case "do()" -> isEnabled = true;
                case "don't()" -> isEnabled = false;
                default -> {
                    if (isEnabled) {
                        int x = Integer.parseInt(matcher.group(1));
                        int y = Integer.parseInt(matcher.group(2));
                        sum += (long) x * y;
                    }
                }
            }
        }

        return sum;
    }
}
