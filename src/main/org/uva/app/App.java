package org.uva.app;

import org.uva.gui.GUIHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.QLValidator;
import org.uva.ql.validation.ValidationResult;
import org.uva.qls.QLSBuilder;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.evaluator.StyleEvaluator;
import org.uva.qls.validation.QLSValidator;

import java.util.logging.LogManager;


class App {

    private App() {
        LogManager.getLogManager().reset();

        String qlInput = new IOHandler().getUserInput("ql");
        String qlsInput = new IOHandler().getUserInput("qls");

        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(qlInput);

        QLValidator validator = new QLValidator(form);
        ValidationResult validationResult = validator.run();

        FormEvaluator formEvaluator = new FormEvaluator(new ExpressionTable(), new StatementTable(), new ValueTable(), form);
        StyleEvaluator styleEvaluator = new StyleEvaluator();


        if (!qlsInput.isEmpty()) {
            QLSBuilder QLSBuilder = new QLSBuilder();
            Stylesheet stylesheet = QLSBuilder.buildAST(qlsInput);

            QLSValidator qlsValidator = new QLSValidator(form, stylesheet);
            validationResult = validationResult.merge(qlsValidator.run());

            styleEvaluator.setStylesheet(stylesheet);
        }

        new GUIHandler(formEvaluator, styleEvaluator, validationResult);
    }

    public static void main(String[] args) {
        new App();
    }
}
