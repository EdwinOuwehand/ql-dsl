package org.uva.qls.ast.Widget.WidgetTypes;

import org.uva.ql.ast.type.Type;
import org.uva.qls.ast.TreeNode;
import org.uva.qls.visitor.WidgetTypeVisitor;

import java.util.List;

public abstract class WidgetType extends TreeNode {

    public abstract List<Class<? extends Type>> getCompatibleTypes();

    public abstract <T, Q, V, B> T accept(WidgetTypeVisitor<T, Q, V, B> visitor, Q Question, V value, B readOnly);

}
