package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.Scanner;

public class ExpressionEvaluator {
	private static String toPostfix(String expression) {
		Scanner sc = new Scanner(expression);
		sc.useLocale(Locale.US);
		String out = "";
		
		Deque<String> opStack = new ArrayDeque<String>();
		
		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				out += " " + sc.nextDouble();
			} else {
				String op = sc.next();
				switch (op) {
					case ")":
						while (!opStack.peek().equals("("))
							out += " " + opStack.pop();
						opStack.pop();
						break;
					case "(":
						opStack.push(op);
						break;
					case "*":
					case "/":
						if (!opStack.isEmpty() && "*/".contains(opStack.peek())) {
							out += " " + opStack.pop();
						}
						opStack.push(op);
						break;
					case "+":
					case "-":
						while(!opStack.isEmpty() && !opStack.peek().equals("("))
						{
							out += " " + opStack.pop();
						}
						opStack.push(op);
						break;
					default:
						break;
				}
			}
		}
		while (!opStack.isEmpty())
			out += " " + opStack.pop();

		sc.close();
		return out;
	}
	
	public static double compute(String exp) {
		String rep;
		rep = exp.replaceAll("([\\*\\+\\-\\/\\(\\)])", " $1 ");
		String expression = " 0 " + toPostfix(rep);
		Scanner sc = new Scanner(expression);
		sc.useLocale(Locale.US);
		Deque<Double> operands = new ArrayDeque<Double>();
		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				operands.push(sc.nextDouble());
			} else {
				double rhs = operands.pop();
				double lhs = operands.pop();
				switch (sc.next()) {
				case "*":
					operands.push(lhs * rhs);
					break;
				case "/":
					operands.push(lhs / rhs);
					break;
				case "-":
					operands.push(lhs - rhs);
					break;
				case "+":
					operands.push(lhs + rhs);
					break;
				}
			}
		}
		sc.close();
		return operands.pop();
	}
	
	public static void main(String[] args) {
		System.out.println(compute("3.5e2+3*(4-2)"));
		System.out.println(compute("3*4"));
		System.out.println(compute("3-4"));
		System.out.println(compute("3+4"));
		System.out.println(compute("-3+4"));
		System.out.println(compute("(3-4)"));
	}
}
