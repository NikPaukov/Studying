package com.company;

public class QuickSort {
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public void sort(int[] arr, int low, int high) {

        int pivot = arr[low + (high - low) / 2];
        int i = low;
        int j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        if(low < j)
            sort(arr,low,j);
        if(i < high)
            sort(arr,i,high);
    }


}