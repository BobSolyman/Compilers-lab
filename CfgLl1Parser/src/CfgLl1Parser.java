package csen1002.main.task7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

public class CfgLl1Parser {

	//constructor attributes
	HashMap<String, ArrayList<String>> rules;
	ArrayList<String> ruleHeads;
	ArrayList<String> alphabets;
	HashMap<String, ArrayList<String>> firstRules;
	HashMap<String, String> followRules;
	HashMap<String, String> ppt;

	
	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	public CfgLl1Parser(String input) {
		
		// init
		String[] input1 = input.split("#");
		ruleHeads = new ArrayList<>();
		ruleHeads.addAll(Arrays.asList(input1[0].split(";")));
		alphabets = new ArrayList<>();
		alphabets.addAll(Arrays.asList(input1[1].split(";")));
		
		rules = new HashMap<>();
		for(String rule : input1[2].split(";")) {
			ArrayList<String> tempArr = new ArrayList<>();
			String tempKey = rule.split("/")[0];
			tempArr.addAll(Arrays.asList(rule.split("/")[1].split(",")));
			rules.put(tempKey, tempArr);
		}
		

		firstRules = new HashMap<>();
		for(String first : input1[3].split(";")) {
			ArrayList<String> tempArr = new ArrayList<>();
			String tempKey = first.split("/")[0];
			tempArr.addAll(Arrays.asList(first.split("/")[1].split(",")));
			firstRules.put(tempKey, tempArr);
		}
		

		followRules = new HashMap<>();
		for(String follow : input1[4].split(";")) {
			String tempKey = follow.split("/")[0];
			String tempVal = follow.split("/")[1];
			followRules.put(tempKey, tempVal);
		}
		
		//predictive parsing table
		ppt = new HashMap<>();
		for(String var : ruleHeads) {
			for(String alpha : alphabets) {
				ppt.put(var+"e"+alpha, pptBuilder(var, alpha));
			}
			ppt.put(var+"e$", pptBuilder(var, "$"));

		}
		
	}
	
	public String pptBuilder(String var, String alpha) {
		
		ArrayList<String> firsts = firstRules.get(var);
		
		if(!alpha.equals("$")) {
			for(int i=0; i<firsts.size(); i++) {
				if(firsts.get(i).contains(alpha)) {
					return rules.get(var).get(i);
				}
			}
		}
		
		if(firsts.contains("e") && followRules.get(var).contains(alpha)) {
			return "e";
		}
		
		
		return "NA";
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		String res = "S";
		String cur = "S";
		
		int pointer = 0;
		input += "$";
		Stack<Character> stack = new Stack<>();
		stack.push('$');
		stack.push(ruleHeads.get(0).charAt(0));
		
		
		while(true) {
			char curIn = input.charAt(pointer);
			
			char val = stack.pop();
			
			if(val=='$') {
				if(curIn!='$')
					res+=";ERROR";
				break;
			}
			
			
			if(alphabets.contains(val+"") && val!=curIn) {
				res+=";ERROR";
				break;
			}
			
			if(alphabets.contains(val+"") && val==curIn)
				pointer++;
			
			if(ruleHeads.contains(val+"")) {
				String pptVal = ppt.get(val+"e"+curIn);
				if(pptVal.equals("NA")) {
					res+=";ERROR";
					break;
				}
				else if(pptVal.equals("e")) {
					cur = cur.substring(0, cur.indexOf(val))+ cur.substring(cur.indexOf(val)+1);
					res+=";"+cur;
				}
				else {
					cur = cur.substring(0, cur.indexOf(val))+pptVal+ cur.substring(cur.indexOf(val)+1);
					res+=";"+cur;
					for(int i=pptVal.length()-1; i>=0; i--)
						stack.push(pptVal.charAt(i));
				}
			}
			
		}
		
		return res;
	}

}
