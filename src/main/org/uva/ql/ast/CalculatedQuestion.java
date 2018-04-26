package org.uva.ql.ast;

import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.type.Type;
import org.uva.ql.visitor.StatementVisitor;

public class CalculatedQuestion extends Question {
    private final Expression expression;

    public CalculatedQuestion(String name, String content, Type type, Expression expression) {
        super(name, content, type);
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return String.format("\"%s\"\t%s:%s = %s", this.getContent(), this.getId(), this.getType(), this.expression);
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
