package org.uva.gui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.gui.widgets.*;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.evaluator.StyleEvaluator;

import java.awt.*;

public class WidgetFactoryTest {

    private StyleEvaluator styleEvaluator;

    @Before
    public void setUp() throws Exception {
        String input = new IOHandler().readFile("input/test/qls/widgetFactoryTest.qls");
        QLSBuilder QLSBuilder = new QLSBuilder();
        Stylesheet stylesheet = QLSBuilder.buildAST(input);

        this.styleEvaluator = new StyleEvaluator();
        this.styleEvaluator.setStylesheet(stylesheet);

    }

    @Test
    public void makeCheckboxWidget() {
        WidgetFactory widgetFactory = new WidgetFactory(new QuestionChangeListener(), this.styleEvaluator);
        Question question = new Question("hasBoughtHouse","",new BooleanType());
        QuestionWidget questionWidget = widgetFactory.makeWidget(question, new BooleanValue(true), false);

        Assert.assertEquals(CheckboxWidget.class, questionWidget.getClass());
    }

    @Test
    public void makeDropdownWidget() {
        WidgetFactory widgetFactory = new WidgetFactory(new QuestionChangeListener(), this.styleEvaluator);
        Question question = new Question("hasMainLoan","",new BooleanType());
        QuestionWidget questionWidget = widgetFactory.makeWidget(question, new BooleanValue(true), false);

        Assert.assertEquals(DropdownWidget.class, questionWidget.getClass());
    }

    @Test
    public void makeIntegerWidget() {

    }

    @Test
    public void makeRadioWidget() {
        WidgetFactory widgetFactory = new WidgetFactory(new QuestionChangeListener(), this.styleEvaluator);
        Question question = new Question("hasSoldHouse","",new BooleanType());
        QuestionWidget questionWidget = widgetFactory.makeWidget(question, new BooleanValue(true), false);

        Assert.assertEquals(RadioWidget.class, questionWidget.getClass());
    }

    @Test
    public void makeSliderWidget() {
        WidgetFactory widgetFactory = new WidgetFactory(new QuestionChangeListener(), this.styleEvaluator);
        Question question = new Question("privateDebt","",new MoneyType());
        QuestionWidget questionWidget = widgetFactory.makeWidget(question, new IntegerValue(10), false);

        Assert.assertEquals(SliderWidget.class, questionWidget.getClass());
    }

    @Test
    public void makeSpinboxWidget() {
        WidgetFactory widgetFactory = new WidgetFactory(new QuestionChangeListener(), this.styleEvaluator);
        Question question = new Question("sellingPrice","",new MoneyType());
        QuestionWidget questionWidget = widgetFactory.makeWidget(question, new IntegerValue(10), false);

        Assert.assertEquals(SpinBoxWidget.class, questionWidget.getClass());
    }

    @Test
    public void makeTextWidget() {
        WidgetFactory widgetFactory = new WidgetFactory(new QuestionChangeListener(), this.styleEvaluator);
        Question question = new Question("","",new MoneyType());
        QuestionWidget questionWidget = widgetFactory.makeWidget(question, new IntegerValue(10), false);
        Font widgetFont = questionWidget.getFont();

        Assert.assertEquals(TextWidget.class, questionWidget.getClass());
    }
}