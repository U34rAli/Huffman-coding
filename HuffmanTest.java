import java.util.Scanner;
public class HuffmanTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		while(flag){
			System.out.println("1 # to Compress File ");
			System.out.println("2 # to Decode File compressed file ");
			System.out.println("0 # to Exit ");
			System.out.print("Enter Your Choice  = " );
			int i = input.nextInt();
			String infile , outfile;
			switch(i){
			case 1:
				System.out.print("Enter file name to encode  ");
				input.nextLine();
				infile = input.nextLine();
				System.out.print("Enter file name to generate  ");
				outfile = input.nextLine();
				HuffmanBuilder.encode(infile,outfile);
				System.out.println("  ");
				break;
			case 2:
				System.out.print("Enter file name to decode  ");
				input.nextLine();
				infile = input.nextLine();
				System.out.print("Enter file name to generate  ");
				outfile = input.nextLine();
				HuffmanBuilder.decode(infile,outfile);
				System.out.println("  ");
				break;
			case 0:
				flag=false;
				input.close();
				break;
			default:	
			}	
		}	
	}
}
