package qualificationRound2016;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.FileIO;

public class Fractiles {

	private static final char[] binary = new char[]{'L', 'G'};
	
	public static void main(String[] args) {
		try {
			String filename = "D-large-practice.in";
			String content = FileIO.readFile(filename);
			String[] contentLines = content.split("\n");
			int numTests = Integer.parseInt( contentLines[0] );
			StringBuilder result = new StringBuilder();
			int count = 1;
			while(count <= numTests) {
				String[] values = contentLines[count].split(" ");
				
				int k = Integer.parseInt(values[0]);
				int c = Integer.parseInt(values[1]);
				int s = Integer.parseInt(values[2]);
				
				result.append("Case #" + count + ": ");
				List<Long> results = new ArrayList<Long>();
				
				getResults(k, c, s, results);
				
				if(results.size() == 0) {
					result.append("IMPOSSIBLE");
				} else {
					for(Long i: results) {
						result.append(i + " ");
					}
				}
				
				result.append("\n");
				count++;
			}
			FileIO.writeFile("out" + filename, result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void getResults(int k, int c, int s,
			List<Long> results) {
		if(c*s < k)
			return;
		
		for(int i = 1 ; i <= k ; i += c) {
			long p = 1L;
			// consider sequence from i to (i + c - 1)
			for(int j = 0 ; j < c ; j++) {
				p = (long)((p - 1)*k) + (long)Math.min(i + j, k);
			}
			results.add(p);
		}
	}

	// tries to generate a bunch of data; runs out of heap space
	public static void main2(String[] args) {
		try {
			String filename = "D-small-practice.in";
			String content = FileIO.readFile(filename);
			String[] contentLines = content.split("\n");
			int numTests = Integer.parseInt( contentLines[0] );
			StringBuilder result = new StringBuilder();
			int count = 1;
			while(count <= numTests) {
				String[] values = contentLines[count].split(" ");
				
				int k = Integer.parseInt(values[0]);
				int c = Integer.parseInt(values[1]);
				int s = Integer.parseInt(values[2]);
				
				result.append("Case #" + count + ": ");
				
				Set<String> set = getFinalComplexities(k,c);
				
				int orderingSize = (int) Math.pow(k,c);
				List<Set<Integer>> results = new ArrayList<Set<Integer>>();
				
				helper(new HashSet<Integer>(), set, s, orderingSize, 0, results);
				
				if(results.size() == 0) {
					result.append("IMPOSSIBLE");
				} else {
					for(int i: results.get(0)) {
						result.append(i + " ");
					}
				}
				
				result.append("\n");
				count++;
			}
			FileIO.writeFile("out" + filename, result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void helper(Set<Integer> temp, Set<String> orderings, int s, 
			int orderingSize, int curStart, List<Set<Integer>> results) {
		if(orderings.size() <= 1) {
			results.add(new HashSet<Integer>(temp));
			return;
		}
		
		if(s == 0)
			return;
		
		for(int i = curStart ; i < orderingSize ; i++) {
			
			if(results.size() > 0)
				return;
			
			temp.add(i);
			Set<String> testedOnThisStack = new HashSet<String>();
			
			for(String st: orderings) {
				if(st.charAt(i) == 'G') {
					testedOnThisStack.add(st);
				}
			}
			
			orderings.removeAll(testedOnThisStack);
			
			helper(temp, orderings, s-1, orderingSize, curStart+1, results);
			
			
			orderings.addAll(testedOnThisStack);
			temp.remove(i);
		}
	}
	
	private static Set<String> getFinalComplexities(int k, int c) {
		Set<String> possibleStartSeqs = new HashSet<String>();
		getAllKLength(k, possibleStartSeqs);
		
		Set<String> finalComplexity = new HashSet<String>();
		for(String start: possibleStartSeqs) {
			String finalSeq = start;
			for(int i = 2 ; i <= c ; i++) {
				StringBuilder sb = new StringBuilder();
				for(int chars = 0 ; chars < finalSeq.length() ; chars++) {
					if(finalSeq.charAt(chars) == 'L') {
						sb.append(start);
					} else {
						for(int kGold = 0 ; kGold < start.length() ; kGold++) {
							sb.append('G');
						}
					}
				}
				finalSeq = sb.toString();
			}
			finalComplexity.add(finalSeq);
		}
		return finalComplexity;
	}
	
	// The method that prints all possible strings of length k.  It is
    //  mainly a wrapper over recursive function getAllKLengthRec()
    private static void getAllKLength(int k, Set<String> set) {
        int n = binary.length;        
        getAllKLengthRec(new StringBuilder(), n, k, set);
    }
 
    // The main recursive method to print all possible strings of length k
    private static void getAllKLengthRec(StringBuilder prefix, int n, int k, Set<String> set) {
         
        // Base case: k is 0, print prefix
        if (k == 0) {
            set.add(prefix.toString());
            return;
        }
 
        // One by one add all characters from set and recursively 
        // call for k equals to k-1
        for (int i = 0; i < n; ++i) {
             
            // Next character of input added
            prefix.append(binary[i]);
             
            // k is decreased, because we have added a new character
            getAllKLengthRec(prefix, n, k - 1, set); 
            
            // remove last character
            prefix.setLength(prefix.length() - 1);
        }
    }
}
