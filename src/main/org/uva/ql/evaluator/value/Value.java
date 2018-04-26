package org.uva.ql.evaluator.value;

public interface Value {

    Object getValue();

    default Value add(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value add(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value subtract(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value subtract(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value divide(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value divide(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value multiply(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value multiply(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value equalTo(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value notEqualTo(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value greaterThan(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value greaterThan(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value greaterThanEqualTo(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value greaterThanEqualTo(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value lessThan(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value lessThan(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value lessThanEqualTo(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value lessThanEqualTo(IntegerValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value logicalAnd(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value logicalAnd(BooleanValue value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value logicalOr(Value value) {
        throw new UnsupportedOperationException(value.toString());
    }

    default Value logicalOr(BooleanValue value) {
        throw new UnsupportedOperationException(value.toString());
    }
}
