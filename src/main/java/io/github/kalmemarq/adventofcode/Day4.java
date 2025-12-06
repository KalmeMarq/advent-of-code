package io.github.kalmemarq.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day4 {
    public static void part1(boolean test) {
        try {
            int total = 0;

            List<String> lines = Files.readAllLines(Utils.getJarResourcesPath().resolve(test ? "day4/test_input.txt" : "day4/input.txt"));

            StringBuilder result = new StringBuilder();

            int rows = lines.size();
            int cols = lines.getFirst().length();

            for (int r = 0; r < rows; ++r) {
                if (r != 0) result.append('\n');

                for (int c = 0; c < cols; ++c) {
                    if (lines.get(r).charAt(c) != '@') {
                        result.append(lines.get(r).charAt(c));
                        continue;
                    }

                    int n = 0;

                    if (r != 0 && lines.get(r - 1).charAt(c) == '@') ++n;
                    if (r != rows - 1 && lines.get(r + 1).charAt(c) == '@') ++n;
                    if (c != 0 && lines.get(r).charAt(c - 1) == '@') ++n;
                    if (c != cols - 1 && lines.get(r).charAt(c + 1) == '@') ++n;
                    if (c != 0 && r != 0 && lines.get(r - 1).charAt(c - 1) == '@') ++n;
                    if (c != cols - 1 && r != 0 && lines.get(r - 1).charAt(c + 1) == '@') ++n;
                    if (c != 0 && r != rows - 1 && lines.get(r + 1).charAt(c - 1) == '@') ++n;
                    if (c != cols - 1 && r != rows - 1 && lines.get(r + 1).charAt(c + 1) == '@') ++n;

                    if (n < 4) {
                        ++total;
                        result.append('x');
                    } else {
                        result.append(lines.get(r).charAt(c));
                    }
                }
            }

//            for (String line : lines) {
//                IO.println(line);
//            }
//
//            IO.println("\n" + result + "\n");
            IO.println("Rolls: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void part2(boolean test) {
        try {
            List<String> lines = Files.readAllLines(Utils.getJarResourcesPath().resolve(test ? "day4/test_input.txt" : "day4/input.txt"));

            int rows = lines.size();
            int cols = lines.getFirst().length();

            char[][] grid = new char[rows][cols];
            for (int r = 0; r < rows; ++r) {
                String line = lines.get(r);

                for (int c = 0; c < cols; ++c) {
                    grid[r][c] = line.charAt(c);
                }
            }

            int total = 0;

            int removed;
            do {
                removed = 0;

                for (int r = 0; r < rows; ++r) {
                    for (int c = 0; c < cols; ++c) {
                        if (grid[r][c] != '@') {
                            continue;
                        }

                        int n = 0;

                        if (r != 0 && (grid[r - 1][c] == '@' || grid[r - 1][c] == 'x')) ++n;
                        if (r != rows - 1 && (grid[r + 1][c] == '@' || grid[r + 1][c] == 'x')) ++n;
                        if (c != 0 && (grid[r][c - 1] == '@' || grid[r][c - 1] == 'x')) ++n;
                        if (c != cols - 1 && (grid[r][c + 1] == '@' || grid[r][c + 1] == 'x')) ++n;
                        if (c != 0 && r != 0 && (grid[r - 1][c - 1] == '@' || grid[r - 1][c - 1] == 'x')) ++n;
                        if (c != cols - 1 && r != 0 && (grid[r - 1][c + 1] == '@' || grid[r - 1][c + 1] == 'x')) ++n;
                        if (c != 0 && r != rows - 1 && (grid[r + 1][c - 1] == '@' || grid[r + 1][c - 1] == 'x')) ++n;
                        if (c != cols - 1 && r != rows - 1 && (grid[r + 1][c + 1] == '@' || grid[r + 1][c + 1] == 'x'))
                            ++n;

                        if (n < 4) {
                            ++total;
                            ++removed;
                            grid[r][c] = 'x';
                        }
                    }
                }


                for (int r = 0; r < rows; ++r) {
                    for (int c = 0; c < cols; ++c) {
                        if (grid[r][c] == 'x') {
                            grid[r][c] = '.';
                        }
                    }
                }
            } while (removed > 0);

//            StringBuilder res = new StringBuilder();
//            for (int r = 0; r < rows; ++r) {
//                if (r != 0) res.append('\n');
//                for (int c = 0; c < cols; ++c) {
//                    res.append(grid[r][c]);
//                }
//            }
//
//            IO.println("\n" + res + "\n");

            IO.println("Rolls: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
