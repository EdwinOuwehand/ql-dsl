package org.uva.qls.ast.Segment;

import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.visitor.SegmentVisitor;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet extends Segment {

    private final String id;
    private final List<Page> pages;

    public Stylesheet(String id, List<Page> pages) {
        this.id = id;
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }

    @Override
    public String getId() {
        return "Stylesheet." + id;
    }

    @Override
    public List<DefaultStyleStatement> getDefaultStyleStatements() {
        return new ArrayList<>();
    }

    @Override
    public List<DefaultWidgetStatement> getDefaultWidgetStatements() {
        return new ArrayList<>();
    }

    @Override
    public <S> S accept(SegmentVisitor<S> visitor, Segment parent) {
        return visitor.visit(this);
    }
}
