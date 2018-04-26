package org.uva.ql.validation.collector;

import org.uva.ql.ast.*;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionContext implements StatementVisitor<Void, String> {

    private final List<Question> questions = new ArrayList<>();

    public QuestionContext(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }


    @Override
    public Void visit(Question question, String context) {
        this.questions.add(question);
        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {
        for (Statement statement : conditional.getIfBlock()) {
            statement.accept(this, null);
        }

        for (Statement statement : conditional.getElseBlock()) {
            statement.accept(this, null);
        }
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion question, String context) {
        this.questions.add(question);
        return null;
    }

    public List<Question> getList() {
        return this.questions;
    }
}
