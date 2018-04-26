package org.uva.qls.ast.Widget;

import org.uva.ql.ast.type.Type;
import org.uva.qls.ast.TreeNode;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;

public class Widget extends TreeNode {

    private final WidgetType type;

    public Widget(WidgetType type) {
        this.type = type;
    }

    public WidgetType getType() {
        return type;
    }

    public boolean isCompatible(Class<? extends Type> questionType) {
        return this.type.getCompatibleTypes().contains(questionType);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
