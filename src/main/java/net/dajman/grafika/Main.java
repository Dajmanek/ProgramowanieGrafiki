package net.dajman.grafika;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    private static int size = 8;

    public static void main(final String... args) {
        // wektor uczacy
        final int[][] vector = new int[size][size];
        fill(vector, 0);
        vector[7][7] = 1;

        // wektor testujacy
        final int[][] vectorTest = new int[size][size];
        fill(vectorTest, 1);

        System.out.println("Wektor uczacy:");
        print(vector);

        System.out.println("Wektor testujacy: ");
        print(vectorTest);

        // liczenie macierzy i wag
        final int[][] weight = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                weight[i][j] = i != j
                        ? (2 * vector[i][j] - 1) * (2 * vector[j][i] - 1)
                        : 0;
            }
        }

        System.out.println("Wagi macierzy: ");
        print(weight);

        // mnozenie wektora testujacego przez macierz
        final int[] multipleVector = new int[size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                multipleVector[i] += (vectorTest[i][j] * weight[i][j]);
            }
        }

        System.out.print("Wektor testowy po pomnozeniu przez macierz: ");
        print(multipleVector);

        // koncowy wynik Y
        final int[][] result = new int[size][size];

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = multipleVector[i] == 0
                        ? vectorTest[i][j]
                        : multipleVector[i] > 0 ? 1 : 0;
            }
        }

        System.out.println("Wynik koncowy: ");
        print(result);
    }

    private static void fill(final int[][] matrix, final int value) {
        for(int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], value);
        }
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
