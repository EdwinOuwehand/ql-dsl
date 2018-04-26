package org.uva.ql.ast;

import org.uva.ql.ast.type.Type;
import org.uva.ql.visitor.StatementVisitor;

public class Question extends Statement {
    private final String id;
    private final String content;
    private final Type type;

    public Question(String id, String content, Type type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return String.format("\"%s\"\t%s:%s", this.content, this.id, this.type);
    }
}
