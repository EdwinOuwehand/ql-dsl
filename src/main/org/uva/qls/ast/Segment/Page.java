package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.List;

public class Page extends Segment {

    private final String id;
    private final List<Segment> segments;
    private final List<DefaultStyleStatement> defaultStyleStatements;
    private final List<DefaultWidgetStatement> defaultWidgetStatements;

    public Page(String id, List<Segment> segments, List<DefaultStyleStatement> defaultStyleStatements, List<DefaultWidgetStatement> defaultWidgetStatements) {
        this.id = id;
        this.segments = segments;
        this.defaultStyleStatements = defaultStyleStatements;
        this.defaultWidgetStatements = defaultWidgetStatements;
    }

    @Override
    public String getId() {
        return "Page." + id;
    }

    public String getTitle() {
        return id;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public List<DefaultStyleStatement> getDefaultStyleStatements() {
        return defaultStyleStatements;
    }

    @Override
    public List<DefaultWidgetStatement> getDefaultWidgetStatements() {
        return defaultWidgetStatements;
    }

    @Override
    public <S> S accept(SegmentVisitor<S> visitor, Segment parent) {
        return visitor.visit(this, parent);
    }
}
