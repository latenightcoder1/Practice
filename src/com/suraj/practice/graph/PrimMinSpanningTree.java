package com.suraj.practice.graph;

import java.util.HashSet;
import java.util.Set;

public class PrimMinSpanningTree {

  public void Prim(int G[][], int V) {
    Set<Integer> includedVertex = new HashSet<>();
    includedVertex.add(0);
    System.out.println("Edge : Weight");
    while (includedVertex.size() < V) {
      int start = -1;
      int end = -1;
      int minEdge = Integer.MAX_VALUE;
      for (int i : includedVertex) {
        int[] edges = G[i];
        for (int j = 0; j < edges.length; j++) {
          if (!includedVertex.contains(j) && edges[j] != 0 && edges[j] < minEdge) {
            minEdge = edges[j];
            start = i;
            end = j;
          }
        }
      }
      System.out.println(start + " - " + end + " :  " + minEdge);
      includedVertex.add(end);
    }
  }

  public static void main(String[] args) {
    PrimMinSpanningTree g = new PrimMinSpanningTree();
    int V = 5;

    // create a 2d array of size 5x5
    // for adjacency matrix to represent graph
    int[][] G = {{0, 9, 75, 0, 0},//
        {9, 0, 95, 19, 42},//
        {75, 95, 0, 51, 66},//
        {0, 19, 51, 0, 31},//
        {0, 42, 66, 31, 0}};

    g.Prim(G, V);
  }

}
