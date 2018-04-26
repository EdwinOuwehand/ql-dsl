package org.uva.ql.validation;

import org.uva.ql.ast.Form;
import org.uva.ql.ast.Question;
import org.uva.ql.validation.checker.*;
import org.uva.ql.validation.collector.ParameterContext;
import org.uva.ql.validation.collector.QuestionContext;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class QLValidator {

    private final Form form;

    public QLValidator(Form form) {
        this.form = form;
    }

    private List<Checker> getCheckers() {
        List<Question> questions = new QuestionContext(form).getList();
        SymbolTable symbolTable = new SymbolTable(form);
        ParameterContext parameterContext = new ParameterContext(form);


        List<Checker> checkers = new ArrayList<>();

        QuestionChecker questionChecker = new QuestionChecker(questions);
        checkers.add(questionChecker);

        ParameterChecker parameterChecker = new ParameterChecker(symbolTable, parameterContext.getList());
        checkers.add(parameterChecker);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterContext.getDependencyMapping());
        checkers.add(dependencyChecker);

        TypeChecker typeChecker = new TypeChecker(form, symbolTable);
        checkers.add(typeChecker);

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
