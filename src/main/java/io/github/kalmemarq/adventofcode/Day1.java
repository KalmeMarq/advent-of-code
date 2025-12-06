package io.github.kalmemarq.adventofcode;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {
    public static void part1(boolean test) {
        int dial = 50;
        int zeroCount = 0;

        Path inputPath = Utils.getJarResourcesPath().resolve(test ? "day1/test_input.txt" : "day1/input.txt");

        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() || line.charAt(0) == ' ') continue;

                String dir = line.substring(0, 1);
                int amount = Integer.parseInt(line.substring(1)) * ("L".equals(dir) ? -1 : 1);

                dial = rotate(dial, amount).dial;
                if (dial == 0) ++zeroCount;
            }

            IO.println("Password: " + zeroCount);
        } catch (Exception e) {
            IO.println("Error: " + e);
        }
    }

    public static void part2(boolean test) {
        int dial = 50;
        int zeroCount = 0;

        Path inputPath = Utils.getJarResourcesPath().resolve(test ? "day1/test_input.txt" : "day1/input.txt");

        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() || line.charAt(0) == ' ') continue;

                String dir = line.substring(0, 1);
                int amount = Integer.parseInt(line.substring(1)) * ("L".equals(dir) ? -1 : 1);

                Result result = rotate(dial, amount);
                dial = result.dial;

                zeroCount += result.zeroPassedCount;
            }

            IO.println("Password: " + zeroCount);
        } catch (Exception e) {
            IO.println("Error: " + e);
        }
    }

    public static Result rotate(int dial, int amount) {
        int unitAmount = amount < 0 ? Math.floorMod(amount, -100) : Math.floorMod(amount, 100);
        int extraFullRotations = amount < 0 ? Math.floorDiv(amount, -100) : Math.floorDiv(amount, 100);

        int r = dial + unitAmount;
        if (r < 0) {
            return new Result(100 + r, (dial != 0 ? 1 : 0) + extraFullRotations);
        } else {
            return r >= 100 ? new Result(r - 100, 1 + extraFullRotations) : new Result(r, (r == 0 ? 1 : 0) + extraFullRotations);
        }
    }

    public record Result(int dial, int zeroPassedCount) {
    }
}
