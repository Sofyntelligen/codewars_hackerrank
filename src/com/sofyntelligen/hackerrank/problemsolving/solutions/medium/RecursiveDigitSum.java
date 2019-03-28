package com.sofyntelligen.hackerrank.problemsolving.solutions.medium;


import java.util.*;

public class RecursiveDigitSum {

    // Complete the superDigit function below.
    static Integer superDigit(String number, int k) {
        Long result = number.chars().mapToLong(x -> Long.valueOf(String.valueOf((char)x))).sum();

        System.out.println(result);

        if (k != 1){
            result = result * k;
            k = 1;
            System.out.println();
        }
        System.out.println(result);
        return String.valueOf(result).length() == 1 ? Integer.parseInt(String.valueOf(result)) : superDigit(String.valueOf(result), k) ;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);

        System.out.println(result);

        scanner.close();
    }
}
