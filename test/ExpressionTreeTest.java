import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import expression.Expression;
import expression.ExpressionTree;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the ExpressionTree class.
 */
public class ExpressionTreeTest {

  private Expression addTree;
  private Expression addTree2;
  private Expression subTree;
  private Expression subTree2;
  private Expression multTree;
  private Expression multTree2;
  private Expression divTree;
  private Expression divTree2;
  private Expression allTree;
  private Expression abcdTree;
  private Expression abcdTree2;
  private Expression single;

  @Before
  public void testsetUp() {
    addTree = new ExpressionTree("a b +");
    addTree2 = new ExpressionTree("-6 3.54 +");
    subTree = new ExpressionTree("60 d -");
    subTree2 = new ExpressionTree("20.3   3.54   -");
    multTree = new ExpressionTree("  a 10 *  ");
    multTree2 = new ExpressionTree("   3   -5.6   *");
    divTree = new ExpressionTree("  6   f   /  ");
    divTree2 = new ExpressionTree("  44.44   4   /  ");
    allTree = new ExpressionTree("10.3 5 / -4.5 * b + 22.2 -23 * -");
    abcdTree = new ExpressionTree("a b + c d + *");
    abcdTree2 = new ExpressionTree("  a   b   -   c  d   +  /  ");
    single = new ExpressionTree("  d  ");
  }

  @Test
  public void testInfix() {
    assertEquals("( a + b )", addTree.infix());
    assertEquals("( -6 + 3.54 )", addTree2.infix());
    assertEquals("( 60 - d )", subTree.infix());
    assertEquals("( 20.3 - 3.54 )", subTree2.infix());
    assertEquals("( a * 10 )", multTree.infix());
    assertEquals("( 3 * -5.6 )", multTree2.infix());
    assertEquals("( 6 / f )", divTree.infix());
    assertEquals("( 44.44 / 4 )", divTree2.infix());
    assertEquals("( ( ( ( 10.3 / 5 ) * -4.5 ) + b ) - ( 22.2 * -23 ) )", allTree.infix());
    assertEquals("( ( a + b ) * ( c + d ) )", abcdTree.infix());
    assertEquals("( ( a - b ) / ( c + d ) )", abcdTree2.infix());
    assertEquals("d", single.infix());
  }

  @Test
  public void testScheme() {
    assertEquals("(+ a b)", addTree.schemeExpression());
    assertEquals("(+ -6 3.54)", addTree2.schemeExpression());
    assertEquals("(- 60 d)", subTree.schemeExpression());
    assertEquals("(- 20.3 3.54)", subTree2.schemeExpression());
    assertEquals("(* a 10)", multTree.schemeExpression());
    assertEquals("(* 3 -5.6)", multTree2.schemeExpression());
    assertEquals("(/ 6 f)", divTree.schemeExpression());
    assertEquals("(/ 44.44 4)", divTree2.schemeExpression());
    assertEquals("(- (+ (* (/ 10.3 5) -4.5) b) (* 22.2 -23))", allTree.schemeExpression());
    assertEquals("(* (+ a b) (+ c d))", abcdTree.schemeExpression());
    assertEquals("(/ (- a b) (+ c d))", abcdTree2.schemeExpression());
    assertEquals("d", single.schemeExpression());
  }

  @Test
  public void testEvaluate() {
    Map<String, Double> noMap = new HashMap<>();

    assertEquals(-2.46, addTree2.evaluate(noMap), 0.01);
    assertEquals(16.76, subTree2.evaluate(noMap), 0.01);
    assertEquals(-16.8, multTree2.evaluate(noMap), 0.01);
    assertEquals(11.11, divTree2.evaluate(noMap), 0.01);

    Expression testEvaluate1 = new ExpressionTree("4 3 +");
    assertEquals(7, testEvaluate1.evaluate(noMap), 0.01);
    Expression testEvaluate2 = new ExpressionTree("10 5 -");
    assertEquals(5, testEvaluate2.evaluate(noMap), 0.01);
    Expression testEvaluate3 = new ExpressionTree("10 5 /");
    assertEquals(2, testEvaluate3.evaluate(noMap), 0.01);
    Expression testEvaluate4 = new ExpressionTree("10 5 *");
    assertEquals(50, testEvaluate4.evaluate(noMap), 0.01);
    Expression testEvaluate5 = new ExpressionTree("20 10 2 / -");
    assertEquals(15, testEvaluate5.evaluate(noMap), 0.01);
    Expression testEvaluate6 = new ExpressionTree("20 10 - 2 /");
    assertEquals(5, testEvaluate6.evaluate(noMap), 0.01);

    Expression testEvalVariable1 = new ExpressionTree("a b +");
    HashMap<String,Double> map1 = new HashMap<>();
    map1.put("a", 100.0);
    map1.put("b", 5.0);
    assertEquals(105, testEvalVariable1.evaluate(map1), 0.01);

    Expression testEvalVariable2 = new ExpressionTree("a 5 +");
    Map<String,Double> map2 = new HashMap<>();
    map2.put("a", 100.0);
    assertEquals(105, testEvalVariable2.evaluate(map1), 0.01);

    assertEquals(-2.46, addTree2.evaluate(map1), 0.01);
    assertEquals(16.76, subTree2.evaluate(map1), 0.01);
    assertEquals(-16.8, multTree2.evaluate(map1), 0.01);
    assertEquals(11.11, divTree2.evaluate(map1), 0.01);

    assertEquals(-2.46, addTree2.evaluate(map2), 0.01);
    assertEquals(16.76, subTree2.evaluate(map2), 0.01);
    assertEquals(-16.8, multTree2.evaluate(map2), 0.01);
    assertEquals(11.11, divTree2.evaluate(map2), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorFormat() {
    new ExpressionTree("b 5 + 3 + * e /");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorFormat2() {
    new ExpressionTree("1 + 3 4 + *");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorTooManyOperators() {
    new ExpressionTree("* 3 4 +");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorNoOperator() {
    new ExpressionTree("5 6");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorNoString() {
    new ExpressionTree("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorWhiteSpace() {
    new ExpressionTree("  ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNullConstructor() {
    new ExpressionTree(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorInfixForm() {
    new ExpressionTree("5 + 7 / 2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorNoLeftChild() {
    new ExpressionTree("+ 6");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorNoRightChild() {
    new ExpressionTree("10 -");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalConstructorNoOperands() {
    new ExpressionTree("/");
  }

  @Test(expected = ArithmeticException.class)
  public void testIllegalEval() {
    Expression illegalEval = new ExpressionTree("a b +");
    Map<String, Double> map = new HashMap<>();
    map.put("b", -5.0);
    illegalEval.evaluate(map);
  }

  @Test(expected = ArithmeticException.class)
  public void testIllegalEval2() {
    Expression illegalEval2 = new ExpressionTree("a b -");
    Map<String, Double> map = new HashMap<>();
    map.put("b", 5.0);
    map.put("c", 12.5);
    illegalEval2.evaluate(map);
  }

  @Test(expected = ArithmeticException.class)
    public void testIllegalEval3() {
    Expression illegalEval3 = new ExpressionTree("a b /");
    Map<String, Double> map = new HashMap<>();
    map.put("a", 100.0);
    map.put("c", 12.5);
    illegalEval3.evaluate(map);
  }

}