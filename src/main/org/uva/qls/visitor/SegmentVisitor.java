package org.uva.qls.visitor;


import org.uva.qls.ast.Segment.*;

public interface SegmentVisitor<N> {

    N visit(Section section, Segment parent);

    N visit(QuestionReference questionReference, Segment parent);

    N visit(Page page, Segment parent);

    N visit(Stylesheet stylesheet);


}
