package org.uva.ql.evaluator.value;

public class StringValue implements Value {

    private final String value;

    public StringValue(String input) {
        this.value = input;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Value equalTo(Value value) {
        return new BooleanValue(this.value.equals(value.getValue()));
    }

    @Override
    public Value notEqualTo(Value value) {
        return new BooleanValue(!value.getValue().equals(this.value));
    }
}