// Generated from QLS.g4 by ANTLR 4.7.1

package antlr.generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLSParser}.
 */
public interface QLSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 */
	void enterPage(QLSParser.PageContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 */
	void exitPage(QLSParser.PageContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(QLSParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(QLSParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(QLSParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(QLSParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#defaultStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStatement(QLSParser.DefaultStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStatement(QLSParser.DefaultStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#defaultWidgetStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultWidgetStatement(QLSParser.DefaultWidgetStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultWidgetStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultWidgetStatement(QLSParser.DefaultWidgetStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#defaultStyleStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStyleStatement(QLSParser.DefaultStyleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultStyleStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStyleStatement(QLSParser.DefaultStyleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidget(QLSParser.WidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidget(QLSParser.WidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code radioType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterRadioType(QLSParser.RadioTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code radioType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitRadioType(QLSParser.RadioTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkboxType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterCheckboxType(QLSParser.CheckboxTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkboxType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitCheckboxType(QLSParser.CheckboxTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropdownType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterDropdownType(QLSParser.DropdownTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropdownType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitDropdownType(QLSParser.DropdownTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sliderType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterSliderType(QLSParser.SliderTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sliderType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitSliderType(QLSParser.SliderTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterTextType(QLSParser.TextTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitTextType(QLSParser.TextTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spinboxType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterSpinboxType(QLSParser.SpinboxTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spinboxType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitSpinboxType(QLSParser.SpinboxTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(QLSParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(QLSParser.BooleanTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntegerType(QLSParser.IntegerTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntegerType(QLSParser.IntegerTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterMoneyType(QLSParser.MoneyTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitMoneyType(QLSParser.MoneyTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(QLSParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(QLSParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterStyle(QLSParser.StyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitStyle(QLSParser.StyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontSizeProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void enterFontSizeProperty(QLSParser.FontSizePropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontSizeProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void exitFontSizeProperty(QLSParser.FontSizePropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void enterFontProperty(QLSParser.FontPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void exitFontProperty(QLSParser.FontPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code widthProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void enterWidthProperty(QLSParser.WidthPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code widthProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void exitWidthProperty(QLSParser.WidthPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void enterColorProperty(QLSParser.ColorPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 */
	void exitColorProperty(QLSParser.ColorPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(QLSParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(QLSParser.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterNumberValue(QLSParser.NumberValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitNumberValue(QLSParser.NumberValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterColorValue(QLSParser.ColorValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitColorValue(QLSParser.ColorValueContext ctx);
}