package org.uva.ql.validation.checker;

class Dependency {

    private final String from;
    private final String to;

    Dependency(String left, String right) {
        this.from = left;
        this.to = right;
    }

    boolean isReflexive() {
        return this.from.equals(this.to);
    }

    @Override
    public String toString() {
        return from + " -> " + to;
    }

    @Override
    public int hashCode() {
        return (from + to).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!Dependency.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Dependency dependency = (Dependency) object;
        return this.from.equals(dependency.getFrom()) && this.to.equals(dependency.getTo());
    }

    String getTo() {
        return to;
    }

    String getFrom() {
        return from;
    }
}
