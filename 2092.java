import java.util.*;

class Solution {
    private Set<Integer> secrets;
    private Set<Integer> visit;
    private Map<Integer, Map<Integer, List<Integer>>> timeMap;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        secrets = new HashSet<>();
        secrets.add(0);
        secrets.add(firstPerson);

        timeMap = new TreeMap<>();

        for (int[] meeting : meetings) {
            int src = meeting[0];
            int dst = meeting[1];
            int t = meeting[2];

            timeMap.putIfAbsent(t, new HashMap<>());
            timeMap.get(t).putIfAbsent(src, new ArrayList<>());
            timeMap.get(t).putIfAbsent(dst, new ArrayList<>());
            timeMap.get(t).get(src).add(dst);
            timeMap.get(t).get(dst).add(src);
        }

        for (int t : timeMap.keySet()) {
            visit = new HashSet<>();
            Map<Integer, List<Integer>> adj = timeMap.get(t);

            for (int src : adj.keySet()) {
                if (secrets.contains(src)) {
                    dfs(src, adj);
                }
            }
        }

        return new ArrayList<>(secrets);
    }

    private void dfs(int src, Map<Integer, List<Integer>> adj) {
        if (visit.contains(src)) {
            return;
        }

        visit.add(src);
        secrets.add(src);

        for (int nei : adj.get(src)) {
            dfs(nei, adj);
        }
    }
}
