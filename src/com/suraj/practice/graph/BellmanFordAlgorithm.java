package com.suraj.practice.graph;

class Edge1 {

  public int s;
  public int d;
  public int w;

  public Edge1(int s, int d, int w) {
    this.s = s;
    this.d = d;
    this.w = w;
  }
}

public class BellmanFordAlgorithm {

  public static void main(String[] args) {
    Edge1[] edges = new Edge1[8];

    // add edge 0-1 (or A-B in above figure)
    edges[0]= new Edge1(0, 1, -1);
    // add edge 0-2 (or A-C in above figure)
    edges[1]= new Edge1(0, 2, 4);
    // add edge 1-2 (or B-C in above figure)
    edges[2]= new Edge1(1, 2, 3);
    // add edge 1-3 (or B-D in above figure)
    edges[3]= new Edge1(1, 3, 2);
    // add edge 1-4 (or B-E in above figure)
    edges[4]= new Edge1(1, 4, 2);
    // add edge 3-2 (or D-C in above figure)
    edges[5]= new Edge1(3, 2, 5);
    // add edge 3-1 (or D-B in above figure)
    edges[6]= new Edge1(3, 1, 1);
    // add edge 4-3 (or E-D in above figure)
    edges[7]= new Edge1(4, 3, -3);

    bellmanFord(edges, 5, 0);
  }

  static void bellmanFord(Edge1[] edges, int vertex, int src) {
    int dist[] = new int[vertex];

    // Step 1: Initialize distances from src to all other
    // vertices as INFINITE
    for (int i = 0; i < vertex; ++i) {
      dist[i] = Integer.MAX_VALUE;
    }

    dist[src] = 0;

    // Step 2: Relax all edges |V| - 1 times. A simple
    // shortest path from src to any other vertex can
    // have at-most |V| - 1 edges
    for (int i = 1; i < vertex; ++i) {
      int change = 0;
      for (int j = 0; j < edges.length; ++j) {
        int u = edges[j].s;
        int v = edges[j].d;
        int weight = edges[j].w;
        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
          dist[v] = dist[u] + weight;
          change++;
        }
      }
      if (change == 0) {
        break;
      }
    }

    // Step 3: check for negative-weight cycles. The above
    // step guarantees shortest distances if graph doesn't
    // contain negative weight cycle. If we get a shorter
    // path, then there is a cycle.
    for (int j = 0; j < edges.length; ++j) {
      int u = edges[j].s;
      int v = edges[j].d;
      int weight = edges[j].w;
      if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
        System.out.println("Graph contains negative weight cycle");
        return;
      }
    }

    for (int i = 0; i < dist.length; i++) {
      System.out.println("Distance from " + src + " to " + i + " is " + dist[i]);
    }
  }
}
