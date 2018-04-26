package org.uva.ql.validation.checker;

import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.validation.ValidationResult;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DependencyChecker extends Checker {

    private final Set<Dependency> dependencies;

    public DependencyChecker(Map<String, List<Parameter>> parameterMapping) {

        /*
         * Unwind the mapping i.e.
         * var1 : [var2, var3]
         * to
         * var 1 -> var2, var2 -> var3
         */
        this.dependencies = new HashSet<>();

        for (Map.Entry<String, List<Parameter>> entry : parameterMapping.entrySet()) {
            for (Parameter parameter : entry.getValue()) {
                dependencies.add(new Dependency(entry.getKey(), parameter.toString()));
            }
        }
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        for (Dependency relation : transitiveClosure(dependencies)) {
            if (relation.isReflexive()) {
                result.addError(String.format("Circular dependency detected at: %s", relation.getFrom()));
            }
        }

        return result;
    }

    private Set<Dependency> transitiveClosure(Set<Dependency> closure) {
        Set<Dependency> newClosure = new HashSet<>(closure);

        for (Dependency i : closure) {
            for (Dependency j : closure) {
                if (i.getTo().equals(j.getFrom())) {
                    newClosure.add(new Dependency(i.getFrom(), j.getTo()));
                }
            }
        }

        if (!closure.containsAll(newClosure)) {
            closure.addAll(transitiveClosure(newClosure));
        }

        return closure;
    }
}
