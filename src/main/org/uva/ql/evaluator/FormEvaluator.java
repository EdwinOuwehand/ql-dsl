package org.uva.ql.evaluator;

import org.json.JSONObject;
import org.uva.app.IOHandler;
import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.Expression;
import org.uva.ql.ast.type.BooleanType;
import org.uva.ql.ast.type.IntegerType;
import org.uva.ql.ast.type.MoneyType;
import org.uva.ql.ast.type.StringType;
import org.uva.ql.evaluator.data.ExpressionTable;
import org.uva.ql.evaluator.data.StatementTable;
import org.uva.ql.evaluator.data.ValueTable;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.visitor.StatementVisitor;
import org.uva.ql.visitor.TypeVisitor;

import java.util.ArrayList;
import java.util.List;

public class FormEvaluator implements StatementVisitor<Void, String>, TypeVisitor<Void, String> {

    private final ExpressionTable expressionTable;
    private final StatementTable statementTable;
    private final ValueTable valueTable;
    private final Form form;

    public FormEvaluator(ExpressionTable expressionTable, StatementTable statementTable, ValueTable valueTable, Form form) {
        this.expressionTable = expressionTable;
        this.statementTable = statementTable;
        this.valueTable = valueTable;
        this.form = form;

        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    public void saveState() {
        JSONObject jsonObject = new JSONObject();

        for (Question question : statementTable.getQuestionsAsList()) {
            jsonObject.put(question.getContent(), valueTable.getValueByID(question.getId()));
        }

        new IOHandler().writeOutput("input/" + form.getId() + ".json", jsonObject);
    }

    public List<Question> getVisibleQuestions(ExpressionEvaluator expressionEvaluator) {
        List<Question> visibleQuestions = new ArrayList<>();
        for (Question question : this.statementTable.getQuestionsAsList()) {
            if (this.questionIsVisible(question, expressionEvaluator)) {
                visibleQuestions.add(question);
            }
        }
        return visibleQuestions;
    }

    public void addOrUpdateValue(String id, Value value) {
        this.valueTable.addOrUpdateValue(id, value);
    }

    private Expression getConditionById(String id) {
        return this.statementTable.getConditionByQuestionID(id);
    }

    private boolean questionIsVisible(Question question, ExpressionEvaluator expressionEvaluator) {
        return !this.statementTable.questionIsConditional(question.getId()) || expressionEvaluator.evaluateCondition(getConditionById(question.getId()), this.valueTable);
    }

    public boolean questionIsCalculated(Question question) {
        return this.expressionTable.questionHasExpression(question.getId());
    }

    public Value getValueById(String id) {
        return this.valueTable.getValueByID(id);
    }

    public void evaluateAllExpressions(ExpressionEvaluator expressionEvaluator) {
        for (Question question : this.statementTable.getQuestionsAsList()) {
            if (this.expressionTable.questionHasExpression(question.getId())) {
                Value value = expressionEvaluator.evaluateExpression(question.getId(), this.expressionTable.getExpressionByName(question.getId()), this.valueTable);
                this.valueTable.addOrUpdateValue(question.getId(), value);
            }
        }
    }

    @Override
    public Void visit(Question question, String context) {
        this.statementTable.addQuestion(question);
        question.getType().accept(this, question.getId());
        return null;
    }

    @Override
    public Void visit(Conditional conditional, String context) {
        for (Statement statement : conditional.getIfBlock()) {
            this.statementTable.addConditional(statement.getId(), conditional.getCondition());
            statement.accept(this, context);
        }
        for (Statement statement : conditional.getElseBlock()) {
            this.statementTable.addConditional(statement.getId(), conditional.getCondition());
            statement.accept(this, context);
        }
        return null;
    }

    @Override
    public Void visit(CalculatedQuestion question, String context) {
        this.statementTable.addQuestion(question);
        this.expressionTable.addExpression(question.getId(), question.getExpression());
        question.getType().accept(this, question.getId());
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType, String context) {
        this.valueTable.addOrUpdateValue(context, new BooleanValue(false));
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        this.valueTable.addOrUpdateValue(context, new IntegerValue(0));
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        this.valueTable.addOrUpdateValue(context, new StringValue(""));
        return null;
    }

    @Override
    public Void visit(MoneyType moneyType, String context) {
        this.valueTable.addOrUpdateValue(context, new IntegerValue(0));
        return null;
    }
}
