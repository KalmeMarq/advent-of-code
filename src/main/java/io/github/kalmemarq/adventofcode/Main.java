package io.github.kalmemarq.adventofcode;

/*
    Day 1
        part 1: 980
        part 2: 5961

    Day 2
        part 1: 55916882972
        part 2: 76169125915

    Day 3
        part 1: 17109
        part 2: 169347417057382

    Day 4
        part 1: 1547
        part 2: 8948

    Day 5
        part 1: 865
        part 2: 352556672963116

    Day 6
        part 1: 7098065460541
        part 2: 13807151830618

    Day 7
        part 1: 1535
        part 2: 4404709551015
 */

public class Main {
    static void main() {
        Utils.getJarResourcesPath();

        IO.println("Day 1");
        long start = System.currentTimeMillis();
        Day1.part1(false);
        Day1.part2(false);
        long end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");

        IO.println("\nDay 2");
        start = System.currentTimeMillis();
        Day2.part1(false);
        Day2.part2(false);
        end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");

        IO.println("\nDay 3");
        start = System.currentTimeMillis();
        Day3.part1(false);
        Day3.part2(false);
        end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");

        IO.println("\nDay 4");
        start = System.currentTimeMillis();
        Day4.part1(false);
        Day4.part2(false);
        end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");

        IO.println("\nDay 5");
        start = System.currentTimeMillis();
        Day5.part1(false);
        Day5.part2(false);
        end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");

        IO.println("\nDay 6");
        start = System.currentTimeMillis();
        Day6.part1(false);
        Day6.part2(false);
        end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");

        IO.println("\nDay 7");
        start = System.currentTimeMillis();
        Day7.part1(false);
        Day7.part2(false);
        end = System.currentTimeMillis();
        IO.println("Took: " + (end - start) + "ms");
    }
}