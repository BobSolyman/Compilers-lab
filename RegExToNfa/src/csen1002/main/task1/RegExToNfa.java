package csen1002.main.task1;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Shehabeldin Solyman
 * @id 46-2664
 * @labNumber 12
 */

class NFA{
	ArrayList<Node> nodes;
	
	public NFA() {
		nodes = new ArrayList<Node>();
	}
	
	public void addNodes(Node n) {
		
		nodes.add(n);
		
	}
			
	public void addNodes(Node n, char pos) {
		
		if(pos == 'f') 
			nodes.add(0, n);
		else if(pos =='l')
			nodes.add(n);

	}
}


class Node implements Comparable<Object> {
	int name;
	ArrayList<Neighbor> pointers;
	
	public Node(int name) {
		this.name = name;
		pointers = new ArrayList<Neighbor>();
	}
	
	public void addPointer(int newNode, char p) {
		
		pointers.add(new Neighbor(newNode, p));
	}
	
	@Override
	public int compareTo(Object o) {
		Node n = (Node) o;
		
		if(this.name- n.name >0) return 1;
		if(this.name- n.name <0) return -1;

		return 0;
	}
}

class Neighbor {
	int name;
	char alphabet;
	
	public Neighbor(int name, char alphabet) {
		this.name = name;
		this.alphabet = alphabet;
	}
}


public class RegExToNfa {

	NFA Nfa = new NFA();
	
	
	String Q="";
	String A="";
	String T="";
	String I="";
	String F="";
	
	
	int counter = 0;
	Stack<NFA> NFAs = new Stack<NFA>();
	
	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
	
	public void handleUnion() {
		//popping the NFAs with '.' applied to
		NFA secondNFA = NFAs.pop();
		NFA firstNFA = NFAs.pop();
		
		//creating the new first and last nodes
		Node newFirstNode = new Node(counter++);
		Node newLastNode = new Node(counter++);

		//gettiing old first nodes
		Node firstNodeFirstNFA = firstNFA.nodes.get(0);
		Node firstNodeSecondNFA = secondNFA.nodes.get(0);

		//adding pointers to the new first node
		newFirstNode.addPointer(firstNodeFirstNFA.name, 'e');
		newFirstNode.addPointer(firstNodeSecondNFA.name, 'e');
		
		//popping old last nodes
		Node lastNodeFirstNFA = firstNFA.nodes.remove(firstNFA.nodes.size() - 1);
		Node lastNodeSecondNFA = secondNFA.nodes.remove(secondNFA.nodes.size() - 1);

		//adding pointers from old last nodes to new one
		lastNodeFirstNFA.addPointer(newLastNode.name, 'e');
		lastNodeSecondNFA.addPointer(newLastNode.name, 'e');
		
		//merging the nodes into one NFA
		firstNFA.addNodes(lastNodeFirstNFA);
		secondNFA.addNodes(lastNodeSecondNFA);
		firstNFA.nodes.addAll(secondNFA.nodes);
		firstNFA.addNodes(newFirstNode, 'f');
		firstNFA.addNodes(newLastNode);
		
		//pushing new NFA
		NFAs.push(firstNFA);
	}	
	
	public void handleConcat() {
		//popping the NFAs with '.' applied to
		NFA secondNFA = NFAs.pop();
		NFA firstNFA = NFAs.pop();
		
		////popping the nodes about to be merged
		Node firstLinkedNode = firstNFA.nodes.remove(firstNFA.nodes.size() - 1);
		Node secondLinkedNode = secondNFA.nodes.remove(0);
		
		//creating the new link node
		Node linkedNode = new Node(firstLinkedNode.name);
		for(int i=0; i<secondLinkedNode.pointers.size(); i++) {
			Neighbor n = secondLinkedNode.pointers.get(i);
			linkedNode.addPointer(n.name, n.alphabet);
		}
		firstNFA.addNodes(linkedNode);

		//merging the two NFAs into one
		firstNFA.nodes.addAll(secondNFA.nodes);
		
		//pushing new NFA
		NFAs.push(firstNFA);
	}	
	
	public void handleStar() {
		//popping the NFA with '*' applied to
		NFA theNFA = NFAs.pop();
		
		//creating the new first and last nodes
		Node newFirstNode = new Node(counter++);
		Node newLastNode = new Node(counter++);
		
		//getting the old first and last nodes
		Node firstNode = theNFA.nodes.get(0);
		Node lastNode = theNFA.nodes.get(theNFA.nodes.size() - 1);
		
		//adding new pointers to old last node
		lastNode.addPointer(firstNode.name, 'e');
		lastNode.addPointer(newLastNode.name, 'e');
		theNFA.nodes.set(theNFA.nodes.size() - 1, lastNode);
		
		//adding new pointers to new first node
		newFirstNode.addPointer(firstNode.name, 'e');
		newFirstNode.addPointer(newLastNode.name, 'e');
		
		//adding new first and last node to NFA
		theNFA.addNodes(newFirstNode, 'f');
		theNFA.addNodes(newLastNode);
		
		//pushing new NFA
		NFAs.push(theNFA);
	}
	
	public void handleSimpleNFA(char alphabet) {
		//create two new nodes
		Node firstNode = new Node(counter++);
		Node secondNode = new Node(counter++);
		
		//add pointer to first node
		firstNode.addPointer(secondNode.name, alphabet);
		
		//create NFA and populate it with the two nodes
		NFA result = new NFA();
		result.addNodes(firstNode);
		result.addNodes(secondNode);
		
		//pushing new NFA
		NFAs.push(result);
		
	}
	
	
	public RegExToNfa(String input) {
		A = input.split("#")[0];
		String R = input.split("#")[1];
		
		for (char alphabet : R.toCharArray()) {
			if (alphabet == '*') {
				handleStar();
			}
			else if (alphabet == '|') {
				handleUnion();
			}
			else if (alphabet == '.') {
				handleConcat();
			}
			else {
				handleSimpleNFA(alphabet);
			}
		}
		
		Nfa = NFAs.pop();
		
	}
	

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		
		
		
		return null;
	}
	
	public static void main(String[] args) {
		int c = 0;
		Node firstNode = new Node(c++);
		Node secondNode = new Node(c++);
		
		firstNode.addPointer(secondNode.name, 'e');
		
		NFA result = new NFA();
		result.addNodes(firstNode);
		result.addNodes(secondNode);
		
		
	
		
	}

}
