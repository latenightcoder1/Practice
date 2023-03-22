package com.suraj.practice.graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSOfGraph {

  public static void main(String[] args) {
    int[][] edges = new int[4][2];
    edges[0] = new int[]{0, 1};
    edges[1] = new int[]{0, 2};
    edges[2] = new int[]{1, 2};
    edges[3] = new int[]{2, 3};
    doBfs(edges);
  }

  private static void doBfs(int[][] edges) {
    Map<Integer, Set<Integer>> vertexAndAdjacent = new HashMap<>();
    for (int[] edge : edges) {
      Set<Integer> firstVertexAdjacent = vertexAndAdjacent.getOrDefault(edge[0], new HashSet<>());
      firstVertexAdjacent.add(edge[1]);
      vertexAndAdjacent.put(edge[0], firstVertexAdjacent);

      Set<Integer> secondVertexAdjacent = vertexAndAdjacent.getOrDefault(edge[1], new HashSet<>());
      secondVertexAdjacent.add(edge[0]);
      vertexAndAdjacent.put(edge[1], secondVertexAdjacent);
    }

    Set<Integer> visitedVertex=new HashSet<>();
    while (visitedVertex.size() < vertexAndAdjacent.keySet().size()) {
      vertexAndAdjacent.keySet().removeAll(visitedVertex);
      visitedVertex=new HashSet<>();
      int startVertex = 0;
      for (int i : vertexAndAdjacent.keySet()) {
        startVertex = i;
      }
      visitedVertex = doBfs(edges, vertexAndAdjacent, visitedVertex, startVertex);
    }
  }

  private static Set<Integer> doBfs(int[][] edges, Map<Integer, Set<Integer>> vertexAndAdjacent,
      Set<Integer> visitedVertex, int startVertex) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(startVertex);
    while (!queue.isEmpty()) {
      int vertex = queue.remove();
      visitedVertex.add(vertex);
      System.out.println(vertex);
      Set<Integer> adjacents = vertexAndAdjacent.get(vertex);
      adjacents.removeAll(visitedVertex);
      for (Integer noLoopingIndex : adjacents) {
        queue.add(noLoopingIndex);
      }
    }
    return visitedVertex;
  }


}
