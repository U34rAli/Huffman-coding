

/* 
 * T is used to make this heap generic
 * T will be used just like a normal object is used.
 * <T extends Comparable> means that T must be comparable, 
 * that means any class that will substitute T should implement interface comparable
 * See the section 2.5.2 of book for more details
 */
public class PriorityHeap<T extends Comparable>
{

	T[] heap;
	int size=0; //logical size of heap
	@SuppressWarnings("unchecked")
	public PriorityHeap()
	{
		heap=(T[]) new Comparable[15];
		//Comparable is parent of our class, so its valid. see book section 2.5.2
	}
	@SuppressWarnings("unchecked")
	public PriorityHeap(int n)
	{
		heap=(T[]) new Comparable[n]; //
		
	}
	public void insert(T entry)
	{
		if(!isFull())
		{
			heap[size++]=entry;
			upHeap();
		}
		else
			System.out.println("Heap Full");
	}
	public T delete()
	{
		if(!isEmpty())
		{
			T deleted=heap[0]; //get the root node
			heap[0]=heap[size-1]; //move last to root
			heap[size-1]=null;		// make it unreachable
			size--;
			downHeap(); 		// process down heap
			return deleted;		//return deleted
		}
		else
		{
			System.out.println("Heap Empty");
			return null;
		}
	}
	public T min()
	{
		if(!isEmpty())
			return heap[0];
		else
		{
			System.out.println("Heap Tmpty");
			return null;
		}
	}
	
	public void upHeap()
	{
		int curr=size-1; //index of current node
		while(curr>0)//till you reach root
		{
			
			T node=heap[curr]; //current node
			T parent=heap[parentIndex(curr)]; //its parent
			if(node.compareTo(parent)>0) //child>parent--> STOP
				return;
			else
			{		
				swap(curr,parentIndex(curr));
				curr=parentIndex(curr);
			}
			
		}
	}
	public void downHeap()
	{
		int curr=0; //index of current node
		while(curr<size-1) //till you reach last node
		{
			
			if(leftIndex(curr) > size || rightIndex(curr) > size){
				//System.out.println(curr);
				return;
			}
			
			T node=heap[curr]; //current node
			T left=heap[leftIndex(curr)]; //its parent
			T right=heap[rightIndex(curr)];
			
			if(node.compareTo(left)<0 && node.compareTo(right)<0) //node< both child--> STOP
				return;
			else
			{		
				if(left.compareTo(right)<0) //left is smaller
				{
					swap(curr,leftIndex(curr));
					curr=leftIndex(curr);
				}
				else
				{
					swap(curr,rightIndex(curr));
					curr=rightIndex(curr);
				}
			}
			
		}
	}
	public void swap(int a, int b)
	{
		T temp=heap[a];
		heap[a]=heap[b];
		heap[b]=temp;
	}
	
	//============Methods to get parent, left, right child
	public int parentIndex(int k)
	{
		return (k-1)/2;
	}
	public int leftIndex(int k)
	{
		return 2*k+1;
	}
	public int rightIndex(int k)
	{
		return 2*k+2;
	}
	//=========================helper methods
	public boolean isFull()
	{
		if(size==heap.length)
			return true;
		else
			return false;
	}
	public boolean isEmpty()
	{
		if(size==0)
			return true;
		else
			return false;
	}
	public int height()
	{
		return (int)(Math.log(size)/Math.log(2));
	}
	public void print()
	{
		for(int i=0;i<size;i++)
			System.out.print("  "+ heap[i]);
		
	}
}
