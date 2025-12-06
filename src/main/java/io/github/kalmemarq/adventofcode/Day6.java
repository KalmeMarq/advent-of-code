package io.github.kalmemarq.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day6 {
    public static void part1(boolean test) {
        try {
            long total = 0;

            List<String> lines = Files.readAllLines(Utils.getJarResourcesPath().resolve((test ? "day6/test_input.txt" : "day6/input.txt")));
            int maxWidth = 0;
            for (String line : lines) {
                maxWidth = Math.max(maxWidth, line.length());
            }

            String opLine = lines.getLast();
            for (int i = 0; i < opLine.length(); ++i) {
                if (opLine.charAt(i) != '*' && opLine.charAt(i) != '+') {
                    continue;
                }

                boolean plus = opLine.charAt(i) == '+';
                boolean star = opLine.charAt(i) == '*';

                int nextPlusIdx = opLine.indexOf('+', i + 1);
                int nextStarIdx = opLine.indexOf('*', i + 1);

                int startIdx = i;
                int endIdx = nextPlusIdx != -1 && nextStarIdx != -1 ? Math.min(nextPlusIdx, nextStarIdx) : nextPlusIdx == -1 ? nextStarIdx == -1 ? maxWidth : nextStarIdx : nextPlusIdx;

                long lnTotal = 0;

                for (int j = 0; j < lines.size() - 1; ++j) {
                    String line = lines.get(j);
                    long value = Long.parseLong(line.substring(startIdx, Math.min(endIdx, line.length())).trim());

                    if (j == 0) lnTotal = value;
                    else {
                        if (plus) lnTotal += value;
                        else if (star) lnTotal *= value;
                    }
                }

                total += lnTotal;
            }

            IO.println("Total: " + total);
        } catch (IOException e) {
            IO.println("Error: " + e);
        }
    }

    public static void part2(boolean test) {
        try {
            long total = 0;

            List<String> lines = Files.readAllLines(Utils.getJarResourcesPath().resolve((test ? "day6/test_input.txt" : "day6/input.txt")));
            int maxWidth = 0;
            for (String line : lines) {
                maxWidth = Math.max(maxWidth, line.length());
            }

            String opLine = lines.getLast();
            for (int i = 0; i < opLine.length(); ++i) {
                if (opLine.charAt(i) != '*' && opLine.charAt(i) != '+') {
                    continue;
                }

                boolean plus = opLine.charAt(i) == '+';
                boolean star = opLine.charAt(i) == '*';

                int nextPlusIdx = opLine.indexOf('+', i + 1);
                int nextStarIdx = opLine.indexOf('*', i + 1);

                int startIdx = i;
                int endIdx = nextPlusIdx != -1 && nextStarIdx != -1 ? Math.min(nextPlusIdx, nextStarIdx) : nextPlusIdx == -1 ? nextStarIdx == -1 ? maxWidth : nextStarIdx : nextPlusIdx;

                long lnTotal = 0;

                for (int j = startIdx; j < endIdx; ++j) {
                    int value = 0;

                    int m = 1;
                    for (int k = lines.size() - 2; k >= 0; --k) {
                        String line = lines.get(k);
                        if (j >= line.length()) continue;
                        if (line.charAt(j) == ' ') continue;
                        value += (line.charAt(j) - '0') * m;
                        m *= 10;
                    }
                    if (m == 1) continue;

                    if (j == startIdx) lnTotal = value;
                    else {
                        if (plus) lnTotal += value;
                        else if (star) lnTotal *= value;
                    }
                }

                total += lnTotal;
            }

            IO.println("Total: " + total);
        } catch (IOException e) {
            IO.println("Error: " + e);
        }
    }
}
