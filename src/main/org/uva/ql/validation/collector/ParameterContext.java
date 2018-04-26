package org.uva.ql.validation.collector;

import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.visitor.ExpressionVisitor;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterContext implements StatementVisitor<Void, String>, ExpressionVisitor<Void, String> {

    private final Map<String, List<Parameter>> dependencyMapping;
    private final List<Parameter> parameters;

    public ParameterContext(Form form) {
        this.dependencyMapping = new HashMap<>();
        this.parameters = new ArrayList<>();

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    public List<Parameter> getList() {
        return parameters;
    }

    public Map<String, List<Parameter>> getDependencyMapping() {
        return dependencyMapping;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        parameters.add(parameter);

        if (dependencyMapping.containsKey(context)) {
            dependencyMapping.get(context).add(parameter);
        } else {
            assert context != null;
        }
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {
        if (!dependencyMapping.containsKey(calculatedQuestion.getId())) {
            dependencyMapping.put(calculatedQuestion.getId(), new ArrayList<>());
        }

        calculatedQuestion.getExpression().accept(this, calculatedQuestion.getId());
        return null;
    }


    @Override
    public Void visit(Conditional conditional, String context) {
        conditional.getCondition().accept(this, "Condition");
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        addition.getLeft().accept(this, context);
        addition.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        division.getLeft().accept(this, context);
        division.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        equal.getLeft().accept(this, context);
        equal.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        greaterThan.getLeft().accept(this, context);
        greaterThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        greaterThanEqualTo.getLeft().accept(this, context);
        greaterThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        lessThan.getLeft().accept(this, context);
        lessThan.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        lessThanEqualTo.getLeft().accept(this, context);
        lessThanEqualTo.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        multiplication.getLeft().accept(this, context);
        multiplication.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        notEqual.getLeft().accept(this, context);
        notEqual.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        subtraction.getLeft().accept(this, context);
        subtraction.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(Or or, String context) {
        or.getLeft().accept(this, context);
        or.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(And and, String context) {
        and.getLeft().accept(this, context);
        and.getRight().accept(this, context);
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral, String Context) {
        return null;
    }

    @Override
    public Void visit(Negation negation, String Context) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(Question question, String context) {
        return null;
    }
}
