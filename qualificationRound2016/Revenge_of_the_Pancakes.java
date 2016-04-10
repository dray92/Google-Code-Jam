package qualificationRound2016;

import java.io.IOException;

import utility.FileIO;

public class Revenge_of_the_Pancakes {
	public static void main(String[] args) {
		try {
			String filename = "B-large.in";
			String content = FileIO.readFile(filename);
			String[] contentLines = content.split("\n");
			int numTests = Integer.parseInt( contentLines[0] );
			StringBuilder result = new StringBuilder();
			int count = 1;
			while(count <= numTests) {
				String line = contentLines[count];
				result.append("Case #" + count + ": ");
				result.append(getNumFlips(line));
				result.append("\n");
				count++;
			}
			FileIO.writeFile("out" + filename, result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int getNumFlips(String text) {
		StringBuilder line = new StringBuilder(text);
		int count = 0;
		int lastIndx = line.lastIndexOf("-");
		while(lastIndx != -1) {
			count++;
			for(int i = 0 ; i <= lastIndx ; i++) {
				if(line.charAt(i) == '-')
					line.setCharAt(i, '+');
				else 
					line.setCharAt(i, '-');
			}
			
			lastIndx = line.lastIndexOf("-");
		}
		return count;
	}
}
