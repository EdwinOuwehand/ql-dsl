package org.uva.ql.evaluator;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.visitor.ExpressionVisitor;

public class ExpressionEvaluator implements ExpressionVisitor<Value, String> {

    private ValueTable valueTable;

    public boolean evaluateCondition(Expression expression, ValueTable valueTable) {
        this.valueTable = valueTable;
        return ((BooleanValue) expression.accept(this, null)).getValue();
    }

    public Value evaluateExpression(String id, Expression expression, ValueTable valueTable) {
        this.valueTable = valueTable;

        return expression.accept(this, id);
    }

    @Override
    public Value visit(Addition addition, String context) {
        Value left = addition.getLeft().accept(this, context);
        Value right = addition.getRight().accept(this, context);
        return left.add(right);
    }

    @Override
    public Value visit(Division division, String context) {
        Value left = division.getLeft().accept(this, context);
        Value right = division.getRight().accept(this, context);
        return left.divide(right);
    }

    @Override
    public Value visit(Equal equal, String context) {
        Value left = equal.getLeft().accept(this, context);
        Value right = equal.getRight().accept(this, context);
        return left.equalTo(right);
    }

    @Override
    public Value visit(GreaterThan greaterThan, String context) {
        Value left = greaterThan.getLeft().accept(this, context);
        Value right = greaterThan.getRight().accept(this, context);
        return left.greaterThan(right);
    }

    @Override
    public Value visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        Value left = greaterThanEqualTo.getLeft().accept(this, context);
        Value right = greaterThanEqualTo.getRight().accept(this, context);
        return left.greaterThanEqualTo(right);
    }

    @Override
    public Value visit(LessThan lessThan, String context) {
        Value left = lessThan.getLeft().accept(this, context);
        Value right = lessThan.getRight().accept(this, context);
        return left.lessThan(right);
    }

    @Override
    public Value visit(LessThanEqualTo lessThanEqualTo, String context) {
        Value left = lessThanEqualTo.getLeft().accept(this, context);
        Value right = lessThanEqualTo.getRight().accept(this, context);
        return left.lessThanEqualTo(right);
    }

    @Override
    public Value visit(And and, String context) {
        Value left = and.getLeft().accept(this, context);
        Value right = and.getRight().accept(this, context);
        return left.logicalAnd(right);
    }

    @Override
    public Value visit(Or or, String context) {
        Value left = or.getLeft().accept(this, context);
        Value right = or.getRight().accept(this, context);
        return left.logicalOr(right);
    }

    @Override
    public Value visit(Multiplication multiplication, String context) {
        Value left = multiplication.getLeft().accept(this, context);
        Value right = multiplication.getRight().accept(this, context);
        return left.multiply(right);
    }

    @Override
    public Value visit(NotEqual notEqual, String context) {
        Value left = notEqual.getLeft().accept(this, context);
        Value right = notEqual.getRight().accept(this, context);
        return left.notEqualTo(right);
    }

    @Override
    public Value visit(Subtraction subtraction, String context) {
        Value left = subtraction.getLeft().accept(this, context);
        Value right = subtraction.getRight().accept(this, context);
        return left.subtract(right);
    }

    @Override
    //todo
    public Value visit(Negation negation, String context) {
        return null;
    }

    @Override
    public Value visit(Parameter parameter, String context) {
        return this.valueTable.getValueByID(parameter.getID());
    }

    @Override
    public Value visit(StringLiteral stringLiteral, String context) {
        return new StringValue(stringLiteral.getValue());
    }

    @Override
    public Value visit(IntegerLiteral integerLiteral, String context) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public Value visit(BooleanLiteral booleanLiteral, String context) {
        return new BooleanValue(booleanLiteral.getValue());
    }
}
