import java.util.*;

public class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (i != j && inRange(bombs[i], bombs[j])) {
                    adj.get(i).add(j);
                }
            }
        }

        int maxDetonated = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int count = dfs(i, adj, visited);
            maxDetonated = Math.max(maxDetonated, count);
        }

        return maxDetonated;
    }

    private boolean inRange(int[] b1, int[] b2) {
        long dx = b1[0] - b2[0];
        long dy = b1[1] - b2[1];
        long distanceSquared = dx * dx + dy * dy;
        long rangeSquared = (long) b1[2] * b1[2];
        return distanceSquared <= rangeSquared;
    }

    private int dfs(int current, List<List<Integer>> adj, boolean[] visited) {
        visited[current] = true;
        int count = 1;
        for (int neighbor : adj.get(current)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, adj, visited);
            }
        }
        return count;
    }
}
