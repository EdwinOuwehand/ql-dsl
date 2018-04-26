package org.uva.qls.collector;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Segment.*;
import org.uva.qls.ast.Widget.WidgetTypes.RadioType;

import java.util.List;

public class StylesheetContextTest {

    private StylesheetContext context;


    @Before
    public void setUp() throws Exception {
        String input = new IOHandler().readFile("input/test/qls/stylesheetTest.qls");
        QLSBuilder QLSBuilder = new QLSBuilder();
        Stylesheet stylesheet = QLSBuilder.buildAST(input);

        Assert.assertEquals("Test started with incorrect stylesheet","Stylesheet.taxOfficeExample", stylesheet.getId());
        Assert.assertEquals("Test started with incorrect stylesheet",2, stylesheet.getPages().size());

        this.context = new StylesheetContext();
        context.setStylesheet(stylesheet);
    }

    @After
    public void tearDown() throws Exception {
        this.context = null;
    }

    @Test
    public void setStylesheet() {
        List<Page> pages = context.getPages();
        List<QuestionReference> questionReferences = context.getQuestions();
        List<Section> sections = context.getSections();

        Assert.assertEquals(pages.size(), 2);
        Assert.assertEquals(questionReferences.size(), 6);
        Assert.assertEquals(sections.size(), 4);
    }


    @Test
    public void getPage() {
        Page nullQuestion = context.getPage(null);
        Assert.assertNull(nullQuestion);

        Page EmptyId = context.getPage(new Question("", "", new MoneyType()));
        Assert.assertNull(EmptyId);

        Page testPage = context.getPage(new Question("hasBoughtHouse", "", new MoneyType()));
        Assert.assertEquals(testPage.getTitle(), "Housing");
    }

    @Test
    public void getParent() {
        Segment nullSegment = context.getParent(null);
        Assert.assertNull(nullSegment);

        Segment nonExistingId = context.getParent("");
        Assert.assertNull(nonExistingId);

        Segment testParent = context.getParent("hasBoughtHouse");
        Assert.assertEquals(testParent.getId(), "Section.Buying");
    }

    @Test
    public void getAllParents() {
        List<Segment> nullId = context.getAllParents(null);
        Assert.assertEquals(nullId.size(), 0);

        List<Segment> nonExistingId = context.getAllParents("");
        Assert.assertEquals(nonExistingId.size(), 0);

        List<Segment> testId = context.getAllParents("valueResidue");
        Assert.assertEquals(testId.size(), 3);
        Assert.assertEquals(testId.get(0).getId(), "Section.You sold a house");
        Assert.assertEquals(testId.get(1).getId(), "Section.Selling");
        Assert.assertEquals(testId.get(2).getId(), "Page.Selling");
    }

    @Test
    public void getAllDefaultWidgetStatements() {
        List<DefaultWidgetStatement> defaults = context.getAllDefaultWidgetStatements();

        Assert.assertEquals(defaults.size(), 1);
        Assert.assertEquals(defaults.get(0).getType().getClass(), BooleanType.class);
        Assert.assertEquals(defaults.get(0).getWidget().getType().getClass(), RadioType.class);
    }

    @Test
    public void getAllDefaultStyleStatements() {
        List<DefaultStyleStatement> defaults = context.getAllDefaultStyleStatements();

        Assert.assertEquals(defaults.size(), 1);
        Assert.assertEquals(defaults.get(0).getType().getClass(), MoneyType.class);
        Assert.assertEquals(defaults.get(0).getStyle().getStyleProperties().size(), 4);
    }

    @Test
    public void getQuestionReference() {
        QuestionReference nullId = context.getQuestionReference(null);
        Assert.assertNull(nullId);

        QuestionReference nonExistingId = context.getQuestionReference(new Question("", "", new MoneyType()));
        Assert.assertNull(nonExistingId);

        QuestionReference testQuestion = context.getQuestionReference(new Question("hasBoughtHouse", "", new MoneyType()));
        Assert.assertEquals(testQuestion.getId(), "hasBoughtHouse");
    }



}