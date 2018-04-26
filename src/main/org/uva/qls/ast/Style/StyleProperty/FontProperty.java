package org.uva.qls.ast.Style.StyleProperty;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.qls.ast.Value.StringValue;

public class FontProperty extends StyleProperty {

    private final StringValue stringValue;

    public FontProperty(StringValue stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public void apply(QuestionWidget widget) {
        widget.setFont(stringValue.getValue());
    }
}
