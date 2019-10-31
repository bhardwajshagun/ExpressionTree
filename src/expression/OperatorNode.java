/**
 * All source code has been added into the expression package.
 */

package expression;

/**
 * Import Map, LinkedList, and List packages.
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a single OperatorNode that contains a single List containing
 * ExpressionNodes that are its resultant children in the expression tree. The OperatorNode itself
 * takes in a input that is an operator (+, -, *, /) that is a single term in the expression tree.
 * It is extended by the abstract class AbstractExpressionNode under the interface ExpressionNode.
 * This class contains the methods: addTerm, evaluate, infix, schemeExpression.
 */
public class OperatorNode implements ExpressionNode {

  private String term;

  /**
   * List containing ExpressionNodes that are the children of the OperatorNode.
   */
  private List<ExpressionNode> children;

  /**
   * Constructs the OperatorNode object that takes a string as a parameter and
   * initializes it with the input as a String data type that is taken in as a parameter and an
   * empty LinkedList that will contain its children in the expression tree.
   *
   * @param input String that contains the operator of the OperatorNode.
   */
  public OperatorNode(String input) {
    term = input;
    children = new LinkedList<>();
  }

  /**
   * Public recursive method takes in a String and an ExpressionNode object adds the
   * ExpressionNode parameter to this object's children list if the parent equals
   * this object's term. Otherwise, adds the ExpresionNode child to this object's
   * children recursively and sets those returns as the children.
   *
   * @param parent the parent of this node in the binary tree as a String data type.
   * @param child  the ExpressionNode object to add to the tree.
   * @return this OperatorNode
   */
  public ExpressionNode addTerm(String parent, ExpressionNode child) {
    if (term.equals(parent)) {
      children.add(child);
      return this;
    }
    for (int i = 0; i < children.size(); i++) {
      children.set(i, children.get(i).addTerm(parent, child));
    }
    return this;
  }

  /**
   * Public recursive method takes in a Map of String to Double variables and returns the result
   * of the evaluation of this OperandNode and its children as a double data type. The Map
   * contains the numerical values of the variables and will throw an ArithmeticException if the
   * OperatorNode cannot be evaluated.
   *
   * @param variables the Map with the numerical values of the variables.
   * @return the evaluation of this OperatorNode as a double data type.
   */
  public double evaluate(Map<String,Double> variables) {
    if (term.equals("+")) {
      return children.get(0).evaluate(variables) + children.get(1).evaluate(variables);
    }
    else if (term.equals("-")) {
      return children.get(0).evaluate(variables) - children.get(1).evaluate(variables);
    }
    else if (term.equals("*")) {
      return children.get(0).evaluate(variables) * children.get(1).evaluate(variables);
    }
    else {
      return children.get(0).evaluate(variables) / children.get(1).evaluate(variables);
    }
  }

  /**
   * Public recursive method that takes in no arguments as parameters and returns the correctly
   * formatted string in infix form of the term and its children.
   *
   * @return the term and the children of the OperandNode as a String data type in infix form.
   */
  @Override
  public String infix() {
    return "( " + children.get(0).infix() + " " + term + " " + children.get(1).infix() + " )";
  }

  /**
   * Public recursive method that takes in no arguments as parameters and returns the correctly
   * formatted string in scheme form of the term and its children.
   *
   * @return the term and the children of the OperandNode as a String data type in scheme form.
   */
  public String schemeExpression() {
    return "(" + term + " " + children.get(0).schemeExpression() + " "
            + children.get(1).schemeExpression() + ")";
  }

}
