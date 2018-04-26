package org.uva.qls;

import antlr.generated.QLSLexer;
import antlr.generated.QLSParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.parsing.ParseTreeVisitor;

public class QLSBuilder {

    public QLSBuilder() {

    }

    public Stylesheet buildAST(String input) {
        QLSParser parser = getQLSParser(input);
        return getStyleSheet(parser);
    }


    private QLSParser getQLSParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        QLSLexer lexer = new QLSLexer(charStream);
        return new QLSParser(new CommonTokenStream(lexer));
    }

    private Stylesheet getStyleSheet(QLSParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        return (Stylesheet) visitor.visit(parser.stylesheet());
    }
}
