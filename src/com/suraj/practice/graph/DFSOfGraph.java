package com.suraj.practice.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DFSOfGraph {

  public static void main(String[] args) {
    int[][] edges = new int[4][2];
    edges[0] = new int[]{0, 1};
    edges[1] = new int[]{0, 2};
    edges[2] = new int[]{1, 2};
    edges[3] = new int[]{2, 3};
    doDfs(edges);
  }

  private static void doDfs(int[][] edges) {
    Map<Integer, Set<Integer>> vertexAndAdjacent = new HashMap<>();
    for (int[] edge : edges) {
      Set<Integer> firstVertexAdjacent = vertexAndAdjacent.getOrDefault(edge[0], new HashSet<>());
      firstVertexAdjacent.add(edge[1]);
      vertexAndAdjacent.put(edge[0], firstVertexAdjacent);

      Set<Integer> secondVertexAdjacent = vertexAndAdjacent.getOrDefault(edge[1], new HashSet<>());
      secondVertexAdjacent.add(edge[0]);
      vertexAndAdjacent.put(edge[1], secondVertexAdjacent);
    }
    Set<Integer> visitedVertex = new HashSet<>();
    int startVertex=edges[0][0];
    Stack<Integer> stack = new Stack<>();
    stack.push(startVertex);
    while (!stack.empty()) {
      Integer vertex = stack.pop();
      visitedVertex.add(vertex);

      Set<Integer> adjacents = vertexAndAdjacent.get(vertex);
      adjacents.removeAll(visitedVertex);
      for (Integer nonLoopingAdjacent : adjacents) {
        stack.push(nonLoopingAdjacent);
      }
    }
  }

}
