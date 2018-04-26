package org.uva.qls.ast.Widget.WidgetTypes;


import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.StringType;
import org.uva.ql.ast.type.Type;
import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class TextType extends WidgetType {

    public TextType() {

    }

    @Override
    public List<Class<? extends Type>> getCompatibleTypes() {
        return Arrays.asList(IntegerType.class, StringType.class);
    }

    @Override
    public <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q question, V value, B readOnly) {
        return visitor.visit(this, question, value, readOnly);
    }

    @Override
    public String toString() {
        return "TextType";
    }
}
