package org.uva.qls.validation;

import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.Type;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.checker.Checker;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.qls.ast.DefaultStatement.DefaultStyleStatement;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Widget.Widget;
import org.uva.qls.collector.StylesheetContext;

import java.util.HashMap;
import java.util.Map;

class CompatibilityChecker extends Checker {

    private final StylesheetContext stylesheetContext;

    private final Map<String, Type> qlQuestionTypes = new HashMap<>();

    public CompatibilityChecker(QuestionContext questionContext, StylesheetContext stylesheetContext) {
        this.stylesheetContext = stylesheetContext;

        for (Question question : questionContext.getList()) {
            qlQuestionTypes.put(question.getId(), question.getType());
        }
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        result = checkQuestionWidgetCompatibility(result);
        result = checkDefaultWidgetTypeCompatibility(result);
        result = checkDefaultWidgetStyleCompatibility(result);

        return result;
    }

    private ValidationResult checkQuestionWidgetCompatibility(ValidationResult validationResult) {
        for (QuestionReference questionReference : this.stylesheetContext.getQuestions()) {
            Type type = qlQuestionTypes.get(questionReference.getId());
            Widget widget = questionReference.getWidget();
            if (widget != null && !widget.isCompatible(type.getClass())) {
                validationResult.addError(String.format("Widget %s is not compatible with %s", widget.toString(), type.toString()));
            }
        }
        return validationResult;
    }

    private ValidationResult checkDefaultWidgetTypeCompatibility(ValidationResult validationResult) {
        for (DefaultWidgetStatement defaultWidgetStatement : this.stylesheetContext.getAllDefaultWidgetStatements()) {
            Type type = defaultWidgetStatement.getType();
            Widget widget = defaultWidgetStatement.getWidget();
            if (!widget.isCompatible(type.getClass())) {
                validationResult.addError(String.format("Type %s is not compatible with widget %s", type.toString(), widget.toString()));
            }
        }
        return validationResult;
    }

    private ValidationResult checkDefaultWidgetStyleCompatibility(ValidationResult validationResult) {
        for (DefaultStyleStatement defaultStyleStatement : this.stylesheetContext.getAllDefaultStyleStatements()) {
            Type type = defaultStyleStatement.getType();
            Style style = defaultStyleStatement.getStyle();

            if (style.getWidget() != null && !style.getWidget().isCompatible(type.getClass())) {
                validationResult.addError(String.format("Style widget %s is not compatible with type %s", style.getWidget().toString(), type.toString()));
            }
        }
        return validationResult;
    }
}
