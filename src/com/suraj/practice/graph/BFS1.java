package com.suraj.practice.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFS1 {

  public static void main(String[] args) {
    BFS1 bfs = new BFS1();
    System.out.println(bfs.bfsOfGraph(6, Arrays.asList(Arrays.asList(5, 4), Arrays.asList(0, 1),
        Arrays.asList(0, 2),
        Arrays.asList(0, 3), Arrays.asList(2, 4))));
  }

  public ArrayList<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {
    Map<Integer, Set<Integer>> adjacencyMap = new HashMap<>();
    System.out.println("hello");
    for (List<Integer> edge : adj) {
      Set<Integer> set1 = adjacencyMap.getOrDefault(edge.get(0), new HashSet<>());
      set1.add(edge.get(1));
      adjacencyMap.put(edge.get(0), set1);

      Set<Integer> set2 = adjacencyMap.getOrDefault(edge.get(1), new HashSet<>());
      set2.add(edge.get(0));
      adjacencyMap.put(edge.get(1), set2);
    }
    ArrayList<Integer> visitedVertex = new ArrayList<>();
    visitedVertex.add(0);
    int i = 0;
    while (visitedVertex.size() < adjacencyMap.keySet().size()) {
      Set<Integer> adjacents = adjacencyMap.get(visitedVertex.get(i));
      for (int j : adjacents) {
        if (!visitedVertex.contains(j)) {
          visitedVertex.add(j);
        }
      }
      i++;
    }
    return visitedVertex;
    // Code here
  }
}
