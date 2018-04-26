package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.checker.DependencyChecker;
import org.uva.ql.validation.collector.ParameterContext;

import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DependencyCheckerTest {

    @Before
    public void setUp() throws Exception {
        //Disable console logging for tests.
        Logger.getGlobal().setUseParentHandlers(false);
    }

    @Test
    public void runCheckTestInput() {
        String input = new IOHandler().readFile("input/test/ql/circularDependency.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        DependencyChecker dependencyChecker = new DependencyChecker(new ParameterContext(form).getDependencyMapping());

        assertTrue(dependencyChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckSelfDependant() {
        Map<String, List<Parameter>> parameterMapping = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q1")));
        parameterMapping.put("Q1", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterMapping);

        assertTrue(dependencyChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckLoopDependency() {
        Map<String, List<Parameter>> parameterMapping = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q2")));
        parameterMapping.put("Q1", parameters);

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q1")));
        parameterMapping.put("Q2", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterMapping);

        assertTrue(dependencyChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckMultiLayderDependency() {
        Map<String, List<Parameter>> expressions = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q2")));
        expressions.put("Q1", parameters);

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q3")));
        expressions.put("Q2", parameters);

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q1")));
        expressions.put("Q3", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(expressions);

        assertTrue(dependencyChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckNonCircular() {
        Map<String, List<Parameter>> expressions = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q3")));
        expressions.put("Q1", parameters);

        parameters = new ArrayList<>(Collections.singletonList(new Parameter("Q3")));
        expressions.put("Q2", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(expressions);

        assertFalse(dependencyChecker.runCheck().hasErrors());
    }
}