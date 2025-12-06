package io.github.kalmemarq.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    public static int part1(boolean test) {
        int total = 0;

        try {
            List<String> lines = Files.readAllLines(Utils.getJarResourcesPath().resolve(test ? "day3/test_input.txt" : "day3/input.txt"));

            for (String line : lines) {
                int f = -1;
                int fIdx = -1;
                int s = -1;

                for (int i = 0; i < line.length(); ++i) {
                    int num = line.charAt(i) - '0';

                    if (f == -1) {
                        f = num;
                        fIdx = i;
                    } else if (num > f && i + 1 < line.length()) {
                        f = num;
                        fIdx = i;
                    }
                }

                for (int i = fIdx + 1; i < line.length(); ++i) {
                    int num = line.charAt(i) - '0';

                    if (s == -1) {
                        s = num;
                    } else if (num > s) {
                        s = num;
                    }
                }

                total += f * 10 + s;
            }

            IO.println("Joltage: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return total;
    }

    public static long part2(boolean test) {
        long total = 0;

        try {
            List<String> lines = Files.readAllLines(Utils.getJarResourcesPath().resolve(test ? "day3/test_input.txt" : "day3/input.txt"));

            for (String line : lines) {
                int[] digits = new int[12];
                Arrays.fill(digits, -1);
                int[] digitsIdxs = new int[12];
                Arrays.fill(digitsIdxs, -1);

                for (int digitIterIdx = 0; digitIterIdx < 12; ++digitIterIdx) {
                    for (int i = digitIterIdx == 0 ? 0 : digitsIdxs[digitIterIdx - 1] + 1; i < line.length(); ++i) {
                        int num = line.charAt(i) - '0';

                        if (digits[digitIterIdx] == -1) {
                            digits[digitIterIdx] = num;
                            digitsIdxs[digitIterIdx] = i;
                        } else if (num > digits[digitIterIdx] && i + (11 - digitIterIdx) < line.length()) {
                            digits[digitIterIdx] = num;
                            digitsIdxs[digitIterIdx] = i;
                        }
                    }
                }

                StringBuilder voltage = new StringBuilder();
                for (int digit : digits) {
                    voltage.append(digit);
                }

                total += Long.parseLong(voltage.toString());
            }

            IO.println("Joltage: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return total;
    }
}
