package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

public class CfgFirstFollow {

	//constructor attributes
	HashMap<String, ArrayList<String>> rules;
	ArrayList<String> ruleHeads;
	String alphabets;
	
	//first attributes
	HashMap<String, TreeSet<String>> firstRules;
	TreeSet<String> currentFirsts;
	
	//follow attributes
	HashMap<String, TreeSet<String>> followRules;
	TreeSet<String> currentFollow;
	
	
	public CfgFirstFollow(String cfg) {
		String[] input = cfg.split("#");
		ruleHeads = new ArrayList<>();
		ruleHeads.addAll(Arrays.asList(input[0].split(";")));
		alphabets = input[1];
		rules = new HashMap<>();
		for(String rule : input[2].split(";")) {
			ArrayList<String> tempArr = new ArrayList<>();
			String tempKey = rule.split("/")[0];
			tempArr.addAll(Arrays.asList(rule.split("/")[1].split(",")));
			rules.put(tempKey, tempArr);
		}
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		
		firstRules = new HashMap<>();
		
		for(String head : ruleHeads) {
			firstRules.put(head, new TreeSet<String>());
		}
		
		boolean added = true;
		
		while(added) {
			added = false;
			
			for(String head : ruleHeads) {
				ArrayList<String> tempV = rules.get(head);
				for(String value : tempV) {
					currentFirsts = new TreeSet<String>(); 
					currentFirsts.addAll(firstRules.get(head));
					firstHelper(head, value);
					if(!currentFirsts.toString().equals(firstRules.get(head).toString())) {
						added = true;
						firstRules.put(head, currentFirsts);
					}
				}
			}
		}
		String result = "";
		for(String head : ruleHeads) {
			result+=";"+head+"/";
			TreeSet<String> tempV = firstRules.get(head);
			for(String value : tempV) {
				result+=value;
			}
		}
		
		
		return result.substring(1);
	}
	
	public void firstHelper(String head, String rule) {
		
		if(rule.length()>1) {
			//ArrayList<String> newFirsts = rules.get(head);

			if(!ruleHeads.contains(rule.charAt(0)+""))
				currentFirsts.add(rule.charAt(0)+"");
			else {
				TreeSet<String> ruleFirsts = new TreeSet<>();
				ruleFirsts.addAll(firstRules.get(rule.charAt(0)+""));
				if(!ruleFirsts.isEmpty()) {
					if(!ruleFirsts.contains("e")) {
						currentFirsts.addAll(ruleFirsts);
					}
					else {
						ruleFirsts.remove("e");
						currentFirsts.addAll(ruleFirsts);
						firstHelper(head, rule.substring(1));
					}
				}
				
			}
		}
		else {
			if(!ruleHeads.contains(rule.charAt(0)+""))
				currentFirsts.add(rule.charAt(0)+"");
			else {
				TreeSet<String> ruleFirsts = firstRules.get(rule.charAt(0)+"");
				currentFirsts.addAll(ruleFirsts);
			}
		}
		
		
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		this.first();
		
		followRules = new HashMap<>();
		
		for(int i=0; i<ruleHeads.size(); i++) {
			TreeSet<String> temp = new TreeSet<String>();
			if(i==0)
				temp.add("$");
			followRules.put(ruleHeads.get(i), temp);
		}
		
		boolean added = true;
		
		while(added) {
			added = false;
		
			for(String head : ruleHeads) {
				for(String otherHead : ruleHeads) {
					ArrayList<String> tempV = rules.get(otherHead);
					for(String value : tempV) {
						if(value.contains(head)) {
							currentFollow = new TreeSet<String>(); 
							currentFollow.addAll(followRules.get(head));
							followHelper(head, otherHead, value);
							if(!currentFollow.toString().equals(followRules.get(head).toString())) {
								added = true;
								followRules.put(head, currentFollow);
							}
						}
					}
				}
			}
		
		}
		
		String result = "";
		for(String head : ruleHeads) {
			result+=";"+head+"/";
			TreeSet<String> tempV = followRules.get(head);
			for(String value : tempV) {
				result+=value;
			}
		}
		
		return result.substring(1);
	}
	
	public void followHelper(String head, String otherHead, String value) {

		for(int index=0; index<value.length(); index++) {
			if(value.charAt(index)==head.charAt(0)) {
				if(index==value.length()-1) {
					currentFollow.addAll(followRules.get(otherHead));
				}
				else {
					boolean ended = false;
					for(int i=index+1; i<value.length(); i++) {
						if(ruleHeads.contains(value.charAt(i)+"")) {
							TreeSet<String> nextValueFirsts = new TreeSet<>();
							nextValueFirsts.addAll(firstRules.get(value.charAt(i)+""));
							if(nextValueFirsts.contains("e")) {
								nextValueFirsts.remove("e");
								currentFollow.addAll(nextValueFirsts);
							}
							else {
								currentFollow.addAll(nextValueFirsts);
								ended = true;
								break;
							}
						}
						else {
							currentFollow.add(value.charAt(i)+"");
							ended = true;
							break;
						}
					}
					if(!ended)
						currentFollow.addAll(followRules.get(otherHead));
				}
			}
		}
	}
}
