package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.TreeNode;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.List;

public abstract class Segment extends TreeNode {

    public abstract String getId();

    public abstract List<DefaultStyleStatement> getDefaultStyleStatements();

    public abstract List<DefaultWidgetStatement> getDefaultWidgetStatements();

    public abstract <S> S accept(SegmentVisitor<S> visitor, Segment parent);
}
