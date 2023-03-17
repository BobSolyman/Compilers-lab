package csen1002.main.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Write your info here
 *
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

public class CfgEpsUnitElim {

	//constructor attributes
	HashMap<String, TreeSet<String>> rules;
	String[] ruleHeads;
	String ruleHeadValue;
	String alphabets;
	
	//eliminate eplison attributes
	ArrayList<String> hadEpsilon;
	Set<String> literalAlterations;
	
	
	public CfgEpsUnitElim(String cfg) {
		String[] input = cfg.split("#");
		ruleHeadValue = input[0];
		ruleHeads = ruleHeadValue.split(";");
		alphabets = input[1];
		rules = new HashMap<>();
		for(String rule : input[2].split(";")) {
			TreeSet<String> tempArr = new TreeSet<>();
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
		
		for(String key: ruleHeads) {
			ruleString+=key+"/";
			TreeSet tempValue = rules.get(key);
			Iterator<String> itr = tempValue.iterator();
			while(itr.hasNext()) {
				ruleString+=itr.next()+",";
			}
			ruleString = ruleString.substring(0, ruleString.length()-1)+";";
		}
		
		return ruleHeadValue+"#"+alphabets+"#"+ruleString.substring(0, ruleString.length()-1);
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		hadEpsilon = new ArrayList<>();
		
		while(!outOfEpsilons(rules.values())) {
			
			for(String key: ruleHeads) {
				TreeSet tempValue = rules.get(key);
				if(tempValue.contains("e")) {
					hadEpsilon.add(key);
					tempValue.remove("e");
					rules.put(key, tempValue);
					
					//edit other rules with this change
					for(String newKey: ruleHeads) {
						literalAlterations = new HashSet<>();
						TreeSet newTempValue = rules.get(newKey);
						
						Iterator<String> itr = newTempValue.iterator();
						while(itr.hasNext()) {
							literalAlterator(newKey, itr.next(), key.charAt(0));
						}
						newTempValue.addAll(literalAlterations);

						rules.put(newKey, newTempValue);
					}
					break;
				}
			}
		}
	}
	
	public void literalAlterator(String ruleHead, String literal, char alphabet) {
        if (literal.indexOf(alphabet) != -1){
            literalAlterator(ruleHead, literal.substring(0, literal.indexOf(alphabet)) + literal.substring(literal.indexOf(alphabet) + 1), alphabet);
            literalAlterator(ruleHead, literal.substring(0, literal.lastIndexOf(alphabet)) + literal.substring(literal.lastIndexOf(alphabet) + 1), alphabet);
        }
        if(literal.isEmpty()) {
        	if(!hadEpsilon.contains(ruleHead))
        		literalAlterations.add("e");
        }
        else
        	literalAlterations.add(literal);
    }
	
	public boolean outOfEpsilons(Collection<TreeSet<String>> input) {
		for(Set<String> rules: input) {
			if(rules.contains("e")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
	}

}
