package org.uva.ql.evaluator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.BooleanLiteral;
import org.uva.ql.ast.expression.unary.IntegerLiteral;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.ast.expression.unary.StringLiteral;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ExpressionEvaluatorTest {

    private ExpressionEvaluator evaluator;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        evaluator = new ExpressionEvaluator();

    }

    @Test
    public void evaluateCondition() {
        Assert.assertTrue(this.evaluator.evaluateCondition(new Or(new BooleanLiteral("True"), new BooleanLiteral("False")), null));
    }


    @Test
    public void visitAddition() {
        Expression integer = new IntegerLiteral("1");

        Addition addition = new Addition(integer, integer);
        Value result = evaluator.evaluateExpression("", addition, null);
        Assert.assertEquals(2, result.getValue());


        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            addition = new Addition(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", addition, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Addition operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitAnd() {
        And and = new And(new BooleanLiteral("True"), new BooleanLiteral("False"));
        Value result = evaluator.evaluateExpression("", and, null);
        Assert.assertFalse((Boolean) result.getValue());

        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(BooleanLiteral.class));
        for (List<Expression> parameters : exceptions) {
            and = new And(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", and, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Logical And operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitDivision() {
        Expression integer = new IntegerLiteral("1");

        Division division = new Division(integer, integer);
        Value result = evaluator.evaluateExpression("", division, null);
        Assert.assertEquals(1, result.getValue());


        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            division = new Division(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", division, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Division operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitEqual() {
        Expression integer = new IntegerLiteral("1");

        Equal expression = new Equal(integer, integer);
        Value result = evaluator.evaluateExpression("", expression, null);
        Assert.assertTrue((Boolean) result.getValue());

        expression = new Equal(integer, new IntegerLiteral("0"));
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertFalse((Boolean) result.getValue());


        Expression bool = new BooleanLiteral("True");

        expression = new Equal(bool, bool);
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertTrue((Boolean) result.getValue());

        expression = new Equal(bool, new BooleanLiteral("False"));
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertFalse((Boolean) result.getValue());


        Expression stringExpression = new StringLiteral("Test");

        expression = new Equal(stringExpression, stringExpression);
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertTrue((Boolean) result.getValue());

        expression = new Equal(stringExpression, new StringLiteral("False"));
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertFalse((Boolean) result.getValue());

        List<List<Expression>> exceptions = getLiteralPairs(Arrays.asList(IntegerLiteral.class, StringLiteral.class, BooleanLiteral.class));
        for (List<Expression> parameters : exceptions) {
            expression = new Equal(parameters.get(0), parameters.get(1));
            result = evaluator.evaluateExpression("", expression, null);
            Assert.assertFalse((boolean)result.getValue());
        }
    }

    @Test
    public void visitGreaterThan() {
        Expression integer = new IntegerLiteral("1");

        GreaterThan greaterThan = new GreaterThan(integer, integer);
        Value result = evaluator.evaluateExpression("", greaterThan, null);
        Assert.assertFalse((boolean)result.getValue());

        greaterThan = new GreaterThan(integer, new IntegerLiteral("-1"));
        result = evaluator.evaluateExpression("", greaterThan, null);
        Assert.assertTrue((boolean)result.getValue());


        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            greaterThan = new GreaterThan(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", greaterThan, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Greater Than operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitGreaterThanEqualTo() {
        Expression integer = new IntegerLiteral("1");

        GreaterThanEqualTo greaterThanEqualTo = new GreaterThanEqualTo(integer, integer);
        Value result = evaluator.evaluateExpression("", greaterThanEqualTo, null);
        Assert.assertTrue((boolean)result.getValue());

        greaterThanEqualTo = new GreaterThanEqualTo(integer, new IntegerLiteral("-1"));
        result = evaluator.evaluateExpression("", greaterThanEqualTo, null);
        Assert.assertTrue((boolean)result.getValue());

        greaterThanEqualTo = new GreaterThanEqualTo(integer, new IntegerLiteral("10"));
        result = evaluator.evaluateExpression("", greaterThanEqualTo, null);
        Assert.assertFalse((boolean)result.getValue());

        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            greaterThanEqualTo = new GreaterThanEqualTo(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", greaterThanEqualTo, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Greater Than Equal to operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitLessThan() {
        Expression integer = new IntegerLiteral("1");

        LessThan lessThan = new LessThan(integer, integer);
        Value result = evaluator.evaluateExpression("", lessThan, null);
        Assert.assertFalse((boolean)result.getValue());

        lessThan = new LessThan(new IntegerLiteral("-1"), integer);
        result = evaluator.evaluateExpression("", lessThan, null);
        Assert.assertTrue((boolean)result.getValue());

        lessThan = new LessThan(integer, new IntegerLiteral("-1"));
        result = evaluator.evaluateExpression("", lessThan, null);
        Assert.assertFalse((boolean)result.getValue());


        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            lessThan = new LessThan(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", lessThan, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Less Than operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitLessThanEqualTo() {
        Expression integer = new IntegerLiteral("1");

        LessThanEqualTo lessThanEqualTo = new LessThanEqualTo(integer, integer);
        Value result = evaluator.evaluateExpression("", lessThanEqualTo, null);
        Assert.assertTrue((boolean)result.getValue());

        lessThanEqualTo = new LessThanEqualTo(integer, new IntegerLiteral("-1"));
        result = evaluator.evaluateExpression("", lessThanEqualTo, null);
        Assert.assertFalse((boolean)result.getValue());

        lessThanEqualTo = new LessThanEqualTo(integer, new IntegerLiteral("10"));
        result = evaluator.evaluateExpression("", lessThanEqualTo, null);
        Assert.assertTrue((boolean)result.getValue());

        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            lessThanEqualTo = new LessThanEqualTo(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", lessThanEqualTo, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Less Than Equal to operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitMultiplication() {
        Expression integer = new IntegerLiteral("2");

        Multiplication multiplication = new Multiplication(integer, integer);
        Value result = evaluator.evaluateExpression("", multiplication, null);
        Assert.assertEquals(4, result.getValue());


        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            multiplication = new Multiplication(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", multiplication, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Multiplication operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitNotEqual() {
        Expression integer = new IntegerLiteral("1");

        NotEqual expression = new NotEqual(integer, integer);
        Value result = evaluator.evaluateExpression("", expression, null);
        Assert.assertFalse((Boolean) result.getValue());

        expression = new NotEqual(integer, new IntegerLiteral("0"));
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertTrue((Boolean) result.getValue());


        Expression bool = new BooleanLiteral("True");

        expression = new NotEqual(bool, bool);
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertFalse((Boolean) result.getValue());

        expression = new NotEqual(bool, new BooleanLiteral("False"));
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertTrue((Boolean) result.getValue());


        Expression stringExpression = new StringLiteral("Test");

        expression = new NotEqual(stringExpression, stringExpression);
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertFalse((Boolean) result.getValue());

        expression = new NotEqual(stringExpression, new StringLiteral("False"));
        result = evaluator.evaluateExpression("", expression, null);
        Assert.assertTrue((Boolean) result.getValue());

        List<List<Expression>> exceptions = getLiteralPairs(Arrays.asList(IntegerLiteral.class, StringLiteral.class, BooleanLiteral.class));
        for (List<Expression> parameters : exceptions) {
            expression = new NotEqual(parameters.get(0), parameters.get(1));
            result = evaluator.evaluateExpression("", expression, null);
            Assert.assertTrue((boolean)result.getValue());
        }
    }

    @Test
    public void visitOr() {
        Or or = new Or(new BooleanLiteral("True"), new BooleanLiteral("False"));
        Value result = evaluator.evaluateExpression("", or, null);
        Assert.assertTrue((Boolean) result.getValue());

         or = new Or(new BooleanLiteral("False"), new BooleanLiteral("False"));
        result = evaluator.evaluateExpression("", or, null);
        Assert.assertFalse((Boolean) result.getValue());

        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(BooleanLiteral.class));
        for (List<Expression> parameters : exceptions) {
            or = new Or(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", or, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Logical Or operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitSubtraction() {
        Expression integer = new IntegerLiteral("1");

        Subtraction subtraction = new Subtraction(integer, integer);
        Value result = evaluator.evaluateExpression("", subtraction, null);
        Assert.assertEquals(0, result.getValue());


        // Test exceptions
        List<List<Expression>> exceptions = getLiteralPairs(Collections.singletonList(IntegerLiteral.class));
        for (List<Expression> parameters : exceptions) {
            subtraction = new Subtraction(parameters.get(0), parameters.get(1));
            try{
                evaluator.evaluateExpression("", subtraction, null);
            }
            catch(UnsupportedOperationException un){
                continue;
            }
            Assert.fail(
                    String.format("Subtraction operation did not throw UnsupportedOperationException for %s and %s",
                            parameters.get(0).getClass(),
                            parameters.get(1).getClass())
            );
        }
    }

    @Test
    public void visitNegation() {
        //todo
    }

    @Test
    public void visitParameter() {
        ValueTable valueTable = new ValueTable();

        Parameter parameter = new Parameter(null);
        Value result = this.evaluator.evaluateExpression("", parameter,valueTable);
        Assert.assertEquals(null, parameter.getID());


        valueTable.addOrUpdateValue("Test", new BooleanValue(true));
        parameter = new Parameter("Test");
        BooleanValue result2 = (BooleanValue) this.evaluator.evaluateExpression("", parameter, valueTable);
        Assert.assertTrue(result2.getValue());
    }

    @Test
    public void visitStringLiteral() {
        StringLiteral stringLiteral = new StringLiteral(null);
        StringValue result = (StringValue) this.evaluator.evaluateExpression("", stringLiteral, null);
        Assert.assertEquals(null, result.getValue());

        stringLiteral = new StringLiteral("Test");
        result = (StringValue) this.evaluator.evaluateExpression("", stringLiteral, null);
        Assert.assertEquals("Test", result.getValue());
    }

    @Test
    public void visitIntegerLiteral() {
        IntegerLiteral integerLiteral = new IntegerLiteral("1");
        IntegerValue result = (IntegerValue) this.evaluator.evaluateExpression("", integerLiteral, null);
        Assert.assertEquals(1, (int)result.getValue());

        integerLiteral = new IntegerLiteral("-1");
        result = (IntegerValue) this.evaluator.evaluateExpression("", integerLiteral, null);
        Assert.assertEquals(-1, (int)result.getValue());

        integerLiteral = new IntegerLiteral(null);
        result = (IntegerValue) this.evaluator.evaluateExpression("", integerLiteral, null);
        Assert.assertEquals(0, (int)result.getValue());

        integerLiteral = new IntegerLiteral("Test");
        result = (IntegerValue) this.evaluator.evaluateExpression("", integerLiteral, null);
        Assert.assertEquals(0, (int)result.getValue());
    }

    @Test
    public void visitBooleanLiteral() {
        BooleanLiteral booleanLiteral = new BooleanLiteral("True");
        BooleanValue result = (BooleanValue) this.evaluator.evaluateExpression("", booleanLiteral, null);
        Assert.assertTrue(result.getValue());

        booleanLiteral = new BooleanLiteral("False");
        result = (BooleanValue) this.evaluator.evaluateExpression("", booleanLiteral, null);
        Assert.assertFalse(result.getValue());

        booleanLiteral = new BooleanLiteral(null);
        result = (BooleanValue) this.evaluator.evaluateExpression("", booleanLiteral, null);
        Assert.assertFalse(result.getValue());
    }

    private List<List<Expression>> getLiteralPairs(List<Class<? extends Expression>> Exclude) {
        BooleanLiteral booleanLiteral = new BooleanLiteral("True");
        StringLiteral stringLiteral = new StringLiteral("");
        IntegerLiteral integerLiteral = new IntegerLiteral("1");

        List<List<Expression>> literalPairs  = new ArrayList<>();

        List<Expression> expressions = Arrays.asList(booleanLiteral,stringLiteral,integerLiteral);
        for (Expression expression1 : expressions) {
            for (Expression expression2 : expressions) {
                if(expression1.getClass().equals(expression2.getClass()) && Exclude.contains(expression1.getClass())){
                    continue; // Skip this pair if in exclude e.g. If StringLiteral is in Exclude don't add (StringLiteral,StringLiteral)
                }
                literalPairs.add(Arrays.asList(expression1, expression2));
            }
        }

        return literalPairs;

    }
}