package csen1002.main.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

public class CfgLeftRecElim {
	
	HashMap<String, ArrayList<String>> rules;
	ArrayList<String> ruleHeads;
	String alphabets;
	Set<String> splitted;
	
	public CfgLeftRecElim(String cfg) {
		String[] input = cfg.split("#");
		ruleHeads = new ArrayList<>();
		splitted = new HashSet<>();
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
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		String ruleString = "";
		String ruleHeadsValues = "";
		
		for(String key: ruleHeads) {
			ruleHeadsValues+=key+";";
			ruleString+=key+"/";
			ArrayList<String> tempValue = rules.get(key);
			Iterator<String> itr = tempValue.iterator();
			while(itr.hasNext()) {
				ruleString+=itr.next()+",";
			}
			ruleString = ruleString.substring(0, ruleString.length()-1)+";";
		}
		
		return ruleHeadsValues.substring(0, ruleHeadsValues.length()-1)+"#"+alphabets+"#"+ruleString.substring(0, ruleString.length()-1);
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		
		//addAlphaAndBeta("S", rules.get("S"));
		int initSize = ruleHeads.size();
		for(int i=0; i<initSize; i++) {
			if(ruleHeads.get(i).length()==1) {
				productManager(ruleHeads.get(i), i);
				addAlphaAndBeta(ruleHeads.get(i), rules.get(ruleHeads.get(i)));
			}
		}
		
	}
	
	public boolean productionsChecker(ArrayList<String> literals, int earlierRulesSize) {
		
		for(int i=0; i<literals.size(); i++) {
			String literal = literals.get(i);
			for(int j=0; j<earlierRulesSize; j++) {
				if(literal.charAt(0)==ruleHeads.get(j).charAt(0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void productManager(String ruleHead, int earlierRulesSize) {
		
		ArrayList<String> literals = new ArrayList<>();
		literals.addAll(rules.get(ruleHead));
		
		while(productionsChecker(literals, earlierRulesSize)) {
			for(int i=0; i<literals.size(); i++) {
				String literal = literals.get(i);
				for(int j=0; j<earlierRulesSize; j++) {
					if(literal.charAt(0)==ruleHeads.get(j).charAt(0)) {
						ArrayList<String> newComers = new ArrayList<>();
						for(String prefix : rules.get(ruleHeads.get(j))) {
							newComers.add(prefix+literal.substring(1));
						}
						for(String newComer : newComers) {
							literals.add(i,newComer);
							i++;
						}
						literals.remove(i--);
						
					}
					
				}
			}
		}
		rules.put(ruleHead, literals);
	}
	
	public void addAlphaAndBeta(String ruleHead, ArrayList<String> literals) {

		
		boolean splitting = false;
		ArrayList<String> betas = new ArrayList<>();
		ArrayList<String> alphas = new ArrayList<>();
		Iterator<String> itr = literals.iterator();
		while(itr.hasNext()) {
			String literal = itr.next();
			if(literal.charAt(0)==ruleHead.charAt(0)) {
				splitting = true;
				break;
			}
		}
		if(splitting) {
			splitted.add(ruleHead);
			itr = literals.iterator();
			while(itr.hasNext()) {
				String literal = itr.next();
				if(literal.charAt(0)==ruleHead.charAt(0)) {
					//alpha
					alphas.add(literal.substring(1)+ruleHead+"'");
				}
				else {
					//beta
					betas.add(literal+ruleHead+"'");
				}
				
			}
			alphas.add("e");
			rules.put(ruleHead, betas);
			ruleHeads.add(ruleHead+"'");
			rules.put(ruleHead+"'", alphas);
			
		}
	}
}
