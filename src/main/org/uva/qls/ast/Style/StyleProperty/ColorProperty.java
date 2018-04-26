package org.uva.qls.ast.Style.StyleProperty;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.qls.ast.Value.ColorValue;

import java.awt.*;

public class ColorProperty extends StyleProperty {

    private final ColorValue colorValue;

    public ColorProperty(ColorValue colorValue) {
        this.colorValue = colorValue;
    }

    @Override
    public void apply(QuestionWidget widget) {
        Color color = Color.LIGHT_GRAY;
        try {
            color = Color.decode(colorValue.getValue());
        } catch (Exception ignored) {

        }
        widget.setColor(color);
    }
}
