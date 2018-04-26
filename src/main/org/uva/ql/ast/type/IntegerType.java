package org.uva.ql.ast.type;

import org.uva.ql.visitor.TypeVisitor;

import java.util.Arrays;

public class IntegerType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return Arrays.asList(MoneyType.class, IntegerType.class).contains(type.getClass());
    }

    @Override
    public String toString() {
        return "IntegerType";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
