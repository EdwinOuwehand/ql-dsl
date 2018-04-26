package org.uva.ql.validation.checker;

import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.List;

public class ParameterChecker extends Checker {

    private final SymbolTable symbolTable;
    private final List<Parameter> parameters;

    public ParameterChecker(SymbolTable symbolTable, List<Parameter> parameters) {
        this.symbolTable = symbolTable;
        this.parameters = parameters;
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        for (Parameter parameter : parameters) {
            if (!symbolTable.contains(parameter.getID())) {
                result.addError(String.format("Referenced parameter is not declared: %s", parameter));
            }
        }

        return result;
    }
}
