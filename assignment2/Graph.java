package assignment2;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Node> vertices; //this is a list of all vertices, populated by Driver class.
	private Heap minHeap; 	//this is the heap to use for Dijkstra

    public ArrayList<Node> path;//used to return the minPath if it exists

	public Graph(int numVertices) {
		minHeap = new Heap();
		vertices = new ArrayList<Node>();
    // feel free to add anything else you may want
        path = new ArrayList<Node>();
	}

  // findShortestPathLength
  //
  // Returns the distance of the shortest path from root to x
  public int findShortestPathLength(Node root, Node x) {

	    /* initialization */
      //TODO: I think this initialization is already done for us in constructor?
	    for(Node vertex: vertices){
	        vertex.setMinDistance(Integer.MAX_VALUE);
	        vertex.parent = -1;
        }

        root.setMinDistance(0);

	    minHeap.buildHeap(vertices);
	    while(!minHeap.isEmpty()){

	        Node min = minHeap.findMin();
	        min.visited = true;

            ArrayList<Integer> weights = min.getWeights();
            ArrayList<Node> neighbors = min.getNeighbors();

	        for(int i = 0; i < min.getNeighbors().size(); i++){
	            Node neighbor = neighbors.get(i);
	            if(!neighbor.visited){ //can only check on nonvisited nodes

                    int combined_distance = min.getMinDistance() + weights.get(i);
                    if(combined_distance < neighbor.getMinDistance()){
                        neighbor.setMinDistance(combined_distance);
                        neighbor.parentArray.add(min);
                    }
                }
            }
            minHeap.extractMin();
        }
        //how do i check for unreachable
      if(x.getMinDistance() == Integer.MAX_VALUE){
	        return -1;
      }
	  return x.getMinDistance();
  }

  // findAShortestPath
  //
  // Returns a list of nodes represent one of the shortest paths
  // from root to x
  public ArrayList<Node> findAShortestPath(Node root, Node x){
	    int pathlength = findShortestPathLength(root,x);
	    if(pathlength == -1){
	        return null;
        }
        if(root == x){
            return null;
        }
	    Node child = x;
	    while(child.parentArray.get(0) != x ){
	        path.add(0,child);
	        child = child.parentArray.get(0);
	        if(child == root){
	            path.add(0,root);
	            break;
            }
        }

	  return path;
  }

  // eachShortestPathLength
  //
  // Returns an ArrayList of Nodes, where minDistance of each node is the
  // length of the shortest path from it to the root. This ArrayList
  // should contain every Node in the graph. Note that 
  // root.getMinDistance() = 0
  public ArrayList<Node> findEveryShortestPathLength(Node root) {
	    ArrayList<Node> everyPath = new ArrayList<>();
	    everyPath.addAll(vertices);

	    for(int i = 0; i < everyPath.size(); i++){
	        int distance = findShortestPathLength(root, everyPath.get(i));
	        everyPath.get(i).setMinDistance(distance);
        }
      return everyPath;
  }
  
  //returns edges and weights in a string.
  public String toString() {
    String o = "";
    for(Node v: vertices) {
      boolean first = true;
      o += "Node ";
      o += v.getNodeName();
      o += " has neighbors: ";
      ArrayList<Node> ngbr = v.getNeighbors();
      for(Node n : ngbr) {
        o += first ? n.getNodeName() : ", " + n.getNodeName();
        first = false;
      }
      first = true;
      o += " with weights ";
      ArrayList<Integer> wght= v.getWeights();
      for (Integer i : wght) {
        o += first ? i : ", " + i;
        first = false;
      }		
      o += System.getProperty("line.separator");
    
    }

    return o;
  }
  
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

  public Heap getHeap() {

    return minHeap;
  }
    
  public ArrayList<Node> getAllNodes(){
    return vertices;
  }
     
	//used by Driver class to populate each Node with correct neighbors and corresponding weights
	public void setEdge(Node curr, Node neighbor, Integer weight) {
		curr.setNeighborAndWeight(neighbor, weight);
	}
    //This is used by Driver.java and sets vertices to reference an ArrayList of all nodes.
  public void setAllNodesArray(ArrayList<Node> x) {
    vertices = x;	
  }    
}
