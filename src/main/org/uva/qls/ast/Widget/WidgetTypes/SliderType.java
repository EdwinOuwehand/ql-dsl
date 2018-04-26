package org.uva.qls.ast.Widget.WidgetTypes;


import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.ast.type.Type;
import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.Arrays;
import java.util.List;

public class SliderType extends WidgetType {

    private final String start;
    private final String end;
    private final String step;

    public SliderType(String start, String end, String step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public int getStart() {
        return Integer.parseInt(this.start);
    }

    public int getEnd() {
        return Integer.parseInt(this.end);
    }

    public int getStep() {
        return Integer.parseInt(this.step);
    }

    @Override
    public List<Class<? extends Type>> getCompatibleTypes() {
        return Arrays.asList(IntegerType.class, MoneyType.class);
    }

    @Override
    public <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q question, V value, B readOnly) {
        return visitor.visit(this, question, value, readOnly);
    }

    @Override
    public String toString() {
        return "SliderType";
    }
}
