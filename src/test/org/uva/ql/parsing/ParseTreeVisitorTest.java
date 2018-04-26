package org.uva.ql.parsing;

import antlr.generated.QLParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.ql.ast.CalculatedQuestion;
import org.uva.ql.ast.Conditional;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.expression.binary.And;
import org.uva.ql.ast.expression.binary.Or;
import org.uva.ql.ast.expression.unary.BooleanLiteral;
import org.uva.ql.ast.expression.unary.IntegerLiteral;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.ast.expression.unary.StringLiteral;

import java.util.Arrays;
import java.util.List;


public class ParseTreeVisitorTest {

    private ASTBuilder builder;

    @Before
    public void setUp() {
        this.builder = new ASTBuilder();
    }

    @Test
    public void visitForm() {
        QLParser parser = builder.getQLParser("form Questions {" +
                "\"Test?\" test: string\n" +
                "if (\"1\" == 1) {\"Test1?\" test: integer }}");
        Form form = builder.getForm(parser);
        Assert.assertEquals("Form: Questions\n" +
                "\t\"Test?\"\ttest:StringType\n" +
                "\tIf (\"1\" == 1) \n" +
                "\t\t\"Test1?\"\ttest:IntegerType\n", form.toString());
    }

    @Test
    public void visitQuestion() {
        QLParser parser = builder.getQLParser("\"TestQuestion:\" testText: integer");
        Question question = (Question) builder.getStatement(parser);
        Assert.assertEquals("\"TestQuestion:\"\ttestText:IntegerType", question.toString());
    }

    @Test
    public void visitIfStatement() {
        String testCase = "if(true) { \"MyQuestion\" myInt: integer }";
        QLParser parser = builder.getQLParser(testCase);
        Conditional conditional = (Conditional) builder.getStatement(parser);
        Assert.assertEquals("\"MyQuestion\"\tmyInt:IntegerType", conditional.getIfBlock().get(0).toString());
    }

    @Test
    public void visitIfElseStatement() {
        String testCase = "if(true) { } else { \"MyQuestion\" myInt: integer }";
        QLParser parser = builder.getQLParser(testCase);
        Conditional conditional = (Conditional) builder.getStatement(parser);
        Assert.assertEquals("\"MyQuestion\"\tmyInt:IntegerType", conditional.getElseBlock().get(0).toString());
    }

    @Test
    public void visitAddSub() {
        List<String> testCases = Arrays.asList(
                "1 + 1",
                "1 - 1",
                "1 - 1 + 1 - 1"
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Expression expression = builder.getExpression(parser);
            Assert.assertEquals(testCase, expression.toString());
        }
    }

    @Test
    public void visitComparation() {
        List<String> testCases = Arrays.asList(
                "(1 > 1)",
                "((1 > 1) > 1)",
                "(1 < 1)",
                "((1 < 1) < 1)",
                "(1 >= 1)",
                "((1 >= 1) >= 1)",
                "(1 <= 1)",
                "((1 <= 1) <= 1)",
                "(1 != 1)",
                "((1 != 1) != 1)",
                "(1 == 1)",
                "((1 == 1) == 1)",
                "(((1 < 1) >= (((1 < 1) > (1 == 1)) != 1)) == 1)"
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Expression expression = builder.getExpression(parser);
            Assert.assertEquals(testCase, expression.toString());
        }
    }

    @Test
    public void visitMoneyType() {
        String testCase = "\"Where is\" myMoney: money";
        QLParser parser = builder.getQLParser(testCase);
        Question question = (Question) builder.getStatement(parser);
        Assert.assertEquals("MoneyType", question.getType().toString());
    }

    @Test
    public void visitIntegerType() {
        String testCase = "\"MyQuestion\" myInt: integer";
        QLParser parser = builder.getQLParser(testCase);
        Question question = (Question) builder.getStatement(parser);
        Assert.assertEquals("IntegerType", question.getType().toString());
    }

    @Test
    public void visitBooleanType() {
        String testCase = "\"MyQuestion\" myBool: boolean";
        QLParser parser = builder.getQLParser(testCase);
        Question question = (Question) builder.getStatement(parser);
        Assert.assertEquals("BooleanType", question.getType().toString());
    }

    @Test
    public void visitStringType() {
        String testCase = "\"MyQuestion\" myString: string";
        QLParser parser = builder.getQLParser(testCase);
        Question question = (Question) builder.getStatement(parser);
        Assert.assertEquals("StringType", question.getType().toString());
    }

    @Test
    public void visitParameter() {
        List<String> testCases = Arrays.asList(
                "P",
                "P",
                "Parameter",
                "PARAMETER"
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Parameter parameter = (Parameter) builder.getExpression(parser);
            Assert.assertEquals(testCase, parameter.getID());
        }
    }

    @Test
    public void visitStringLiteral() {
        List<String> testCases = Arrays.asList(
                "\"a\"",
                "\"ABC\"",
                "\"TestTestTestTestTestTest\""
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            StringLiteral stringLiteral = (StringLiteral) builder.getExpression(parser);
            Assert.assertEquals(testCase, stringLiteral.getValue());
        }
    }

    @Test
    public void visitBooleanLiteral() {
        List<String> testCases = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            BooleanLiteral booleanLiteral = (BooleanLiteral) builder.getExpression(parser);
            Assert.assertEquals(Boolean.parseBoolean(testCase), booleanLiteral.getValue());
        }
    }

    @Test
    public void visitIntegerLiteral() {
        List<String> testCases = Arrays.asList(
                "-1",
                "0",
                "1",
                "10",
                "2147483647" // Max signed positive int
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            IntegerLiteral integerLiteral = (IntegerLiteral) builder.getExpression(parser);
            Assert.assertEquals(Integer.parseInt(testCase), integerLiteral.getValue());
        }
    }

    @Test
    public void visitLogicalOr() {
        QLParser parser = builder.getQLParser("A || B");
        Or or = (Or) builder.getExpression(parser);
        Assert.assertEquals("A || B", or.toString());
    }

    @Test
    public void visitLogicalAnd() {
        QLParser parser = builder.getQLParser("A && B");
        And and = (And) builder.getExpression(parser);
        Assert.assertEquals("A && B", and.toString());
    }

    @Test
    public void visitCalculatedValue() {
        QLParser parser = builder.getQLParser("\"TestQuestion:\" testText: integer = 10");
        CalculatedQuestion calculatedQuestion = (CalculatedQuestion) builder.getStatement(parser);
        Assert.assertEquals("\"TestQuestion:\"\ttestText:IntegerType = 10", calculatedQuestion.toString());
    }

    @Test
    public void visitMulDiv() {
        List<String> testCases = Arrays.asList(
                "1 * 1",
                "1 / 1"
        );
        for (String testCase : testCases) {
            QLParser parser = builder.getQLParser(testCase);
            Expression expression = builder.getExpression(parser);
            Assert.assertEquals(testCase, expression.toString());
        }
    }
}