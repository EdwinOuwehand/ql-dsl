package org.uva.ql.validation.collector;

import org.uva.ql.ast.Form;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.Type;

import java.util.HashMap;
import java.util.List;

public class SymbolTable {

    private final HashMap<String, Type> table;

    public SymbolTable(Form form) {
        List<Question> questions = new QuestionContext(form).getList();

        this.table = new HashMap<>();
        for (Question question : questions) {
            this.add(question.getId(), question.getType());
        }
    }

    public void add(String name, Type type) {
        this.table.put(name, type);
    }

    public Type getTypeByName(String name) {
        return this.table.get(name);
    }

    public boolean contains(String name) {
        return this.table.containsKey(name);
    }

    public String toString() {
        return table.toString();
    }
}
