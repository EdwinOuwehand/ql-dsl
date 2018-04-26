package org.uva.ql.ast.type;

import org.uva.ql.visitor.TypeVisitor;

public class StringType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return StringType.class.equals(type.getClass());
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return "StringType";
    }
}
