
public class HuffmanBuilder {
	// do not add any additional variable here, instead use them as local to
	// functions
	// try to breakdown the both methods into smaller parts and transfer the
	// work to helper methods
	public static void encode(String infile, String outfile) {
		/*
		 * read the file find unique character and their frequencies build tree
		 * using algo discussed in handout get the bitcode for each unique
		 * character by tree traversal write it to file using info from orignal
		 * file. print the frequency table similar to one shown in handout.
		 */
		
		String file = readFile(infile);
		int[] freq = ReturnFreq(infile , file);
		Node[] nodeArray = makeNodeArrayOFFrequency(freq);
		PriorityHeap<Node> heap =createHeap(nodeArray);
		//  these two lines will set root for huffman's tree
		HuffmanTree tree = new HuffmanTree();
		tree.root = HuffmanTree.bulidTree(heap);
		//tree.preOrder();
		String[] str = new String[256];
		tree.buildCode(str);//str will be modified
		//System.out.println((int)nodeArray[1].getValue());
		String treeString = tree.generateTreeString(nodeArray);
		String fileBits = tree.generateFileBits(str, file);
		//System.out.println(str['F']);
		//System.out.println(fileBits.substring(0, 20));
		//System.out.println(fileBits.length());
		//System.out.println( treeString.length() );
		writeTree(  treeString + ":" + fileBits  , outfile);
	}

	private static void writeTree(String data , String fileName){
		FileIO.writeBitSequence(data , fileName);
	}
		
	private static PriorityHeap<Node> createHeap(Node[] nodeArray){
		//System.out.println(nodeArray.length);
		PriorityHeap<Node> heap = new PriorityHeap<Node>(nodeArray.length);
		for(int i=0 ; i<nodeArray.length ; i++){
		//	System.out.println(nodeArray[i]);
			heap.insert(nodeArray[i]);
		}
		return heap;
	}
	
	// this mehtod will convert the frequency into the node frequency array
	private static Node[] makeNodeArrayOFFrequency(int[] freq){
		final int size = 256;
		
		int z=0;
		for(int i = 0 ; i<size ; i++){
			if(freq[i] != 0){
				z++;
			}
		}
		//System.out.println(z);
		Node[] nodeArrray = new Node[z];
		int j=0;
		for(int i=0 ; i<size ; i++){
			if(freq[i] != 0){
			//	System.out.println((char)i  + "  "  +i);
				nodeArrray[j] = new Node((char)i, freq[i], null, null);
				j++;
			}
		}
		return nodeArrray;
	}
	
	private static String readFile(String fileName){
		return FileIO.readFile(fileName); // whole data of in file
	}
	
	// this method will open given file and will return the frequency of all characters
	private static int[] ReturnFreq(String infile , String file) {
		// Total number of ascii codes
		final int arraySize = 256;		
		char[] charArray = file.toCharArray(); // convert whole string into the
												// chars
		// System.out.println("file size " + charArray.length);
		// tabulate frequency counts
		int[] freq = new int[arraySize];
		for (int i = 0; i < charArray.length; i++) {
			int j = charArray[i] % 255;
			freq[j]++; // count frequency of each char
		}
		return freq;
	}

	// infile will be a file which contains bitscodes
	public static void decode(String infile, String outfile) {
		/*
		 * read the infile get the tree build tree get the bit sequence read bit
		 * sequence and construct the string write to file.
		 */
		String data = FileIO.readBitSequence(infile);
		String[] treeAndBits = data.split(":");
		int[] freq = getFreqForDecode(treeAndBits[0]);
		Node[] nodes = makeNodeArrayOFFrequency(freq);
		PriorityHeap<Node> heap =createHeap(nodes);
		
		HuffmanTree tree = new HuffmanTree();
		tree.root = HuffmanTree.bulidTree(heap);	
		//System.out.println(treeAndBits[1].substring(0, 20));
		String newFile = tree.decodeBits(treeAndBits[1]);
		FileIO.writeFile(newFile, outfile);
		//System.out.println(newFile);
		//tree.preOrder();
		//System.out.println(treeAndBits.length);	
		//System.out.println(treeAndBits[0].length());
		//System.out.println(treeAndBits[1].length() );	
	}
	
	private static int[] getFreqForDecode(String str){
		String[] s = str.split(",");
		int[] freq = new int[256];	
		for(int i = 0 ; i< s.length ; i++){
			String[] VF = s[i].split("-");
			int v = Integer.parseInt(VF[0]);
			int f = Integer.parseInt(VF[1]);	
			freq[v] = f;
		//	System.out.println(  (char)v + "  " + f );
		}		
		return freq;
	}

}
