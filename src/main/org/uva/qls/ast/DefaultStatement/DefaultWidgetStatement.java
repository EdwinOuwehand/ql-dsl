package org.uva.qls.ast.DefaultStatement;

import org.uva.ql.ast.type.Type;
import org.uva.qls.ast.Widget.Widget;

public class DefaultWidgetStatement extends DefaultStatement {

    private final Type type;
    private final Widget widget;

    public DefaultWidgetStatement(Type type, Widget widget) {
        this.type = type;
        this.widget = widget;
    }

    public Type getType() {
        return type;
    }

    public Widget getWidget() {
        return widget;
    }
}
