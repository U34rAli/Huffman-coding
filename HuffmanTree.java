/*
 * a class that will be used to build huffman's tree
 * it has root node and additional methods to build tree
 * you need to add the methods yourself according to your logic
 * 
 */
public class HuffmanTree {
	Node root;
	
	public HuffmanTree() {
	}
	public HuffmanTree(Node r) {
		// TODO Auto-generated constructor stub
		root=r;
	}
	
	
	public static Node bulidTree(PriorityHeap<Node> heap){	
		//heap.print();
		//System.out.println();
		while(heap.size > 1){
			Node left = heap.delete();
			Node right = heap.delete();	
			//heap.print();
			//System.out.println("\nheap size  "  + heap.size);
			//System.out.println('\0' + left.getKey() + right.getKey() + " " +left +" " + right);
			//System.out.println("\n  "+ left + " left "   + "  right  "  + right);
			heap.insert(new Node('\0' , left.getKey() + right.getKey() , left , right));
		}
		return heap.delete();
	}
	
	
	public void preOrder(){
		printPreOrder(root);
	}
	private void printPreOrder(Node node) {
		// TODO Auto-generated method stub
		if(node == null)
			return;
		else{
			if(node.isLeaf()){
				System.out.print(" "+node.getValue());
			}
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
			
	}
	
	// these mehtod will generate tthe binary code for each char present the tree
	public void buildCode(String[] str){
		buildCode_helper(str, root, "");
	} 
	private void buildCode_helper(String[] array , Node node , String code){
		if(! node.isLeaf()){
			buildCode_helper(array, node.left, code+"0");
			buildCode_helper(array, node.right, code+"1");
		}
		else{
			array[  (int)node.getValue()] = code;
		}
	}
	
	// write the free
	public String generateTreeString(Node[] node){
		String str = "";
		for(int i=0 ; i<node.length ; i++){
			str = str + (int)node[i].getValue()+ "-" + node[i].getKey() + ",";
		}
		str = str.substring(0, str.length()-1);
		//str = str+": ";
		return str;
	}
	
	public String generateFileBits(String[] str , String file){
		StringBuilder b= new StringBuilder();
		for(int i=0 ; i<file.length() ; i++){
			int j = (int)file.charAt(i) % 255;
			//System.out.println( str[j]  + "  "  + (char)j );
			b = b.append(str[j]);
			//System.out.print( file.charAt(i) + "" );
		}
		
		//System.out.println(b.length());
		//System.out.println(b.charAt(94733));
		return b.toString();
		
	}
	
	// this mehtod will  decode the 
	public String decodeBits(String str){
		return decodeBits_helper(str, root);
	}
	private String decodeBits_helper(String str , Node root){
		Node node;
		String s = "";
		//System.out.println(str.length());
		for(int i=0 ; i < str.length() ; ){
			node = root;
			while(  !node.isLeaf()   ){
				if(i == str.length()){
					break;
				}
				if( str.charAt(i) == '1'){
					node = node.right;
				}
				else if(str.charAt(i) == '0'){
					node = node.left;
				}
				i++;
			}
			//System.out.println();
			s = s+ node.getValue();
		}
		
		return s;
		
	}
	
}
