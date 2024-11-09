import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        Arrays.sort(initial);
        int minInfectedCount = Integer.MAX_VALUE;
        int nodeToRemove = initial[0];

        for (int node : initial) {
            HashSet<Integer> infected = new HashSet<>();

            for (int infectedNode : initial) {
                if (infectedNode != node) {
                    dfs(graph, infected, infectedNode, new boolean[n]);
                }
            }

            int infectedCount = infected.size();
            if (infectedCount < minInfectedCount) {
                minInfectedCount = infectedCount;
                nodeToRemove = node;
            }
        }

        return nodeToRemove;
    }

    private void dfs(int[][] graph, HashSet<Integer> infected, int node, boolean[] visited) {
        if (visited[node]) return;
        visited[node] = true;
        infected.add(node);

        for (int neighbor = 0; neighbor < graph.length; neighbor++) {
            if (graph[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(graph, infected, neighbor, visited);
            }
        }
    }
}
