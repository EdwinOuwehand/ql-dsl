package org.uva.ql.ast.type;

import org.uva.ql.ast.TreeNode;
import org.uva.ql.visitor.TypeVisitor;

public abstract class Type extends TreeNode {

    public abstract boolean isCompatible(Type type);

    public abstract <T, C> T accept(TypeVisitor<T, C> visitor, C context);
}
