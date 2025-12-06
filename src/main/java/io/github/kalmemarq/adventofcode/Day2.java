package io.github.kalmemarq.adventofcode;

import java.io.IOException;
import java.nio.file.Files;

public class Day2 {
    public static void part1(boolean test) {
        try {
            String content = Files.readString(Utils.getJarResourcesPath().resolve(test ? "day2/test_input.txt" : "day2/input.txt"));

            long total = 0;

            for (int i = 0; i < content.length(); ++i) {
                while (i < content.length() && !Character.isDigit(content.charAt(i))) i++;
                if (i >= content.length()) continue;

                long firstId = 0;
                int j = 0;
                while (i < content.length() && Character.isDigit(content.charAt(i))) {
                    firstId = firstId * 10 + (content.charAt(i) - '0');
                    ++i;
                    ++j;
                }

                if (content.charAt(i++) != '-') {
                    throw new IllegalStateException("Expected - ");
                }

                long secondId = 0;
                int k = 0;
                while (i < content.length() && Character.isDigit(content.charAt(i))) {
                    secondId = secondId * 10 + (content.charAt(i) - '0');
                    ++i;
                    ++k;
                }

                if ((j & 1) != 0 && (k & 1) != 0) continue;

                for (long id = firstId; id <= secondId; ++id) {
                    String idStr = String.valueOf(id);
                    if (idStr.length() % 2 != 0) {
                        continue;
                    }

                    String firstHalf = idStr.substring(0, idStr.length() / 2);
                    String secondHalf = idStr.substring(idStr.length() / 2);

                    if (firstHalf.equals(secondHalf)) {
                        total += id;
                    }
                }
            }

            IO.println("Result: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void part2(boolean test) {
        try {
            String content = Files.readString(Utils.getJarResourcesPath().resolve(test ? "day2/test_input.txt" : "day2/input.txt"));

            long total = 0;

            for (int i = 0; i < content.length(); ++i) {
                while (i < content.length() && !Character.isDigit(content.charAt(i))) i++;
                if (i >= content.length()) continue;

                long firstId = 0;
                while (i < content.length() && Character.isDigit(content.charAt(i))) {
                    firstId = firstId * 10 + (content.charAt(i) - '0');
                    ++i;
                }

                if (content.charAt(i++) != '-') {
                    throw new IllegalStateException("Expected - ");
                }

                long secondId = 0;
                while (i < content.length() && Character.isDigit(content.charAt(i))) {
                    secondId = secondId * 10 + (content.charAt(i) - '0');
                    ++i;
                }

                for (long id = firstId; id <= secondId; ++id) {
                    if (id < 10) {
                        continue;
                    }

                    String idStr = String.valueOf(id);

                    if (idStr.length() % 2 == 0) {
                        String firstHalf = idStr.substring(0, idStr.length() / 2);
                        String secondHalf = idStr.substring(idStr.length() / 2);

                        if (firstHalf.equals(secondHalf)) {
                            total += id;
                            continue;
                        }
                    }

                    int len = idStr.length() / 2;
                    do {
                        if (Math.floor(idStr.length() / (float) len) == Math.ceil(idStr.length() / (float) len)) {
                            int parts = idStr.length() / len;
                            int partSize = idStr.length() / parts;

                            String seg = null;
                            for (int p = 0; p < parts; ++p) {
                                String segment = idStr.substring(p * partSize, p * partSize + partSize);

                                if (seg == null) {
                                    seg = segment;
                                } else if (!seg.equals(segment)) {
                                    break;
                                } else if (p == parts - 1) {
                                    total += id;
                                }
                            }
                        }
                    } while ((len = len - 1) > 0);
                }
            }

            IO.println("Result: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
