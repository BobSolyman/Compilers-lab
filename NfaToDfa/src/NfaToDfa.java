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

public class NfaToDfa {
	
	Hashtable<Integer, Set<Integer>> eclosures;
	
	boolean createdDeadState = false;
	
	ArrayList<String> acceptStatesNFA;
	int startStateNFA;
	ArrayList<String> grammer;
	
	Queue<Set<Integer>> statesQueue = new ArrayDeque<>();
	ArrayList<String> 	statesArray = new ArrayList<>();
	
	ArrayList<String> transfers = new ArrayList<String>();

	String Q="";
	String A="";
	String T="";
	String I="";
	String F="";
	
	
	public void getEClosure(String states, String transitions){

		eclosures = new Hashtable<Integer, Set<Integer>>();

		String[] statesArray = states.split(";");
		
		//initialising each state with itself
		for(int i=0; i<statesArray.length; i++) {
			int tempKey = Integer.parseInt(statesArray[i]);
			Set<Integer> tempVal = new HashSet<Integer>();
			tempVal.add(tempKey);
			eclosures.put(tempKey, tempVal);
		}
		
		String[] transitionsArray = transitions.split(";");
		
		//adding direct e transitions only
		for(int i=0; i<transitionsArray.length; i++) {
			if(transitionsArray[i].split(",")[1].equals("e")) {
				int tempKey = Integer.parseInt(transitionsArray[i].split(",")[0]);
				Set<Integer> tempVal = eclosures.get(tempKey);
				tempVal.add(Integer.parseInt(transitionsArray[i].split(",")[2]));
				eclosures.put(tempKey, tempVal);
			}
		}
		
		
		String changeDetector = "";
		
		while(!changeDetector.equals(eclosures.toString())) {
			
			Enumeration<Integer> e = eclosures.keys();
			
			changeDetector = eclosures.toString();
			
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
	}
	
	public void createTransitions(Set<Integer> states, String[] transition) {
		
		ArrayList<String> currentTransitions = new ArrayList<String>();
		
		//collection all the alphabet+e transitions from this state
		Iterator<Integer> itr = states.iterator();
		while(itr.hasNext()) {
			int state = itr.next();
			for(int i=0; i<transition.length; i++) {
				if(state==Integer.parseInt(transition[i].split(",")[0])) {
					currentTransitions.add(transition[i]);
				}
				//optimization step
				if(state<Integer.parseInt(transition[i].split(",")[0]))
					break;
			}
		}
		
		
		for(String alphabet : grammer) {
			Set<Integer> eclosedTargets = new HashSet<Integer>();
			for(String stateTransition : currentTransitions ) {
				if(stateTransition.split(",")[1].equals(alphabet)) {
					int target = Integer.parseInt(stateTransition.split(",")[2]);
					eclosedTargets.addAll(eclosures.get(target));
				}
			}
			if(eclosedTargets.isEmpty()) {
				createdDeadState = true;
				Set<Integer> deadState = new HashSet<Integer>();
				deadState.add(-1);
				transfers.add(setToString(states)+","+ alphabet +","+ setToString(deadState));
			}
			else {
				transfers.add(setToString(states)+","+ alphabet +","+ setToString(eclosedTargets));
				
				// add to queue and array to handle new state
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
		
		for(String state : statesArray) {
			for(String stateEntry: state.split("/")) {
				if(acceptStatesNFA.contains(stateEntry)) {
					F += ";" + state;
					break;
				}
			}
		}
		
		F = F.substring(1);
		
	}

	public int compareStates(String state1, String state2) {
		
		String[] arr1 = state1.split("/");
		String[] arr2 = state2.split("/");
		
		if(arr1.length<arr2.length) {
			for(int i=0; i<arr1.length; i++) {
				if(Integer.parseInt(arr1[i])>Integer.parseInt(arr2[i]))
					return 1;
				if(Integer.parseInt(arr1[i])<Integer.parseInt(arr2[i]))
					return -1;
			}
			return -1;
		}
		if(arr1.length>arr2.length) {
			for(int i=0; i<arr2.length; i++) {
				if(Integer.parseInt(arr1[i])>Integer.parseInt(arr2[i]))
					return 1;
				if(Integer.parseInt(arr1[i])<Integer.parseInt(arr2[i]))
					return -1;
			}
			return 1;
		}
		
		for(int i=0; i<arr1.length; i++) {
			if(Integer.parseInt(arr1[i])>Integer.parseInt(arr2[i]))
				return 1;
			if(Integer.parseInt(arr1[i])<Integer.parseInt(arr2[i]))
				return -1;
		}
		
		return 0;
	}
	
	public int compareTransfers(String transfer1, String transfer2) {
		
		String[] arr1 = transfer1.split(",");
		String[] arr2 = transfer2.split(",");
		int comparison = compareStates(arr1[0], arr2[0]);
		
		if(comparison!=0){
			return comparison;
		}
		
		comparison = arr1[1].compareTo(arr2[1]);
		
		if(comparison!=0){
			return comparison;
		}
		
		return compareStates(arr1[2], arr2[2]);
		
	}
	
	public NfaToDfa(String input) {
		
		String[] arrayInput = input.split("#");

		startStateNFA = Integer.parseInt(arrayInput[3]);
		acceptStatesNFA = new ArrayList<String>(Arrays.asList(arrayInput[4].split(";")));
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
		
        for (int i = 0; i < statesArray.size(); i++) {
            for (int j = i + 1; j < statesArray.size(); j++) {
                String temp = "";
                if (compareStates(statesArray.get(i), statesArray.get(j))==1) {
                    temp = statesArray.get(i);
                    statesArray.set(i, statesArray.get(j));
                    statesArray.set(j, temp);
                }
            }
        }
		
        for (int i = 0; i < statesArray.size(); i++) {
        	Q += ";" + statesArray.get(i);
        }
        Q = Q.substring(1);
        
        
        for (int i = 0; i < transfers.size(); i++) {
            for (int j = i + 1; j < transfers.size(); j++) {
                String temp = "";
                if (compareTransfers(transfers.get(i), transfers.get(j))==1) {
                    temp = transfers.get(i);
                    transfers.set(i, transfers.get(j));
                    transfers.set(j, temp);
                }
            }
        }
        
        for (int i = 0; i < transfers.size(); i++) {
        	T += ";" + transfers.get(i);
        }
        T = T.substring(1);
		
		
		getAcceptStates();
		
		if(createdDeadState) {
			handleDeadState();
		}
	}

	@Override
	public String toString() {
		return Q+"#"+A+"#"+T+"#"+I+"#"+F;
	}

	
}
