package org.uva.qls.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.collector.StylesheetContext;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReferenceCheckerTest {

    private Form form;
    private Stylesheet correctStylesheet;
    private Stylesheet inCorrectStylesheet;

    @Before
    public void setUp() throws Exception {

        //Disable console logging for tests.
        Logger.getGlobal().setUseParentHandlers(false);

        QLSBuilder QLSBuilder = new QLSBuilder();

        this.correctStylesheet = QLSBuilder.buildAST(new IOHandler().readFile("input/test/qls/stylesheetTest.qls"));
        this.inCorrectStylesheet = QLSBuilder.buildAST(new IOHandler().readFile("input/test/qls/incorrectStyleSheetTest.qls"));

        ASTBuilder builder = new ASTBuilder();
        this.form = builder.buildAST(new IOHandler().readFile("input/default.ql"));

    }

    @Test
    public void runCheck() {
        QuestionContext questionContext = new QuestionContext(form);
        StylesheetContext stylesheetContext = new StylesheetContext();
        stylesheetContext.setStylesheet(correctStylesheet);
        ReferenceChecker referenceChecker = new ReferenceChecker(questionContext, stylesheetContext);
        ValidationResult validationResult = referenceChecker.runCheck();

        Assert.assertFalse(validationResult.hasWarnings());
        Assert.assertFalse(validationResult.hasErrors());

        stylesheetContext = new StylesheetContext();
        stylesheetContext.setStylesheet(inCorrectStylesheet);
        referenceChecker = new ReferenceChecker(questionContext, stylesheetContext);
        validationResult = referenceChecker.runCheck();

        Assert.assertTrue(validationResult.hasErrors());
        Assert.assertFalse(validationResult.hasWarnings());

        List<String> expectedErrors = Arrays.asList(
                "Questions:[houseIsForSell] are in QLS but not in QL",
                "Questions: [valueResidue] are in QL but not in QLS"
        );

        Assert.assertEquals(expectedErrors, validationResult.getErrors());

    }
}