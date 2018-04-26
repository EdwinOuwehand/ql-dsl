package org.uva.ql.ast.expression.binary;

import org.uva.ql.ast.expression.Expression;

public abstract class BinaryOperation extends Expression {

    private final Expression left;
    private final Expression right;

    BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }
}
