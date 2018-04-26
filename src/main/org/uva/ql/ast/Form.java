package org.uva.ql.ast;

import java.util.List;

public class Form extends TreeNode {

    private final String id;

    private final List<Statement> statements;

    public Form(String id, List<Statement> statements) {
        this.id = id;
        this.statements = statements;
    }

    @Override
    public String toString() {
        StringBuilder form = new StringBuilder(String.format("Form: %s", this.id));
        for (Statement statement : this.statements) {
            form.append(String.format("\n\t%s", statement.toString()));
        }
        return form.toString();
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public String getId() {
        return this.id;
    }
}
