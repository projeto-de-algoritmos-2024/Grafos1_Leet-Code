import java.util.*;

public class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int[] componentSize = new int[n];
        int[] componentId = new int[n];
        int componentIdx = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = dfsComponent(graph, i, visited, componentId, componentIdx);
                for (int j = 0; j < n; j++) {
                    if (componentId[j] == componentIdx) {
                        componentSize[j] = size;
                    }
                }
                componentIdx++;
            }
        }

        int[] infectedInComponent = new int[componentIdx];
        for (int node : initial) {
            infectedInComponent[componentId[node]]++;
        }

        Arrays.sort(initial);
        int minNode = initial[0];
        int maxReducedInfection = 0;

        for (int node : initial) {
            int comp = componentId[node];
            if (infectedInComponent[comp] == 1) {
                int saved = componentSize[node];
                if (saved > maxReducedInfection || (saved == maxReducedInfection && node < minNode)) {
                    maxReducedInfection = saved;
                    minNode = node;
                }
            }
        }

        return minNode;
    }

    private int dfsComponent(int[][] graph, int node, boolean[] visited, int[] componentId, int componentIdx) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;
        componentId[node] = componentIdx;
        int size = 1;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[curr][neighbor] == 1 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    componentId[neighbor] = componentIdx;
                    stack.push(neighbor);
                    size++;
                }
            }
        }
        return size;
    }
}