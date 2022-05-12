package com.company;

import java.util.ArrayList;
import java.util.List;

public class HeapSort {

    public void sort(int arrA[]) {
        int size = arrA.length;

        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(arrA, size, i);

        for (int i = size - 1; i >= 0; i--) {

            int x = arrA[0];
            arrA[0] = arrA[i];
            arrA[i] = x;

            heapify(arrA, i, 0);
        }
    }

    private void heapify(int arr[], int heapSize, int i) {
        int largest = i;
        int leftChildIdx = 2 * i + 1;
        int rightChildIdx = 2 * i + 2;

        if (leftChildIdx < heapSize && arr[leftChildIdx] > arr[largest])
            largest = leftChildIdx;

        if (rightChildIdx < heapSize && arr[rightChildIdx] > arr[largest])
            largest = rightChildIdx;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, heapSize, largest);
        }
    }
}