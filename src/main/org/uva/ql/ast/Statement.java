package org.uva.ql.ast;

import org.uva.ql.visitor.StatementVisitor;

public abstract class Statement extends TreeNode {

    public abstract String getId();

    public abstract <T, C> T accept(StatementVisitor<T, C> visitor, C context);
}
