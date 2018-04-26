package org.uva.qls.ast.Style.StyleProperty;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.qls.ast.Value.NumberValue;

public class FontSizeProperty extends StyleProperty {

    private final NumberValue value;

    public FontSizeProperty(NumberValue numberValue) {
        this.value = numberValue;
    }

    @Override
    public void apply(QuestionWidget widget) {
        int fontSize = value.getValue();
        widget.setFontSize(fontSize);
    }
}
