package com.suraj.practice.ds;

class Node {

  int value;
  Node previous;
  Node next;

  public Node(int value) {
    this.value = value;
  }

  public Node add(Node n){
    Node result=this;
    Node temp=this;
    while(temp.next!=null){
      temp=temp.next;
    }
    temp.next=n;
    n.previous=temp;
    return result;
  }
  @Override
  public String toString() {
    return "Node{" +
        "value=" + value +
        ", previous=" + previous +
        ", next=" + next +
        '}';
  }
}

public class DoublyLinkedList {

  public static void main(String[] args) {
    Node node1=new Node(1);
    Node node2=new Node(2);
    Node node3=new Node(3);
System.out.println(node1.add(node2).add(node3));
  }

}
