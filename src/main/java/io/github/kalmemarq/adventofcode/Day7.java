package io.github.kalmemarq.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day7 {
    public static void part1(boolean test) {
        Path inputPath = Utils.getJarResourcesPath().resolve(test ? "day7/test_input.txt" : "day7/input.txt");

        try {
            List<String> lines = Files.readAllLines(inputPath);

            int rows = lines.size();
            int cols = lines.getFirst().length();
            char[][] grid = new char[rows][cols];

            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    grid[r][c] = lines.get(r).charAt(c);
                }
            }

            int splitCount = 0;

            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    if (grid[r][c] == '^') {
                        ++splitCount;

                        for (int rr = r - 1; rr >= 0; --rr) {
                            if (c - 1 > 0 && grid[rr][c - 1] == '^')
                                break;

                            if (c + 1 < cols && grid[rr][c + 1] == '^')
                                break;

                            if (grid[rr][c] == '^') {
                                --splitCount;
                                break;
                            }
                        }
                    }
                }
            }

            IO.println("Split count: " + splitCount);
        } catch (IOException e) {
            IO.println("Error: " + e);
        }
    }

    public static void part2(boolean test) {
        Path inputPath = Utils.getJarResourcesPath().resolve(test ? "day7/test_input.txt" : "day7/input.txt");

        try {
            List<String> lines = Files.readAllLines(inputPath);

            int rows = lines.size();
            int cols = lines.getFirst().length();
            char[][] grid = new char[rows][cols];
            long[][] ngrid = new long[rows][cols];

            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    grid[r][c] = lines.get(r).charAt(c);
                }
            }

            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    if (grid[r][c] == 'S') {
                        ngrid[r + 1][c] = 1;
                    }

                    if (grid[r][c] == '^') {
                        if (c - 1 >= 0) {
                            ngrid[r][c - 1] += ngrid[r - 1][c];
                        }

                        if (c + 1 < cols) {
                            ngrid[r][c + 1] += ngrid[r - 1][c];
                        }
                    } else if (r - 1 >= 0 && ngrid[r - 1][c] != 0) {
                        ngrid[r][c] += ngrid[r - 1][c];
                    }
                }
            }

            long total = 0;
            for (int c = 0; c < cols; ++c) {
                total += ngrid[rows - 1][c];
            }

            IO.println("Timelines: " + total);
        } catch (IOException e) {
            IO.println("Error: " + e);
        }
    }
}
