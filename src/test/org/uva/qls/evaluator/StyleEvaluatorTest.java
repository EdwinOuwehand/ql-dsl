package org.uva.qls.evaluator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.MoneyType;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Widget.WidgetTypes.SpinboxType;
import org.uva.qls.ast.Widget.WidgetTypes.TextType;
import org.uva.qls.ast.Widget.WidgetTypes.WidgetType;

import javax.swing.*;

public class StyleEvaluatorTest {

    private Stylesheet stylesheet;

    @Before
    public void setUp() throws Exception {
        String input = new IOHandler().readFile("input/test/qls/stylesheetTest.qls");
        QLSBuilder QLSBuilder = new QLSBuilder();
        this.stylesheet = QLSBuilder.buildAST(input);
    }

    @Test
    public void getLayout() {
        StyleEvaluator styleEvaluator = new StyleEvaluator();
        Assert.assertEquals(JPanel.class, styleEvaluator.getLayout(null).getClass());

        styleEvaluator.setStylesheet(this.stylesheet);

        styleEvaluator.setWidget(new Question("sellingPrice","", new MoneyType()), new JPanel());
        JTabbedPane tabbedPane = (JTabbedPane) styleEvaluator.getLayout(null);
        Assert.assertEquals(1, tabbedPane.getTabCount());

        styleEvaluator.setWidget(new Question("hasMainLoan","", new MoneyType()), new JPanel());
        tabbedPane = (JTabbedPane) styleEvaluator.getLayout(null);
        Assert.assertEquals(2, tabbedPane.getTabCount());
    }

    @Test
    public void getStyle() {
        StyleEvaluator styleEvaluator = new StyleEvaluator();
        Style defaultStyle = styleEvaluator.getStyle(new Question("sellingPrice","", new MoneyType()));
        Assert.assertEquals(defaultStyle.getStyleProperties().size(), 4);

        styleEvaluator.setStylesheet(this.stylesheet);
        Style newStyle = styleEvaluator.getStyle(new Question("sellingPrice", "", new MoneyType()));
        Assert.assertNotEquals(defaultStyle, newStyle);

    }

    @Test
    public void getWidgetType() {
        StyleEvaluator styleEvaluator = new StyleEvaluator();
        WidgetType defaultWidgetType = styleEvaluator.getWidgetType(new Question("sellingPrice","", new MoneyType()));
        Assert.assertEquals(TextType.class, defaultWidgetType.getClass());

        styleEvaluator.setStylesheet(this.stylesheet);
        WidgetType newWidgetType = styleEvaluator.getWidgetType(new Question("sellingPrice","", new MoneyType()));
        Assert.assertEquals(SpinboxType.class, newWidgetType.getClass());

    }

    @Test
    public void generateSections() {
        StyleEvaluator styleEvaluator = new StyleEvaluator();
        styleEvaluator.generateSections();
        Assert.assertEquals(0, styleEvaluator.getSections().size());

        styleEvaluator.setStylesheet(this.stylesheet);
        styleEvaluator.generateSections();
        Assert.assertEquals(6, styleEvaluator.getSections().size());

    }
}