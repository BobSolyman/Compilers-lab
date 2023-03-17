package csen1002.main.task3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

public class FallbackDfa {

	int armR;
	int armL;
	
	int startState;
	int currentState;
	String[] transitions;
	ArrayList<Integer> acceptStates;
	
	static Stack<Integer> readings;
	
	String result;
	
	public void read(String input) {
		while(armL<input.length()) {
			for(String transition : transitions) {
				String[] arrayT = transition.split(",");
				if(Integer.parseInt(arrayT[0])==currentState && arrayT[1].equals(input.charAt(armL)+"")) {
					currentState = Integer.parseInt(arrayT[2]);
					armL++;
					readings.push(currentState);
					break;
				}
			}
		}
	}
	
	public void backTrack(String input) {
		boolean foundAcceptState = false;
		Stack<Integer> cloneStack = new Stack<>();
		cloneStack.addAll(readings);
		while(armL>=armR) {
			if(acceptStates.contains(currentState)){
				foundAcceptState = true;
				result += input.substring(armR, armL+1)+","+currentState+";";
				armR = ++armL;
				currentState = startState;
				readings.clear();
				break;
			}
			else {
				currentState = readings.pop();
				armL--;
			}
		}
		if(foundAcceptState) {
			runHelper(input);
		}
		else {
			result += input.substring(armR)+","+cloneStack.pop()+";";
		}
	}
	
	
	public FallbackDfa(String fdfa) {
		String[] input = fdfa.split("#");
		
		startState = Integer.parseInt(input[3]);
		currentState = Integer.parseInt(input[3]);
		
		acceptStates = new ArrayList<Integer>();
		for(String state : input[4].split(";")) {
			acceptStates.add(Integer.parseInt(state));
		}
		
		transitions = input[2].split(";");
	}

	public String run(String input) {
		armR = 0;
		armL = 0;
		readings = new Stack<Integer>();
		result = "";
		
		runHelper(input);
		
		return result.substring(0, result.length()-1);
	}
	
	public void runHelper(String input) {

		readings.add(currentState);
		read(input);
		
		if(acceptStates.contains(currentState)) {
			result += input.substring(armR)+","+currentState+";";
		}
		else {
			backTrack(input);
		}
		
	}
}
