package finalproject;

import java.util.ArrayList;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	int size;
	Tile[] heap = new Tile[100];
	// TODO level 3: implement the constructor for the priority queue
	public void add(Tile t) {
		size++;
		heap[size] = t;
		int i = size;
		while (i > 1 && heap[i].costEstimate < heap[i/2].costEstimate) {
			Tile temp = heap[i];
			heap[i] = heap[i/2];
			heap[i/2] = temp;
			i = i/2;
		}
	}
	private void downHeap(int startInd,int endInd ){
		int i = startInd;
		while(2*i <= endInd){
			int j = 2*i;
			if(j < endInd && heap[j].costEstimate > heap[j+1].costEstimate){
				j++;
			}
			if(heap[i].costEstimate <= heap[j].costEstimate){
				break;
			}
			Tile temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
			i = j;
		}
	}
	public TilePriorityQ (ArrayList<Tile> vertices) {
        for (Tile vertex : vertices) {
            add(vertex);
        }
	}
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Tile t) {
		for (int i = 1; i <= size; i++) {
			if (heap[i] == t) {
				return true;
			}
		}
		return false;
	}
	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		Tile min = heap[1];
		heap[1] = heap[size];
		size--;
		downHeap(1,size);
		return min;
	}
	
	// TODO level 3: implement updateKeys as described in the pdf
	//updateKeys(Tile t, Tile newPred, double newEstimate): a method that takes as input a Tile t.If such tile belongs to the queue,the method updates which Tile is predicted to be the predecessor of t in the minimum weight path that leads from a source tile to t as well as the estimated cost for this path. Note that this information should be stored in the appropriate fields from the Tile class, and after these updates, the queue should remained a valid min heap.
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
for (int i = 1; i <= size; i++) {
			if (heap[i] == t) {
				heap[i].predecessor = newPred;
				heap[i].costEstimate = newEstimate;
				downHeap(i,size);
			}
		}
	}
}
