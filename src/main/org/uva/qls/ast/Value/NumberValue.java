package org.uva.qls.ast.Value;

public class NumberValue extends Value {

    private int value = 0;

    public NumberValue(String value) {
        try {
            this.value = Integer.parseInt(value);
        } catch (NumberFormatException ignored) {

        }
    }

    public int getValue() {
        return value;
    }
}
