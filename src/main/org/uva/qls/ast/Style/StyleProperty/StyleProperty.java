package org.uva.qls.ast.Style.StyleProperty;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.qls.ast.TreeNode;

public abstract class StyleProperty extends TreeNode {

    public abstract void apply(QuestionWidget widget);


}
