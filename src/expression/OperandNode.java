/**
 * All source code has been added into the expression package.
 */

package expression;

/**
 * Import Map package.
 */
import java.util.Map;

/**
 * This class represents a single OperandNode that contains a single algebraic or numerical input
 * that is a single term in the expression tree. It is extended by the abstract class
 * AbstractExpressionNode under the interface ExpressionNode. This class contains the methods:
 * addTerm, evaluate, infix, schemeExpression.
 */
public class OperandNode implements ExpressionNode {

  private String term;

  /**
   * Constructs the OperandNode object that takes a string as a parameter and
   * initializes it with the input as a String data type that is taken in as a parameter.
   *
   * @param input String that contains the operator of the OperandNode.
   */
  public OperandNode(String input) {
    term = input;
  }

  /**
   * Public method takes in a String and an ExpressionNode object and return this
   * OperandNode object.
   *
   * @param parent the parent of this node in the binary tree as a String data type.
   * @param child  an ExpressionNode object
   * @return this OperandNode
   */
  public ExpressionNode addTerm(String parent, ExpressionNode child) {
    return this;
  }

  /**
   * Public method takes in a Map of String to Double variable and returns the result of the
   * evaluation of this OperandNode as a double data type. The Map contains the numerical
   * value of the variable and will throw an ArithmeticException if the OperandNode cannot be
   * evaluated.
   *
   * @param variables the Map with the numerical value of the variable.
   * @return the evaluation of this OperandNode as a double data type.
   * @throws ArithmeticException if the value of the OperandNode cannot be determined.
   */
  public double evaluate(Map<String,Double> variables) throws ArithmeticException {
    try {
      return Double.parseDouble(term);
    } catch (NumberFormatException a) {
      if (variables.containsKey(term)) {
        return variables.get(term);
      }
      else {
        throw new ArithmeticException("Cannot evaluate variables in expression.");
      }
    }
  }

  /**
   * Public method that takes in no arguments as parameters and returns the term of the OperandNode
   * as a String data type for conversion of the expression to infix form.
   *
   * @return the term of the OperandNode as a String data type.
   */
  public String infix() {
    return term;
  }

  /**
   * Public method that takes in no arguments as parameters and returns the term of the OperandNode
   * as a String data type for conversion of the expression to Scheme form.
   *
   * @return the term of the OperandNode as a String data type.
   */
  public String schemeExpression() {
    return term;
  }

}
