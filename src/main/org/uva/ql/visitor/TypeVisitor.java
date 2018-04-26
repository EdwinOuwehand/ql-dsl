package org.uva.ql.visitor;

import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.ast.type.StringType;

public interface TypeVisitor<T, C> {

    T visit(BooleanType booleanType, C context);

    T visit(IntegerType integerType, C context);

    T visit(StringType stringType, C context);

    T visit(MoneyType moneyType, C context);
}
