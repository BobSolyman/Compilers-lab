package csen1002.main.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

class transfer{
	Set<Integer> source;
	String alphabet;
	Set<Integer> target;
	
	public transfer(Set<Integer> source, String alphabet, Set<Integer> target) {
		this.source = source;
		this.alphabet = alphabet;
		this.target = target;
	}
}



public class NfaToDfa {
	
	Hashtable<Integer, Set<Integer>> eclosures;
	
	boolean createdDeadState = false;
	
	int acceptStateNFA;
	int startStateNFA;
	ArrayList<String> grammer;
	
	Queue<Set<Integer>> statesQueue;
	ArrayList<String> statesArray;
	
	ArrayList<transfer> transfers;

	String Q="";
	String A="";
	String T="";
	String I="";
	String F="";
	
	
	public void getEClosure(String states, String transitions){

		eclosures = new Hashtable<Integer, Set<Integer>>();

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
	}
	
	public void createTransitions(Set<Integer> states, String[] transition) {
		
		ArrayList<String> currentTransitions = new ArrayList<String>();
		
		Iterator<Integer> itr = states.iterator();
		while(itr.hasNext()) {
			int state = itr.next();
			for(int i=0; i<transition.length; i++) {
				if(state==Integer.parseInt(transition[i].split(",")[0])) {
					currentTransitions.add(transition[i]);
				}
				if(state<Integer.parseInt(transition[i].split(",")[0]))
					break;
			}
		}
		
		for(String alphabet : grammer) {
			Set<Integer> eclosedTargets = new HashSet<Integer>();
			for(String stateTransition : currentTransitions ) {
				if(stateTransition.split(",")[0].equals(alphabet)) {
					int target = Integer.parseInt(stateTransition.split(",")[2]);
					eclosedTargets.addAll(eclosures.get(target));
				}
			}
			if(eclosedTargets.isEmpty()) {
				createdDeadState = true;
				Set<Integer> deadState = new HashSet<Integer>();
				deadState.add(-1);
				transfer t = new transfer(states, alphabet, deadState);
				transfers.add(t);
			}
			else {
				transfer t = new transfer(states, alphabet, eclosedTargets);
				transfers.add(t);
				
				// queue and array to handle new state
				
				if(!statesArray.contains(setToString(eclosedTargets))) {
					statesArray.add(setToString(eclosedTargets));
					statesQueue.add(eclosedTargets);
				}
			}
		}
	}
	
	public String setToString(Set<Integer> input) {
		
		ArrayList<Integer> tempArray = new ArrayList<>();
		tempArray.addAll(input);
		
		Collections.sort(tempArray);
		String result = "";
		for(int i=0; i<tempArray.size(); i++) {
			result += tempArray.get(i)+"/";
		}
		result = result.substring(0, result.length() - 1);
		
		return result;
	}

	public void handleDeadState() {
		Q = "-1;"+Q;
		
		String temp = "";
		for(int i=0; i<grammer.size(); i++) {
			temp = temp + "-1,"+grammer.get(i)+",-1;";
		}
		
		T = temp + T;
	}
	
	public void getAcceptStates() {
		
	}
	
	
	public NfaToDfa(String input) {
		
		statesArray = new ArrayList<>();
		statesQueue = new ArrayDeque<>();
		
		transfers = new ArrayList<transfer>();
		
		String[] arrayInput = input.split("#");

		startStateNFA = Integer.parseInt(arrayInput[3]);
		acceptStateNFA = Integer.parseInt(arrayInput[4]);
		A = arrayInput[1];
		grammer = new ArrayList<String>(Arrays.asList(A.split(";")));
		
		getEClosure(arrayInput[0], arrayInput[2]);
		
		Set<Integer> newStartState = eclosures.get(startStateNFA);
		I = setToString(newStartState); 
		statesArray.add(I);
		
		createTransitions(newStartState, arrayInput[2].split(";"));
		
		while(!statesQueue.isEmpty())
		{
			createTransitions(statesQueue.poll(), arrayInput[2].split(";"));
		}
		
		
		
		
		
		
		
		if(createdDeadState) {
			handleDeadState();
		}
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		return Q+"#"+A+"#"+T+"#"+I+"#"+F;
	}

	public static void main(String[] args) {
		NfaToDfa x = new NfaToDfa("0;1;2;3;4;5;6;7;8;9;10#a;b#0,e,1;1,b,2;2,e,3;3,e,4;3,e,9;4,e,5;4,e,7;5,a,6;6,e,4;6,e,9;7,b,8;8,e,4;8,e,9;9,a,10#0#10");

		System.out.println(x.Q);
		System.out.println(x.A);
		System.out.println(x.T);
		System.out.println(x.I);
		System.out.println(x.F);
	}
	
	
}
