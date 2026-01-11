import java.util.*;

public class PhonePeChocolateProblem {

    static int totalChocolates = 0;

    static void dfs(int box, List<List<Integer>> graph,
                    int[] chocolates, boolean[] visited) {

        visited[box] = true;
        totalChocolates += hocolates[bo
        for (int next : graph.get(bo) {
            if (!visited[next]) {
                dfs(next, graph, chocolates, visited);
            }
        }
    }

    public static void main(String[] args) {

        int n = 5;

        int[] chocolates = {5, 10, 20, 15, 25};

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(4);

        boolean[] visited = new boolean[n];


        dfs(0, graph, chocolates, visited);

        System.out.println(totalChocolates);
    }
}
