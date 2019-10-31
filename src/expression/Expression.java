/**
 * All source code has been added into the expression package.
 */

package expression;

/**
 * Import Map package.
 */
import java.util.Map;

/**
 * This interface contains all operations that all Expression classes should contain. It should
 * contain these methods: infix, evaluate, and schemeExpression. It is implemented in the class
 * ExpressionTree.
 */
public interface Expression {

  /**
   * This method takes in no arguments and returns this expression as a string in infix form. The
   * string format has the operators written in-between their operands like ( x = y ).
   *
   * @return the expression in infix form as a string data type.
   */
  String infix();

  /**
   * This method takes in a Map of String to Double variables and returns the result of the
   * evaluation of this expression as a double data type. The Map contains the numerical values
   * of the variables located in the expression tree itself. This method will throw an Arithmetic
   * Exception if the expression cannot be evaluated due to some operands having no pre-determined
   * numerical values.
   *
   * @param variables the Map with the numerical values of variables in the expression tree.
   * @return the evaluation of this expression tree as a double data type.
   * @throws ArithmeticException if the values of the operands cannot be determined.
   */
  double evaluate(Map<String,Double> variables) throws ArithmeticException;

  /**
   * This method takes in no arguments and returns this expression as a string in scheme form. The
   * string format starts with the operator and then the operands in that order like (+ a b). There
   * are no space after the ( and before the ), but there are still spaces between the operator and
   * operands.
   *
   * @return the expression in scheme form as a string data type.
   */
  String schemeExpression();

}
