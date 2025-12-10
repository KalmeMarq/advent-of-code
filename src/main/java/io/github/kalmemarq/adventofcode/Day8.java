package io.github.kalmemarq.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day8 {
    public static void part1(boolean test) {
        Path inputPath = Utils.getJarResourcesPath().resolve(test ? "day8/test_input.txt" : "day8/input.txt");
        try {
            List<String> lines = Files.readAllLines(inputPath);
            List<Point> points = new ArrayList<>();

            for (String line : lines) {
                String[] coords = line.split(",");
                points.add(new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2])));
            }

            List<PointPairWithDistance> distancePairs = new ArrayList<>();

            for (int i = 0; i < points.size(); ++i) {
                Point a = points.get(i);

                for (int j = i + 1; j < points.size(); ++j) {
                    Point b = points.get(j);

                    distancePairs.add(new PointPairWithDistance(Math.sqrt(Math.powExact(b.x - a.x, 2) + Math.powExact(b.y - a.y, 2) + Math.powExact(b.z - a.z, 2)), a, b));
                }
            }

            distancePairs.sort(Comparator.comparingDouble(p -> p.distance));

            List<Set<Point>> circuits = new ArrayList<>();
            Map<Point, Set<Point>> pointToCircuit = new HashMap<>();

            int iterations = test ? 10 : 1000;

            for (int i = 0; i < iterations; ++i) {
                PointPairWithDistance distancePair = distancePairs.removeFirst();

                if (pointToCircuit.containsKey(distancePair.p1) && !pointToCircuit.containsKey(distancePair.p2)) {
                    pointToCircuit.get(distancePair.p1).add(distancePair.p2);
                    pointToCircuit.put(distancePair.p2, pointToCircuit.get(distancePair.p1));
                } else if (!pointToCircuit.containsKey(distancePair.p1) && pointToCircuit.containsKey(distancePair.p2)) {
                    pointToCircuit.get(distancePair.p2).add(distancePair.p1);
                    pointToCircuit.put(distancePair.p1, pointToCircuit.get(distancePair.p2));
                } else if (!pointToCircuit.containsKey(distancePair.p1) && !pointToCircuit.containsKey(distancePair.p2)) {
                    Set<Point> circuit = new HashSet<>();
                    circuit.add(distancePair.p1);
                    circuit.add(distancePair.p2);
                    circuits.add(circuit);
                    pointToCircuit.put(distancePair.p1, circuit);
                    pointToCircuit.put(distancePair.p2, circuit);
                } else if (pointToCircuit.get(distancePair.p1) != pointToCircuit.get(distancePair.p2)) {
                    Set<Point> p2Circuit = pointToCircuit.get(distancePair.p2);
                    pointToCircuit.get(distancePair.p1).addAll(p2Circuit);
                    for (Point p : p2Circuit) {
                        pointToCircuit.put(p, pointToCircuit.get(distancePair.p1));
                    }
                    circuits.remove(p2Circuit);
                }
            }

            circuits.sort((a, b) -> b.size() - a.size());

            int total = 0;
            for (int i = 0; i < 3; ++i) {
                if (i == 0) total = circuits.get(i).size();
                else total *= circuits.get(i).size();
            }

            IO.println("Total: " + total);
        } catch (IOException e) {
            IO.println("Error: " + e);
        }
    }

    public static void part2(boolean test) {
        Path inputPath = Utils.getJarResourcesPath().resolve(test ? "day8/test_input.txt" : "day8/input.txt");
        try {
            List<String> lines = Files.readAllLines(inputPath);
            List<Point> points = new ArrayList<>();

            for (String line : lines) {
                String[] coords = line.split(",");
                points.add(new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2])));
            }

            List<PointPairWithDistance> distancePairs = new ArrayList<>();

            for (int i = 0; i < points.size(); ++i) {
                Point a = points.get(i);

                for (int j = i + 1; j < points.size(); ++j) {
                    Point b = points.get(j);

                    distancePairs.add(new PointPairWithDistance(Math.sqrt(Math.powExact(b.x - a.x, 2) + Math.powExact(b.y - a.y, 2) + Math.powExact(b.z - a.z, 2)), a, b));
                }
            }

            distancePairs.sort(Comparator.comparingDouble(p -> p.distance));

            List<Set<Point>> circuits = new ArrayList<>();
            Map<Point, Set<Point>> pointToCircuit = new HashMap<>();

            int iterations = distancePairs.size();

            for (int i = 0; i < iterations; ++i) {
                PointPairWithDistance distancePair = distancePairs.removeFirst();

                if (pointToCircuit.containsKey(distancePair.p1) && !pointToCircuit.containsKey(distancePair.p2)) {
                    pointToCircuit.get(distancePair.p1).add(distancePair.p2);
                    pointToCircuit.put(distancePair.p2, pointToCircuit.get(distancePair.p1));
                } else if (!pointToCircuit.containsKey(distancePair.p1) && pointToCircuit.containsKey(distancePair.p2)) {
                    pointToCircuit.get(distancePair.p2).add(distancePair.p1);
                    pointToCircuit.put(distancePair.p1, pointToCircuit.get(distancePair.p2));
                } else if (!pointToCircuit.containsKey(distancePair.p1) && !pointToCircuit.containsKey(distancePair.p2)) {
                    Set<Point> circuit = new HashSet<>();
                    circuit.add(distancePair.p1);
                    circuit.add(distancePair.p2);
                    circuits.add(circuit);
                    pointToCircuit.put(distancePair.p1, circuit);
                    pointToCircuit.put(distancePair.p2, circuit);
                } else if (pointToCircuit.get(distancePair.p1) != pointToCircuit.get(distancePair.p2)) {
                    Set<Point> p2Circuit = pointToCircuit.get(distancePair.p2);
                    pointToCircuit.get(distancePair.p1).addAll(p2Circuit);
                    for (Point p : p2Circuit) {
                        pointToCircuit.put(p, pointToCircuit.get(distancePair.p1));
                    }
                    circuits.remove(p2Circuit);
                }

                if (circuits.size() == 1 && circuits.getFirst().size() == points.size()) {
                    IO.println("Result: " + (distancePair.p1().x * distancePair.p2().x));
                    break;
                }
            }
        } catch (IOException e) {
            IO.println("Error: " + e);
        }
    }

    public record PointPairWithDistance(double distance, Point p1, Point p2) {
    }

    public record Point(long x, long y, long z) {
    }
}
