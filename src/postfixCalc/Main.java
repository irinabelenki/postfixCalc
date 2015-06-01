package postfixCalc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<Integer>();

		while (true) {
			try {
				
				System.out.println("Enter " + (stack.size() < 2 ? "operand" : "operation") 
											+ " or 'exit' to exit");
				
				String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
				if (line.equals("exit")) {
					break;
				}
				
				if (stack.size() < 2) {
					try {
						int operand = Integer.parseInt(line);
						stack.push(operand);
					} 
					catch (NumberFormatException nfe) {						
						System.err.println("Invalid integer");
						continue;
					}
				} 
				else {
					int result = 0;
					int secondOperand = 0;
					if (line.equals("+")) {
						result = stack.pop() + stack.pop();
					}
					else if(line.equals("*")) {
						result = stack.pop() * stack.pop();
					}
					else if(line.equals("-")) {
						secondOperand = stack.pop();
						result = stack.pop() - secondOperand;
					}
					else if(line.equals("/")) {
						secondOperand = stack.pop();
						if(secondOperand == 0) {
							System.err.println("Cannot divide by zero");
							continue;
						}
						result = stack.pop() / secondOperand;
					}
					else {
						System.err.println("Invalid operation");
						continue;
					}
					stack.push(result);
					System.out.println("Result: " + result);
				}
			} catch (Exception e) {
				System.err.println("Exception:" + e.getMessage());
				continue;
			}
		}
	}

}
