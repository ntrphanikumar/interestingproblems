package interesting.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MissingNumbers {
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        final int minBrr = brr.stream().reduce((a, b) -> a < b ? a : b).get();
        final int[] counts = new int[100];
        brr.forEach(val -> counts[val - minBrr]++);
        arr.stream().filter(val -> val >= minBrr && val <= minBrr + 100).forEach(val -> counts[val - minBrr]--);
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            if(counts[i]>0) {
                results.add(minBrr+i);
            }
        }
        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = missingNumbers(arr, brr);
        System.out.println(result);
        bufferedReader.close();
    }
}
