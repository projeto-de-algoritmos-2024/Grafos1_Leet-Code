import java.util.*;

public class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int maxDetonated = 0;

        for (int i = 0; i < n; i++) {
            maxDetonated = Math.max(maxDetonated, bfs(i, bombs, n));
        }

        return maxDetonated;
    }

    private int bfs(int start, int[][] bombs, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(start);
        visited[start] = true;
        int detonatedCount = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < n; next++) {
                if (!visited[next] && inRange(bombs[current], bombs[next])) {
                    visited[next] = true;
                    queue.offer(next);
                    detonatedCount++;
                }
            }
        }

        return detonatedCount;
    }

    private boolean inRange(int[] bomb1, int[] bomb2) {
        long dx = bomb1[0] - bomb2[0];
        long dy = bomb1[1] - bomb2[1];
        long distanceSquared = dx * dx + dy * dy;
        long rangeSquared = (long) bomb1[2] * bomb1[2];
        return distanceSquared <= rangeSquared;
    }
}
