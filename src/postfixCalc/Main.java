package postfixCalc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
	private static Stack<Integer> operandStack = new Stack<Integer>();
	private static LinkedList<String> operationQueue = new LinkedList<String>();
	
	public static void main(String[] args) {
		while (true) {
			try {
				
				System.out.println("Enter operand or operation\n"  
										+ " or 'exit'  command to exit\n"
										+ " or 'clear' command to clear stack and queue\n"
										+ " or 'print' command to print stack and queue");
				
				String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
				if (line.trim().equals("exit")) {
					break;
				}
				else if (line.trim().equals("clear")) {
					operandStack.clear();
					operationQueue.clear();
					continue;
				}
				else if (line.trim().equals("print")) {
					printStack();
					printQueue();
					continue;
				}
				else {
					boolean operandFound = false;
					boolean operationFound = false;
					try {
						int operand = Integer.parseInt(line.trim());
						operandStack.push(operand);
						operandFound = true;
						continue;
					} 
					catch (NumberFormatException nfe) {						
						operandFound = false;
					}
					
					if(containsOperation(line)) {
						operationFound = true;
						operationQueue.addLast(line.trim());
					}
					else {
						operationFound = false;
					}
					
					if(!operandFound && !operationFound) {
						System.out.println("Invalid input");
						continue;
					}
					
					if(operandStack.size() < 2) {
						System.out.println("Number of operands less than two");
						continue;
					}
					
					int result = 0;
					int secondOperand = 0;
					String operation = operationQueue.removeFirst();
					if (operation.equals("+")) {
						result = operandStack.pop() + operandStack.pop();
					}
					else if(operation.equals("*")) {
						result = operandStack.pop() * operandStack.pop();
					}
					else if(operation.equals("-")) {
						secondOperand = operandStack.pop();
						result = operandStack.pop() - secondOperand;
					}
					else if(operation.equals("/")) {
						secondOperand = operandStack.pop();
						if(secondOperand == 0) {
							System.err.println("Cannot divide by zero");
							continue;
						}
						result = operandStack.pop() / secondOperand;
					}
					operandStack.push(result);
					System.out.println("Result: " + result);
					operationFound = true;
					
					
				}
			} catch (Exception e) {
				System.err.println("Exception:" + e.getMessage());
				continue;
			}
		}
	}
	
	public static void printStack() {
		if(operandStack.size() == 0) {
			System.out.println("Operand stack is empty");
			return;
		}
		System.out.println("Operands:");
		Iterator<Integer> it = operandStack.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.print("\n");
	}
	
	public static void printQueue() {
		if(operationQueue.size() == 0) {
			System.out.println("Operation queue is empty");
			return;
		}
		System.out.println("Operations:");
		Iterator<String> it = operationQueue.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.print("\n");
	}
	
	public static boolean containsOperation(String line) {
		if (line.trim().equals("+") || 
			line.trim().equals("-") ||
			line.trim().equals("*") ||
			line.trim().equals("/")) {
			return true;
		}
		return false;
	}

}
