package assignment2;

// feel free to add things to this file. Just don't remove anything

import java.util.*;
public class Node {
	
  private int minDistance;
  private int nodeName;
  private ArrayList<Node> neighbors;
  private ArrayList<Integer> weights;

  /* my variables */
  public int parent;
  public ArrayList<Node> parentArray;
  public boolean visited;
  
  public Node(int x) {
	nodeName = x;
	minDistance = Integer.MAX_VALUE;
	neighbors = new ArrayList<Node>();
	weights = new ArrayList<Integer>();

	/* my variables */
	parent = -1;
	parentArray = new ArrayList<Node>();
	visited = false;
  }
	
  public void setNeighborAndWeight(Node n, Integer w) {
  	neighbors.add(n);
  	weights.add(w);
  }
	
  public ArrayList<Node> getNeighbors(){
  	return neighbors;
  }
	
  public ArrayList<Integer> getWeights(){
  	return weights;
  }
	
  public int getMinDistance() {
  	return minDistance;
  }
	
  public void setMinDistance(int x) {
	minDistance = x;
  }

  public int getNodeName() {
	return nodeName;

  }
}
