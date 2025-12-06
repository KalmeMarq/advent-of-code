package io.github.kalmemarq.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Day5 {
    public static void part1(boolean test) {
        List<RangeChecker> checkers = new ArrayList<>();

        int total = 0;

        try (BufferedReader reader = Files.newBufferedReader(Utils.getJarResourcesPath().resolve(test ? "day5/test_input.txt" : "day5/input.txt"))) {
            String line;

            boolean accumCheckers = true;
            while ((line = reader.readLine()) != null) {
                if (accumCheckers && line.isBlank()) {
                    accumCheckers = false;
                    continue;
                }

                if (accumCheckers) {
                    String[] range = line.split("-");
                    checkers.add(new RangeChecker(Long.parseLong(range[0]), Long.parseLong(range[1])));
                    continue;
                }

                boolean passes = false;
                long value = Long.parseLong(line);
                for (RangeChecker checker : checkers) {
                    if (checker.isInRange(value)) {
                        passes = true;
                        break;
                    }
                }

                if (passes) {
                    ++total;
                }
            }

            IO.println("Fresh: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void part2(boolean test) {
        try (BufferedReader reader = Files.newBufferedReader(Utils.getJarResourcesPath().resolve(test ? "day5/test_input.txt" : "day5/input.txt"))) {
            String line;
            List<Range> ranges = new ArrayList<>();

            boolean accumCheckers = true;
            while ((line = reader.readLine()) != null) {
                if (accumCheckers && line.isBlank()) {
                    accumCheckers = false;
                    continue;
                }

                if (accumCheckers) {
                    String[] range = line.split("-");
                    ranges.add(new Range(Long.parseLong(range[0]), Long.parseLong(range[1])));
                    continue;
                }
            }

            Set<Integer> removed = new HashSet<>();
            int collapses;
            do {
                collapses = 0;

                for (int i = 0; i < ranges.size(); ++i) {
                    for (int j = 0; j < ranges.size(); ++j) {
                        if (i == j) continue;
                        if (removed.contains(i) || removed.contains(j)) continue;

                        Range r0 = ranges.get(i);
                        Range r1 = ranges.get(j);

                        // 10-20
                        // 12-18
                        if (r0.isFullyInside(r1)) {
                            ++collapses;

                            removed.add(j);
                            continue;
                        } else if (r1.isFullyInside(r0)) {
                            ++collapses;

                            removed.add(i);
                            continue;
                        }

                        // 10-20
                        // 5-15
                        if (r0.intersectRight(r1)) {
                            ++collapses;

                            ranges.set(i, new Range(r1.min, r0.max));
                            removed.add(j);
                            continue;
                        } else if (r1.intersectRight(r0)) {
                            ++collapses;

                            ranges.set(i, new Range(r0.min, r1.max));
                            removed.add(j);
                            continue;
                        }

                        // 10-20
                        // 15-25
                        if (r0.intersectLeft(r1)) {
                            ++collapses;

                            ranges.set(i, new Range(r0.min, r1.max));
                            removed.add(j);
                        } else if (r1.intersectLeft(r0)) {
                            ++collapses;

                            ranges.set(i, new Range(r1.min, r0.max));
                            removed.add(j);
                        }
                    }
                }
            } while (collapses > 0);

            List<Integer> idxToRemoved = new ArrayList<>(removed);
            idxToRemoved.sort((a, b) -> b - a);

            for (int idx : idxToRemoved) {
                ranges.remove(idx);
            }

            long total = 0;
            for (Range range : ranges) {
                total += (range.max - range.min + 1);
            }

            IO.println("Total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public record RangeChecker(long min, long max) {
        public boolean isInRange(long value) {
            return value >= this.min && value <= this.max;
        }
    }

    public record Range(long min, long max) {
        public boolean isFullyInside(Range range) {
            return range.min >= this.min && range.max <= this.max;
        }

        public boolean intersectRight(Range range) {
            return range.min <= this.min && range.max >= this.min;
        }

        public boolean intersectLeft(Range range) {
            return range.min <= this.max && range.max >= this.max;
        }
    }
}
