package org.uva.ql.evaluator.data;


import org.uva.ql.ast.Question;
import org.uva.ql.ast.expression.Expression;

import java.util.*;

public class StatementTable {

    private final Map<String, Question> questions;
    private final Map<String, Expression> conditions;

    public StatementTable() {
        questions = new LinkedHashMap<>();
        conditions = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questions.put(question.getId(), question);
    }

    public List<Question> getQuestionsAsList() {
        return new ArrayList<>(questions.values());
    }

    public void addConditional(String id, Expression expression) {
        conditions.put(id, expression);
    }

    public boolean questionIsConditional(String id) {
        return conditions.containsKey(id);
    }

    public Expression getConditionByQuestionID(String questionId) {
        return conditions.get(questionId);
    }

}
