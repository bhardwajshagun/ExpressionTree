/**
 * All source code has been added into the expression package.
 */

package expression;

/**
 * Import Map package.
 */
import java.util.Map;

/**
 * This interface contains all operations that all ExpressionNode classes should contain. All
 * ExpressionNodes are represented by the ExpressionTree class that is being implemented by the
 * Expression interface. This interface is implemented by the AbstractExpressionNode that is
 * extended into its subclasses OperatorNode and OperandNode. It should contain these methods:
 * getTerm, addTerm, infix, evaluate, and schemeExpression.
 */
public interface ExpressionNode {

  ExpressionNode addTerm(String parent, ExpressionNode child);

  /**
   * Method that takes in no arguments as parameters and returns the correctly
   * formatted string in infix form of the term and its children.
   *
   * @return the term and the children of the OperandNode as a String data type in infix form.
   */
  String infix();

  /**
   * Method that takes in a Map of String to Double variables and returns the result
   * of the evaluation of this ExpressionNode double data type. The Map
   * contains the numerical values of the variables and will throw an ArithmeticException if the
   * OperatorNode cannot be evaluated.
   *
   * @param variables the Map with the numerical values of the variables.
   * @return the evaluation of this OperatorNode as a double data type.
   */
  double evaluate(Map<String,Double> variables);

  /**
   * Method that takes in no arguments as parameters and returns the correctly
   * formatted string in scheme form of the term and its children.
   *
   * @return the term and the children of the ExpressionNode as a String data type in scheme form.
   */
  String schemeExpression();

}
