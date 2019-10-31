/**
 * All source code has been added into the expression package.
 */

package expression;

/**
 * Import EmptyStackException, Map, and Stack packages.
 */
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

/**
 * This class represents a single ExpressionTree object that contains an ExpressionNode that
 * represents the root or start of the ExpressionTree. This class is under the interface Expression
 * and contains the methods getTerm, addTerm, infix, evaluate, and schemeExpression.
 */
public class ExpressionTree implements Expression {

  /**
   * ExpressionNode that is the root of the ExpressionTree.
   */
  private ExpressionNode root;

  /**
   * Constructs the binary tree of the postfix expresssion input and stores
   * the root of the tree as an attribute of type ExpressionNode. Throws
   * an IllegalArgumentException if the expression is not valid.
   *
   * @param input the string input of the postfix expression.
   * @throws IllegalArgumentException if the input is an invalid postfix expression.
   */
  public ExpressionTree(String input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Expression is invalid.");
    }
    String equation = input.trim().replaceAll(" +", " ");
    if (equation.equals("")) {
      throw new IllegalArgumentException("Expression is invalid.");
    }
    ExpressionNode node2;
    ExpressionNode node1;
    Stack<ExpressionNode> test_stack = new Stack();
    String[] terms = equation.split(" ");
    for (String s : terms) {
      if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
        try {
          node2 = test_stack.pop();
          node1 = test_stack.pop();
        } catch (EmptyStackException a) {
          throw new IllegalArgumentException("Expression is invalid.");
        }
        ExpressionNode newOperator = new OperatorNode(s);
        newOperator.addTerm(s, node1);
        newOperator.addTerm(s, node2);
        test_stack.push(newOperator);
      }
      else {
        ExpressionNode newOperand = new OperandNode(s);
        test_stack.push(newOperand);
      }
    }
    try {
      root = test_stack.pop();
    } catch (EmptyStackException b) {
      throw new IllegalArgumentException("Expression is invalid.");
    }
    if (!test_stack.empty()) {
      throw new IllegalArgumentException("Expression is invalid.");
    }
  }

  /**
   * This recursive method takes in no arguments and returns the root of this expression tree as a
   * string in infix form.
   *
   * @return the root of this expression tree in infix form as a string data type.
   */
  public String infix() {
    return root.infix();
  }

  /**
   * This recursive method takes in a Map of String to Double variables and returns the result
   * of the evaluation of the root of this expression tree as a double data type. Throws an
   * ArithmeticException is the value of a variable cannot be determined from the provided map.
   *
   * @param variables the Map with the numerical values of variables in the expression tree.
   * @return the evaluation of the expression tree as a double data type.
   * @throws ArithmeticException if the value of this expression cannot be determined.
   */
  public double evaluate(Map<String,Double> variables) throws ArithmeticException {
    return root.evaluate(variables);
  }

  /**
   * This recursive method takes in no arguments and returns the root of this expression tree as a
   * string in scheme form.
   *
   * @return the root of this expression tree in scheme form as a string data type.
   */
  public String schemeExpression() {
    return root.schemeExpression();
  }

}