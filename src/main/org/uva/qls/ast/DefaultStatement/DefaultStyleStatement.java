package org.uva.qls.ast.DefaultStatement;

import org.uva.ql.ast.type.Type;
import org.uva.qls.ast.Style.Style;

public class DefaultStyleStatement extends DefaultStatement {

    private final Type type;
    private final Style style;

    public DefaultStyleStatement(Type type, Style style) {
        this.type = type;
        this.style = style;
    }

    public Type getType() {
        return type;
    }

    public Style getStyle() {
        return style;
    }
}
