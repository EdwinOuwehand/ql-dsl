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
import org.uva.ql.validation.checker.TypeChecker;
import org.uva.ql.validation.collector.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypeCheckerTest {

    @Before
    public void setUp() throws Exception {
        //Disable console logging for tests.
        Logger.getGlobal().setUseParentHandlers(false);
    }

    @Test
    public void runCheckTestInputCalculation() {
        String input = new IOHandler().readFile("input/test/ql/typeCalculation.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        SymbolTable symbolTable = new SymbolTable(form);

        TypeChecker typeChecker = new TypeChecker(form, symbolTable);

        assertTrue(typeChecker.runCheck().hasErrors());
    }

    @Test
    public void runCheckTestInputConditional() {
        String input = new IOHandler().readFile("input/test/ql/typeConditional.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        SymbolTable symbolTable = new SymbolTable(form);

        TypeChecker typeChecker = new TypeChecker(form, symbolTable);

        assertTrue(typeChecker.runCheck().hasErrors());
    }

    @Test
    public void correctTypesRunCheck() {
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
        symbolTable.add("parameter", new IntegerType());


        TypeChecker typeChecker = new TypeChecker(form, symbolTable);

        assertFalse(typeChecker.runCheck().hasWarnings());
    }

    @Test
    public void incorrectTypesRunCheck() {
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


        TypeChecker typeChecker = new TypeChecker(form, symbolTable);

        assertTrue(typeChecker.runCheck().hasErrors());
    }
}