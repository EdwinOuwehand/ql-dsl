package org.uva.ql.visitor;

import org.uva.ql.ast.CalculatedQuestion;
import org.uva.ql.ast.Conditional;
import org.uva.ql.ast.Question;


public interface StatementVisitor<T, C> {

    T visit(Question question, C context);

    T visit(Conditional conditional, C context);

    T visit(CalculatedQuestion question, C context);
}