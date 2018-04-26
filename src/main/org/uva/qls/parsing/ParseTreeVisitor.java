package org.uva.qls.parsing;

import antlr.generated.QLSBaseVisitor;
import antlr.generated.QLSParser;
import org.uva.ql.ast.type.*;
import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Segment.*;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.*;
import org.uva.qls.ast.Value.ColorValue;
import org.uva.qls.ast.Value.NumberValue;
import org.uva.qls.ast.Value.StringValue;
import org.uva.qls.ast.Widget.Widget;
import org.uva.qls.ast.Widget.WidgetTypes.*;

import java.util.ArrayList;
import java.util.List;

public class ParseTreeVisitor extends QLSBaseVisitor {
    @Override
    public Object visitStylesheet(QLSParser.StylesheetContext ctx) {
        String styleSheetId = ctx.id.getText();
        List<Page> pages = new ArrayList<>();

        for (QLSParser.PageContext pageContext : ctx.page()) {
            pages.add((Page) visit(pageContext));
        }

        return new Stylesheet(styleSheetId, pages);
    }

    @Override
    public Object visitPage(QLSParser.PageContext ctx) {
        String pageId = ctx.ID().getText();

        List<Segment> segments = new ArrayList<>();
        for (QLSParser.SegmentContext segmentContext : ctx.segment()) {
            segments.add((Segment) visit(segmentContext));
        }

        List<DefaultStyleStatement> defaultStyleStatements = new ArrayList<>();
        List<DefaultWidgetStatement> defaultWidgetStatements = new ArrayList<>();
        for (QLSParser.DefaultStatementContext defaultStatementContext : ctx.defaultStatement()) {
            if (defaultStatementContext.defaultStyleStatement() != null) {
                defaultStyleStatements.add((DefaultStyleStatement) visit(defaultStatementContext.defaultStyleStatement()));
            } else {
                defaultWidgetStatements.add((DefaultWidgetStatement) visit(defaultStatementContext.defaultWidgetStatement()));
            }
        }

        return new Page(pageId, segments, defaultStyleStatements, defaultWidgetStatements);
    }

    @Override
    public Object visitSection(QLSParser.SectionContext ctx) {
        String sectionId = ctx.id.getText();
        sectionId = sectionId.replaceAll("^\"|\"$", "");

        List<Segment> segments = new ArrayList<>();
        for (QLSParser.SegmentContext segmentContext : ctx.segment()) {
            segments.add((Segment) visit(segmentContext));
        }

        List<DefaultStyleStatement> defaultStyleStatements = new ArrayList<>();
        List<DefaultWidgetStatement> defaultWidgetStatements = new ArrayList<>();
        for (QLSParser.DefaultStatementContext defaultStatementContext : ctx.defaultStatement()) {
            if (defaultStatementContext.defaultStyleStatement() != null) {
                defaultStyleStatements.add((DefaultStyleStatement) visit(defaultStatementContext.defaultStyleStatement()));
            } else {
                defaultWidgetStatements.add((DefaultWidgetStatement) visit(defaultStatementContext.defaultWidgetStatement()));
            }
        }

        return new Section(sectionId, segments, defaultStyleStatements, defaultWidgetStatements);
    }

    @Override
    public Object visitSegment(QLSParser.SegmentContext ctx) {
        if (ctx.section() != null) {
            return visit(ctx.section());
        }
        return visit(ctx.question());
    }

    @Override
    public Object visitDefaultWidgetStatement(QLSParser.DefaultWidgetStatementContext ctx) {
        Type type = (Type) visit(ctx.type());
        Widget widget = (Widget) visit(ctx.widget());
        return new DefaultWidgetStatement(type, widget);
    }

    @Override
    public Object visitDefaultStyleStatement(QLSParser.DefaultStyleStatementContext ctx) {
        Type type = (Type) visit(ctx.type());
        Style style = (Style) visit(ctx.style());
        return new DefaultStyleStatement(type, style);
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.id.getText();
        Style style = null;
        Widget widget = null;

        if (ctx.style() != null) {
            style = (Style) visit(ctx.style());
            widget = style.getWidget();
        } else if (ctx.widget() != null) {
            widget = (Widget) visit(ctx.widget());
        }

        return new QuestionReference(id, widget);
    }

    @Override
    public Object visitWidget(QLSParser.WidgetContext ctx) {
        return new Widget((WidgetType) visit(ctx.widgetType()));
    }

    @Override
    public Object visitRadioType(QLSParser.RadioTypeContext ctx) {
        if (ctx.yes != null && ctx.no != null)
            return new RadioType(ctx.yes.getText(), ctx.no.getText());
        return new RadioType(null, null);
    }

    @Override
    public Object visitCheckboxType(QLSParser.CheckboxTypeContext ctx) {
        if (ctx.yes != null)
            return new CheckboxType(ctx.yes.getText());
        return new CheckboxType(null);
    }

    @Override
    public Object visitDropdownType(QLSParser.DropdownTypeContext ctx) {
        if (ctx.yes != null && ctx.no != null)
            return new DropDownType(ctx.yes.getText(), ctx.no.getText());
        return new DropDownType(null, null);

    }

    @Override
    public Object visitSliderType(QLSParser.SliderTypeContext ctx) {
        return new SliderType(ctx.start.getText(), ctx.end.getText(), ctx.step.getText());
    }

    @Override
    public Object visitTextType(QLSParser.TextTypeContext ctx) {
        return new TextType();
    }

    @Override
    public Object visitSpinboxType(QLSParser.SpinboxTypeContext ctx) {
        return new SpinboxType();
    }

    @Override
    public Object visitBooleanType(QLSParser.BooleanTypeContext ctx) {
        return new BooleanType();
    }

    @Override
    public Object visitIntegerType(QLSParser.IntegerTypeContext ctx) {
        return new IntegerType();
    }

    @Override
    public Object visitMoneyType(QLSParser.MoneyTypeContext ctx) {
        return new MoneyType();
    }

    @Override
    public Object visitStringType(QLSParser.StringTypeContext ctx) {
        return new StringType();
    }

    @Override
    public Object visitStyle(QLSParser.StyleContext ctx) {
        List<StyleProperty> styleProperties = new ArrayList<>();
        for (QLSParser.StylePropertyContext stylePropertyContext : ctx.styleProperty()) {
            styleProperties.add((StyleProperty) visit(stylePropertyContext));
        }
        if (ctx.widget() != null)
            return new Style(styleProperties, (Widget) visit(ctx.widget()));
        return new Style(styleProperties, null);
    }

    @Override
    public Object visitFontSizeProperty(QLSParser.FontSizePropertyContext ctx) {
        return new FontSizeProperty(new NumberValue(ctx.NUMBER().toString()));
    }

    @Override
    public Object visitFontProperty(QLSParser.FontPropertyContext ctx) {
        return new FontProperty(new StringValue(ctx.STRING().toString()));
    }

    @Override
    public Object visitWidthProperty(QLSParser.WidthPropertyContext ctx) {
        return new WidthProperty(new NumberValue(ctx.NUMBER().toString()));
    }

    @Override
    public Object visitColorProperty(QLSParser.ColorPropertyContext ctx) {
        return new ColorProperty(new ColorValue(ctx.COLOR().toString()));
    }

    @Override
    public Object visitStringValue(QLSParser.StringValueContext ctx) {
        return new StringValue(ctx.STRING().toString());
    }

    @Override
    public Object visitNumberValue(QLSParser.NumberValueContext ctx) {
        return new NumberValue(ctx.NUMBER().toString());
    }

    @Override
    public Object visitColorValue(QLSParser.ColorValueContext ctx) {
        return new ColorValue(ctx.COLOR().toString());
    }


}
