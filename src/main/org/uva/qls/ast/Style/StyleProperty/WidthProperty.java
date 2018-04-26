package org.uva.qls.ast.Style.StyleProperty;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.qls.ast.Value.NumberValue;

public class WidthProperty extends StyleProperty {

    private final NumberValue value;

    public WidthProperty(NumberValue numberValue) {
        this.value = numberValue;
    }

    @Override
    public void apply(QuestionWidget widget) {
        widget.setWidth(value.getValue());
    }
}
