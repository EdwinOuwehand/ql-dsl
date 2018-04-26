package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.app.IOHandler;
import org.uva.ql.ast.CalculatedQuestion;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.Statement;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.checker.ParameterChecker;
import org.uva.ql.validation.collector.ParameterContext;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParameterCheckerTest {

    @Before
    public void setUp() throws Exception {
        //Disable console logging for tests.
        Logger.getGlobal().setUseParentHandlers(false);
    }

    @Test
    public void runCheckTestInputCalculation() {
        String input = new IOHandler().readFile("input/test/ql/parameterCalculation.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        SymbolTable symbolTable = new SymbolTable(form);

        ParameterChecker parameterChecker = new ParameterChecker(symbolTable, new ParameterContext(form).getList());

        assertTrue(parameterChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckTestInputConditional() {
        String input = new IOHandler().readFile("input/test/ql/parameterConditional.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        SymbolTable symbolTable = new SymbolTable(form);

        ParameterChecker parameterChecker = new ParameterChecker(symbolTable, new ParameterContext(form).getList());

        assertTrue(parameterChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckEmptySymbolTable() {
        List<Statement> statements;
        statements = new ArrayList<>(Collections.singletonList(
                new CalculatedQuestion(
                        "name",
                        "content",
                        new IntegerType(),
                        new Parameter("parameter")
                )
        ));
        Form form = new Form("form", statements);

        ParameterChecker parameterChecker = new ParameterChecker(new SymbolTable(form), new ParameterContext(form).getList());

        assertTrue(parameterChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckInSymbolTable() {
        List<Statement> statements;
        statements = new ArrayList<>(Collections.singletonList(
                new CalculatedQuestion(
                        "name",
                        "content",
                        new IntegerType(),
                        new Parameter("parameter")
                )
        ));
        Form form = new Form("form", statements);

        SymbolTable symbolTable = new SymbolTable(form);
        symbolTable.add("parameter", new BooleanType());
        ParameterChecker parameterChecker = new ParameterChecker(symbolTable, new ParameterContext(form).getList());
        parameterChecker.runCheck();

        assertFalse(parameterChecker.runCheck().hasWarnings());
    }
}