package org.uva.ql.evaluator.data;

import org.uva.ql.ast.expression.Expression;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTable {

    private final Map<String, Expression> expressions;

    public ExpressionTable() {
        expressions = new HashMap<>();
    }

    public void addExpression(String name, Expression expression) {
        expressions.put(name, expression);
    }

    public Expression getExpressionByName(String name) {
        return this.expressions.get(name);
    }

    public boolean questionHasExpression(String name) {
        return this.expressions.containsKey(name);
    }
}
