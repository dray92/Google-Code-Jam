package qualificationRound2016;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import utility.FileIO;

public class Counting_Sheep {

	public static void main(String[] args) {
		try {
			String filename = "A-large.in";
			String content = FileIO.readFile(filename);
			String[] contentLines = content.split("\n");
			int numTests = Integer.parseInt( contentLines[0] );
			StringBuilder result = new StringBuilder();
			int count = 1;
			while(count <= numTests) {
				Integer num = Integer.parseInt( contentLines[count] );
				result.append("Case #" + count + ": ");
				BigInteger bigNum = BigInteger.valueOf(num);
				result.append(sleepy(bigNum));
				result.append("\n");
				count++;
			}
			FileIO.writeFile("out" + filename, result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private static String sleepy(BigInteger num) {
		Set<Character> digits = new HashSet<Character>();
		
		BigInteger probableUpper = BigInteger.valueOf(10000000L);
		
		BigInteger upper = (probableUpper.compareTo(num) > 0) ? probableUpper : num;
		
		for(BigInteger i = BigInteger.ONE ; i.compareTo(upper) <= 0 ; i = i.add(BigInteger.ONE)) {
			BigInteger curValue = num.multiply(i);
			
			String stringNum = String.valueOf(curValue);
			for(int chars = 0 ; chars < stringNum.length() ; chars++) {
				digits.add(stringNum.charAt(chars));
			}
			
			if(digits.size() == 10) {
				return curValue.toString();
			}
		}
		return "INSOMNIA";
	}
}
