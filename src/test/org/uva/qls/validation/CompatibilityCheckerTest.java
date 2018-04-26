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


public class CompatibilityCheckerTest {

    private Form form;
    private Stylesheet compatibleStylesheet;
    private Stylesheet incompatibleStylesheet;

    @Before
    public void setUp() throws Exception {

        //Disable console logging for tests.
        Logger.getGlobal().setUseParentHandlers(false);

        QLSBuilder QLSBuilder = new QLSBuilder();

        this.compatibleStylesheet = QLSBuilder.buildAST(new IOHandler().readFile("input/test/qls/stylesheetTest.qls"));
        this.incompatibleStylesheet = QLSBuilder.buildAST(new IOHandler().readFile("input/test/qls/incorrectStyleSheetTest.qls"));

        ASTBuilder builder = new ASTBuilder();
        this.form = builder.buildAST(new IOHandler().readFile("input/default.ql"));

    }

    @Test
    public void runCheck() {
        QuestionContext questionContext = new QuestionContext(this.form);
        StylesheetContext stylesheetContext = new StylesheetContext();
        stylesheetContext.setStylesheet(this.compatibleStylesheet);

        CompatibilityChecker compatibilityChecker = new CompatibilityChecker(questionContext, stylesheetContext);
        ValidationResult validationResult = compatibilityChecker.runCheck();

        Assert.assertFalse(validationResult.hasErrors());
        Assert.assertFalse(validationResult.hasWarnings());

        stylesheetContext = new StylesheetContext();
        stylesheetContext.setStylesheet(this.incompatibleStylesheet);

        compatibilityChecker = new CompatibilityChecker(questionContext, stylesheetContext);
        validationResult = compatibilityChecker.runCheck();

        Assert.assertTrue(validationResult.hasErrors());
        Assert.assertFalse(validationResult.hasWarnings());

        List<String> expectedErrors = Arrays.asList(
                "Widget RadioType is not compatible with MoneyType",
                "Type MoneyType is not compatible with widget RadioType",
                "Style widget RadioType is not compatible with type MoneyType"
        );

        Assert.assertEquals(expectedErrors, validationResult.getErrors());


    }
}