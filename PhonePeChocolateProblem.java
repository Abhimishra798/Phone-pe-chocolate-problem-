import java.util.*;

public class FuelTankChocolateProblem {

    static class State {
        int city, fuel, cost;

        State(int city, int fuel, int cost) {
            this.city = city;
            this.fuel = fuel;
            this.cost = cost;
        }
    }

    static class Edge {
        int to, fuelCost;

        Edge(int to, int fuelCost) {
            this.to = to;
            this.fuelCost = fuelCost;
        }
    }

    public static int minCost(
            int n, int C,
            int[] fuelCost,
            List<List<Edge>> graph,
            int src, int dest
    ) {
        int[][] dist = new int[n][C + 1];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<State> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        dist[src][C] = 0;
        pq.add(new State(src, C, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.cost > dist[cur.city][cur.fuel]) continue;

            if (cur.city == dest) return cur.cost;

            // Buy fuel
            if (cur.fuel < C) {
                int newCost = cur.cost + fuelCost[cur.city];
                if (newCost < dist[cur.city][cur.fuel + 1]) {
                    dist[cur.city][cur.fuel + 1] = newCost;
                    pq.add(new State(cur.city, cur.fuel + 1, newCost));
                }
            }

            // Travel roads
            for (Edge e : graph.get(cur.city)) {
                if (cur.fuel >= e.fuelCost) {
                    int newFuel = cur.fuel - e.fuelCost;
                    if (cur.cost < dist[e.to][newFuel]) {
                        dist[e.to][newFuel] = cur.cost;
                        pq.add(new State(e.to, newFuel, cur.cost));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int n = 4;        // cities
        int C = 5;        // fuel capacity

        int[] fuelCost = {5, 2, 4, 1}; // cost per liter at each city

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Roads (bidirectional)
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));

        graph.get(1).add(new Edge(3, 1));
        graph.get(3).add(new Edge(1, 1));

        graph.get(3).add(new Edge(2, 4));
        graph.get(2).add(new Edge(3, 4));

        graph.get(2).add(new Edge(0, 3));
        graph.get(0).add(new Edge(2, 3));

        int src = 0;
        int dest = 3;

        System.out.println(minCost(n, C, fuelCost, graph, src, dest));
    }
}
