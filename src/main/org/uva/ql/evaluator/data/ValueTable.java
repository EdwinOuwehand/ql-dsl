package org.uva.ql.evaluator.data;

import org.uva.ql.evaluator.value.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueTable {

    private final Map<String, Value> values;

    public ValueTable() {
        values = new HashMap<>();
    }

    public void addOrUpdateValue(String name, Value value) {
        values.put(name, value);
    }

    public Value getValueByID(String name) {
        if (name != null) {
            return values.get(name);
        }
        return null;
    }
}