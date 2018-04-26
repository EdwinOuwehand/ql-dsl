package org.uva.qls.validation;

import org.uva.ql.ast.Form;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.checker.Checker;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.collector.StylesheetContext;

import java.util.ArrayList;
import java.util.List;

public class QLSValidator {

    private final StylesheetContext stylesheetContext;
    private final QuestionContext questionContext;


    public QLSValidator(Form form, Stylesheet stylesheet) {
        this.stylesheetContext = new StylesheetContext();
        this.stylesheetContext.setStylesheet(stylesheet);
        this.questionContext = new QuestionContext(form);
    }

    private List<Checker> getCheckers() {
        List<Checker> checkers = new ArrayList<>();
        checkers.add(new ReferenceChecker(this.questionContext, this.stylesheetContext));
        checkers.add(new CompatibilityChecker(this.questionContext, this.stylesheetContext));
        return checkers;
    }

    public ValidationResult run() {
        ValidationResult result = new ValidationResult();

        for (Checker checker : getCheckers()) {
            result = result.merge(checker.runCheck());

            if (result.hasErrors()) {
                break;
            }
        }
        return result;
    }
}
