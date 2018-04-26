// Generated from QLS.g4 by ANTLR 4.7.1

package antlr.generated;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPage(QLSParser.PageContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(QLSParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(QLSParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultStatement(QLSParser.DefaultStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultWidgetStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultWidgetStatement(QLSParser.DefaultWidgetStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultStyleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultStyleStatement(QLSParser.DefaultStyleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidget(QLSParser.WidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code radioType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadioType(QLSParser.RadioTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkboxType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckboxType(QLSParser.CheckboxTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropdownType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropdownType(QLSParser.DropdownTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sliderType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliderType(QLSParser.SliderTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextType(QLSParser.TextTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code spinboxType}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpinboxType(QLSParser.SpinboxTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(QLSParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerType(QLSParser.IntegerTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyType(QLSParser.MoneyTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(QLSParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyle(QLSParser.StyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fontSizeProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontSizeProperty(QLSParser.FontSizePropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fontProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontProperty(QLSParser.FontPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code widthProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidthProperty(QLSParser.WidthPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colorProperty}
	 * labeled alternative in {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorProperty(QLSParser.ColorPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(QLSParser.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberValue(QLSParser.NumberValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colorValue}
	 * labeled alternative in {@link QLSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorValue(QLSParser.ColorValueContext ctx);
}