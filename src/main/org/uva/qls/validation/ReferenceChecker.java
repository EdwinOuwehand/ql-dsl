package org.uva.qls.validation;

import org.uva.ql.ast.Question;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.checker.Checker;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.collector.StylesheetContext;

import java.util.*;
import java.util.stream.Collectors;

class ReferenceChecker extends Checker {

    private final List<String> qlQuestionIds;
    private final List<String> qlsQuestionIds;

    ReferenceChecker(QuestionContext questionContext, StylesheetContext stylesheetContext) {
        this.qlQuestionIds = questionContext.getList().stream().map(Question::getId).collect(Collectors.toList());
        this.qlsQuestionIds = stylesheetContext.getQuestions().stream().map(QuestionReference::getId).collect(Collectors.toList());


        Collections.sort(this.qlQuestionIds);
        Collections.sort(this.qlsQuestionIds);
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        Set<String> uniqueIds = new HashSet<>();
        List<String> duplicateIds = this.qlsQuestionIds.stream()
                .filter(e -> !uniqueIds.add(e))
                .collect(Collectors.toList());
        if (duplicateIds.size() > 0) {
            result.addError(String.format("Questions: %s are referenced multiple times by the QLS", duplicateIds.toString()));
        }

        List<String> qlsQuestionIdsCopy = new ArrayList<>(qlsQuestionIds);
        qlsQuestionIdsCopy.removeAll(qlQuestionIds);

        if (qlsQuestionIdsCopy.size() > 0) {
            result.addError(String.format("Questions:%s are in QLS but not in QL", qlsQuestionIdsCopy.toString()));
        }

        qlQuestionIds.removeAll(qlsQuestionIds);
        if (qlQuestionIds.size() > 0) {
            result.addError(String.format("Questions: %s are in QL but not in QLS", qlQuestionIds.toString()));
        }

        return result;
    }
}
