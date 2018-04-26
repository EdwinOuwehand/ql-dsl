package org.uva.ql.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ValidationResult {

    private final Logger logger;

    private final List<String> errors;
    private final List<String> warnings;

    public ValidationResult() {
        logger = Logger.getGlobal();
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public void addWarning(String warning) {
        logger.warning(warning);
        warnings.add(warning);
    }

    public void addError(String error) {
        logger.severe(error);
        errors.add(error);
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public boolean hasWarnings() {
        return warnings.size() > 0;
    }

    @Override
    public String toString() {
        ArrayList<String> result = new ArrayList<>(errors);
        result.addAll(warnings);
        return result.toString();
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public ValidationResult merge(ValidationResult toMerge) {
        this.errors.addAll(toMerge.getErrors());
        this.warnings.addAll(toMerge.getWarnings());
        return this;
    }
}
