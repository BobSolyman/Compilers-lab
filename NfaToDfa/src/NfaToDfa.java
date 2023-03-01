package csen1002.main.task2;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

public class NfaToDfa {

	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	
	public static Hashtable<Integer, Set<Integer>> getEClosure(String states, String transitions){

		Hashtable<Integer, Set<Integer>> eclosures = new Hashtable<Integer, Set<Integer>>();

		String[] statesArray = states.split(";");
		
		for(int i=0; i<statesArray.length; i++) {
			int tempKey = Integer.parseInt(statesArray[i]);
			Set<Integer> tempVal = new HashSet<Integer>();
			tempVal.add(tempKey);
			eclosures.put(tempKey, tempVal);
		}
		
		String[] transitionsArray = transitions.split(";");
		
		for(int i=0; i<transitionsArray.length; i++) {
			if(transitionsArray[i].split(",")[1].equals("e")) {
				int tempKey = Integer.parseInt(transitionsArray[i].split(",")[0]);
				Set<Integer> tempVal = eclosures.get(tempKey);
				tempVal.add(Integer.parseInt(transitionsArray[i].split(",")[2]));
				eclosures.put(tempKey, tempVal);
			}
		}
		
		Enumeration<Integer> e = eclosures.keys();
		
		while(e.hasMoreElements()) {
			int tempKey = e.nextElement(); //3
			Set<Integer> tempSet = eclosures.get(tempKey); //3,4,9
			Set<Integer> newSet = new HashSet<Integer>();
			newSet.addAll(tempSet); //3,4,9
			Iterator<Integer> itr = tempSet.iterator();
			while(itr.hasNext()){
				int tempVal = itr.next(); 
				if(tempVal!=tempKey) {
					Set<Integer> eclosedSet = eclosures.get(tempVal); //4,5,7
					newSet.addAll(eclosedSet); //3,4,5,7,9
				}
			}
			eclosures.put(tempKey, newSet);

		}
		
		return eclosures;
	}
	
	
	public NfaToDfa(String input) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Set<Integer> x= new HashSet<>();
		Set<Integer> y= new HashSet<>();
		
		
		Hashtable<Integer, Set<Integer>> A = getEClosure("1;2;3;4;5;6;7;8;9;10", "1,b,2;2,e,3;3,e,4;3,e,9;4,e,5;4,e,7;5,a,6;6,e,4;6,e,9;7,b,8;8,e,4;8,e,9;9,a,10");

		
	}
	
	
}
