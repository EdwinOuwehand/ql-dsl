package org.uva.ql.parsing;

import antlr.generated.QLLexer;
import antlr.generated.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.Statement;
import org.uva.ql.ast.expression.Expression;

public class ASTBuilder {
    public ASTBuilder() {

    }

    public Form buildAST(String input) {
        QLParser parser = getQLParser(input);
        return getForm(parser);
    }


    public QLParser getQLParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLLexer lexer = new QLLexer(charStream);
        return new QLParser(new CommonTokenStream(lexer));
    }

    public Form getForm(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.FormContext formContext = parser.form();
        return (Form) visitor.visit(formContext);
    }

    public Expression getExpression(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.ExpressionContext context = parser.expression();
        return (Expression) visitor.visit(context);
    }

    public Statement getStatement(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.StatementContext context = parser.statement();
        return (Statement) visitor.visit(context);
    }
}
