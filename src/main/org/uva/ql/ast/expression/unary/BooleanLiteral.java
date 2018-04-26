package org.uva.ql.ast.expression.unary;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.visitor.ExpressionVisitor;

public class BooleanLiteral extends Expression {
    private final Boolean value;

    public BooleanLiteral(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
