package com.suraj.practice.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
class Edge {

  public int weight;
  public int source;
  public int destination;

  public Edge(int weight, int source, int destination) {
    this.weight = weight;
    this.source = source;
    this.destination = destination;
  }
}
public class KruskalMinSpanningTree {

  public static void main(String[] args) {
    Edge[] edges = new Edge[8];
    edges[0]=new Edge(4,0,1);
    edges[1]=new Edge(4,0,2);
    edges[2]=new Edge(2,1,2);
    edges[3]=new Edge(3,2,3);
    edges[4]=new Edge(2,2,5);
    edges[5]=new Edge(4,2,4);
    edges[6]=new Edge(3,3,4);
    edges[7]=new Edge(3,5,4);

    kruskalAlgo(edges,6);

  }

  /* not implemented */
  private static void kruskalAlgo(Edge[] edges,int noOfVertices) {
    Arrays.sort(edges,(a,b)->a.weight-b.weight);
    Set<Integer> includedVertex=new HashSet<>();
    System.out.println("Edge : Weight");
    for(Edge e:edges){
    }


  }

}
