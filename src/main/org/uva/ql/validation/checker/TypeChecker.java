package org.uva.ql.validation.checker;

import com.sun.istack.internal.NotNull;
import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.ast.type.*;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.collector.SymbolTable;
import org.uva.ql.visitor.ExpressionVisitor;
import org.uva.ql.visitor.StatementVisitor;
import org.uva.ql.visitor.TypeVisitor;

public class TypeChecker extends Checker
        implements StatementVisitor<Type, String>, ExpressionVisitor<Type, String>, TypeVisitor<Type, String> {

    private final String ERROR_MESSAGE = "Type checking error at: ";
    private final SymbolTable symbolTable;
    private final Form form;
    private ValidationResult result;

    public TypeChecker(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.form = form;
    }

    @Override
    public ValidationResult runCheck() {
        result = new ValidationResult();

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }

        return result;
    }

    @NotNull
    private Type validateExpression(BinaryOperation operation, Type type) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!type.isCompatible(left) || !type.isCompatible(right)) {
            result.addError(ERROR_MESSAGE + operation);
        }

        return type;
    }

    @Override
    public Type visit(Parameter parameter, String Context) {
        Type type = symbolTable.getTypeByName(parameter.getID());
        assert type != null;
        return type;
    }

    @Override
    public Type visit(Conditional conditional, String context) {
        Type type = conditional.getCondition().accept(this, null);

        for (Statement statement : conditional.getIfBlock()) {
            statement.accept(this, null);
        }

        for (Statement statement : conditional.getElseBlock()) {
            statement.accept(this, null);
        }

        if (!new BooleanType().isCompatible(type)) {
            result.addError(ERROR_MESSAGE + conditional);
        }

        return new BooleanType();
    }

    @Override
    public Type visit(CalculatedQuestion question, String context) {
        Type calculationType = question.getExpression().accept(this, null);

        if (!question.getType().isCompatible(calculationType)) {
            result.addError(ERROR_MESSAGE + question);
        }

        return question.getType();
    }

    @Override
    public Type visit(Addition addition, String context) {
        return validateExpression(addition, new IntegerType());
    }

    @Override
    public Type visit(Division division, String context) {
        return validateExpression(division, new IntegerType());
    }

    @Override
    public Type visit(Multiplication multiplication, String context) {
        return validateExpression(multiplication, new IntegerType());
    }

    @Override
    public Type visit(Subtraction subtraction, String context) {
        return validateExpression(subtraction, new IntegerType());
    }

    @Override
    public Type visit(GreaterThan greaterThan, String context) {
        validateExpression(greaterThan, new IntegerType());
        return new BooleanType();
    }

    @Override
    public Type visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        validateExpression(greaterThanEqualTo, new IntegerType());
        return new BooleanType();
    }

    @Override
    public Type visit(LessThan lessThan, String context) {
        validateExpression(lessThan, new IntegerType());
        return new BooleanType();
    }

    @Override
    public Type visit(LessThanEqualTo lessThanEqualTo, String context) {
        validateExpression(lessThanEqualTo, new IntegerType());
        return new BooleanType();
    }

    @Override
    public Type visit(Equal equal, String context) {
        Type expectedType = equal.getLeft().accept(this, null);
        validateExpression(equal, expectedType);
        return new BooleanType();
    }

    @Override
    public Type visit(NotEqual notEqual, String context) {
        Type expectedType = notEqual.getLeft().accept(this, null);
        validateExpression(notEqual, expectedType);
        return new BooleanType();
    }

    @Override
    public Type visit(Or or, String context) {
        return validateExpression(or, new BooleanType());
    }

    @Override
    public Type visit(And and, String context) {
        return validateExpression(and, new BooleanType());
    }

    @Override
    public Type visit(Negation negation, String Context) {
        Type type = negation.getExpression().accept(this, null);

        if (!new BooleanType().isCompatible(type)) {
            result.addError(ERROR_MESSAGE + negation);
        }

        return new BooleanType();
    }

    @Override
    public Type visit(Question question, String context) {
        return null;
    }

    @Override
    public Type visit(BooleanType booleanType, String context) {
        return booleanType;
    }

    @Override
    public Type visit(IntegerType integerType, String context) {
        return integerType;
    }

    @Override
    public Type visit(MoneyType moneyType, String context) {
        return moneyType;
    }

    @Override
    public Type visit(StringType stringType, String context) {
        return stringType;
    }

    @Override
    public Type visit(StringLiteral stringLiteral, String context) {
        return new StringType();
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral, String Context) {
        return new IntegerType();
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral, String context) {
        return new BooleanType();
    }
}
