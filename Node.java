public class Node implements Comparable {
	private char value;// character
	private int key;// frequency
	Node left, right;

	// 1. Complete constructor
	public Node(char v, int k, Node left, Node right) {
		value = v;
		key = k;
		this.left = left;
		this.right = right;
	}

	// ----------------------------------
	// 2. add all getter/setter here
	// ------------------------------------
	
	
	
	public boolean isLeaf() // method required in decoding
	{
		if (left == null && right == null)
			return true;
		else
			return false;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	// ----------------------
	public int compareTo(Object o) {
		if (o instanceof Node) {
			Node d = (Node) o;
			return this.key - d.key; // will return a number= 0, <1 or >1
		}
		return -1;
	}

	// --------------------------
	public String toString() {
		return "" + key;
	}
}
