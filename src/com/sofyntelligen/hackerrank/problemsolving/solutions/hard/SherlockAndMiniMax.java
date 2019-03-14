package com.sofyntelligen.hackerrank.problemsolving.solutions.hard;

import java.io.IOException;
import java.util.Arrays;

public class SherlockAndMiniMax {

    public static void main(String[] args) throws IOException {

        int n = 10;
        int[] arr = new int[n];


        String[] arrItems = "46 64 26 82 18 106 60 138 194 22".split(" ");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int p = 82;
        int q = 182;

        int result = sherlockAndMinimax(arr, p, q);

        System.out.println(String.valueOf(result));

    }

    static int sherlockAndMinimax(int[] arr, int p, int q) {


        Arrays.sort(arr);
        Integer n = Integer.valueOf("" + Arrays.stream(arr).distinct().count());
        System.out.println(n);

        int i = Arrays.binarySearch(arr, 0, n, p);
        int j = Arrays.binarySearch(arr, 0, n, q);

        System.out.println(i);
        System.out.println(j);


        int curMaxMin;
        int maxMin = 0;
        int maxMinVal = 0;

        if (i < 0) {
            if (((i = ~i) == 0 || p > arr[i - 1] + ((arr[i] - arr[i - 1]) >> 1))) {
                maxMin = arr[i] - p;
                maxMinVal = p;
            } else {
                --i;
            }
        }

        if (j < 0 && ((j = ~j) == n || q < arr[j - 1] + ((arr[j] - arr[j - 1]) >> 1))) {
            curMaxMin = q - arr[j - 1];
            if (curMaxMin > maxMin) {
                maxMin = curMaxMin;
                maxMinVal = q;
            }
            System.out.println("sss");
            --j;
        }

        while (i < j) {
            curMaxMin = (arr[i + 1] - arr[i]) >> 1;
            if (curMaxMin > maxMin || (curMaxMin == maxMin && maxMinVal == q)) {
                maxMin = curMaxMin;
                maxMinVal = arr[i] + curMaxMin;
            }
            ++i;
        }

        return maxMinVal;
    }

}
