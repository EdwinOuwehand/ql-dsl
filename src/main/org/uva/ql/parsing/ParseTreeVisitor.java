package org.uva.ql.parsing;

import antlr.generated.QLBaseVisitor;
import antlr.generated.QLParser;
import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.ast.type.*;

import java.util.ArrayList;
import java.util.List;

public class ParseTreeVisitor extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext context) {
        String formId = context.id.getText();
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : context.statement()) {
            statements.add((Statement) visit(statementContext));
        }
        return new Form(formId, statements);
    }

    @Override
    public TreeNode visitQuestion(QLParser.QuestionContext context) {
        String questionName = context.ID().getText();
        String questionContent = context.text.getText();
        questionContent = questionContent.replaceAll("^\"|\"$", "");

        Type questionType = (Type) visit(context.type());

        if (context.calculatedValue() != null) {
            Expression calculation = (Expression) visit(context.calculatedValue());
            return new CalculatedQuestion(questionName, questionContent, questionType, calculation);
        } else {
            return new Question(questionName, questionContent, questionType);
        }
    }

    @Override
    public TreeNode visitIfStatement(QLParser.IfStatementContext context) {
        List<Statement> body = new ArrayList<>();
        for (QLParser.StatementContext item : context.statement()) {
            body.add((Statement) visit(item));
        }

        return new Conditional((Expression) visit(context.expression()), body);
    }

    @Override
    public TreeNode visitIfElseStatement(QLParser.IfElseStatementContext context) {
        List<Statement> ifBody = new ArrayList<>();
        for (QLParser.StatementContext item : context.ifCase) {
            ifBody.add((Statement) visit(item));
        }

        List<Statement> elseBody = new ArrayList<>();
        for (QLParser.StatementContext item : context.elseCase) {
            elseBody.add((Statement) visit(item));
        }

        return new Conditional((Expression) visit(context.expression()), ifBody, elseBody);
    }

    @Override
    public TreeNode visitAddSub(QLParser.AddSubContext context) {
        Expression left = (Expression) visit(context.left);
        Expression right = (Expression) visit(context.right);
        String operation = context.op.getText();

        if (operation.equals("+")) {
            return new Addition(left, right);
        } else {
            return new Subtraction(left, right);
        }
    }

    @Override
    public TreeNode visitMulDiv(QLParser.MulDivContext context) {
        Expression left = (Expression) visit(context.left);
        Expression right = (Expression) visit(context.right);
        String operation = context.op.getText();

        if (operation.equals("*")) {
            return new Multiplication(left, right);
        } else {
            return new Division(left, right);
        }
    }

    @Override
    public TreeNode visitNegation(QLParser.NegationContext context) {
        return new Negation((Expression) visit(context.expression()));
    }

    @Override
    public TreeNode visitComparation(QLParser.ComparationContext context) {
        String operation = context.op.getText();

        switch (operation) {
            case ">":
                return new GreaterThan((Expression) visit(context.left), (Expression) visit(context.right));
            case "<":
                return new LessThan((Expression) visit(context.left), (Expression) visit(context.right));
            case "<=":
                return new LessThanEqualTo((Expression) visit(context.left), (Expression) visit(context.right));
            case ">=":
                return new GreaterThanEqualTo((Expression) visit(context.left), (Expression) visit(context.right));
            case "!=":
                return new NotEqual((Expression) visit(context.left), (Expression) visit(context.right));
            default:
                return new Equal((Expression) visit(context.left), (Expression) visit(context.right));
        }
    }

    @Override
    public TreeNode visitMoneyType(QLParser.MoneyTypeContext context) {
        return new MoneyType();
    }

    @Override
    public TreeNode visitIntegerType(QLParser.IntegerTypeContext context) {
        return new IntegerType();
    }

    @Override
    public TreeNode visitBooleanType(QLParser.BooleanTypeContext context) {
        return new BooleanType();
    }

    @Override
    public TreeNode visitStringType(QLParser.StringTypeContext context) {
        return new StringType();
    }

    @Override
    public TreeNode visitParameter(QLParser.ParameterContext context) {
        return new Parameter(context.getText());
    }

    @Override
    public TreeNode visitStringLiteral(QLParser.StringLiteralContext context) {
        return new StringLiteral(context.getText());
    }

    @Override
    public TreeNode visitBooleanLiteral(QLParser.BooleanLiteralContext context) {
        return new BooleanLiteral(context.getText());
    }

    @Override
    public TreeNode visitIntegerLiteral(QLParser.IntegerLiteralContext context) {
        return new IntegerLiteral(context.getText());
    }

    @Override
    public TreeNode visitLogicalOr(QLParser.LogicalOrContext context) {
        return new Or((Expression) visit(context.left), (Expression) visit(context.right));
    }

    @Override
    public TreeNode visitLogicalAnd(QLParser.LogicalAndContext context) {
        return new And((Expression) visit(context.left), (Expression) visit(context.right));
    }

    @Override
    public TreeNode visitCalculatedValue(QLParser.CalculatedValueContext context) {
        return (Expression) visit(context.expression());
    }

    @Override
    public TreeNode visitParameterGroup(QLParser.ParameterGroupContext context) {
        return (Expression) visit(context.expression());
    }
}
