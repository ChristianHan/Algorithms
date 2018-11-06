package assignment2;

import java.util.ArrayList;


public class Heap {

	private ArrayList<Node> minHeap; // do not remove


	public int size;
	public Node[] minimumHeap;

	public Heap() {
		minHeap = new ArrayList<Node>(); // do not remove
		size = 0;
	}

	public Heap(int numVertices){
		minHeap = new ArrayList<Node>(numVertices);
		size = 0;
		minimumHeap = new Node[numVertices];
	}
	////////////////////////////////////////////////////////////////////////////

	public int parent(int i) { //returns the index of i's parent
		return (i - 1) / 2;
	}

	public int left(int i) { //returns the index of i's left child
		return (2 * i + 1);
	}

	public int right(int i) { //returns the index of i's right child
		return (2 * i + 2);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean hasParent(int i) {//does index i have a parent
		return parent(i) >= 0;
	}

	public boolean hasLeftChild(int i) {//does index i have a left child
		return left(i) < size;
	}

	public boolean hasRightChild(int i) {//does index i have a right child
		return right(i) < size;
	}

	public void arrayNodeSwap(int i1, int i2) {
		Node temp = minimumHeap[i1];
		minimumHeap[i1] = minimumHeap[i2];
		minimumHeap[i2] = temp;
	}

	public void nodeSwap(int i1, int i2){
		Node temp = minHeap.get(i1);
		minHeap.set(i1, minHeap.get(i2));
		minHeap.set(i2,temp);
	}
	////////////////////////////////////////////////////////////////////////////////

  // Given an ArrayList of Nodes, build a minimum heap keyed on each
  // Node's minDistance
  //
  // Time Complexity Requirement: theta(n)
	public void buildHeap(ArrayList<Node> nodes) {

		for(Node n:nodes){
			insertNode(n);
		}
	}

  // Insert a Node into the heap
  // Time Complexity Requirement: theta(log(n))
	public void insertNode(Node in) {

		size = size + 1;
		int lastIndex = size - 1;
		minimumHeap[lastIndex] = in;
		//minHeap.set(lastIndex, in);

		heapifyUp();
	}

	public void heapifyUp(){

		int index = size - 1;

		while(hasParent(index) && minimumHeap[parent(index)].getMinDistance() > minimumHeap[index].getMinDistance()){
			arrayNodeSwap(index,parent(index));
			index = parent(index);
		}

//		while(hasParent(index) && minHeap.get(parent(index)).getMinDistance() > minHeap.get(index).getMinDistance()){
//			nodeSwap(index, parent(index));
//			index = parent(index);
//		}
	}

  // Returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(1)
	public Node findMin() {

		return minimumHeap[0];
		//return minHeap.get(0);
	}

	public void heapifyDown(){
		int index = 0;
		while(hasLeftChild(index)){
			/* need to find the smaller child from the root index */
			int smallerChild = left(index);

			/* if the right child is smaller */
			if(hasRightChild(index) && minimumHeap[right(index)].getMinDistance() < minimumHeap[left(index)].getMinDistance()){
				smallerChild = right(index);
			}

//			if(hasRightChild(index) && minHeap.get(right(index)).getMinDistance() < minHeap.get(left(index)).getMinDistance()){
//				smallerChild = right(index);
//			}

			if(minimumHeap[index].getMinDistance() > minimumHeap[smallerChild].getMinDistance()){
				arrayNodeSwap(index,smallerChild);
				index = smallerChild;
			}


//			if(minHeap.get(index).getMinDistance() > minHeap.get(smallerChild).getMinDistance()){
//				nodeSwap(index,smallerChild);
//				index = smallerChild;
//			}
			else{
				break;
			}
		}
	}

  // Removes and returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(log(n))
	public Node extractMin() {

		Node min = findMin();
		int lastIndex = size - 1;
		//minHeap.set(0,minHeap.get(lastIndex));

		minimumHeap[0] = minimumHeap[lastIndex];
		size = size - 1;
		heapifyDown();

		return min;
	}
	
  public String toString() {
		String output = "";
//		for(int i = 0; i < minHeap.size(); i++) {
//			output+= minHeap.get(i).getNodeName() + " ";
//		}
		for(int i = 0; i < minimumHeap.length; i++){
			output+= minimumHeap[i].getNodeName() + " ";
		}

		return output;
	}
	
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

	public ArrayList<Node> toArrayList() {
		return minHeap;
	}
}
