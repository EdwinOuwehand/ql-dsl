package org.uva.qls.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.ValidationResult;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Segment.Stylesheet;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class QLSValidatorTest {

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
    public void run() {
        QLSValidator qlsValidator = new QLSValidator(form, compatibleStylesheet);

        ValidationResult validationResult = qlsValidator.run();

        Assert.assertFalse(validationResult.hasErrors());
        Assert.assertFalse(validationResult.hasWarnings());

        qlsValidator = new QLSValidator(form, incompatibleStylesheet);

        validationResult = qlsValidator.run();

        List<String> expectedErrors = Arrays.asList(
                "Questions:[houseIsForSell] are in QLS but not in QL",
                "Questions: [valueResidue] are in QL but not in QLS"
        );

        Assert.assertEquals(expectedErrors, validationResult.getErrors());
    }


}