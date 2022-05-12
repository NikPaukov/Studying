package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        int[] hundred_random = arrayCreator(true, 100_000);
        int[] mil_random = arrayCreator(true, 1_000_000);
        int[] hundred_iter = arrayCreator(false, 100_000);
        int[] mil_iter = arrayCreator(false, 1_000_000);
        int[][] arrs = new int[][]{hundred_random, mil_random, hundred_iter, mil_iter};

        QuickSort qs = new QuickSort();
        MergeSort ms = new MergeSort();
        ShellSort ss = new ShellSort();
        HeapSort hs = new HeapSort();
        long start, end;

        System.out.println("Quick Sort 100k Random, 1m Random, 100k sorted, 1m Sorted:");
        for (int[] arr : arrs) {
            int[] array = arr.clone();
            start = System.nanoTime();
            qs.sort(array, 0, array.length - 1);
            end = System.nanoTime();
            System.out.println("is sorted?  " + isSortedArray(array, array.length));
            System.out.printf("Result: %.1f ms\n\n", (end - start) / 1000000.);
        }

        System.out.println("Merge Sort 100k Random, 1m Random, 100k sorted, 1m Sorted:");
        for (int[] arr : arrs) {
            int[] array = arr.clone();
            start = System.nanoTime();
            ms.sort(array, array.length);
            end = System.nanoTime();
            System.out.println("is sorted?  " + isSortedArray(array, array.length));
            System.out.printf("Result: %.1f ms\n\n", (end - start) / 1000000.);
        }

        System.out.println("Shell Sort 100k Random, 1m Random, 100k sorted, 1m Sorted:");
        for (int[] arr : arrs) {
            int[] array = arr.clone();
            start = System.nanoTime();
            ss.sort(array);
            end = System.nanoTime();
            System.out.println("is sorted?  " + isSortedArray(array, array.length));
            System.out.printf("Result: %.1f ms\n\n", (end - start) / 1000000.);
        }

        System.out.println("Heap Sort 100k Random, 1m Random, 100k sorted, 1m Sorted:");
        for (int[] arr : arrs) {
            int[] array = arr.clone();
            start = System.nanoTime();
            hs.sort(array);
            end = System.nanoTime();
            System.out.println("is sorted?  " + isSortedArray(array, array.length));
            System.out.printf("Result: %.1f ms\n\n", (end - start) / 1000000.);
        }

    }


    private static boolean isSortedArray(int[] array, int n) {
        if (n == 1 || n == 0) return true;
        for (int i = 1; i < n; i++) {
            if (array[i] < array[i - 1]) return false;
        }
        return true;
    }

    public static int[] arrayCreator(boolean random, int size) {
        int[] res = new int[size];
        if (random) {
            Random random1 = new Random();
            for (int i = 0; i < res.length; i++) {
                res[i] = random1.nextInt();
            }
        }
        if (!random) {
            for (int i = 0; i < res.length; i++) {
                res[i] = i;
            }
        }
        return res;
    }

}
