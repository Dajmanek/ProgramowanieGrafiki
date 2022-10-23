package net.dajman.grafika;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(final String... args) {
        // wektory uczace
        final int[] vector1 = new int[] {1, 1, 2, 2, 3, 3, 4, 4};
        final int[] vector2 = new int[] {2, 2, 1, 1, 4, 4, 3, 3};

        // wektor testujacy
        final int[] vectorTest = new int[] {1, 1, 0, 0, 2, 3, 4, 5};

        System.out.println("Wektory uczace:");

        System.out.print("1) ");
        print(vector1);

        System.out.print("2) ");
        print(vector2);

        System.out.println("Wektor testujacy: ");
        print(vectorTest);

        // liczenie macierzy i wag
        final int[][] weight = new int[vector1.length][vector2.length];

        for(int i = 0; i < vector1.length; i++) {
            for(int j = 0; j < vector2.length; j++) {
                weight[i][j] = i != j
                        ? ((2 * vector1[i] - 1) * (2 * vector1[j] - 1) + (2 * vector2[i] - 1) * (2 * vector2[j] - 1))
                        : 0;
            }
        }

        System.out.println("Wagi macierzy: ");
        print(weight);

        // mnozenie wektora testujacego przez macierz
        final int[] multipleVector = new int[vector1.length];

        for(int i = 0; i < vector1.length; i++) {
            for(int j = 0; j < vector2.length; j++) {
                multipleVector[i] += (vectorTest[j] * weight[i][j]);
            }
        }

        System.out.print("Wektor testowy po pomnozeniu przez macierz: ");
        print(multipleVector);

        // koncowy wynik Y
        final int[] result = new int[multipleVector.length];

        for(int i = 0; i < result.length; i++) {
            result[i] = multipleVector[i] > 0
                    ?  1
                    : multipleVector[i] == 0
                        ? vectorTest[i]
                        : 0;
        }

        System.out.println("Wynik koncowy: ");
        print(result);
    }

    private static void print(final int... vector) {
        System.out.println(Arrays.toString(vector));
    }

    private static void print(final int[][] matrix) {
        final int maxValueLen = Arrays.stream(matrix)
                .map(Arrays::stream)
                .flatMap(IntStream::boxed)
                .map(integer -> Integer.toString(integer))
                .map(String::length)
                .mapToInt(Integer::intValue)
                .max().orElse(1);

        Arrays.stream(matrix).forEach(
                array -> {
                    Arrays.stream(array).forEach(
                        value -> {

                            final String valueString = Integer.toString(value);

                            final int prefixLength = Math.max(maxValueLen - valueString.length(), 0);
                            final String prefix = " ".repeat(prefixLength);

                            System.out.print(" " + prefix + valueString + " |");
                        });
                    System.out.println();
                });
    }

}
