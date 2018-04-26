package org.uva.ql.visitor;

import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;

public interface ExpressionVisitor<T, C> {

    T visit(Addition addition, C context);

    T visit(Division division, C context);

    T visit(Equal equal, C context);

    T visit(GreaterThan greaterThan, C context);

    T visit(GreaterThanEqualTo greaterThanEqualTo, C context);

    T visit(LessThan lessThan, C context);

    T visit(LessThanEqualTo lessThanEqualTo, C context);

    T visit(Multiplication multiplication, C context);

    T visit(NotEqual notEqual, C context);

    T visit(Subtraction subtraction, C context);

    T visit(Or or, C context);

    T visit(And and, C context);

    T visit(Parameter parameter, C Context);

    T visit(StringLiteral stringLiteral, C context);

    T visit(IntegerLiteral integerLiteral, C Context);

    T visit(Negation negation, C Context);

    T visit(BooleanLiteral booleanLiteral, C context);
}
