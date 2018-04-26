package org.uva.ql.evaluator.value;

public class IntegerValue implements Value {

    private final int value;

    public IntegerValue(int input) {
        this.value = input;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public Value add(IntegerValue value) {
        return new IntegerValue(this.value + value.getValue());
    }

    @Override
    public Value add(Value value) {
        return value.add(this);
    }

    @Override
    public Value subtract(IntegerValue value) {
        return new IntegerValue(this.value - value.getValue());
    }

    @Override
    public Value subtract(Value value) {
        return value.subtract(this);
    }

    @Override
    public Value divide(IntegerValue value) {

        if (value.getValue() == 0 || this.value == 0) {
            return new IntegerValue(0);
        }

        return new IntegerValue(this.value / value.getValue());
    }

    @Override
    public Value divide(Value value) {
        return value.divide(this);
    }

    @Override
    public Value multiply(IntegerValue value) {
        return new IntegerValue(this.value * value.getValue());
    }

    @Override
    public Value multiply(Value value) {
        return value.multiply(this);
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
    public Value greaterThan(IntegerValue value) {
        return new BooleanValue(value.getValue() > this.value);
    }

    @Override
    public Value greaterThan(Value value) {
        return value.greaterThan(this);
    }

    @Override
    public Value greaterThanEqualTo(IntegerValue value) {
        return new BooleanValue(value.getValue() >= this.value);
    }

    @Override
    public Value greaterThanEqualTo(Value value) {
        return value.greaterThanEqualTo(this);
    }

    @Override
    public Value lessThan(IntegerValue value) {
        return new BooleanValue(value.getValue() < this.value);
    }

    @Override
    public Value lessThan(Value value) {
        return value.lessThan(this);
    }

    @Override
    public Value lessThanEqualTo(IntegerValue value) {
        return new BooleanValue(value.getValue() <= this.value);
    }

    @Override
    public Value lessThanEqualTo(Value value) {
        return value.lessThanEqualTo(this);
    }

}