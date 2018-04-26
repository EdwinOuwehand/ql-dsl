package org.uva.qls.ast.Widget.WidgetTypes;

import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.Type;
import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckboxType extends WidgetType {

    private final String label;

    public CheckboxType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public List<Class<? extends Type>> getCompatibleTypes() {
        return Collections.singletonList(BooleanType.class);
    }

    @Override
    public <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q question, V value, B readOnly) {
        return visitor.visit(this, question, value, readOnly);
    }

    @Override
    public String toString() {
        return "CheckboxType";
    }
}
