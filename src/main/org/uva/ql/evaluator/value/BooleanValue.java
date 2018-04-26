package org.uva.ql.evaluator.value;

public class BooleanValue implements Value {
    private final boolean value;

    public BooleanValue(boolean input) {
        this.value = input;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public Value equalTo(Value value) {
        return new BooleanValue(value.getValue().equals(this.value));
    }

    @Override
    public Value notEqualTo(Value value) {
        return new BooleanValue(!value.getValue().equals(this.value));
    }

    @Override
    public Value logicalAnd(BooleanValue value) {
        return new BooleanValue(this.value && value.getValue());
    }

    @Override
    public Value logicalAnd(Value value) {
        return value.logicalAnd(this);
    }

    @Override
    public Value logicalOr(BooleanValue value) {
        return new BooleanValue(this.value || value.getValue());
    }

    @Override
    public Value logicalOr(Value value) {
        return value.logicalOr(this);
    }

}
