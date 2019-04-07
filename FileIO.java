import java.io.*;
public class FileIO {
	//to note separation of tree from data
	static Character delimiter=':';
	
	/*
	 * function to read the file of characters
	 * it will return a string that will contain content of complete file	
	 * //file name will be passed along with full path
	 */
	public static String readFile(String file)
	{
		FileReader fr;
		BufferedReader br;
		StringBuilder characterSequence=new StringBuilder();
		try	{
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String c=br.readLine();//read one line
			while(c!=null)//end of file
			{	
				characterSequence.append(c); //append it
				c=br.readLine();
			}
			br.close();
		}
		catch(IOException io){
			System.out.println(io);
		}
		
		return characterSequence.toString();	
	}
	/*
	 * Will write the given string to file
	 * string is basically decoded character sequence from bits
	 */
	public static void writeFile( String out, String file)
	{
		PrintWriter pw;
		/*
		 * pw is an alternative of System.out for file io. it also contain two type of functions one with print(args), other with println(arg)
		 * you can use accordingly
		 */
		try{
			pw=new PrintWriter(new FileWriter(file,false));//file will be opened in overwrite mode
			
			pw.print(out);	//will read one line at a time and will append
			
			pw.close();
		}
		catch(IOException io){
			System.out.println(io);
		}
	}
	//=================================================================================
	/*
	 * to write the tree and bitsequence, you can either use two functions as it is done with read.
	 * or use single function and call it two time.
	 * one function is defined here
	 */
	public static void writeBitSequence(String encoded_sequence,String file)//you can 
	{
		BitWriter w;
		String tree,bitSequence;
		try{
			w=new BitWriter(file);//create and open the file to write.
			
			String tok[]=encoded_sequence.split(delimiter.toString());
			//System.out.println(tok.length);
			if(tok.length==2)//if encoded_sequence contains tree
			{
				//System.out.println("token");
				tree=tok[0]; //tree data. need to be converted into 0's and 1's
				bitSequence=tok[1]; //binary sequence of 0's and 1's
				
				//write tree character by character
				for(int i=0; i<tree.length();i++)
					w.writeCharacter(tree.charAt(i));
				//write delimter
				w.writeCharacter(delimiter);
				//write bits
				//System.out.println("    biyyyyyyyyyyyyyyy");
				for(int i=0;i<bitSequence.length();i++)
					w.writeBit(Integer.parseInt(""+bitSequence.charAt(i)));	//will read one line at a time and will append

				//System.out.println("no   d");
			}
			else
			{
				//System.out.println("fff");
				for(int i=0;i<encoded_sequence.length();i++)
					w.writeBit(Integer.parseInt(""+encoded_sequence.charAt(i)));	//will read one line at a time and will append
			}
			w.close();
		}
		catch(IOException io){
			System.out.println(io);
		}
	}
	//------------------------------------------------------
/*
 * function to read bit sequence
 * it will return a string as output, this string will in following format:
 * tree:bits, you will have to split it in decode method
*/
	public static String readBitSequence(String file) //file name will be passed along with full path
	{
		BitReader br;
		StringBuilder bitSequence=new StringBuilder();
		try	{
			br=new BitReader(file);
			int b=br.readBit();
			while(b!=-1)//end of file
			{
				bitSequence.append(b);	//will read one line at a time and will append
				b=br.readBit();
			}
			br.close();
		}
		catch(IOException io){
			System.out.println(io);
		}//=========================================================
	/* 
	 * bitsequence contains only bits. if tree is also written in same file
	 * We need tree as characters and not bits, 
	 * following code is processing tree bits to get it as characters
	 */
		//Step.1 --> will read tree as character
			String tree=readTree(file);
			//System.out.println(tree);
		//Step.2 --> remove tree bits from bitSequence
			
			int numOftreeBits=tree.length()*8+8;//+8 for delimiter character
			bitSequence.delete(0, numOftreeBits);
		//Step.3 -->  append tree at start of bitSequence and return
			//System.out.println(tree.length() + " 0 --  " + numOftreeBits );
			tree = tree + ":";
			return tree+bitSequence.toString();
	}
	//=============================================================
	/*
	 * a helper function to read tree from encoded file.
	 */
	private static String readTree(String file)
	{
		BufferedReader br;
		StringBuilder tree=new StringBuilder();
		try
		{
			br=new BufferedReader(new FileReader(file));
			int code=br.read();//read one char
			char ch=(char)code;
			while(code!=-1)//end of file
			{
				if(delimiter.compareTo(ch)==0){
					break;
				}
				else{
					tree.append(ch);
				}
				code=br.read();//read one char
				ch=(char)code;
				
			}
			br.close();
		}
		catch(IOException io){
			System.out.println(io);
		}
		return tree.toString();
	}
	//========================================================


}
