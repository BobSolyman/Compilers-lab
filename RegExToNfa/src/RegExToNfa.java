package csen1002.main.task1;

import java.util.ArrayList;
import java.util.Collections;
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


class Node {
	int name;
	ArrayList<Neighbor> pointers;

	public Node(int name) {
		this.name = name;
		pointers = new ArrayList<Neighbor>();
	}

	public void addPointer(int newNode, char p) {

		pointers.add(new Neighbor(newNode, p));
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

	static NFA Nfa = new NFA();


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
		I = ""+ Nfa.nodes.get(0).name;
		F = ""+ Nfa.nodes.get(Nfa.nodes.size()-1).name;

	}
	
	public int compareT(String t1, String t2) {
		
		if(Integer.parseInt(t1.split(",")[0])<Integer.parseInt(t2.split(",")[0]))
			return -1;
		if(Integer.parseInt(t1.split(",")[0])>Integer.parseInt(t2.split(",")[0]))
			return 1;
		
		if(t1.split(",")[1].compareTo(t2.split(",")[1])==-1)
			return -1;
		if(t1.split(",")[1].compareTo(t2.split(",")[1])==1)
			return 1;
		
		if(Integer.parseInt(t1.split(",")[2])<Integer.parseInt(t2.split(",")[2]))
			return -1;
		if(Integer.parseInt(t1.split(",")[2])>Integer.parseInt(t2.split(",")[2]))
			return 1;
		
		return 0;
	}


	@Override
	public String toString() {

		ArrayList<Integer> qArray = new ArrayList<Integer>();
		ArrayList<String> tArray = new ArrayList<String>();

		for(int i=0; i<Nfa.nodes.size(); i++) {
			qArray.add(Nfa.nodes.get(i).name);
			//System.out.println(Nfa.nodes.get(i).name);
			for(int j=0; j<Nfa.nodes.get(i).pointers.size(); j++) {
				tArray.add(Nfa.nodes.get(i).name+","+Nfa.nodes.get(i).pointers.get(j).alphabet+","+Nfa.nodes.get(i).pointers.get(j).name);
				
				//System.out.println("--"+Nfa.nodes.get(i).pointers.get(j).alphabet+"-->"+Nfa.nodes.get(i).pointers.get(j).name);
			}
		}
		
		
		Collections.sort(qArray);
		for (int i=0; i<qArray.size(); i++) {
			Q += ";" + qArray.get(i);
		}
		Q = Q.substring(1);


		for(int i=0; i<tArray.size(); i++) {
			for(int j=i+1; j<tArray.size(); j++) {
				String temp = "";
                if (compareT(tArray.get(j), tArray.get(i))==-1) {
                    temp = tArray.get(i);
                    tArray.set(i, tArray.get(j));
                    tArray.set(j, temp);
                }
			}
		}
		
		
		//Collections.sort(tArray);
		for (int i=0; i<tArray.size(); i++) {
			T += ";" + tArray.get(i);
		}
		T = T.substring(1);

		return Q+"#"+A+"#"+T+"#"+I+"#"+F;
	}

}
