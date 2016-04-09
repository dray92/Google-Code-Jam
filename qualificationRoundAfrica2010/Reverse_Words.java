package qualificationRoundAfrica2010;

import java.io.IOException;

import utility.FileIO;

public class Reverse_Words {
	
	public static void main(String[] args) {
		try {
			String filename = "B-large-practice.in";
			String content = FileIO.readFile(filename);
			String[] contentLines = content.split("\n");
			int numTests = Integer.parseInt( contentLines[0] );
			StringBuilder result = new StringBuilder();
			int count = 1;
			while(count <= numTests) {
				String line = contentLines[count];
				result.append("Case #" + count + ": ");
				result.append(reverseWordOrder(line));
				result.append("\n");
				count++;
			}
			FileIO.writeFile("out" + filename, result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String reverseWordOrder(String line) {
		StringBuilder sb = new StringBuilder(line);
		
		reverseString(sb, 0, sb.length()-1);
		
		int curIndx = 0, start = 0;;
		while(curIndx <= sb.length()) {
			if(curIndx == sb.length() || sb.charAt(curIndx) == ' ') {
				// word exists from start to curIndx-1
				reverseString(sb, start, curIndx-1);
				start = curIndx+1;
			}
			curIndx++;
		}
		
		return sb.toString();
	}
	
	private static void reverseString(StringBuilder sb, int start, int end) {
		for(int i = start ; i <= start+(end-start)/2 ; i++) {
			char c1 = sb.charAt(i);
			sb.setCharAt(i,sb.charAt(end-(i-start)));
			sb.setCharAt(end-(i-start), c1);
		}
	}

}
