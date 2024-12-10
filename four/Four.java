package four;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Four {

    public static void main(String[] args) throws IOException {
        var matrix = getMatrix();
        System.out.println(getChristmasOccurrences(matrix));
    }

    private static List<List<Character>> getMatrix() throws IOException {
        return Files.readAllLines(Path.of("four/4.txt"))
                .stream()
                .map(row -> row.chars()
                        .mapToObj(ch -> (char) ch)
                        .toList())
                .toList();
    }

    private static long getChristmasOccurrences(List<List<Character>> matrix) {
        var sum = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (matrix.get(i).get(j) == 'X') {
                    sum += checkRow(i, j, matrix) + checkColumn(i, j, matrix) + checkDiagonals(i, j, matrix);
                }
            }
        }
        return sum;
    }

    private static int checkRow(int i, int j, List<List<Character>> matrix) {
        int total = 0;
        StringBuilder leftSide = new StringBuilder();
        StringBuilder rightSide = new StringBuilder();
        if (j - 3 >= 0) {
            leftSide.append(matrix.get(i).get(j - 1)).append(matrix.get(i).get(j - 2)).append(matrix.get(i).get(j - 3));
        }
        if (j + 3 < matrix.get(i).size()) {
            rightSide.append(matrix.get(i).get(j + 1)).append(matrix.get(i).get(j + 2)).append(matrix.get(i).get(j + 3));
        }

        if (leftSide.toString().equals("MAS")) {
            total += 1;
        }

        if (rightSide.toString().equals("MAS")) {
            total += 1;
        }

        return total;
    }

    private static int checkColumn(int i, int j, List<List<Character>> matrix) {
        int total = 0;
        StringBuilder up = new StringBuilder();
        StringBuilder down = new StringBuilder();
        if (i - 3 >= 0) {
            up.append(matrix.get(i - 1).get(j)).append(matrix.get(i - 2).get(j)).append(matrix.get(i - 3).get(j));
        }
        if (i + 3 < matrix.size()) {
            down.append(matrix.get(i + 1).get(j)).append(matrix.get(i + 2).get(j)).append(matrix.get(i + 3).get(j));
        }

        if (up.toString().equals("MAS")) {
            total += 1;
        }

        if (down.toString().equals("MAS")) {
            total += 1;
        }

        return total;
    }

    private static int checkDiagonals(int i, int j, List<List<Character>> matrix) {
        int total = 0;
        StringBuilder upLeft = new StringBuilder();
        StringBuilder upRight = new StringBuilder();
        StringBuilder downLeft = new StringBuilder();
        StringBuilder downRight = new StringBuilder();
        if (i - 3 >= 0) {
            if (j - 3 >= 0) {
                upLeft.append(matrix.get(i - 1).get(j - 1)).append(matrix.get(i - 2).get(j - 2)).append(matrix.get(i - 3).get(j - 3));
            }
            if (j + 3 < matrix.get(i).size()) {
                upRight.append(matrix.get(i - 1).get(j + 1)).append(matrix.get(i - 2).get(j + 2)).append(matrix.get(i - 3).get(j + 3));
            }
        }
        if (i + 3 < matrix.size()) {
            if (j - 3 >= 0) {
                downLeft.append(matrix.get(i + 1).get(j - 1)).append(matrix.get(i + 2).get(j - 2)).append(matrix.get(i + 3).get(j - 3));
            }
            if (j + 3 < matrix.get(i).size()) {
                downRight.append(matrix.get(i + 1).get(j + 1)).append(matrix.get(i + 2).get(j + 2)).append(matrix.get(i + 3).get(j + 3));
            }
        }

        if (upLeft.toString().equals("MAS")) {
            total += 1;
        }

        if (upRight.toString().equals("MAS")) {
            total += 1;
        }

        if (downLeft.toString().equals("MAS")) {
            total += 1;
        }

        if (downRight.toString().equals("MAS")) {
            total += 1;
        }

        return total;
    }
}
