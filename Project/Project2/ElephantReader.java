//author: cheng758
import java.io.*;
import java.util.Scanner; 
class ElephantReader{
	
	public static boolean readElephants(ElephantHerd e, String fileName){
		Scanner s = null;
		try{
			s = new Scanner(new File(fileName));
			e.clear();
			while(s.hasNextLine()){
				String[] tokens = s.nextLine().split(" ");//to split the line into words by " "
				e.add(new Elephant(tokens[0],Integer.parseInt(tokens[1]),Double.parseDouble(tokens[2])));
			}
			s.close();
			return true;
		} catch (Exception x){
			return false;
		}
	}

	public static boolean writeElephants(ElephantHerd e, String fileName){
		PrintWriter p = null;
		try{
			p = new PrintWriter(new File(fileName));
			p.write(e.toString());
			p.close();
			return true;
		} catch (Exception x){
			return false;
		}
	}
}