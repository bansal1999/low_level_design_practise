package org.example;

import javax.imageio.ImageTranscoder;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static void DFS(int[][] graph, int start, boolean[] visited) {
        // Mark the current node as visited
        visited[start] = true;
        System.out.print(start + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < graph[start].length; i++) {
            if (graph[start][i] == 1 && !visited[i]) {
                DFS(graph, i, visited);
            }
        }
    }

    static void kth_largest_maxHeap(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // Build a max heap using the first k elements
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }
        // Process the remaining elements
        for (int i = k; i < arr.length; i++) {
            // If the current element is greater than the root of the max heap
            if (arr[i] > pq.peek()) {
                // Remove the root and add the current element
                pq.poll();
                pq.add(arr[i]);
            }

        }
        // The root of the max heap is the k-th largest element
        System.out.println("The " + k + "-th largest element is: " + pq.peek());
    }


    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 5, 6};

        kth_largest_maxHeap(arr, 3);
    }
}